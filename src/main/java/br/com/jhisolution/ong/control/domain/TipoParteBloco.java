package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoParteBloco.
 */
@Entity
@Table(name = "tipo_parte_bloco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoParteBloco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @OneToOne(mappedBy = "tipoParteBloco")
    @JsonIgnore
    private ParteBloco parteBloco;

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

    public TipoParteBloco nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ParteBloco getParteBloco() {
        return parteBloco;
    }

    public TipoParteBloco parteBloco(ParteBloco parteBloco) {
        this.parteBloco = parteBloco;
        return this;
    }

    public void setParteBloco(ParteBloco parteBloco) {
        this.parteBloco = parteBloco;
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
        TipoParteBloco tipoParteBloco = (TipoParteBloco) o;
        if (tipoParteBloco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoParteBloco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoParteBloco{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
