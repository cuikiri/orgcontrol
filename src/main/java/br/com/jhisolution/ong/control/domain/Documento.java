package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A Documento.
 */
@Entity
@Table(name = "documento")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 50)
    @Column(name = "numero", length = 50, nullable = false)
    private String numero;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoDocumento tipoDocumento;

    @OneToMany(mappedBy = "documento")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FotoDocumento> fotoDocumentos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    private Pessoa pessoa;

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    private Colaborador colaborador;

    @ManyToOne
    @JsonIgnoreProperties("documentos")
    private DependenteLegal dependenteLegal;
    
    // Construtores
    public Documento() {}
    
    public Documento(Long id) {
    	this.id = id;
    }
    
    // Fábricas
    public static Documento getInstance() {
    	return new Documento();
    }

    public static Documento getInstance(Long id) {
    	return new Documento(id);
    }
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

    public Documento nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public Documento numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public Documento obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public Documento tipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Set<FotoDocumento> getFotoDocumentos() {
        return this.fotoDocumentos;
    }

    public Documento fotoDocumentos(Set<FotoDocumento> fotoDocumentos) {
        this.fotoDocumentos = fotoDocumentos;
        return this;
    }

    public void setFotoDocumento(Set<FotoDocumento> fotoDocumentos) {
        this.fotoDocumentos = fotoDocumentos;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Documento pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Documento colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public DependenteLegal getDependenteLegal() {
        return dependenteLegal;
    }

    public Documento dependenteLegal(DependenteLegal dependenteLegal) {
        this.dependenteLegal = dependenteLegal;
        return this;
    }

    public void setDependenteLegal(DependenteLegal dependenteLegal) {
        this.dependenteLegal = dependenteLegal;
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
        Documento documento = (Documento) o;
        if (documento.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Documento{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
