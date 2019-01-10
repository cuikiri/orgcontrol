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
 * A DependenteLegal.
 */
@Entity
@Table(name = "dependente_legal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DependenteLegal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToMany(mappedBy = "dependenteLegal")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Documento> documentos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("dependenteLegals")
    private Colaborador colaborador;

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

    public DependenteLegal nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public DependenteLegal dataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
        return this;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getObs() {
        return obs;
    }

    public DependenteLegal obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public DependenteLegal documentos(Set<Documento> documentos) {
        this.documentos = documentos;
        return this;
    }

    public DependenteLegal addDocumento(Documento documento) {
        this.documentos.add(documento);
        documento.setDependenteLegal(this);
        return this;
    }

    public DependenteLegal removeDocumento(Documento documento) {
        this.documentos.remove(documento);
        documento.setDependenteLegal(null);
        return this;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public DependenteLegal colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
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
        DependenteLegal dependenteLegal = (DependenteLegal) o;
        if (dependenteLegal.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dependenteLegal.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DependenteLegal{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dataNascimento='" + getDataNascimento() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
