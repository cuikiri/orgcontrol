package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoAcompanhamentoAluno.
 */
@Entity
@Table(name = "tipo_acompanhamento_aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoAcompanhamentoAluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @OneToOne(mappedBy = "tipoAcompanhamentoAluno")
    @JsonIgnore
    private AcompanhamentoAluno acompanhamentoAluno;

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

    public TipoAcompanhamentoAluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public AcompanhamentoAluno getAcompanhamentoAluno() {
        return acompanhamentoAluno;
    }

    public TipoAcompanhamentoAluno acompanhamentoAluno(AcompanhamentoAluno acompanhamentoAluno) {
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
        TipoAcompanhamentoAluno tipoAcompanhamentoAluno = (TipoAcompanhamentoAluno) o;
        if (tipoAcompanhamentoAluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoAcompanhamentoAluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoAcompanhamentoAluno{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
