package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Candidato.
 */
@Entity
@Table(name = "candidato")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "apelido", length = 50, nullable = false)
    private String apelido;

    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private Cargo cargo;

    @OneToOne    @JoinColumn(unique = true)
    private Colaborador colaborador;

    @ManyToOne
    @JsonIgnoreProperties("candidatoes")
    private Chapa chapa;

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

    public Candidato apelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public Candidato dataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObs() {
        return obs;
    }

    public Candidato obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public Candidato cargo(Cargo cargo) {
        this.cargo = cargo;
        return this;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Candidato colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Chapa getChapa() {
        return chapa;
    }

    public Candidato chapa(Chapa chapa) {
        this.chapa = chapa;
        return this;
    }

    public void setChapa(Chapa chapa) {
        this.chapa = chapa;
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
        Candidato candidato = (Candidato) o;
        if (candidato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Candidato{" +
            "id=" + getId() +
            ", apelido='" + getApelido() + "'" +
            ", dataCadastro='" + getDataCadastro() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
