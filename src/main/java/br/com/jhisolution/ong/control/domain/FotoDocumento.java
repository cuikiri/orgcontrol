package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FotoDocumento.
 */
@Entity
@Table(name = "foto_documento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FotoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "foto", nullable = false)
    private byte[] foto;

    @Column(name = "foto_content_type", nullable = false)
    private String fotoContentType;

    @OneToOne(mappedBy = "fotoDocumento")
    @JsonIgnore
    private Documento documento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public FotoDocumento foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public FotoDocumento fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public Documento getDocumento() {
        return documento;
    }

    public FotoDocumento documento(Documento documento) {
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
        FotoDocumento fotoDocumento = (FotoDocumento) o;
        if (fotoDocumento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fotoDocumento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FotoDocumento{" +
            "id=" + getId() +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            "}";
    }
}
