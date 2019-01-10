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
 * A RespostaAtividade.
 */
@Entity
@Table(name = "resposta_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespostaAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private RespAtivDescritiva respAtivDescritiva;

    @OneToOne    @JoinColumn(unique = true)
    private RespAtivOptativa respAtivOptativa;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Conceito conceito;

    @ManyToOne
    @JsonIgnoreProperties("respostaAtividades")
    private ItemAcompanhamentoAtividade itemAcompanhamentoAtividade;

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

    public RespostaAtividade data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public RespostaAtividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAtivDescritiva getRespAtivDescritiva() {
        return respAtivDescritiva;
    }

    public RespostaAtividade respAtivDescritiva(RespAtivDescritiva respAtivDescritiva) {
        this.respAtivDescritiva = respAtivDescritiva;
        return this;
    }

    public void setRespAtivDescritiva(RespAtivDescritiva respAtivDescritiva) {
        this.respAtivDescritiva = respAtivDescritiva;
    }

    public RespAtivOptativa getRespAtivOptativa() {
        return respAtivOptativa;
    }

    public RespostaAtividade respAtivOptativa(RespAtivOptativa respAtivOptativa) {
        this.respAtivOptativa = respAtivOptativa;
        return this;
    }

    public void setRespAtivOptativa(RespAtivOptativa respAtivOptativa) {
        this.respAtivOptativa = respAtivOptativa;
    }

    public Conceito getConceito() {
        return conceito;
    }

    public RespostaAtividade conceito(Conceito conceito) {
        this.conceito = conceito;
        return this;
    }

    public void setConceito(Conceito conceito) {
        this.conceito = conceito;
    }

    public ItemAcompanhamentoAtividade getItemAcompanhamentoAtividade() {
        return itemAcompanhamentoAtividade;
    }

    public RespostaAtividade itemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
        return this;
    }

    public void setItemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
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
        RespostaAtividade respostaAtividade = (RespostaAtividade) o;
        if (respostaAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respostaAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespostaAtividade{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
