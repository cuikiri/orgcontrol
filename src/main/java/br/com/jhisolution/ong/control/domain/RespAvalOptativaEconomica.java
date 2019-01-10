package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RespAvalOptativaEconomica.
 */
@Entity
@Table(name = "resp_aval_optativa_economica")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespAvalOptativaEconomica implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "opcaoResposta")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OpcaoRespAvalOptativaEconomica> opcaoRespostas = new HashSet<>();
    @OneToOne(mappedBy = "respAvalOptativaEconomica")
    @JsonIgnore
    private ItemAvaliacaoEconomica itemAvaliacaoEconomica;

    @OneToOne(mappedBy = "respAvalOptativaEconomica")
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

    public RespAvalOptativaEconomica data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<OpcaoRespAvalOptativaEconomica> getOpcaoRespostas() {
        return opcaoRespostas;
    }

    public RespAvalOptativaEconomica opcaoRespostas(Set<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicas) {
        this.opcaoRespostas = opcaoRespAvalOptativaEconomicas;
        return this;
    }

    public RespAvalOptativaEconomica addOpcaoResposta(OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica) {
        this.opcaoRespostas.add(opcaoRespAvalOptativaEconomica);
        opcaoRespAvalOptativaEconomica.setOpcaoResposta(this);
        return this;
    }

    public RespAvalOptativaEconomica removeOpcaoResposta(OpcaoRespAvalOptativaEconomica opcaoRespAvalOptativaEconomica) {
        this.opcaoRespostas.remove(opcaoRespAvalOptativaEconomica);
        opcaoRespAvalOptativaEconomica.setOpcaoResposta(null);
        return this;
    }

    public void setOpcaoRespostas(Set<OpcaoRespAvalOptativaEconomica> opcaoRespAvalOptativaEconomicas) {
        this.opcaoRespostas = opcaoRespAvalOptativaEconomicas;
    }

    public ItemAvaliacaoEconomica getItemAvaliacaoEconomica() {
        return itemAvaliacaoEconomica;
    }

    public RespAvalOptativaEconomica itemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
        return this;
    }

    public void setItemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomica = itemAvaliacaoEconomica;
    }

    public RespostaAvaliacaoEconomica getRespostaAvaliacaoEconomica() {
        return respostaAvaliacaoEconomica;
    }

    public RespAvalOptativaEconomica respostaAvaliacaoEconomica(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) {
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
        RespAvalOptativaEconomica respAvalOptativaEconomica = (RespAvalOptativaEconomica) o;
        if (respAvalOptativaEconomica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respAvalOptativaEconomica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespAvalOptativaEconomica{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
