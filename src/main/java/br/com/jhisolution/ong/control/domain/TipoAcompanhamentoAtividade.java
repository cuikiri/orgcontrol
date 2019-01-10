package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TipoAcompanhamentoAtividade.
 */
@Entity
@Table(name = "tipo_acompanhamento_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TipoAcompanhamentoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne(mappedBy = "tipoAcompanhamentoAtividade")
    @JsonIgnore
    private AcompanhamentoAtividade acompanhamentoAtividade;

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

    public TipoAcompanhamentoAtividade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public TipoAcompanhamentoAtividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public AcompanhamentoAtividade getAcompanhamentoAtividade() {
        return acompanhamentoAtividade;
    }

    public TipoAcompanhamentoAtividade acompanhamentoAtividade(AcompanhamentoAtividade acompanhamentoAtividade) {
        this.acompanhamentoAtividade = acompanhamentoAtividade;
        return this;
    }

    public void setAcompanhamentoAtividade(AcompanhamentoAtividade acompanhamentoAtividade) {
        this.acompanhamentoAtividade = acompanhamentoAtividade;
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
        TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade = (TipoAcompanhamentoAtividade) o;
        if (tipoAcompanhamentoAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoAcompanhamentoAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoAcompanhamentoAtividade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
