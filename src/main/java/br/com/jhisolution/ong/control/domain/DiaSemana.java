package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DiaSemana.
 */
@Entity
@Table(name = "dia_semana")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DiaSemana implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "abreviatura", length = 20, nullable = false)
    private String abreviatura;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne(mappedBy = "diaSemana")
    @JsonIgnore
    private HorarioMateria horarioMateria;

    @ManyToOne
    @JsonIgnoreProperties("diaSemanas")
    private PeriodoSemana periodoSemana;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public DiaSemana abreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
        return this;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNome() {
        return nome;
    }

    public DiaSemana nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public DiaSemana numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public DiaSemana obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public HorarioMateria getHorarioMateria() {
        return horarioMateria;
    }

    public DiaSemana horarioMateria(HorarioMateria horarioMateria) {
        this.horarioMateria = horarioMateria;
        return this;
    }

    public void setHorarioMateria(HorarioMateria horarioMateria) {
        this.horarioMateria = horarioMateria;
    }

    public PeriodoSemana getPeriodoSemana() {
        return periodoSemana;
    }

    public DiaSemana periodoSemana(PeriodoSemana periodoSemana) {
        this.periodoSemana = periodoSemana;
        return this;
    }

    public void setPeriodoSemana(PeriodoSemana periodoSemana) {
        this.periodoSemana = periodoSemana;
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
        DiaSemana diaSemana = (DiaSemana) o;
        if (diaSemana.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diaSemana.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiaSemana{" +
            "id=" + getId() +
            ", abreviatura='" + getAbreviatura() + "'" +
            ", nome='" + getNome() + "'" +
            ", numero=" + getNumero() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
