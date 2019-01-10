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
 * A ItemAvaliacaoEconomica.
 */
@Entity
@Table(name = "item_avaliacao_economica")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemAvaliacaoEconomica implements Serializable {

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
    private RespAvalDescritivaEconomica respAvalDescritivaEconomica;

    @OneToOne    @JoinColumn(unique = true)
    private RespAvalOptativaEconomica respAvalOptativaEconomica;

    @OneToMany(mappedBy = "itemAvaliacaoEconomica")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("itemAvaliacaoEconomicas")
    private AvaliacaoEconomica avaliacaoEconomica;

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

    public ItemAvaliacaoEconomica tipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
        return this;
    }

    public void setTipoResposta(TipoResposta tipoResposta) {
        this.tipoResposta = tipoResposta;
    }

    public String getQuestao() {
        return questao;
    }

    public ItemAvaliacaoEconomica questao(String questao) {
        this.questao = questao;
        return this;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getObs() {
        return obs;
    }

    public ItemAvaliacaoEconomica obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAvalDescritivaEconomica getRespAvalDescritivaEconomica() {
        return respAvalDescritivaEconomica;
    }

    public ItemAvaliacaoEconomica respAvalDescritivaEconomica(RespAvalDescritivaEconomica respAvalDescritivaEconomica) {
        this.respAvalDescritivaEconomica = respAvalDescritivaEconomica;
        return this;
    }

    public void setRespAvalDescritivaEconomica(RespAvalDescritivaEconomica respAvalDescritivaEconomica) {
        this.respAvalDescritivaEconomica = respAvalDescritivaEconomica;
    }

    public RespAvalOptativaEconomica getRespAvalOptativaEconomica() {
        return respAvalOptativaEconomica;
    }

    public ItemAvaliacaoEconomica respAvalOptativaEconomica(RespAvalOptativaEconomica respAvalOptativaEconomica) {
        this.respAvalOptativaEconomica = respAvalOptativaEconomica;
        return this;
    }

    public void setRespAvalOptativaEconomica(RespAvalOptativaEconomica respAvalOptativaEconomica) {
        this.respAvalOptativaEconomica = respAvalOptativaEconomica;
    }

    public Set<RespostaAvaliacaoEconomica> getRespostaAvaliacaoEconomicas() {
        return respostaAvaliacaoEconomicas;
    }

    public ItemAvaliacaoEconomica respostaAvaliacaoEconomicas(Set<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicas) {
        this.respostaAvaliacaoEconomicas = respostaAvaliacaoEconomicas;
        return this;
    }

    public ItemAvaliacaoEconomica addRespostaAvaliacaoEconomica(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) {
        this.respostaAvaliacaoEconomicas.add(respostaAvaliacaoEconomica);
        respostaAvaliacaoEconomica.setItemAvaliacaoEconomica(this);
        return this;
    }

    public ItemAvaliacaoEconomica removeRespostaAvaliacaoEconomica(RespostaAvaliacaoEconomica respostaAvaliacaoEconomica) {
        this.respostaAvaliacaoEconomicas.remove(respostaAvaliacaoEconomica);
        respostaAvaliacaoEconomica.setItemAvaliacaoEconomica(null);
        return this;
    }

    public void setRespostaAvaliacaoEconomicas(Set<RespostaAvaliacaoEconomica> respostaAvaliacaoEconomicas) {
        this.respostaAvaliacaoEconomicas = respostaAvaliacaoEconomicas;
    }

    public AvaliacaoEconomica getAvaliacaoEconomica() {
        return avaliacaoEconomica;
    }

    public ItemAvaliacaoEconomica avaliacaoEconomica(AvaliacaoEconomica avaliacaoEconomica) {
        this.avaliacaoEconomica = avaliacaoEconomica;
        return this;
    }

    public void setAvaliacaoEconomica(AvaliacaoEconomica avaliacaoEconomica) {
        this.avaliacaoEconomica = avaliacaoEconomica;
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
        ItemAvaliacaoEconomica itemAvaliacaoEconomica = (ItemAvaliacaoEconomica) o;
        if (itemAvaliacaoEconomica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), itemAvaliacaoEconomica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ItemAvaliacaoEconomica{" +
            "id=" + getId() +
            ", tipoResposta='" + getTipoResposta() + "'" +
            ", questao='" + getQuestao() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
