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
 * A RespAvalOptativa.
 */
@Entity
@Table(name = "resp_aval_optativa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespAvalOptativa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "opcaoResposta")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OpcaoRespAvalOptativa> opcaoRespostas = new HashSet<>();
    @OneToOne(mappedBy = "respAvalOptativa")
    @JsonIgnore
    private ItemAvaliacao itemAvaliacao;

    @OneToOne(mappedBy = "respAvalOptativa")
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

    public RespAvalOptativa data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<OpcaoRespAvalOptativa> getOpcaoRespostas() {
        return opcaoRespostas;
    }

    public RespAvalOptativa opcaoRespostas(Set<OpcaoRespAvalOptativa> opcaoRespAvalOptativas) {
        this.opcaoRespostas = opcaoRespAvalOptativas;
        return this;
    }

    public RespAvalOptativa addOpcaoResposta(OpcaoRespAvalOptativa opcaoRespAvalOptativa) {
        this.opcaoRespostas.add(opcaoRespAvalOptativa);
        opcaoRespAvalOptativa.setOpcaoResposta(this);
        return this;
    }

    public RespAvalOptativa removeOpcaoResposta(OpcaoRespAvalOptativa opcaoRespAvalOptativa) {
        this.opcaoRespostas.remove(opcaoRespAvalOptativa);
        opcaoRespAvalOptativa.setOpcaoResposta(null);
        return this;
    }

    public void setOpcaoRespostas(Set<OpcaoRespAvalOptativa> opcaoRespAvalOptativas) {
        this.opcaoRespostas = opcaoRespAvalOptativas;
    }

    public ItemAvaliacao getItemAvaliacao() {
        return itemAvaliacao;
    }

    public RespAvalOptativa itemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
        return this;
    }

    public void setItemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacao = itemAvaliacao;
    }

    public RespostaAvaliacao getRespostaAvaliacao() {
        return respostaAvaliacao;
    }

    public RespAvalOptativa respostaAvaliacao(RespostaAvaliacao respostaAvaliacao) {
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
        RespAvalOptativa respAvalOptativa = (RespAvalOptativa) o;
        if (respAvalOptativa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respAvalOptativa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespAvalOptativa{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
