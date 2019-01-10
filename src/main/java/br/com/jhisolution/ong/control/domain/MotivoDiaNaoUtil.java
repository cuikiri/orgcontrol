package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A MotivoDiaNaoUtil.
 */
@Entity
@Table(name = "motivo_dia_nao_util")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MotivoDiaNaoUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("motivoDiaNaoUtils")
    private DiaNaoUtil diaNaoUtil;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public MotivoDiaNaoUtil nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public MotivoDiaNaoUtil obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public DiaNaoUtil getDiaNaoUtil() {
        return diaNaoUtil;
    }

    public MotivoDiaNaoUtil diaNaoUtil(DiaNaoUtil diaNaoUtil) {
        this.diaNaoUtil = diaNaoUtil;
        return this;
    }

    public void setDiaNaoUtil(DiaNaoUtil diaNaoUtil) {
        this.diaNaoUtil = diaNaoUtil;
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
        MotivoDiaNaoUtil motivoDiaNaoUtil = (MotivoDiaNaoUtil) o;
        if (motivoDiaNaoUtil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), motivoDiaNaoUtil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MotivoDiaNaoUtil{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
