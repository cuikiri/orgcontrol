package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoDocumento.
 */
@Entity
@Table(name = "tipo_documento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;

    @OneToOne(mappedBy = "tipoDocumento")
    @JsonIgnore
    private Documento documento;

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

    public TipoDocumento nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Documento getDocumento() {
        return documento;
    }

    public TipoDocumento documento(Documento documento) {
        this.documento = documento;
        return this;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
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
        TipoDocumento tipoDocumento = (TipoDocumento) o;
        if (tipoDocumento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoDocumento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoDocumento{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
