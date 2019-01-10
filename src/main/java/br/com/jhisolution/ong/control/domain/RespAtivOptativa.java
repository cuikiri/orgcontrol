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
 * A RespAtivOptativa.
 */
@Entity
@Table(name = "resp_ativ_optativa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RespAtivOptativa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "opcaoResposta")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OpcaoRespAtividade> opcaoRespostas = new HashSet<>();
    @OneToOne(mappedBy = "respAtivOptativa")
    @JsonIgnore
    private ItemAcompanhamentoAtividade itemAcompanhamentoAtividade;

    @OneToOne(mappedBy = "respAtivOptativa")
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

    public RespAtivOptativa data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<OpcaoRespAtividade> getOpcaoRespostas() {
        return opcaoRespostas;
    }

    public RespAtivOptativa opcaoRespostas(Set<OpcaoRespAtividade> opcaoRespAtividades) {
        this.opcaoRespostas = opcaoRespAtividades;
        return this;
    }

    public RespAtivOptativa addOpcaoResposta(OpcaoRespAtividade opcaoRespAtividade) {
        this.opcaoRespostas.add(opcaoRespAtividade);
        opcaoRespAtividade.setOpcaoResposta(this);
        return this;
    }

    public RespAtivOptativa removeOpcaoResposta(OpcaoRespAtividade opcaoRespAtividade) {
        this.opcaoRespostas.remove(opcaoRespAtividade);
        opcaoRespAtividade.setOpcaoResposta(null);
        return this;
    }

    public void setOpcaoRespostas(Set<OpcaoRespAtividade> opcaoRespAtividades) {
        this.opcaoRespostas = opcaoRespAtividades;
    }

    public ItemAcompanhamentoAtividade getItemAcompanhamentoAtividade() {
        return itemAcompanhamentoAtividade;
    }

    public RespAtivOptativa itemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
        return this;
    }

    public void setItemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividade = itemAcompanhamentoAtividade;
    }

    public RespostaAtividade getRespostaAtividade() {
        return respostaAtividade;
    }

    public RespAtivOptativa respostaAtividade(RespostaAtividade respostaAtividade) {
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
        RespAtivOptativa respAtivOptativa = (RespAtivOptativa) o;
        if (respAtivOptativa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), respAtivOptativa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RespAtivOptativa{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
