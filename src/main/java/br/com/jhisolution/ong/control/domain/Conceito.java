package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Conceito.
 */
@Entity
@Table(name = "conceito")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Conceito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;

    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    @Column(name = "nota")
    private Float nota;

    @OneToOne    @JoinColumn(unique = true)
    private TipoConceito tipoConceito;

    @OneToOne(mappedBy = "conceito")
    @JsonIgnore
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

    public Conceito nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getNota() {
        return nota;
    }

    public Conceito nota(Float nota) {
        this.nota = nota;
        return this;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public TipoConceito getTipoConceito() {
        return tipoConceito;
    }

    public Conceito tipoConceito(TipoConceito tipoConceito) {
        this.tipoConceito = tipoConceito;
        return this;
    }

    public void setTipoConceito(TipoConceito tipoConceito) {
        this.tipoConceito = tipoConceito;
    }

    public FechamentoBimestre getFechamentoBimestre() {
        return fechamentoBimestre;
    }

    public Conceito fechamentoBimestre(FechamentoBimestre fechamentoBimestre) {
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
        Conceito conceito = (Conceito) o;
        if (conceito.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), conceito.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Conceito{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", nota=" + getNota() +
            "}";
    }
}
