package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Materia.
 */
@Entity
@Table(name = "materia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Materia implements Serializable {

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

    @Size(max = 500)
    @Column(name = "descricao", length = 500)
    private String descricao;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToOne(mappedBy = "materia")
    @JsonIgnore
    private HorarioMateria horarioMateria;

    @OneToOne(mappedBy = "materia")
    @JsonIgnore
    private Diario diario;

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

    public Materia abreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
        return this;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNome() {
        return nome;
    }

    public Materia nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Materia descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public Materia obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public HorarioMateria getHorarioMateria() {
        return horarioMateria;
    }

    public Materia horarioMateria(HorarioMateria horarioMateria) {
        this.horarioMateria = horarioMateria;
        return this;
    }

    public void setHorarioMateria(HorarioMateria horarioMateria) {
        this.horarioMateria = horarioMateria;
    }

    public Diario getDiario() {
        return diario;
    }

    public Materia diario(Diario diario) {
        this.diario = diario;
        return this;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
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
        Materia materia = (Materia) o;
        if (materia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), materia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Materia{" +
            "id=" + getId() +
            ", abreviatura='" + getAbreviatura() + "'" +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
