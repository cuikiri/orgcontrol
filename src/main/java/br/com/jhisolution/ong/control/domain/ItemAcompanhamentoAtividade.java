package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import br.com.jhisolution.ong.control.domain.enumeration.TipoResposta;

/**
 * A ItemAcompanhamentoAtividade.
 */
@Entity
@Table(name = "item_acompanhamento_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAcompanhamentoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_resposta", nullable = false)
    private TipoResposta tipoResposta;

    @NotNull
    @Size(max = 1000)
    @Column(name = "questao", length = 1000, nullable = false)
    private String questao;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private RespAtivDescritiva respAtivDescritiva;

    @OneToOne    @JoinColumn(unique = true)
    private RespAtivOptativa respAtivOptativa;

    @OneToMany(mappedBy = "itemAcompanhamentoAtividade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RespostaAtividade> respostaAtividades = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("itemAcompanhamentoAtividades")
    private AcompanhamentoAtividade acompanhamentoAtividade;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoResposta getTipoResposta() {
        return tipoResposta;
    }

    public ItemAcompanhamentoAtividade tipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
        return this;
    }

    public void setTipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
    }

    public String getQuestao() {
        return questao;
    }

    public ItemAcompanhamentoAtividade questao(String questao) {
        this.questao = questao;
        return this;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getObs() {
        return obs;
    }

    public ItemAcompanhamentoAtividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAtivDescritiva getRespAtivDescritiva() {
        return respAtivDescritiva;
    }

    public ItemAcompanhamentoAtividade respAtivDescritiva(RespAtivDescritiva respAtivDescritiva) {
        this.respAtivDescritiva = respAtivDescritiva;
        return this;
    }

    public void setRespAtivDescritiva(RespAtivDescritiva respAtivDescritiva) {
        this.respAtivDescritiva = respAtivDescritiva;
    }

    public RespAtivOptativa getRespAtivOptativa() {
        return respAtivOptativa;
    }

    public ItemAcompanhamentoAtividade respAtivOptativa(RespAtivOptativa respAtivOptativa) {
        this.respAtivOptativa = respAtivOptativa;
        return this;
    }

    public void setRespAtivOptativa(RespAtivOptativa respAtivOptativa) {
        this.respAtivOptativa = respAtivOptativa;
    }

    public Set<RespostaAtividade> getRespostaAtividades() {
        return respostaAtividades;
    }

    public ItemAcompanhamentoAtividade respostaAtividades(Set<RespostaAtividade> respostaAtividades) {
        this.respostaAtividades = respostaAtividades;
        return this;
    }

    public ItemAcompanhamentoAtividade addRespostaAtividade(RespostaAtividade respostaAtividade) {
        this.respostaAtividades.add(respostaAtividade);
        respostaAtividade.setItemAcompanhamentoAtividade(this);
        return this;
    }

    public ItemAcompanhamentoAtividade removeRespostaAtividade(RespostaAtividade respostaAtividade) {
        this.respostaAtividades.remove(respostaAtividade);
        respostaAtividade.setItemAcompanhamentoAtividade(null);
        return this;
    }

    public void setRespostaAtividades(Set<RespostaAtividade> respostaAtividades) {
        this.respostaAtividades = respostaAtividades;
    }

    public AcompanhamentoAtividade getAcompanhamentoAtividade() {
        return acompanhamentoAtividade;
    }

    public ItemAcompanhamentoAtividade acompanhamentoAtividade(AcompanhamentoAtividade acompanhamentoAtividade) {
        this.acompanhamentoAtividade = acompanhamentoAtividade;
        return this;
    }

    public void setAcompanhamentoAtividade(AcompanhamentoAtividade acompanhamentoAtividade) {
        this.acompanhamentoAtividade = acompanhamentoAtividade;
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
        ItemAcompanhamentoAtividade itemAcompanhamentoAtividade = (ItemAcompanhamentoAtividade) o;
        if (itemAcompanhamentoAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAcompanhamentoAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAcompanhamentoAtividade{" +
            "id=" + getId() +
            ", tipoResposta='" + getTipoResposta() + "'" +
            ", questao='" + getQuestao() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
