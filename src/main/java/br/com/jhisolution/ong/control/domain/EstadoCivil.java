package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A EstadoCivil.
 */
@Entity
@Table(name = "estado_civil")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EstadoCivil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne(mappedBy = "estadoCivil")
    @JsonIgnore
    private Colaborador colaborador;

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

    public EstadoCivil nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public EstadoCivil obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public EstadoCivil colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
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
        EstadoCivil estadoCivil = (EstadoCivil) o;
        if (estadoCivil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadoCivil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadoCivil{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
