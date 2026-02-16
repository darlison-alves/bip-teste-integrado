package com.example.ejb.domain.models;

import com.example.ejb.domain.exceptions.InsufficientBalanceException;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;

@Entity
@Table(name = "BENEFICIO")
public class Beneficio {

    @Id
    private Long id;
    private BigDecimal valor;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void debit(BigDecimal valor) {
        if(this.valor.compareTo(valor) < 0) {
            throw new InsufficientBalanceException();
        }

        this.valor = this.valor.subtract(valor);
    }

    public void credit(BigDecimal amount) {
        this.valor = this.valor.add(amount);
    }
}
