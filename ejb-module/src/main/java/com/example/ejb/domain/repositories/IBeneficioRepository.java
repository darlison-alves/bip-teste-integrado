package com.example.ejb.domain.repositories;

import com.example.ejb.domain.models.Beneficio;

import java.util.List;
import java.util.Optional;

public interface IBeneficioRepository {
    Optional<Beneficio> findById(Long id);
    Beneficio save(Beneficio beneficio);
    List<Beneficio> findAll();
}
