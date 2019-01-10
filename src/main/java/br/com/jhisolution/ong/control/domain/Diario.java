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
 * A Diario.
 */
@Entity
@Table(name = "diario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Diario implements Serializable {

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

    @OneToOne    @JoinColumn(unique = true)
    private Materia materia;

    @OneToOne    @JoinColumn(unique = true)
    private ObservacaoProfessor observacaoProfessor;

    @OneToOne    @JoinColumn(unique = true)
    private ObservacaoCoordenador observacaoCoordenador;

    @OneToOne    @JoinColumn(unique = true)
    private RegistroRecuperacao registroRecuperacao;

    @OneToOne    @JoinColumn(unique = true)
    private Colaborador colaborador;

    @OneToMany(mappedBy = "diario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Generalidade> generalidades = new HashSet<>();
    @OneToMany(mappedBy = "diario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Anotacao> anotacaos = new HashSet<>();
    @OneToMany(mappedBy = "diario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> alunos = new HashSet<>();
    @OneToMany(mappedBy = "diario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Atividade> atividades = new HashSet<>();
    @OneToMany(mappedBy = "diario")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bimestre> bimestres = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("diarios")
    private Turma turma;

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

    public Diario nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public Diario obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Materia getMateria() {
        return materia;
    }

    public Diario materia(Materia materia) {
        this.materia = materia;
        return this;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public ObservacaoProfessor getObservacaoProfessor() {
        return observacaoProfessor;
    }

    public Diario observacaoProfessor(ObservacaoProfessor observacaoProfessor) {
        this.observacaoProfessor = observacaoProfessor;
        return this;
    }

    public void setObservacaoProfessor(ObservacaoProfessor observacaoProfessor) {
        this.observacaoProfessor = observacaoProfessor;
    }

    public ObservacaoCoordenador getObservacaoCoordenador() {
        return observacaoCoordenador;
    }

    public Diario observacaoCoordenador(ObservacaoCoordenador observacaoCoordenador) {
        this.observacaoCoordenador = observacaoCoordenador;
        return this;
    }

    public void setObservacaoCoordenador(ObservacaoCoordenador observacaoCoordenador) {
        this.observacaoCoordenador = observacaoCoordenador;
    }

    public RegistroRecuperacao getRegistroRecuperacao() {
        return registroRecuperacao;
    }

    public Diario registroRecuperacao(RegistroRecuperacao registroRecuperacao) {
        this.registroRecuperacao = registroRecuperacao;
        return this;
    }

    public void setRegistroRecuperacao(RegistroRecuperacao registroRecuperacao) {
        this.registroRecuperacao = registroRecuperacao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Diario colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Set<Generalidade> getGeneralidades() {
        return generalidades;
    }

    public Diario generalidades(Set<Generalidade> generalidades) {
        this.generalidades = generalidades;
        return this;
    }

    public Diario addGeneralidade(Generalidade generalidade) {
        this.generalidades.add(generalidade);
        generalidade.setDiario(this);
        return this;
    }

    public Diario removeGeneralidade(Generalidade generalidade) {
        this.generalidades.remove(generalidade);
        generalidade.setDiario(null);
        return this;
    }

    public void setGeneralidades(Set<Generalidade> generalidades) {
        this.generalidades = generalidades;
    }

    public Set<Anotacao> getAnotacaos() {
        return anotacaos;
    }

    public Diario anotacaos(Set<Anotacao> anotacaos) {
        this.anotacaos = anotacaos;
        return this;
    }

    public Diario addAnotacao(Anotacao anotacao) {
        this.anotacaos.add(anotacao);
        anotacao.setDiario(this);
        return this;
    }

    public Diario removeAnotacao(Anotacao anotacao) {
        this.anotacaos.remove(anotacao);
        anotacao.setDiario(null);
        return this;
    }

    public void setAnotacaos(Set<Anotacao> anotacaos) {
        this.anotacaos = anotacaos;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Diario alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Diario addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.setDiario(this);
        return this;
    }

    public Diario removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.setDiario(null);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Atividade> getAtividades() {
        return atividades;
    }

    public Diario atividades(Set<Atividade> atividades) {
        this.atividades = atividades;
        return this;
    }

    public Diario addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        atividade.setDiario(this);
        return this;
    }

    public Diario removeAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
        atividade.setDiario(null);
        return this;
    }

    public void setAtividades(Set<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Set<Bimestre> getBimestres() {
        return bimestres;
    }

    public Diario bimestres(Set<Bimestre> bimestres) {
        this.bimestres = bimestres;
        return this;
    }

    public Diario addBimestre(Bimestre bimestre) {
        this.bimestres.add(bimestre);
        bimestre.setDiario(this);
        return this;
    }

    public Diario removeBimestre(Bimestre bimestre) {
        this.bimestres.remove(bimestre);
        bimestre.setDiario(null);
        return this;
    }

    public void setBimestres(Set<Bimestre> bimestres) {
        this.bimestres = bimestres;
    }

    public Turma getTurma() {
        return turma;
    }

    public Diario turma(Turma turma) {
        this.turma = turma;
        return this;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
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
        Diario diario = (Diario) o;
        if (diario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Diario{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
