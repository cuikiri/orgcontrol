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
 * A RespAtivDescritiva.
 */
@Entity
@Table(name = "resp_ativ_descritiva")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespAtivDescritiva implements Serializable {

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

    @OneToOne(mappedBy = "respAtivDescritiva")
    @JsonIgnore
    private ItemAcompanhamentoAtividade itemAcompanhamentoAtividade;

    @OneToOne(mappedBy = "respAtivDescritiva")
    @JsonIgnore
    private RespostaAtividade respostaAtividade;

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

    public RespAtivDescritiva data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getResposta() {
        return resposta;
    }

    public RespAtivDescritiva resposta(String resposta) {
        this.resposta = resposta;
        return this;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public ItemAcompanhamentoAtividade getItemAcompanhamentoAtividade() {
        return itemAcompanhamentoAtividade;
    }

    public RespAtivDescritiva itemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
        return this;
    }

    public void setItemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
    }

    public RespostaAtividade getRespostaAtividade() {
        return respostaAtividade;
    }

    public RespAtivDescritiva respostaAtividade(RespostaAtividade respostaAtividade) {
        this.respostaAtividade = respostaAtividade;
        return this;
    }

    public void setRespostaAtividade(RespostaAtividade respostaAtividade) {
        this.respostaAtividade = respostaAtividade;
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
        RespAtivDescritiva respAtivDescritiva = (RespAtivDescritiva) o;
        if (respAtivDescritiva.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respAtivDescritiva.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespAtivDescritiva{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", resposta='" + getResposta() + "'" +
            "}";
    }
}
