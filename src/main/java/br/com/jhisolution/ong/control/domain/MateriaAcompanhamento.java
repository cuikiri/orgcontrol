package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A MateriaAcompanhamento.
 */
@Entity
@Table(name = "materia_acompanhamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MateriaAcompanhamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToMany(mappedBy = "materiaAcompanhamento")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BimestreAcompanhamento> bimestreAcompanhamentos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("materiaAcompanhamentos")
    private AcompanhamentoEscolarAluno acompanhamentoEscolarAluno;

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

    public MateriaAcompanhamento nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public MateriaAcompanhamento obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<BimestreAcompanhamento> getBimestreAcompanhamentos() {
        return bimestreAcompanhamentos;
    }

    public MateriaAcompanhamento bimestreAcompanhamentos(Set<BimestreAcompanhamento> bimestreAcompanhamentos) {
        this.bimestreAcompanhamentos = bimestreAcompanhamentos;
        return this;
    }

    public MateriaAcompanhamento addBimestreAcompanhamento(BimestreAcompanhamento bimestreAcompanhamento) {
        this.bimestreAcompanhamentos.add(bimestreAcompanhamento);
        bimestreAcompanhamento.setMateriaAcompanhamento(this);
        return this;
    }

    public MateriaAcompanhamento removeBimestreAcompanhamento(BimestreAcompanhamento bimestreAcompanhamento) {
        this.bimestreAcompanhamentos.remove(bimestreAcompanhamento);
        bimestreAcompanhamento.setMateriaAcompanhamento(null);
        return this;
    }

    public void setBimestreAcompanhamentos(Set<BimestreAcompanhamento> bimestreAcompanhamentos) {
        this.bimestreAcompanhamentos = bimestreAcompanhamentos;
    }

    public AcompanhamentoEscolarAluno getAcompanhamentoEscolarAluno() {
        return acompanhamentoEscolarAluno;
    }

    public MateriaAcompanhamento acompanhamentoEscolarAluno(AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) {
        this.acompanhamentoEscolarAluno = acompanhamentoEscolarAluno;
        return this;
    }

    public void setAcompanhamentoEscolarAluno(AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) {
        this.acompanhamentoEscolarAluno = acompanhamentoEscolarAluno;
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
        MateriaAcompanhamento materiaAcompanhamento = (MateriaAcompanhamento) o;
        if (materiaAcompanhamento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), materiaAcompanhamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MateriaAcompanhamento{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
