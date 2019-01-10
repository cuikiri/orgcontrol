package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import br.com.jhisolution.ong.control.domain.enumeration.TipoResidencia;

import br.com.jhisolution.ong.control.domain.enumeration.EnderecoTipo;

import br.com.jhisolution.ong.control.domain.enumeration.TipoLogradouro;

import br.com.jhisolution.ong.control.domain.enumeration.TipoBairro;

import br.com.jhisolution.ong.control.domain.enumeration.Regiao;

/**
 * A Endereco.
 */
@Entity
@Table(name = "endereco")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_residencia")
    private TipoResidencia tipoResidencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_endereco")
    private EnderecoTipo tipoEndereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_logradouro")
    private TipoLogradouro tipoLogradouro;

    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;

    @Max(value = 99999)
    @Column(name = "numero")
    private Integer numero;

    @NotNull
    @Size(max = 50)
    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_bairoo", nullable = false)
    private TipoBairro tipoBairoo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "zona", nullable = false)
    private Regiao zona;

    @Size(max = 10)
    @Column(name = "cep", length = 10)
    private String cep;

    @Size(max = 30)
    @Column(name = "bloco", length = 30)
    private String bloco;

    @Size(max = 20)
    @Column(name = "apto", length = 20)
    private String apto;

    @Size(max = 50)
    @Column(name = "complemento", length = 50)
    private String complemento;

    @Size(max = 50)
    @Column(name = "cidade", length = 50)
    private String cidade;

    @OneToOne    @JoinColumn(unique = true)
    private Uf estado;

    @OneToOne    @JoinColumn(unique = true)
    private Localizacao localizacao;

    @OneToOne(mappedBy = "endereco")
    @JsonIgnore
    private Unidade unidade;

    @ManyToOne
    @JsonIgnoreProperties("enderecos")
    private Pessoa pessoa;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoResidencia getTipoResidencia() {
        return tipoResidencia;
    }

    public Endereco tipoResidencia(TipoResidencia tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
        return this;
    }

    public void setTipoResidencia(TipoResidencia tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public EnderecoTipo getTipoEndereco() {
        return tipoEndereco;
    }

    public Endereco tipoEndereco(EnderecoTipo tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
        return this;
    }

    public void setTipoEndereco(EnderecoTipo tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public Endereco tipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
        return this;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public String getNome() {
        return nome;
    }

    public Endereco nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public Endereco numero(Integer numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public Endereco bairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public TipoBairro getTipoBairoo() {
        return tipoBairoo;
    }

    public Endereco tipoBairoo(TipoBairro tipoBairoo) {
        this.tipoBairoo = tipoBairoo;
        return this;
    }

    public void setTipoBairoo(TipoBairro tipoBairoo) {
        this.tipoBairoo = tipoBairoo;
    }

    public Regiao getZona() {
        return zona;
    }

    public Endereco zona(Regiao zona) {
        this.zona = zona;
        return this;
    }

    public void setZona(Regiao zona) {
        this.zona = zona;
    }

    public String getCep() {
        return cep;
    }

    public Endereco cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBloco() {
        return bloco;
    }

    public Endereco bloco(String bloco) {
        this.bloco = bloco;
        return this;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }

    public String getApto() {
        return apto;
    }

    public Endereco apto(String apto) {
        this.apto = apto;
        return this;
    }

    public void setApto(String apto) {
        this.apto = apto;
    }

    public String getComplemento() {
        return complemento;
    }

    public Endereco complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Endereco cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Uf getEstado() {
        return estado;
    }

    public Endereco estado(Uf uf) {
        this.estado = uf;
        return this;
    }

    public void setEstado(Uf uf) {
        this.estado = uf;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public Endereco localizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
        return this;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public Endereco unidade(Unidade unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Endereco pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        Endereco endereco = (Endereco) o;
        if (endereco.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), endereco.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Endereco{" +
            "id=" + getId() +
            ", tipoResidencia='" + getTipoResidencia() + "'" +
            ", tipoEndereco='" + getTipoEndereco() + "'" +
            ", tipoLogradouro='" + getTipoLogradouro() + "'" +
            ", nome='" + getNome() + "'" +
            ", numero=" + getNumero() +
            ", bairro='" + getBairro() + "'" +
            ", tipoBairoo='" + getTipoBairoo() + "'" +
            ", zona='" + getZona() + "'" +
            ", cep='" + getCep() + "'" +
            ", bloco='" + getBloco() + "'" +
            ", apto='" + getApto() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", cidade='" + getCidade() + "'" +
            "}";
    }
}
