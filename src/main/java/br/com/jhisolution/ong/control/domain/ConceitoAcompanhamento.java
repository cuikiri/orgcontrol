package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ConceitoAcompanhamento.
 */
@Entity
@Table(name = "conceito_acompanhamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConceitoAcompanhamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "nota", nullable = false)
    private Float nota;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("conceitoAcompanhamentos")
    private BimestreAcompanhamento bimestreAcompanhamento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public ConceitoAcompanhamento nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getNota() {
        return nota;
    }

    public ConceitoAcompanhamento nota(Float nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public String getObs() {
        return obs;
    }

    public ConceitoAcompanhamento obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public BimestreAcompanhamento getBimestreAcompanhamento() {
        return bimestreAcompanhamento;
    }

    public ConceitoAcompanhamento bimestreAcompanhamento(BimestreAcompanhamento bimestreAcompanhamento) {
        this.bimestreAcompanhamento = bimestreAcompanhamento;
        return this;
    }

    public void setBimestreAcompanhamento(BimestreAcompanhamento bimestreAcompanhamento) {
        this.bimestreAcompanhamento = bimestreAcompanhamento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConceitoAcompanhamento conceitoAcompanhamento = (ConceitoAcompanhamento) o;
        if (conceitoAcompanhamento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conceitoAcompanhamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConceitoAcompanhamento{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", nota=" + getNota() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
