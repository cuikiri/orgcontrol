package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Colaborador.
 */
@Entity
@Table(name = "colaborador")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Colaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @NotNull
    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(name = "salario", precision = 10, scale = 2)
    private BigDecimal salario;

    @Size(max = 50)
    @Column(name = "pai", length = 50)
    private String pai;

    @Size(max = 50)
    @Column(name = "mae", length = 50)
    private String mae;

    @Size(max = 50)
    @Column(name = "conjuge", length = 50)
    private String conjuge;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoContratacao tipoContratacao;

    @OneToOne    @JoinColumn(unique = true)
    private TipoColaborador tipoColaborador;

    @OneToOne    @JoinColumn(unique = true)
    private EstadoCivil estadoCivil;

    @OneToOne    @JoinColumn(unique = true)
    private Pessoa pessoa;

    @OneToOne    @JoinColumn(unique = true)
    private DadosMedico dadosMedico;

    @OneToMany(mappedBy = "colaborador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Ensino> ensinos = new HashSet<>();
    @OneToMany(mappedBy = "colaborador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Documento> documentos = new HashSet<>();
    @OneToMany(mappedBy = "colaborador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DependenteLegal> dependenteLegals = new HashSet<>();
    @OneToMany(mappedBy = "colaborador")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AgendaColaborador> agendaColaboradors = new HashSet<>();
    @OneToOne(mappedBy = "colaborador")
    @JsonIgnore
    private Candidato candidato;

    @OneToOne(mappedBy = "colaborador")
    @JsonIgnore
    private Diario diario;

    @ManyToOne
    @JsonIgnoreProperties("colaboradors")
    private Instituicao instituicao;

    @ManyToOne
    @JsonIgnoreProperties("colaboradors")
    private Unidade unidade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Colaborador dataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public Colaborador dataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
        return this;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Colaborador salario(BigDecimal salario) {
        this.salario = salario;
        return this;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getPai() {
        return pai;
    }

    public Colaborador pai(String pai) {
        this.pai = pai;
        return this;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public Colaborador mae(String mae) {
        this.mae = mae;
        return this;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getConjuge() {
        return conjuge;
    }

    public Colaborador conjuge(String conjuge) {
        this.conjuge = conjuge;
        return this;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public String getObs() {
        return obs;
    }

    public Colaborador obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoContratacao getTipoContratacao() {
        return tipoContratacao;
    }

    public Colaborador tipoContratacao(TipoContratacao tipoContratacao) {
        this.tipoContratacao = tipoContratacao;
        return this;
    }

    public void setTipoContratacao(TipoContratacao tipoContratacao) {
        this.tipoContratacao = tipoContratacao;
    }

    public TipoColaborador getTipoColaborador() {
        return tipoColaborador;
    }

    public Colaborador tipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
        return this;
    }

    public void setTipoColaborador(TipoColaborador tipoColaborador) {
        this.tipoColaborador = tipoColaborador;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public Colaborador estadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
        return this;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Colaborador pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public DadosMedico getDadosMedico() {
        return dadosMedico;
    }

    public Colaborador dadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
        return this;
    }

    public void setDadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
    }

    public Set<Ensino> getEnsinos() {
        return ensinos;
    }

    public Colaborador ensinos(Set<Ensino> ensinos) {
        this.ensinos = ensinos;
        return this;
    }

    public Colaborador addEnsino(Ensino ensino) {
        this.ensinos.add(ensino);
        ensino.setColaborador(this);
        return this;
    }

    public Colaborador removeEnsino(Ensino ensino) {
        this.ensinos.remove(ensino);
        ensino.setColaborador(null);
        return this;
    }

    public void setEnsinos(Set<Ensino> ensinos) {
        this.ensinos = ensinos;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public Colaborador documentos(Set<Documento> documentos) {
        this.documentos = documentos;
        return this;
    }

    public Colaborador addDocumento(Documento documento) {
        this.documentos.add(documento);
        documento.setColaborador(this);
        return this;
    }

    public Colaborador removeDocumento(Documento documento) {
        this.documentos.remove(documento);
        documento.setColaborador(null);
        return this;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<DependenteLegal> getDependenteLegals() {
        return dependenteLegals;
    }

    public Colaborador dependenteLegals(Set<DependenteLegal> dependenteLegals) {
        this.dependenteLegals = dependenteLegals;
        return this;
    }

    public Colaborador addDependenteLegal(DependenteLegal dependenteLegal) {
        this.dependenteLegals.add(dependenteLegal);
        dependenteLegal.setColaborador(this);
        return this;
    }

    public Colaborador removeDependenteLegal(DependenteLegal dependenteLegal) {
        this.dependenteLegals.remove(dependenteLegal);
        dependenteLegal.setColaborador(null);
        return this;
    }

    public void setDependenteLegals(Set<DependenteLegal> dependenteLegals) {
        this.dependenteLegals = dependenteLegals;
    }

    public Set<AgendaColaborador> getAgendaColaboradors() {
        return agendaColaboradors;
    }

    public Colaborador agendaColaboradors(Set<AgendaColaborador> agendaColaboradors) {
        this.agendaColaboradors = agendaColaboradors;
        return this;
    }

    public Colaborador addAgendaColaborador(AgendaColaborador agendaColaborador) {
        this.agendaColaboradors.add(agendaColaborador);
        agendaColaborador.setColaborador(this);
        return this;
    }

    public Colaborador removeAgendaColaborador(AgendaColaborador agendaColaborador) {
        this.agendaColaboradors.remove(agendaColaborador);
        agendaColaborador.setColaborador(null);
        return this;
    }

    public void setAgendaColaboradors(Set<AgendaColaborador> agendaColaboradors) {
        this.agendaColaboradors = agendaColaboradors;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public Colaborador candidato(Candidato candidato) {
        this.candidato = candidato;
        return this;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Diario getDiario() {
        return diario;
    }

    public Colaborador diario(Diario diario) {
        this.diario = diario;
        return this;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public Colaborador instituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
        return this;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Colaborador unidade(Unidade unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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
        Colaborador colaborador = (Colaborador) o;
        if (colaborador.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), colaborador.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Colaborador{" +
            "id=" + getId() +
            ", dataCadastro='" + getDataCadastro() + "'" +
            ", dataAdmissao='" + getDataAdmissao() + "'" +
            ", salario=" + getSalario() +
            ", pai='" + getPai() + "'" +
            ", mae='" + getMae() + "'" +
            ", conjuge='" + getConjuge() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
