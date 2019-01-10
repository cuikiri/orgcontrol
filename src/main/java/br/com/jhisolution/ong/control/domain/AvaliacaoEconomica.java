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
 * A AvaliacaoEconomica.
 */
@Entity
@Table(name = "avaliacao_economica")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AvaliacaoEconomica implements Serializable {

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
    private TipoAvaliacaoEconomica tipoAvaliacaoEconomica;

    @OneToMany(mappedBy = "avaliacaoEconomica")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemAvaliacaoEconomica> itemAvaliacaoEconomicas = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("avaliacaoEconomicas")
    private Aluno aluno;

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

    public AvaliacaoEconomica nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public AvaliacaoEconomica data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTema() {
        return tema;
    }

    public AvaliacaoEconomica tema(String tema) {
        this.tema = tema;
        return this;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescricao() {
        return descricao;
    }

    public AvaliacaoEconomica descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public AvaliacaoEconomica dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public AvaliacaoEconomica dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return obs;
    }

    public AvaliacaoEconomica obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoAvaliacaoEconomica getTipoAvaliacaoEconomica() {
        return tipoAvaliacaoEconomica;
    }

    public AvaliacaoEconomica tipoAvaliacaoEconomica(TipoAvaliacaoEconomica tipoAvaliacaoEconomica) {
        this.tipoAvaliacaoEconomica = tipoAvaliacaoEconomica;
        return this;
    }

    public void setTipoAvaliacaoEconomica(TipoAvaliacaoEconomica tipoAvaliacaoEconomica) {
        this.tipoAvaliacaoEconomica = tipoAvaliacaoEconomica;
    }

    public Set<ItemAvaliacaoEconomica> getItemAvaliacaoEconomicas() {
        return itemAvaliacaoEconomicas;
    }

    public AvaliacaoEconomica itemAvaliacaoEconomicas(Set<ItemAvaliacaoEconomica> itemAvaliacaoEconomicas) {
        this.itemAvaliacaoEconomicas = itemAvaliacaoEconomicas;
        return this;
    }

    public AvaliacaoEconomica addItemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomicas.add(itemAvaliacaoEconomica);
        itemAvaliacaoEconomica.setAvaliacaoEconomica(this);
        return this;
    }

    public AvaliacaoEconomica removeItemAvaliacaoEconomica(ItemAvaliacaoEconomica itemAvaliacaoEconomica) {
        this.itemAvaliacaoEconomicas.remove(itemAvaliacaoEconomica);
        itemAvaliacaoEconomica.setAvaliacaoEconomica(null);
        return this;
    }

    public void setItemAvaliacaoEconomicas(Set<ItemAvaliacaoEconomica> itemAvaliacaoEconomicas) {
        this.itemAvaliacaoEconomicas = itemAvaliacaoEconomicas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public AvaliacaoEconomica aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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
        AvaliacaoEconomica avaliacaoEconomica = (AvaliacaoEconomica) o;
        if (avaliacaoEconomica.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avaliacaoEconomica.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AvaliacaoEconomica{" +
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
