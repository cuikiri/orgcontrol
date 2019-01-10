package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Vacina.
 */
@Entity
@Table(name = "vacina")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vacina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 5)
    @Column(name = "idade", length = 5)
    private String idade;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("vacinas")
    private DadosMedico dadosMedico;

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

    public Vacina nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public Vacina idade(String idade) {
        this.idade = idade;
        return this;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getObs() {
        return obs;
    }

    public Vacina obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public DadosMedico getDadosMedico() {
        return dadosMedico;
    }

    public Vacina dadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
        return this;
    }

    public void setDadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
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
        Vacina vacina = (Vacina) o;
        if (vacina.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vacina.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vacina{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", idade='" + getIdade() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
