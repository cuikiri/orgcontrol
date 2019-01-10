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
 * A RespostaAvaliacao.
 */
@Entity
@Table(name = "resposta_avaliacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespostaAvaliacao implements Serializable {

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
    private RespAvalDescritiva respAvalDescritiva;

    @OneToOne    @JoinColumn(unique = true)
    private RespAvalOptativa respAvalOptativa;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Conceito conceito;

    @ManyToOne
    @JsonIgnoreProperties("respostaAvaliacaos")
    private ItemAvaliacao itemAvaliacao;

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

    public RespostaAvaliacao data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public RespostaAvaliacao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAvalDescritiva getRespAvalDescritiva() {
        return respAvalDescritiva;
    }

    public RespostaAvaliacao respAvalDescritiva(RespAvalDescritiva respAvalDescritiva) {
        this.respAvalDescritiva = respAvalDescritiva;
        return this;
    }

    public void setRespAvalDescritiva(RespAvalDescritiva respAvalDescritiva) {
        this.respAvalDescritiva = respAvalDescritiva;
    }

    public RespAvalOptativa getRespAvalOptativa() {
        return respAvalOptativa;
    }

    public RespostaAvaliacao respAvalOptativa(RespAvalOptativa respAvalOptativa) {
        this.respAvalOptativa = respAvalOptativa;
        return this;
    }

    public void setRespAvalOptativa(RespAvalOptativa respAvalOptativa) {
        this.respAvalOptativa = respAvalOptativa;
    }

    public Conceito getConceito() {
        return conceito;
    }

    public RespostaAvaliacao conceito(Conceito conceito) {
        this.conceito = conceito;
        return this;
    }

    public void setConceito(Conceito conceito) {
        this.conceito = conceito;
    }

    public ItemAvaliacao getItemAvaliacao() {
        return itemAvaliacao;
    }

    public RespostaAvaliacao itemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
        return this;
    }

    public void setItemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
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
        RespostaAvaliacao respostaAvaliacao = (RespostaAvaliacao) o;
        if (respostaAvaliacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respostaAvaliacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespostaAvaliacao{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
