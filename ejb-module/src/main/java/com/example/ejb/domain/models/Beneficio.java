package com.example.ejb.domain.models;

import com.example.ejb.domain.exceptions.InsufficientBalanceException;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "BENEFICIO")
public class Beneficio {

    @Id
    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal valor;

    @Version
    private Long version;

    public Beneficio(Long id, String nome, String descricao, BigDecimal valor, Long clienteId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.version = clienteId;
    }

    public Beneficio() {}

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
