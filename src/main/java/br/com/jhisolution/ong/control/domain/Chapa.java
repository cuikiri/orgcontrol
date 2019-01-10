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
 * A Chapa.
 */
@Entity
@Table(name = "chapa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Chapa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "tota_votos")
    private Integer totaVotos;

    @Column(name = "vencedor")
    private Boolean vencedor;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToMany(mappedBy = "chapa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Candidato> candidatoes = new HashSet<>();
    @OneToOne(mappedBy = "chapaGanhadora")
    @JsonIgnore
    private Eleicao eleicaoGanhadora;

    @ManyToOne
    @JsonIgnoreProperties("chapas")
    private Eleicao eleicao;

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

    public Chapa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Chapa dataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getTotaVotos() {
        return totaVotos;
    }

    public Chapa totaVotos(Integer totaVotos) {
        this.totaVotos = totaVotos;
        return this;
    }

    public void setTotaVotos(Integer totaVotos) {
        this.totaVotos = totaVotos;
    }

    public Boolean isVencedor() {
        return vencedor;
    }

    public Chapa vencedor(Boolean vencedor) {
        this.vencedor = vencedor;
        return this;
    }

    public void setVencedor(Boolean vencedor) {
        this.vencedor = vencedor;
    }

    public String getObs() {
        return obs;
    }

    public Chapa obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<Candidato> getCandidatoes() {
        return candidatoes;
    }

    public Chapa candidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
        return this;
    }

    public Chapa addCandidato(Candidato candidato) {
        this.candidatoes.add(candidato);
        candidato.setChapa(this);
        return this;
    }

    public Chapa removeCandidato(Candidato candidato) {
        this.candidatoes.remove(candidato);
        candidato.setChapa(null);
        return this;
    }

    public void setCandidatoes(Set<Candidato> candidatoes) {
        this.candidatoes = candidatoes;
    }

    public Eleicao getEleicaoGanhadora() {
        return eleicaoGanhadora;
    }

    public Chapa eleicaoGanhadora(Eleicao eleicao) {
        this.eleicaoGanhadora = eleicao;
        return this;
    }

    public void setEleicaoGanhadora(Eleicao eleicao) {
        this.eleicaoGanhadora = eleicao;
    }

    public Eleicao getEleicao() {
        return eleicao;
    }

    public Chapa eleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
        return this;
    }

    public void setEleicao(Eleicao eleicao) {
        this.eleicao = eleicao;
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
        Chapa chapa = (Chapa) o;
        if (chapa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chapa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Chapa{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dataCadastro='" + getDataCadastro() + "'" +
            ", totaVotos=" + getTotaVotos() +
            ", vencedor='" + isVencedor() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
