package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ParteBloco.
 */
@Entity
@Table(name = "parte_bloco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ParteBloco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "abreviatura", length = 20, nullable = false)
    private String abreviatura;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "andar")
    private Integer andar;

    @Column(name = "numero")
    private Integer numero;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoParteBloco tipoParteBloco;

    @OneToOne(mappedBy = "parteBloco")
    @JsonIgnore
    private PeriodoAtividade periodoAtividade;

    @ManyToOne
    @JsonIgnoreProperties("parteBlocos")
    private Bloco bloco;

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

    public ParteBloco abreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
        return this;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getNome() {
        return nome;
    }

    public ParteBloco nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAndar() {
        return andar;
    }

    public ParteBloco andar(Integer andar) {
        this.andar = andar;
        return this;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public Integer getNumero() {
        return numero;
    }

    public ParteBloco numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public ParteBloco obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoParteBloco getTipoParteBloco() {
        return tipoParteBloco;
    }

    public ParteBloco tipoParteBloco(TipoParteBloco tipoParteBloco) {
        this.tipoParteBloco = tipoParteBloco;
        return this;
    }

    public void setTipoParteBloco(TipoParteBloco tipoParteBloco) {
        this.tipoParteBloco = tipoParteBloco;
    }

    public PeriodoAtividade getPeriodoAtividade() {
        return periodoAtividade;
    }

    public ParteBloco periodoAtividade(PeriodoAtividade periodoAtividade) {
        this.periodoAtividade = periodoAtividade;
        return this;
    }

    public void setPeriodoAtividade(PeriodoAtividade periodoAtividade) {
        this.periodoAtividade = periodoAtividade;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public ParteBloco bloco(Bloco bloco) {
        this.bloco = bloco;
        return this;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
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
        ParteBloco parteBloco = (ParteBloco) o;
        if (parteBloco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), parteBloco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParteBloco{" +
            "id=" + getId() +
            ", abreviatura='" + getAbreviatura() + "'" +
            ", nome='" + getNome() + "'" +
            ", andar=" + getAndar() +
            ", numero=" + getNumero() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
