package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import br.com.jhisolution.ong.control.domain.enumeration.Regiao;

/**
 * A Uf.
 */
@Entity
@Table(name = "uf")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Uf implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 2)
    @Column(name = "nome", length = 2, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 50)
    @Column(name = "estdo", length = 50, nullable = false)
    private String estdo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "regiao", nullable = false)
    private Regiao regiao;

    @OneToOne(mappedBy = "estado")
    @JsonIgnore
    private Endereco endereco;

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

    public Uf nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstdo() {
        return estdo;
    }

    public Uf estdo(String estdo) {
        this.estdo = estdo;
        return this;
    }

    public void setEstdo(String estdo) {
        this.estdo = estdo;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public Uf regiao(Regiao regiao) {
        this.regiao = regiao;
        return this;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Uf endereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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
        Uf uf = (Uf) o;
        if (uf.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uf.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Uf{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", estdo='" + getEstdo() + "'" +
            ", regiao='" + getRegiao() + "'" +
            "}";
    }
}
