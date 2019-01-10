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
 * A Avaliacao.
 */
@Entity
@Table(name = "avaliacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Avaliacao implements Serializable {

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
    private TipoAvaliacao tipoAvaliacao;

    @OneToMany(mappedBy = "avaliacao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliacao> itemAvaliacaos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("avaliacaos")
    private FechamentoBimestre fechamentoBimestre;

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

    public Avaliacao nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public Avaliacao data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTema() {
        return tema;
    }

    public Avaliacao tema(String tema) {
        this.tema = tema;
        return this;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public Avaliacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public Avaliacao dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public Avaliacao dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return obs;
    }

    public Avaliacao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public Avaliacao tipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
        return this;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public Set<ItemAvaliacao> getItemAvaliacaos() {
        return itemAvaliacaos;
    }

    public Avaliacao itemAvaliacaos(Set<ItemAvaliacao> itemAvaliacaos) {
        this.itemAvaliacaos = itemAvaliacaos;
        return this;
    }

    public Avaliacao addItemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacaos.add(itemAvaliacao);
        itemAvaliacao.setAvaliacao(this);
        return this;
    }

    public Avaliacao removeItemAvaliacao(ItemAvaliacao itemAvaliacao) {
        this.itemAvaliacaos.remove(itemAvaliacao);
        itemAvaliacao.setAvaliacao(null);
        return this;
    }

    public void setItemAvaliacaos(Set<ItemAvaliacao> itemAvaliacaos) {
        this.itemAvaliacaos = itemAvaliacaos;
    }

    public FechamentoBimestre getFechamentoBimestre() {
        return fechamentoBimestre;
    }

    public Avaliacao fechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
        this.fechamentoBimestre = fechamentoBimestre;
        return this;
    }

    public void setFechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
        this.fechamentoBimestre = fechamentoBimestre;
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
        Avaliacao avaliacao = (Avaliacao) o;
        if (avaliacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avaliacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
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
