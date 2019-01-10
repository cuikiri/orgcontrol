package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A Atividade.
 */
@Entity
@Table(name = "atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Atividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Size(max = 1000)
    @Column(name = "resumo", length = 1000)
    private String resumo;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoAtividade tipoAtividade;

    @OneToMany(mappedBy = "atividade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConteudoProgramatico> conteudoProgramaticos = new HashSet<>();
    @OneToMany(mappedBy = "atividade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AcompanhamentoAtividade> acompanhamentoAtividades = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "atividade_aluno",
               joinColumns = @JoinColumn(name = "atividades_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "alunos_id", referencedColumnName = "id"))
    private Set<Aluno> alunos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("atividades")
    private Diario diario;

    @ManyToOne
    @JsonIgnoreProperties("atividades")
    private Bimestre bimestre;

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

    public Atividade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public Atividade data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getResumo() {
        return resumo;
    }

    public Atividade resumo(String resumo) {
        this.resumo = resumo;
        return this;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getObs() {
        return obs;
    }

    public Atividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public Atividade tipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
        return this;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public Set<ConteudoProgramatico> getConteudoProgramaticos() {
        return conteudoProgramaticos;
    }

    public Atividade conteudoProgramaticos(Set<ConteudoProgramatico> conteudoProgramaticos) {
        this.conteudoProgramaticos = conteudoProgramaticos;
        return this;
    }

    public Atividade addConteudoProgramatico(ConteudoProgramatico conteudoProgramatico) {
        this.conteudoProgramaticos.add(conteudoProgramatico);
        conteudoProgramatico.setAtividade(this);
        return this;
    }

    public Atividade removeConteudoProgramatico(ConteudoProgramatico conteudoProgramatico) {
        this.conteudoProgramaticos.remove(conteudoProgramatico);
        conteudoProgramatico.setAtividade(null);
        return this;
    }

    public void setConteudoProgramaticos(Set<ConteudoProgramatico> conteudoProgramaticos) {
        this.conteudoProgramaticos = conteudoProgramaticos;
    }

    public Set<AcompanhamentoAtividade> getAcompanhamentoAtividades() {
        return acompanhamentoAtividades;
    }

    public Atividade acompanhamentoAtividades(Set<AcompanhamentoAtividade> acompanhamentoAtividades) {
        this.acompanhamentoAtividades = acompanhamentoAtividades;
        return this;
    }

    public Atividade addAcompanhamentoAtividade(AcompanhamentoAtividade acompanhamentoAtividade) {
        this.acompanhamentoAtividades.add(acompanhamentoAtividade);
        acompanhamentoAtividade.setAtividade(this);
        return this;
    }

    public Atividade removeAcompanhamentoAtividade(AcompanhamentoAtividade acompanhamentoAtividade) {
        this.acompanhamentoAtividades.remove(acompanhamentoAtividade);
        acompanhamentoAtividade.setAtividade(null);
        return this;
    }

    public void setAcompanhamentoAtividades(Set<AcompanhamentoAtividade> acompanhamentoAtividades) {
        this.acompanhamentoAtividades = acompanhamentoAtividades;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Atividade alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Atividade addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.getAtividades().add(this);
        return this;
    }

    public Atividade removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.getAtividades().remove(this);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Diario getDiario() {
        return diario;
    }

    public Atividade diario(Diario diario) {
        this.diario = diario;
        return this;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public Atividade bimestre(Bimestre bimestre) {
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
        Atividade atividade = (Atividade) o;
        if (atividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), atividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Atividade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data='" + getData() + "'" +
            ", resumo='" + getResumo() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
