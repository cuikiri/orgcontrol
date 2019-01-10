package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A OpcaoRespAvalOptativa.
 */
@Entity
@Table(name = "opcao_resp_aval_optativa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OpcaoRespAvalOptativa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "opcao", length = 30, nullable = false)
    private String opcao;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("opcaoRespostas")
    private RespAvalOptativa opcaoResposta;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcao() {
        return opcao;
    }

    public OpcaoRespAvalOptativa opcao(String opcao) {
        this.opcao = opcao;
        return this;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getObs() {
        return obs;
    }

    public OpcaoRespAvalOptativa obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public RespAvalOptativa getOpcaoResposta() {
        return opcaoResposta;
    }

    public OpcaoRespAvalOptativa opcaoResposta(RespAvalOptativa respAvalOptativa) {
        this.opcaoResposta = respAvalOptativa;
        return this;
    }

    public void setOpcaoResposta(RespAvalOptativa respAvalOptativa) {
        this.opcaoResposta = respAvalOptativa;
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
        OpcaoRespAvalOptativa opcaoRespAvalOptativa = (OpcaoRespAvalOptativa) o;
        if (opcaoRespAvalOptativa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), opcaoRespAvalOptativa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OpcaoRespAvalOptativa{" +
            "id=" + getId() +
            ", opcao='" + getOpcao() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
