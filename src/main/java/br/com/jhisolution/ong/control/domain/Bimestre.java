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
 * A Bimestre.
 */
@Entity
@Table(name = "bimestre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bimestre implements Serializable {

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

    @Size(max = 50)
    @Column(name = "componente", length = 50)
    private String componente;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @NotNull
    @Column(name = "atividades_previstas", nullable = false)
    private Integer atividadesPrevistas;

    @NotNull
    @Column(name = "atividades_dadas", nullable = false)
    private Integer atividadesDadas;

    @NotNull
    @Column(name = "atividades_repostas", nullable = false)
    private Integer atividadesRepostas;

    @OneToOne    @JoinColumn(unique = true)
    private FechamentoBimestre fechamentoBimestre;

    @OneToMany(mappedBy = "bimestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Atividade> atividades = new HashSet<>();
    @OneToMany(mappedBy = "bimestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Generalidade> generalidades = new HashSet<>();
    @OneToMany(mappedBy = "bimestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ObservacaoProfessor> observacaoProfessors = new HashSet<>();
    @OneToMany(mappedBy = "bimestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ObservacaoCoordenador> observacaoCoordenadors = new HashSet<>();
    @OneToMany(mappedBy = "bimestre")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RegistroRecuperacao> registroRecuperacaos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("bimestres")
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

    public Bimestre abreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
        return this;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNome() {
        return nome;
    }

    public Bimestre nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComponente() {
        return componente;
    }

    public Bimestre componente(String componente) {
        this.componente = componente;
        return this;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public Bimestre dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Bimestre dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Integer getNumero() {
        return numero;
    }

    public Bimestre numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAtividadesPrevistas() {
        return atividadesPrevistas;
    }

    public Bimestre atividadesPrevistas(Integer atividadesPrevistas) {
        this.atividadesPrevistas = atividadesPrevistas;
        return this;
    }

    public void setAtividadesPrevistas(Integer atividadesPrevistas) {
        this.atividadesPrevistas = atividadesPrevistas;
    }

    public Integer getAtividadesDadas() {
        return atividadesDadas;
    }

    public Bimestre atividadesDadas(Integer atividadesDadas) {
        this.atividadesDadas = atividadesDadas;
        return this;
    }

    public void setAtividadesDadas(Integer atividadesDadas) {
        this.atividadesDadas = atividadesDadas;
    }

    public Integer getAtividadesRepostas() {
        return atividadesRepostas;
    }

    public Bimestre atividadesRepostas(Integer atividadesRepostas) {
        this.atividadesRepostas = atividadesRepostas;
        return this;
    }

    public void setAtividadesRepostas(Integer atividadesRepostas) {
        this.atividadesRepostas = atividadesRepostas;
    }

    public FechamentoBimestre getFechamentoBimestre() {
        return fechamentoBimestre;
    }

    public Bimestre fechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
        this.fechamentoBimestre = fechamentoBimestre;
        return this;
    }

    public void setFechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
        this.fechamentoBimestre = fechamentoBimestre;
    }

    public Set<Atividade> getAtividades() {
        return atividades;
    }

    public Bimestre atividades(Set<Atividade> atividades) {
        this.atividades = atividades;
        return this;
    }

    public Bimestre addAtividade(Atividade atividade) {
        this.atividades.add(atividade);
        atividade.setBimestre(this);
        return this;
    }

    public Bimestre removeAtividade(Atividade atividade) {
        this.atividades.remove(atividade);
        atividade.setBimestre(null);
        return this;
    }

    public void setAtividades(Set<Atividade> atividades) {
        this.atividades = atividades;
    }

    public Set<Generalidade> getGeneralidades() {
        return generalidades;
    }

    public Bimestre generalidades(Set<Generalidade> generalidades) {
        this.generalidades = generalidades;
        return this;
    }

    public Bimestre addGeneralidade(Generalidade generalidade) {
        this.generalidades.add(generalidade);
        generalidade.setBimestre(this);
        return this;
    }

    public Bimestre removeGeneralidade(Generalidade generalidade) {
        this.generalidades.remove(generalidade);
        generalidade.setBimestre(null);
        return this;
    }

    public void setGeneralidades(Set<Generalidade> generalidades) {
        this.generalidades = generalidades;
    }

    public Set<ObservacaoProfessor> getObservacaoProfessors() {
        return observacaoProfessors;
    }

    public Bimestre observacaoProfessors(Set<ObservacaoProfessor> observacaoProfessors) {
        this.observacaoProfessors = observacaoProfessors;
        return this;
    }

    public Bimestre addObservacaoProfessor(ObservacaoProfessor observacaoProfessor) {
        this.observacaoProfessors.add(observacaoProfessor);
        observacaoProfessor.setBimestre(this);
        return this;
    }

    public Bimestre removeObservacaoProfessor(ObservacaoProfessor observacaoProfessor) {
        this.observacaoProfessors.remove(observacaoProfessor);
        observacaoProfessor.setBimestre(null);
        return this;
    }

    public void setObservacaoProfessors(Set<ObservacaoProfessor> observacaoProfessors) {
        this.observacaoProfessors = observacaoProfessors;
    }

    public Set<ObservacaoCoordenador> getObservacaoCoordenadors() {
        return observacaoCoordenadors;
    }

    public Bimestre observacaoCoordenadors(Set<ObservacaoCoordenador> observacaoCoordenadors) {
        this.observacaoCoordenadors = observacaoCoordenadors;
        return this;
    }

    public Bimestre addObservacaoCoordenador(ObservacaoCoordenador observacaoCoordenador) {
        this.observacaoCoordenadors.add(observacaoCoordenador);
        observacaoCoordenador.setBimestre(this);
        return this;
    }

    public Bimestre removeObservacaoCoordenador(ObservacaoCoordenador observacaoCoordenador) {
        this.observacaoCoordenadors.remove(observacaoCoordenador);
        observacaoCoordenador.setBimestre(null);
        return this;
    }

    public void setObservacaoCoordenadors(Set<ObservacaoCoordenador> observacaoCoordenadors) {
        this.observacaoCoordenadors = observacaoCoordenadors;
    }

    public Set<RegistroRecuperacao> getRegistroRecuperacaos() {
        return registroRecuperacaos;
    }

    public Bimestre registroRecuperacaos(Set<RegistroRecuperacao> registroRecuperacaos) {
        this.registroRecuperacaos = registroRecuperacaos;
        return this;
    }

    public Bimestre addRegistroRecuperacao(RegistroRecuperacao registroRecuperacao) {
        this.registroRecuperacaos.add(registroRecuperacao);
        registroRecuperacao.setBimestre(this);
        return this;
    }

    public Bimestre removeRegistroRecuperacao(RegistroRecuperacao registroRecuperacao) {
        this.registroRecuperacaos.remove(registroRecuperacao);
        registroRecuperacao.setBimestre(null);
        return this;
    }

    public void setRegistroRecuperacaos(Set<RegistroRecuperacao> registroRecuperacaos) {
        this.registroRecuperacaos = registroRecuperacaos;
    }

    public Diario getDiario() {
        return diario;
    }

    public Bimestre diario(Diario diario) {
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
        Bimestre bimestre = (Bimestre) o;
        if (bimestre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bimestre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bimestre{" +
            "id=" + getId() +
            ", abreviatura='" + getAbreviatura() + "'" +
            ", nome='" + getNome() + "'" +
            ", componente='" + getComponente() + "'" +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataFim='" + getDataFim() + "'" +
            ", numero=" + getNumero() +
            ", atividadesPrevistas=" + getAtividadesPrevistas() +
            ", atividadesDadas=" + getAtividadesDadas() +
            ", atividadesRepostas=" + getAtividadesRepostas() +
            "}";
    }
}
