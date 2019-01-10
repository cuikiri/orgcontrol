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
 * A FechamentoBimestre.
 */
@Entity
@Table(name = "fechamento_bimestre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FechamentoBimestre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "frequencia", nullable = false)
    private Integer frequencia;

    @NotNull
    @Column(name = "ausencia", nullable = false)
    private Integer ausencia;

    @NotNull
    @Column(name = "total_atividades", nullable = false)
    private Integer totalAtividades;

    @Column(name = "porcentagem_frequencia")
    private Integer porcentagemFrequencia;

    @Column(name = "porcentagem_ausequencia")
    private Integer porcentagemAusequencia;

    @OneToOne    @JoinColumn(unique = true)
    private Conceito conceito;

    @OneToMany(mappedBy = "fechamentoBimestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Avaliacao> avaliacaos = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "fechamento_bimestre_aluno",
               joinColumns = @JoinColumn(name = "fechamento_bimestres_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "alunos_id", referencedColumnName = "id"))
    private Set<Aluno> alunos = new HashSet<>();

    @OneToOne(mappedBy = "fechamentoBimestre")
    @JsonIgnore
    private Bimestre bimestre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public FechamentoBimestre frequencia(Integer frequencia) {
        this.frequencia = frequencia;
        return this;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    public Integer getAusencia() {
        return ausencia;
    }

    public FechamentoBimestre ausencia(Integer ausencia) {
        this.ausencia = ausencia;
        return this;
    }

    public void setAusencia(Integer ausencia) {
        this.ausencia = ausencia;
    }

    public Integer getTotalAtividades() {
        return totalAtividades;
    }

    public FechamentoBimestre totalAtividades(Integer totalAtividades) {
        this.totalAtividades = totalAtividades;
        return this;
    }

    public void setTotalAtividades(Integer totalAtividades) {
        this.totalAtividades = totalAtividades;
    }

    public Integer getPorcentagemFrequencia() {
        return porcentagemFrequencia;
    }

    public FechamentoBimestre porcentagemFrequencia(Integer porcentagemFrequencia) {
        this.porcentagemFrequencia = porcentagemFrequencia;
        return this;
    }

    public void setPorcentagemFrequencia(Integer porcentagemFrequencia) {
        this.porcentagemFrequencia = porcentagemFrequencia;
    }

    public Integer getPorcentagemAusequencia() {
        return porcentagemAusequencia;
    }

    public FechamentoBimestre porcentagemAusequencia(Integer porcentagemAusequencia) {
        this.porcentagemAusequencia = porcentagemAusequencia;
        return this;
    }

    public void setPorcentagemAusequencia(Integer porcentagemAusequencia) {
        this.porcentagemAusequencia = porcentagemAusequencia;
    }

    public Conceito getConceito() {
        return conceito;
    }

    public FechamentoBimestre conceito(Conceito conceito) {
        this.conceito = conceito;
        return this;
    }

    public void setConceito(Conceito conceito) {
        this.conceito = conceito;
    }

    public Set<Avaliacao> getAvaliacaos() {
        return avaliacaos;
    }

    public FechamentoBimestre avaliacaos(Set<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
        return this;
    }

    public FechamentoBimestre addAvaliacao(Avaliacao avaliacao) {
        this.avaliacaos.add(avaliacao);
        avaliacao.setFechamentoBimestre(this);
        return this;
    }

    public FechamentoBimestre removeAvaliacao(Avaliacao avaliacao) {
        this.avaliacaos.remove(avaliacao);
        avaliacao.setFechamentoBimestre(null);
        return this;
    }

    public void setAvaliacaos(Set<Avaliacao> avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public FechamentoBimestre alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public FechamentoBimestre addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.getFechamentoBimestres().add(this);
        return this;
    }

    public FechamentoBimestre removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.getFechamentoBimestres().remove(this);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public FechamentoBimestre bimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
        return this;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
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
        FechamentoBimestre fechamentoBimestre = (FechamentoBimestre) o;
        if (fechamentoBimestre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fechamentoBimestre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FechamentoBimestre{" +
            "id=" + getId() +
            ", frequencia=" + getFrequencia() +
            ", ausencia=" + getAusencia() +
            ", totalAtividades=" + getTotalAtividades() +
            ", porcentagemFrequencia=" + getPorcentagemFrequencia() +
            ", porcentagemAusequencia=" + getPorcentagemAusequencia() +
            "}";
    }
}
