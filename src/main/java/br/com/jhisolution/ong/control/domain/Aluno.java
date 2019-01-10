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
 * A Aluno.
 */
@Entity
@Table(name = "aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 30)
    @Column(name = "apelido", length = 30)
    private String apelido;

    @Column(name = "numero_irmaos")
    private Integer numeroIrmaos;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private Pessoa pessoa;

    @OneToOne    @JoinColumn(unique = true)
    private Pessoa mae;

    @OneToOne    @JoinColumn(unique = true)
    private Pessoa pai;

    @OneToOne    @JoinColumn(unique = true)
    private DadosMedico dadosMedico;

    @OneToMany(mappedBy = "alunoIrmao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Pessoa> irmaos = new HashSet<>();
    @OneToMany(mappedBy = "aluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Locomocao> locomocaos = new HashSet<>();
    @OneToMany(mappedBy = "aluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Advertencia> advertencias = new HashSet<>();
    @OneToMany(mappedBy = "aluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AvaliacaoEconomica> avaliacaoEconomicas = new HashSet<>();
    @OneToMany(mappedBy = "aluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AcompanhamentoAluno> acompanhamentoAlunos = new HashSet<>();
    @OneToMany(mappedBy = "aluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunos = new HashSet<>();
    @OneToMany(mappedBy = "aluno")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CaracteristicasPsiquicas> caracteristicasPsiquicas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private Raca raca;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Sexo sexo;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Religiao religiao;

    @ManyToOne
    @JsonIgnoreProperties("alunos")
    private Unidade unidade;

    @ManyToOne
    @JsonIgnoreProperties("alunos")
    private Diario diario;

    @ManyToMany(mappedBy = "alunos")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Responsavel> responsavels = new HashSet<>();

    @ManyToMany(mappedBy = "alunos")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Atividade> atividades = new HashSet<>();

    @ManyToMany(mappedBy = "alunos")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FechamentoBimestre> fechamentoBimestres = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApelido() {
        return apelido;
    }

    public Aluno apelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getNumeroIrmaos() {
        return numeroIrmaos;
    }

    public Aluno numeroIrmaos(Integer numeroIrmaos) {
        this.numeroIrmaos = numeroIrmaos;
        return this;
    }

    public void setNumeroIrmaos(Integer numeroIrmaos) {
        this.numeroIrmaos = numeroIrmaos;
    }

    public String getObs() {
        return obs;
    }

    public Aluno obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Aluno pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getMae() {
        return mae;
    }

    public Aluno mae(Pessoa pessoa) {
        this.mae = pessoa;
        return this;
    }

    public void setMae(Pessoa pessoa) {
        this.mae = pessoa;
    }

    public Pessoa getPai() {
        return pai;
    }

    public Aluno pai(Pessoa pessoa) {
        this.pai = pessoa;
        return this;
    }

    public void setPai(Pessoa pessoa) {
        this.pai = pessoa;
    }

    public DadosMedico getDadosMedico() {
        return dadosMedico;
    }

    public Aluno dadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
        return this;
    }

    public void setDadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
    }

    public Set<Pessoa> getIrmaos() {
        return irmaos;
    }

    public Aluno irmaos(Set<Pessoa> pessoas) {
        this.irmaos = pessoas;
        return this;
    }

    public Aluno addIrmao(Pessoa pessoa) {
        this.irmaos.add(pessoa);
        pessoa.setAlunoIrmao(this);
        return this;
    }

    public Aluno removeIrmao(Pessoa pessoa) {
        this.irmaos.remove(pessoa);
        pessoa.setAlunoIrmao(null);
        return this;
    }

    public void setIrmaos(Set<Pessoa> pessoas) {
        this.irmaos = pessoas;
    }

    public Set<Locomocao> getLocomocaos() {
        return locomocaos;
    }

    public Aluno locomocaos(Set<Locomocao> locomocaos) {
        this.locomocaos = locomocaos;
        return this;
    }

    public Aluno addLocomocao(Locomocao locomocao) {
        this.locomocaos.add(locomocao);
        locomocao.setAluno(this);
        return this;
    }

    public Aluno removeLocomocao(Locomocao locomocao) {
        this.locomocaos.remove(locomocao);
        locomocao.setAluno(null);
        return this;
    }

    public void setLocomocaos(Set<Locomocao> locomocaos) {
        this.locomocaos = locomocaos;
    }

    public Set<Advertencia> getAdvertencias() {
        return advertencias;
    }

    public Aluno advertencias(Set<Advertencia> advertencias) {
        this.advertencias = advertencias;
        return this;
    }

    public Aluno addAdvertencia(Advertencia advertencia) {
        this.advertencias.add(advertencia);
        advertencia.setAluno(this);
        return this;
    }

    public Aluno removeAdvertencia(Advertencia advertencia) {
        this.advertencias.remove(advertencia);
        advertencia.setAluno(null);
        return this;
    }

    public void setAdvertencias(Set<Advertencia> advertencias) {
        this.advertencias = advertencias;
    }

    public Set<AvaliacaoEconomica> getAvaliacaoEconomicas() {
        return avaliacaoEconomicas;
    }

    public Aluno avaliacaoEconomicas(Set<AvaliacaoEconomica> avaliacaoEconomicas) {
        this.avaliacaoEconomicas = avaliacaoEconomicas;
        return this;
    }

    public Aluno addAvaliacaoEconomica(AvaliacaoEconomica avaliacaoEconomica) {
        this.avaliacaoEconomicas.add(avaliacaoEconomica);
        avaliacaoEconomica.setAluno(this);
        return this;
    }

    public Aluno removeAvaliacaoEconomica(AvaliacaoEconomica avaliacaoEconomica) {
        this.avaliacaoEconomicas.remove(avaliacaoEconomica);
        avaliacaoEconomica.setAluno(null);
        return this;
    }

    public void setAvaliacaoEconomicas(Set<AvaliacaoEconomica> avaliacaoEconomicas) {
        this.avaliacaoEconomicas = avaliacaoEconomicas;
    }

    public Set<AcompanhamentoAluno> getAcompanhamentoAlunos() {
        return acompanhamentoAlunos;
    }

    public Aluno acompanhamentoAlunos(Set<AcompanhamentoAluno> acompanhamentoAlunos) {
        this.acompanhamentoAlunos = acompanhamentoAlunos;
        return this;
    }

    public Aluno addAcompanhamentoAluno(AcompanhamentoAluno acompanhamentoAluno) {
        this.acompanhamentoAlunos.add(acompanhamentoAluno);
        acompanhamentoAluno.setAluno(this);
        return this;
    }

    public Aluno removeAcompanhamentoAluno(AcompanhamentoAluno acompanhamentoAluno) {
        this.acompanhamentoAlunos.remove(acompanhamentoAluno);
        acompanhamentoAluno.setAluno(null);
        return this;
    }

    public void setAcompanhamentoAlunos(Set<AcompanhamentoAluno> acompanhamentoAlunos) {
        this.acompanhamentoAlunos = acompanhamentoAlunos;
    }

    public Set<AcompanhamentoEscolarAluno> getAcompanhamentoEscolarAlunos() {
        return acompanhamentoEscolarAlunos;
    }

    public Aluno acompanhamentoEscolarAlunos(Set<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunos) {
        this.acompanhamentoEscolarAlunos = acompanhamentoEscolarAlunos;
        return this;
    }

    public Aluno addAcompanhamentoEscolarAluno(AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) {
        this.acompanhamentoEscolarAlunos.add(acompanhamentoEscolarAluno);
        acompanhamentoEscolarAluno.setAluno(this);
        return this;
    }

    public Aluno removeAcompanhamentoEscolarAluno(AcompanhamentoEscolarAluno acompanhamentoEscolarAluno) {
        this.acompanhamentoEscolarAlunos.remove(acompanhamentoEscolarAluno);
        acompanhamentoEscolarAluno.setAluno(null);
        return this;
    }

    public void setAcompanhamentoEscolarAlunos(Set<AcompanhamentoEscolarAluno> acompanhamentoEscolarAlunos) {
        this.acompanhamentoEscolarAlunos = acompanhamentoEscolarAlunos;
    }

    public Set<CaracteristicasPsiquicas> getCaracteristicasPsiquicas() {
        return caracteristicasPsiquicas;
    }

    public Aluno caracteristicasPsiquicas(Set<CaracteristicasPsiquicas> caracteristicasPsiquicas) {
        this.caracteristicasPsiquicas = caracteristicasPsiquicas;
        return this;
    }

    public Aluno addCaracteristicasPsiquicas(CaracteristicasPsiquicas caracteristicasPsiquicas) {
        this.caracteristicasPsiquicas.add(caracteristicasPsiquicas);
        caracteristicasPsiquicas.setAluno(this);
        return this;
    }

    public Aluno removeCaracteristicasPsiquicas(CaracteristicasPsiquicas caracteristicasPsiquicas) {
        this.caracteristicasPsiquicas.remove(caracteristicasPsiquicas);
        caracteristicasPsiquicas.setAluno(null);
        return this;
    }

    public void setCaracteristicasPsiquicas(Set<CaracteristicasPsiquicas> caracteristicasPsiquicas) {
        this.caracteristicasPsiquicas = caracteristicasPsiquicas;
    }

    public Raca getRaca() {
        return raca;
    }

    public Aluno raca(Raca raca) {
        this.raca = raca;
        return this;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Aluno sexo(Sexo sexo) {
        this.sexo = sexo;
        return this;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Religiao getReligiao() {
        return religiao;
    }

    public Aluno religiao(Religiao religiao) {
        this.religiao = religiao;
        return this;
    }

    public void setReligiao(Religiao religiao) {
        this.religiao = religiao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Aluno unidade(Unidade unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Diario getDiario() {
        return diario;
    }

    public Aluno diario(Diario diario) {
        this.diario = diario;
        return this;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Set<Responsavel> getResponsavels() {
        return responsavels;
    }

    public Aluno responsavels(Set<Responsavel> responsavels) {
        this.responsavels = responsavels;
        return this;
    }

    public Aluno addResponsavel(Responsavel responsavel) {
        this.responsavels.add(responsavel);
        responsavel.getAlunos().add(this);
        return this;
    }

    public Aluno removeResponsavel(Responsavel responsavel) {
        this.responsavels.remove(responsavel);
        responsavel.getAlunos().remove(this);
        return this;
    }

    public void setResponsavels(Set<Responsavel> responsavels) {
        this.responsavels = responsavels;
    }

    public Set<Atividade> getAtividades() {
        return atividades;
    }

    public Aluno atividades(Set<Atividade> atividades) {
        this.atividades = atividades;
        return this;
    }

    public Aluno addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        atividade.getAlunos().add(this);
        return this;
    }

    public Aluno removeAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
        atividade.getAlunos().remove(this);
        return this;
    }

    public void setAtividades(Set<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Set<FechamentoBimestre> getFechamentoBimestres() {
        return fechamentoBimestres;
    }

    public Aluno fechamentoBimestres(Set<FechamentoBimestre> fechamentoBimestres) {
        this.fechamentoBimestres = fechamentoBimestres;
        return this;
    }

    public Aluno addFechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
        this.fechamentoBimestres.add(fechamentoBimestre);
        fechamentoBimestre.getAlunos().add(this);
        return this;
    }

    public Aluno removeFechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
        this.fechamentoBimestres.remove(fechamentoBimestre);
        fechamentoBimestre.getAlunos().remove(this);
        return this;
    }

    public void setFechamentoBimestres(Set<FechamentoBimestre> fechamentoBimestres) {
        this.fechamentoBimestres = fechamentoBimestres;
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
        Aluno aluno = (Aluno) o;
        if (aluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aluno{" +
            "id=" + getId() +
            ", apelido='" + getApelido() + "'" +
            ", numeroIrmaos=" + getNumeroIrmaos() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
