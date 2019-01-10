package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoBloco.
 */
@Entity
@Table(name = "tipo_bloco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoBloco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @OneToOne(mappedBy = "tipoBloco")
    @JsonIgnore
    private Bloco bloco;

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

    public TipoBloco nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public TipoBloco bloco(Bloco bloco) {
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
        TipoBloco tipoBloco = (TipoBloco) o;
        if (tipoBloco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoBloco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoBloco{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
