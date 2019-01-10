package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A FotoAcompanhamentoAluno.
 */
@Entity
@Table(name = "foto_acompanhamento_aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FotoAcompanhamentoAluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "foto", nullable = false)
    private byte[] foto;

    @Column(name = "foto_content_type", nullable = false)
    private String fotoContentType;

    @OneToOne(mappedBy = "fotoAcompanhamentoAluno")
    @JsonIgnore
    private AcompanhamentoAluno acompanhamentoAluno;

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

    public FotoAcompanhamentoAluno foto(byte[] foto) {
        this.foto = foto;
        return this;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getFotoContentType() {
        return fotoContentType;
    }

    public FotoAcompanhamentoAluno fotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
        return this;
    }

    public void setFotoContentType(String fotoContentType) {
        this.fotoContentType = fotoContentType;
    }

    public AcompanhamentoAluno getAcompanhamentoAluno() {
        return acompanhamentoAluno;
    }

    public FotoAcompanhamentoAluno acompanhamentoAluno(AcompanhamentoAluno acompanhamentoAluno) {
        this.acompanhamentoAluno = acompanhamentoAluno;
        return this;
    }

    public void setAcompanhamentoAluno(AcompanhamentoAluno acompanhamentoAluno) {
        this.acompanhamentoAluno = acompanhamentoAluno;
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
        FotoAcompanhamentoAluno fotoAcompanhamentoAluno = (FotoAcompanhamentoAluno) o;
        if (fotoAcompanhamentoAluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fotoAcompanhamentoAluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FotoAcompanhamentoAluno{" +
            "id=" + getId() +
            ", foto='" + getFoto() + "'" +
            ", fotoContentType='" + getFotoContentType() + "'" +
            "}";
    }
}
