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
 * A RespAvalDescritiva.
 */
@Entity
@Table(name = "resp_aval_descritiva")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespAvalDescritiva implements Serializable {

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

    @OneToOne(mappedBy = "respAvalDescritiva")
    @JsonIgnore
    private ItemAvaliacao itemAvaliacao;

    @OneToOne(mappedBy = "respAvalDescritiva")
    @JsonIgnore
    private RespostaAvaliacao respostaAvaliacao;

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

    public RespAvalDescritiva data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getResposta() {
        return resposta;
    }

    public RespAvalDescritiva resposta(String resposta) {
        this.resposta = resposta;
        return this;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public ItemAvaliacao getItemAvaliacao() {
        return itemAvaliacao;
    }

    public RespAvalDescritiva itemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
        return this;
    }

    public void setItemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
    }

    public RespostaAvaliacao getRespostaAvaliacao() {
        return respostaAvaliacao;
    }

    public RespAvalDescritiva respostaAvaliacao(RespostaAvaliacao respostaAvaliacao) {
        this.respostaAvaliacao = respostaAvaliacao;
        return this;
    }

    public void setRespostaAvaliacao(RespostaAvaliacao respostaAvaliacao) {
        this.respostaAvaliacao = respostaAvaliacao;
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
        RespAvalDescritiva respAvalDescritiva = (RespAvalDescritiva) o;
        if (respAvalDescritiva.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respAvalDescritiva.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespAvalDescritiva{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", resposta='" + getResposta() + "'" +
            "}";
    }
}
