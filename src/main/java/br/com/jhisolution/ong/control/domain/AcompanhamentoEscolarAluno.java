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
 * A AcompanhamentoEscolarAluno.
 */
@Entity
@Table(name = "acompanhamento_escolar_aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AcompanhamentoEscolarAluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToMany(mappedBy = "acompanhamentoEscolarAluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MateriaAcompanhamento> materiaAcompanhamentos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("acompanhamentoEscolarAlunos")
    private Aluno aluno;

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

    public AcompanhamentoEscolarAluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public AcompanhamentoEscolarAluno obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<MateriaAcompanhamento> getMateriaAcompanhamentos() {
        return materiaAcompanhamentos;
    }

    public AcompanhamentoEscolarAluno materiaAcompanhamentos(Set<MateriaAcompanhamento> materiaAcompanhamentos) {
        this.materiaAcompanhamentos = materiaAcompanhamentos;
        return this;
    }

    public AcompanhamentoEscolarAluno addMateriaAcompanhamento(MateriaAcompanhamento materiaAcompanhamento) {
        this.materiaAcompanhamentos.add(materiaAcompanhamento);
        materiaAcompanhamento.setAcompanhamentoEscolarAluno(this);
        return this;
    }

    public AcompanhamentoEscolarAluno removeMateriaAcompanhamento(MateriaAcompanhamento materiaAcompanhamento) {
        this.materiaAcompanhamentos.remove(materiaAcompanhamento);
        materiaAcompanhamento.setAcompanhamentoEscolarAluno(null);
        return this;
    }

    public void setMateriaAcompanhamentos(Set<MateriaAcompanhamento> materiaAcompanhamentos) {
        this.materiaAcompanhamentos = materiaAcompanhamentos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public AcompanhamentoEscolarAluno aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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
        AcompanhamentoEscolarAluno acompanhamentoEscolarAluno = (AcompanhamentoEscolarAluno) o;
        if (acompanhamentoEscolarAluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), acompanhamentoEscolarAluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcompanhamentoEscolarAluno{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
