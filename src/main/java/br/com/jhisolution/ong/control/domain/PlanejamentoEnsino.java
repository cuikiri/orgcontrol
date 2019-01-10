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

/**
 * A PlanejamentoEnsino.
 */
@Entity
@Table(name = "planejamento_ensino")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlanejamentoEnsino implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Size(max = 1000)
    @Column(name = "objetivo", length = 1000)
    private String objetivo;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToMany(mappedBy = "planejamentoEnsino")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemPlanejamentoEnsino> itemPlanejamentoEnsinos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("planejamentoEnsinos")
    private CalendarioInstituicao calendarioInstituicao;

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

    public PlanejamentoEnsino nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public PlanejamentoEnsino objetivo(String objetivo) {
        this.objetivo = objetivo;
        return this;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObs() {
        return obs;
    }

    public PlanejamentoEnsino obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<ItemPlanejamentoEnsino> getItemPlanejamentoEnsinos() {
        return itemPlanejamentoEnsinos;
    }

    public PlanejamentoEnsino itemPlanejamentoEnsinos(Set<ItemPlanejamentoEnsino> itemPlanejamentoEnsinos) {
        this.itemPlanejamentoEnsinos = itemPlanejamentoEnsinos;
        return this;
    }

    public PlanejamentoEnsino addItemPlanejamentoEnsino(ItemPlanejamentoEnsino itemPlanejamentoEnsino) {
        this.itemPlanejamentoEnsinos.add(itemPlanejamentoEnsino);
        itemPlanejamentoEnsino.setPlanejamentoEnsino(this);
        return this;
    }

    public PlanejamentoEnsino removeItemPlanejamentoEnsino(ItemPlanejamentoEnsino itemPlanejamentoEnsino) {
        this.itemPlanejamentoEnsinos.remove(itemPlanejamentoEnsino);
        itemPlanejamentoEnsino.setPlanejamentoEnsino(null);
        return this;
    }

    public void setItemPlanejamentoEnsinos(Set<ItemPlanejamentoEnsino> itemPlanejamentoEnsinos) {
        this.itemPlanejamentoEnsinos = itemPlanejamentoEnsinos;
    }

    public CalendarioInstituicao getCalendarioInstituicao() {
        return calendarioInstituicao;
    }

    public PlanejamentoEnsino calendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicao = calendarioInstituicao;
        return this;
    }

    public void setCalendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicao = calendarioInstituicao;
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
        PlanejamentoEnsino planejamentoEnsino = (PlanejamentoEnsino) o;
        if (planejamentoEnsino.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planejamentoEnsino.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanejamentoEnsino{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", objetivo='" + getObjetivo() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
