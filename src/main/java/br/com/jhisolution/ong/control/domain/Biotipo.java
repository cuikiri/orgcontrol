package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Biotipo.
 */
@Entity
@Table(name = "biotipo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Biotipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "valor", nullable = false)
    private Float valor;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoBiotipo tipoBiotipo;

    @ManyToOne
    @JsonIgnoreProperties("biotipos")
    private ExameMedico exameMedico;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getValor() {
        return valor;
    }

    public Biotipo valor(Float valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public Biotipo obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoBiotipo getTipoBiotipo() {
        return tipoBiotipo;
    }

    public Biotipo tipoBiotipo(TipoBiotipo tipoBiotipo) {
        this.tipoBiotipo = tipoBiotipo;
        return this;
    }

    public void setTipoBiotipo(TipoBiotipo tipoBiotipo) {
        this.tipoBiotipo = tipoBiotipo;
    }

    public ExameMedico getExameMedico() {
        return exameMedico;
    }

    public Biotipo exameMedico(ExameMedico exameMedico) {
        this.exameMedico = exameMedico;
        return this;
    }

    public void setExameMedico(ExameMedico exameMedico) {
        this.exameMedico = exameMedico;
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
        Biotipo biotipo = (Biotipo) o;
        if (biotipo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), biotipo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Biotipo{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
