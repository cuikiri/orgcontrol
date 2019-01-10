package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import br.com.jhisolution.ong.control.domain.enumeration.TipoAviso;

/**
 * A Aviso.
 */
@Entity
@Table(name = "aviso")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aviso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @NotNull
    @Size(max = 1000)
    @Column(name = "aviso", length = 1000, nullable = false)
    private String aviso;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoAviso tipo;

    @ManyToMany(mappedBy = "avisos")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Pessoa> pessoas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public Aviso data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getAviso() {
        return aviso;
    }

    public Aviso aviso(String aviso) {
        this.aviso = aviso;
        return this;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public TipoAviso getTipo() {
        return tipo;
    }

    public Aviso tipo(TipoAviso tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(TipoAviso tipo) {
        this.tipo = tipo;
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public Aviso pessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }

    public Aviso addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
        pessoa.getAvisos().add(this);
        return this;
    }

    public Aviso removePessoa(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
        pessoa.getAvisos().remove(this);
        return this;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
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
        Aviso aviso = (Aviso) o;
        if (aviso.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aviso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aviso{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", aviso='" + getAviso() + "'" +
            ", tipo='" + getTipo() + "'" +
            "}";
    }
}
