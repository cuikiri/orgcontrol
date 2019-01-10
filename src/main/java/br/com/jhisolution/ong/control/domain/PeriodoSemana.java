package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PeriodoSemana.
 */
@Entity
@Table(name = "periodo_semana")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PeriodoSemana implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToMany(mappedBy = "periodoSemana")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiaSemana> diaSemanas = new HashSet<>();
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

    public PeriodoSemana nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public PeriodoSemana obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<DiaSemana> getDiaSemanas() {
        return diaSemanas;
    }

    public PeriodoSemana diaSemanas(Set<DiaSemana> diaSemanas) {
        this.diaSemanas = diaSemanas;
        return this;
    }

    public PeriodoSemana addDiaSemana(DiaSemana diaSemana) {
        this.diaSemanas.add(diaSemana);
        diaSemana.setPeriodoSemana(this);
        return this;
    }

    public PeriodoSemana removeDiaSemana(DiaSemana diaSemana) {
        this.diaSemanas.remove(diaSemana);
        diaSemana.setPeriodoSemana(null);
        return this;
    }

    public void setDiaSemanas(Set<DiaSemana> diaSemanas) {
        this.diaSemanas = diaSemanas;
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
        PeriodoSemana periodoSemana = (PeriodoSemana) o;
        if (periodoSemana.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), periodoSemana.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PeriodoSemana{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
