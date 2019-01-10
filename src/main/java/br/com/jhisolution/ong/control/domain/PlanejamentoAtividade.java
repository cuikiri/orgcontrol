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
 * A PlanejamentoAtividade.
 */
@Entity
@Table(name = "planejamento_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PlanejamentoAtividade implements Serializable {

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

    @OneToMany(mappedBy = "planejamentoAtividade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemPlanejamentoAtividade> itemPlanejamentoAtividades = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("planejamentoAtividades")
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

    public PlanejamentoAtividade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public PlanejamentoAtividade objetivo(String objetivo) {
        this.objetivo = objetivo;
        return this;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObs() {
        return obs;
    }

    public PlanejamentoAtividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<ItemPlanejamentoAtividade> getItemPlanejamentoAtividades() {
        return itemPlanejamentoAtividades;
    }

    public PlanejamentoAtividade itemPlanejamentoAtividades(Set<ItemPlanejamentoAtividade> itemPlanejamentoAtividades) {
        this.itemPlanejamentoAtividades = itemPlanejamentoAtividades;
        return this;
    }

    public PlanejamentoAtividade addItemPlanejamentoAtividade(ItemPlanejamentoAtividade itemPlanejamentoAtividade) {
        this.itemPlanejamentoAtividades.add(itemPlanejamentoAtividade);
        itemPlanejamentoAtividade.setPlanejamentoAtividade(this);
        return this;
    }

    public PlanejamentoAtividade removeItemPlanejamentoAtividade(ItemPlanejamentoAtividade itemPlanejamentoAtividade) {
        this.itemPlanejamentoAtividades.remove(itemPlanejamentoAtividade);
        itemPlanejamentoAtividade.setPlanejamentoAtividade(null);
        return this;
    }

    public void setItemPlanejamentoAtividades(Set<ItemPlanejamentoAtividade> itemPlanejamentoAtividades) {
        this.itemPlanejamentoAtividades = itemPlanejamentoAtividades;
    }

    public CalendarioInstituicao getCalendarioInstituicao() {
        return calendarioInstituicao;
    }

    public PlanejamentoAtividade calendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
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
        PlanejamentoAtividade planejamentoAtividade = (PlanejamentoAtividade) o;
        if (planejamentoAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planejamentoAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlanejamentoAtividade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", objetivo='" + getObjetivo() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
