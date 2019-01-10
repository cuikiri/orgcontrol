package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Doenca.
 */
@Entity
@Table(name = "doenca")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Doenca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 1000)
    @Column(name = "sintoma", length = 1000)
    private String sintoma;

    @Size(max = 1000)
    @Column(name = "precaucoes", length = 1000)
    private String precaucoes;

    @Size(max = 1000)
    @Column(name = "socorro", length = 1000)
    private String socorro;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @ManyToMany(mappedBy = "doencas")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<ExameMedico> exameMedicos = new HashSet<>();

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

    public Doenca nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSintoma() {
        return sintoma;
    }

    public Doenca sintoma(String sintoma) {
        this.sintoma = sintoma;
        return this;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getPrecaucoes() {
        return precaucoes;
    }

    public Doenca precaucoes(String precaucoes) {
        this.precaucoes = precaucoes;
        return this;
    }

    public void setPrecaucoes(String precaucoes) {
        this.precaucoes = precaucoes;
    }

    public String getSocorro() {
        return socorro;
    }

    public Doenca socorro(String socorro) {
        this.socorro = socorro;
        return this;
    }

    public void setSocorro(String socorro) {
        this.socorro = socorro;
    }

    public String getObs() {
        return obs;
    }

    public Doenca obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<ExameMedico> getExameMedicos() {
        return exameMedicos;
    }

    public Doenca exameMedicos(Set<ExameMedico> exameMedicos) {
        this.exameMedicos = exameMedicos;
        return this;
    }

    public Doenca addExameMedico(ExameMedico exameMedico) {
        this.exameMedicos.add(exameMedico);
        exameMedico.getDoencas().add(this);
        return this;
    }

    public Doenca removeExameMedico(ExameMedico exameMedico) {
        this.exameMedicos.remove(exameMedico);
        exameMedico.getDoencas().remove(this);
        return this;
    }

    public void setExameMedicos(Set<ExameMedico> exameMedicos) {
        this.exameMedicos = exameMedicos;
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
        Doenca doenca = (Doenca) o;
        if (doenca.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doenca.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Doenca{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", sintoma='" + getSintoma() + "'" +
            ", precaucoes='" + getPrecaucoes() + "'" +
            ", socorro='" + getSocorro() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
