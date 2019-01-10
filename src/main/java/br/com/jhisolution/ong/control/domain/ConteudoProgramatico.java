package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ConteudoProgramatico.
 */
@Entity
@Table(name = "conteudo_programatico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConteudoProgramatico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotNull
    @Size(max = 1000)
    @Column(name = "descricao", length = 1000, nullable = false)
    private String descricao;

    @ManyToOne
    @JsonIgnoreProperties("conteudoProgramaticos")
    private Atividade atividade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public ConteudoProgramatico data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public ConteudoProgramatico descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public ConteudoProgramatico atividade(Atividade atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
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
        ConteudoProgramatico conteudoProgramatico = (ConteudoProgramatico) o;
        if (conteudoProgramatico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conteudoProgramatico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConteudoProgramatico{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            "}";
    }
}
