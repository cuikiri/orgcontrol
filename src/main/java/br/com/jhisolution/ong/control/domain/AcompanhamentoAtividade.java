package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A AcompanhamentoAtividade.
 */
@Entity
@Table(name = "acompanhamento_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AcompanhamentoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Size(max = 50)
    @Column(name = "tema", length = 50)
    private String tema;

    @Size(max = 1000)
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade;

    @OneToMany(mappedBy = "acompanhamentoAtividade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividades = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("acompanhamentoAtividades")
    private Atividade atividade;

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

    public AcompanhamentoAtividade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public AcompanhamentoAtividade data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTema() {
        return tema;
    }

    public AcompanhamentoAtividade tema(String tema) {
        this.tema = tema;
        return this;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public AcompanhamentoAtividade descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public AcompanhamentoAtividade dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public AcompanhamentoAtividade dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return obs;
    }

    public AcompanhamentoAtividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoAcompanhamentoAtividade getTipoAcompanhamentoAtividade() {
        return tipoAcompanhamentoAtividade;
    }

    public AcompanhamentoAtividade tipoAcompanhamentoAtividade(TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade) {
        this.tipoAcompanhamentoAtividade = tipoAcompanhamentoAtividade;
        return this;
    }

    public void setTipoAcompanhamentoAtividade(TipoAcompanhamentoAtividade tipoAcompanhamentoAtividade) {
        this.tipoAcompanhamentoAtividade = tipoAcompanhamentoAtividade;
    }

    public Set<ItemAcompanhamentoAtividade> getItemAcompanhamentoAtividades() {
        return itemAcompanhamentoAtividades;
    }

    public AcompanhamentoAtividade itemAcompanhamentoAtividades(Set<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividades) {
        this.itemAcompanhamentoAtividades = itemAcompanhamentoAtividades;
        return this;
    }

    public AcompanhamentoAtividade addItemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividades.add(itemAcompanhamentoAtividade);
        itemAcompanhamentoAtividade.setAcompanhamentoAtividade(this);
        return this;
    }

    public AcompanhamentoAtividade removeItemAcompanhamentoAtividade(ItemAcompanhamentoAtividade itemAcompanhamentoAtividade) {
        this.itemAcompanhamentoAtividades.remove(itemAcompanhamentoAtividade);
        itemAcompanhamentoAtividade.setAcompanhamentoAtividade(null);
        return this;
    }

    public void setItemAcompanhamentoAtividades(Set<ItemAcompanhamentoAtividade> itemAcompanhamentoAtividades) {
        this.itemAcompanhamentoAtividades = itemAcompanhamentoAtividades;
    }

    public Atividade getAtividade() {
        return atividade;
    }

    public AcompanhamentoAtividade atividade(Atividade atividade) {
        this.atividade = atividade;
        return this;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
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
        AcompanhamentoAtividade acompanhamentoAtividade = (AcompanhamentoAtividade) o;
        if (acompanhamentoAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), acompanhamentoAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcompanhamentoAtividade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data='" + getData() + "'" +
            ", tema='" + getTema() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataFim='" + getDataFim() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
