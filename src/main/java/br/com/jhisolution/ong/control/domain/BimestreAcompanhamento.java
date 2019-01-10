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
 * A BimestreAcompanhamento.
 */
@Entity
@Table(name = "bimestre_acompanhamento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BimestreAcompanhamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "nome", length = 30, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToMany(mappedBy = "bimestreAcompanhamento")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ConceitoAcompanhamento> conceitoAcompanhamentos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("bimestreAcompanhamentos")
    private MateriaAcompanhamento materiaAcompanhamento;

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

    public BimestreAcompanhamento nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public BimestreAcompanhamento numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public BimestreAcompanhamento obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<ConceitoAcompanhamento> getConceitoAcompanhamentos() {
        return conceitoAcompanhamentos;
    }

    public BimestreAcompanhamento conceitoAcompanhamentos(Set<ConceitoAcompanhamento> conceitoAcompanhamentos) {
        this.conceitoAcompanhamentos = conceitoAcompanhamentos;
        return this;
    }

    public BimestreAcompanhamento addConceitoAcompanhamento(ConceitoAcompanhamento conceitoAcompanhamento) {
        this.conceitoAcompanhamentos.add(conceitoAcompanhamento);
        conceitoAcompanhamento.setBimestreAcompanhamento(this);
        return this;
    }

    public BimestreAcompanhamento removeConceitoAcompanhamento(ConceitoAcompanhamento conceitoAcompanhamento) {
        this.conceitoAcompanhamentos.remove(conceitoAcompanhamento);
        conceitoAcompanhamento.setBimestreAcompanhamento(null);
        return this;
    }

    public void setConceitoAcompanhamentos(Set<ConceitoAcompanhamento> conceitoAcompanhamentos) {
        this.conceitoAcompanhamentos = conceitoAcompanhamentos;
    }

    public MateriaAcompanhamento getMateriaAcompanhamento() {
        return materiaAcompanhamento;
    }

    public BimestreAcompanhamento materiaAcompanhamento(MateriaAcompanhamento materiaAcompanhamento) {
        this.materiaAcompanhamento = materiaAcompanhamento;
        return this;
    }

    public void setMateriaAcompanhamento(MateriaAcompanhamento materiaAcompanhamento) {
        this.materiaAcompanhamento = materiaAcompanhamento;
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
        BimestreAcompanhamento bimestreAcompanhamento = (BimestreAcompanhamento) o;
        if (bimestreAcompanhamento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bimestreAcompanhamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BimestreAcompanhamento{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", numero=" + getNumero() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
