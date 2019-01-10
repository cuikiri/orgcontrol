package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ItemPlanejamentoAtividade.
 */
@Entity
@Table(name = "item_planejamento_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemPlanejamentoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nome", length = 200, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 2000)
    @Column(name = "descricao", length = 2000, nullable = false)
    private String descricao;

    @ManyToOne
    @JsonIgnoreProperties("itemPlanejamentoAtividades")
    private PlanejamentoAtividade planejamentoAtividade;

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

    public ItemPlanejamentoAtividade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public ItemPlanejamentoAtividade descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public PlanejamentoAtividade getPlanejamentoAtividade() {
        return planejamentoAtividade;
    }

    public ItemPlanejamentoAtividade planejamentoAtividade(PlanejamentoAtividade planejamentoAtividade) {
        this.planejamentoAtividade = planejamentoAtividade;
        return this;
    }

    public void setPlanejamentoAtividade(PlanejamentoAtividade planejamentoAtividade) {
        this.planejamentoAtividade = planejamentoAtividade;
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
        ItemPlanejamentoAtividade itemPlanejamentoAtividade = (ItemPlanejamentoAtividade) o;
        if (itemPlanejamentoAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemPlanejamentoAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemPlanejamentoAtividade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
