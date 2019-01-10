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
 * A Bloco.
 */
@Entity
@Table(name = "bloco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bloco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 1000)
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoBloco tipoBloco;

    @OneToOne    @JoinColumn(unique = true)
    private Localizacao localizacao;

    @OneToMany(mappedBy = "bloco")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ParteBloco> parteBlocos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("blocos")
    private Unidade unidade;

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

    public Bloco nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Bloco descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public Bloco obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoBloco getTipoBloco() {
        return tipoBloco;
    }

    public Bloco tipoBloco(TipoBloco tipoBloco) {
        this.tipoBloco = tipoBloco;
        return this;
    }

    public void setTipoBloco(TipoBloco tipoBloco) {
        this.tipoBloco = tipoBloco;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Bloco localizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
        return this;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Set<ParteBloco> getParteBlocos() {
        return parteBlocos;
    }

    public Bloco parteBlocos(Set<ParteBloco> parteBlocos) {
        this.parteBlocos = parteBlocos;
        return this;
    }

    public Bloco addParteBloco(ParteBloco parteBloco) {
        this.parteBlocos.add(parteBloco);
        parteBloco.setBloco(this);
        return this;
    }

    public Bloco removeParteBloco(ParteBloco parteBloco) {
        this.parteBlocos.remove(parteBloco);
        parteBloco.setBloco(null);
        return this;
    }

    public void setParteBlocos(Set<ParteBloco> parteBlocos) {
        this.parteBlocos = parteBlocos;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Bloco unidade(Unidade unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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
        Bloco bloco = (Bloco) o;
        if (bloco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bloco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Bloco{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
