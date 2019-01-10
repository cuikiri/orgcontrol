package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Advertencia.
 */
@Entity
@Table(name = "advertencia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Advertencia implements Serializable {

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

    @NotNull
    @Column(name = "data_advertencia", nullable = false)
    private LocalDate dataAdvertencia;

    @NotNull
    @Size(max = 5)
    @Column(name = "hora_advertencia", length = 5, nullable = false)
    private String horaAdvertencia;

    @NotNull
    @Size(max = 1000)
    @Column(name = "resumo", length = 1000, nullable = false)
    private String resumo;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("advertencias")
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

    public Advertencia nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public Advertencia data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getDataAdvertencia() {
        return dataAdvertencia;
    }

    public Advertencia dataAdvertencia(LocalDate dataAdvertencia) {
        this.dataAdvertencia = dataAdvertencia;
        return this;
    }

    public void setDataAdvertencia(LocalDate dataAdvertencia) {
        this.dataAdvertencia = dataAdvertencia;
    }

    public String getHoraAdvertencia() {
        return horaAdvertencia;
    }

    public Advertencia horaAdvertencia(String horaAdvertencia) {
        this.horaAdvertencia = horaAdvertencia;
        return this;
    }

    public void setHoraAdvertencia(String horaAdvertencia) {
        this.horaAdvertencia = horaAdvertencia;
    }

    public String getResumo() {
        return resumo;
    }

    public Advertencia resumo(String resumo) {
        this.resumo = resumo;
        return this;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getObs() {
        return obs;
    }

    public Advertencia obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Advertencia aluno(Aluno aluno) {
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
        Advertencia advertencia = (Advertencia) o;
        if (advertencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), advertencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Advertencia{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data='" + getData() + "'" +
            ", dataAdvertencia='" + getDataAdvertencia() + "'" +
            ", horaAdvertencia='" + getHoraAdvertencia() + "'" +
            ", resumo='" + getResumo() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
