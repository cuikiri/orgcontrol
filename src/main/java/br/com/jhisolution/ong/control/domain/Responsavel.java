package br.com.jhisolution.ong.control.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Responsavel.
 */
@Entity
@Table(name = "responsavel")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Responsavel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private Pessoa pessoa;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "responsavel_aluno",
               joinColumns = @JoinColumn(name = "responsavels_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "alunos_id", referencedColumnName = "id"))
    private Set<Aluno> alunos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public Responsavel data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public Responsavel obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Responsavel pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Responsavel alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Responsavel addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.getResponsavels().add(this);
        return this;
    }

    public Responsavel removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.getResponsavels().remove(this);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
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
        Responsavel responsavel = (Responsavel) o;
        if (responsavel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), responsavel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Responsavel{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
