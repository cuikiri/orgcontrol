package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A RespAvalDescritivaEconomica.
 */
@Entity
@Table(name = "resp_aval_descritiva_economica")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespAvalDescritivaEconomica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotNull
    @Size(max = 1000)
    @Column(name = "resposta", length = 1000, nullable = false)
    private String resposta;

    @OneToOne(mappedBy = "respAvalDescritivaEconomica")
    @JsonIgnore
    private ItemAvaliacaoEconomica itemAvaliacaoEconomica;

    @OneToOne(mappedBy = "respAvalDescritivaEconomica")
    @JsonIgnore
    private RespostaAvaliacaoEconomica respostaAvaliacaoEconomica;

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

    public RespAvalDescritivaEconomica data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getResposta() {
        return resposta;
    }

    public RespAvalDescritivaEconomica resposta(String resposta) {
        this.resposta = resposta;
        return this;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public ItemAvaliacaoEconomica getItemAvaliacaoEconomica() {
        return itemAvaliacaoEconomica;
    }

    public RespAvalDescritivaEconomica itemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
        return this;
    }

    public void setItemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
    }

    public RespostaAvaliacaoEconomica getRespostaAvaliacaoEconomica() {
        return respostaAvaliacaoEconomica;
    }

    public RespAvalDescritivaEconomica respostaAvaliacaoEconomica(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) {
        this.respostaAvaliacaoEconomica = respostaAvaliacaoEconomica;
        return this;
    }

    public void setRespostaAvaliacaoEconomica(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) {
        this.respostaAvaliacaoEconomica = respostaAvaliacaoEconomica;
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
        RespAvalDescritivaEconomica respAvalDescritivaEconomica = (RespAvalDescritivaEconomica) o;
        if (respAvalDescritivaEconomica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respAvalDescritivaEconomica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespAvalDescritivaEconomica{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", resposta='" + getResposta() + "'" +
            "}";
    }
}
