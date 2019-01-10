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
 * A ItemAvaliacao.
 */
@Entity
@Table(name = "item_avaliacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAvaliacao implements Serializable {

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
    private RespAvalDescritiva respAvalDescritiva;

    @OneToOne    @JoinColumn(unique = true)
    private RespAvalOptativa respAvalOptativa;

    @OneToMany(mappedBy = "itemAvaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RespostaAvaliacao> respostaAvaliacaos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("itemAvaliacaos")
    private Avaliacao avaliacao;

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

    public ItemAvaliacao tipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
        return this;
    }

    public void setTipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
    }

    public String getQuestao() {
        return questao;
    }

    public ItemAvaliacao questao(String questao) {
        this.questao = questao;
        return this;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getObs() {
        return obs;
    }

    public ItemAvaliacao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAvalDescritiva getRespAvalDescritiva() {
        return respAvalDescritiva;
    }

    public ItemAvaliacao respAvalDescritiva(RespAvalDescritiva respAvalDescritiva) {
        this.respAvalDescritiva = respAvalDescritiva;
        return this;
    }

    public void setRespAvalDescritiva(RespAvalDescritiva respAvalDescritiva) {
        this.respAvalDescritiva = respAvalDescritiva;
    }

    public RespAvalOptativa getRespAvalOptativa() {
        return respAvalOptativa;
    }

    public ItemAvaliacao respAvalOptativa(RespAvalOptativa respAvalOptativa) {
        this.respAvalOptativa = respAvalOptativa;
        return this;
    }

    public void setRespAvalOptativa(RespAvalOptativa respAvalOptativa) {
        this.respAvalOptativa = respAvalOptativa;
    }

    public Set<RespostaAvaliacao> getRespostaAvaliacaos() {
        return respostaAvaliacaos;
    }

    public ItemAvaliacao respostaAvaliacaos(Set<RespostaAvaliacao> respostaAvaliacaos) {
        this.respostaAvaliacaos = respostaAvaliacaos;
        return this;
    }

    public ItemAvaliacao addRespostaAvaliacao(RespostaAvaliacao respostaAvaliacao) {
        this.respostaAvaliacaos.add(respostaAvaliacao);
        respostaAvaliacao.setItemAvaliacao(this);
        return this;
    }

    public ItemAvaliacao removeRespostaAvaliacao(RespostaAvaliacao respostaAvaliacao) {
        this.respostaAvaliacaos.remove(respostaAvaliacao);
        respostaAvaliacao.setItemAvaliacao(null);
        return this;
    }

    public void setRespostaAvaliacaos(Set<RespostaAvaliacao> respostaAvaliacaos) {
        this.respostaAvaliacaos = respostaAvaliacaos;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public ItemAvaliacao avaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
        return this;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
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
        ItemAvaliacao itemAvaliacao = (ItemAvaliacao) o;
        if (itemAvaliacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAvaliacao{" +
            "id=" + getId() +
            ", tipoResposta='" + getTipoResposta() + "'" +
            ", questao='" + getQuestao() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
