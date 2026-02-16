package com.example.ejb;

import com.example.ejb.domain.exceptions.NotFountException;
import com.example.ejb.domain.models.Beneficio;
import com.example.ejb.domain.repositories.IBeneficioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BeneficioEjbService {

    IBeneficioRepository beneficioRepository;

    public BeneficioEjbService(IBeneficioRepository beneficioRepository) {
        this.beneficioRepository = beneficioRepository;
    }

    @Transactional
    public void transfer(Long fromId, Long toId, BigDecimal amount) {
        Beneficio from = this.findBeneficionById(fromId);
        Beneficio to   = this.findBeneficionById(toId);

        // BUG: sem validações, sem locking, pode gerar saldo negativo e lost update

        from.debit(amount);
        to.credit(amount);

        this.beneficioRepository.save(from);
        this.beneficioRepository.save(to);
    }

    private Beneficio findBeneficionById(Long id) {
        return this.beneficioRepository.findById(id).orElseThrow(() -> new NotFountException("account not found with id: " + id ));
    }
}
