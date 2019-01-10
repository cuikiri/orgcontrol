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
 * A RespostaAvaliacaoEconomica.
 */
@Entity
@Table(name = "resposta_avaliacao_economica")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespostaAvaliacaoEconomica implements Serializable {

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
    private RespAvalDescritivaEconomica respAvalDescritivaEconomica;

    @OneToOne    @JoinColumn(unique = true)
    private RespAvalOptativaEconomica respAvalOptativaEconomica;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Conceito conceito;

    @ManyToOne
    @JsonIgnoreProperties("respostaAvaliacaoEconomicas")
    private ItemAvaliacaoEconomica itemAvaliacaoEconomica;

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

    public RespostaAvaliacaoEconomica data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public RespostaAvaliacaoEconomica obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAvalDescritivaEconomica getRespAvalDescritivaEconomica() {
        return respAvalDescritivaEconomica;
    }

    public RespostaAvaliacaoEconomica respAvalDescritivaEconomica(RespAvalDescritivaEconomica respAvalDescritivaEconomica) {
        this.respAvalDescritivaEconomica = respAvalDescritivaEconomica;
        return this;
    }

    public void setRespAvalDescritivaEconomica(RespAvalDescritivaEconomica respAvalDescritivaEconomica) {
        this.respAvalDescritivaEconomica = respAvalDescritivaEconomica;
    }

    public RespAvalOptativaEconomica getRespAvalOptativaEconomica() {
        return respAvalOptativaEconomica;
    }

    public RespostaAvaliacaoEconomica respAvalOptativaEconomica(RespAvalOptativaEconomica respAvalOptativaEconomica) {
        this.respAvalOptativaEconomica = respAvalOptativaEconomica;
        return this;
    }

    public void setRespAvalOptativaEconomica(RespAvalOptativaEconomica respAvalOptativaEconomica) {
        this.respAvalOptativaEconomica = respAvalOptativaEconomica;
    }

    public Conceito getConceito() {
        return conceito;
    }

    public RespostaAvaliacaoEconomica conceito(Conceito conceito) {
        this.conceito = conceito;
        return this;
    }

    public void setConceito(Conceito conceito) {
        this.conceito = conceito;
    }

    public ItemAvaliacaoEconomica getItemAvaliacaoEconomica() {
        return itemAvaliacaoEconomica;
    }

    public RespostaAvaliacaoEconomica itemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
        return this;
    }

    public void setItemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
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
        RespostaAvaliacaoEconomica respostaAvaliacaoEconomica = (RespostaAvaliacaoEconomica) o;
        if (respostaAvaliacaoEconomica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respostaAvaliacaoEconomica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespostaAvaliacaoEconomica{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
