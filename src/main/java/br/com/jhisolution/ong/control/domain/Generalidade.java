package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Generalidade.
 */
@Entity
@Table(name = "generalidade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Generalidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "codigo", length = 100, nullable = false)
    private String codigo;

    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;

    @ManyToOne
    @JsonIgnoreProperties("generalidades")
    private Diario diario;

    @ManyToOne
    @JsonIgnoreProperties("generalidades")
    private Bimestre bimestre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Generalidade codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public Generalidade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Diario getDiario() {
        return diario;
    }

    public Generalidade diario(Diario diario) {
        this.diario = diario;
        return this;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public Generalidade bimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
        return this;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
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
        Generalidade generalidade = (Generalidade) o;
        if (generalidade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), generalidade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Generalidade{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
