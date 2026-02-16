package com.example.ejb.adapter.persistence;

import com.example.ejb.domain.models.Beneficio;
import com.example.ejb.domain.repositories.IBeneficioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BeneficioRepository implements IBeneficioRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Beneficio> findById(Long id) {
        return Optional.ofNullable(this.em.find(Beneficio.class, id));
    }

    @Override
    public Beneficio save(Beneficio beneficio) {
        return this.em.merge(beneficio);
    }

    @Override
    public List<Beneficio> findAll() {
        return this.em.createQuery("SELECT id, nome, descricao, valor, version FROM Beneficio", Beneficio.class).getResultList();
    }


}
