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
 * A HorarioMateria.
 */
@Entity
@Table(name = "horario_materia")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HorarioMateria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 5)
    @Column(name = "hora_inicio", length = 5, nullable = false)
    private String horaInicio;

    @NotNull
    @Size(max = 5)
    @Column(name = "hora_fim", length = 5, nullable = false)
    private String horaFim;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private Materia materia;

    @OneToOne    @JoinColumn(unique = true)
    private DiaSemana diaSemana;

    @ManyToMany(mappedBy = "horarioMaterias")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Turma> turmas = new HashSet<>();

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

    public HorarioMateria nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public HorarioMateria horaInicio(String horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public HorarioMateria horaFim(String horaFim) {
        this.horaFim = horaFim;
        return this;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getObs() {
        return obs;
    }

    public HorarioMateria obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Materia getMateria() {
        return materia;
    }

    public HorarioMateria materia(Materia materia) {
        this.materia = materia;
        return this;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public HorarioMateria diaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
        return this;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public HorarioMateria turmas(Set<Turma> turmas) {
        this.turmas = turmas;
        return this;
    }

    public HorarioMateria addTurma(Turma turma) {
        this.turmas.add(turma);
        turma.getHorarioMaterias().add(this);
        return this;
    }

    public HorarioMateria removeTurma(Turma turma) {
        this.turmas.remove(turma);
        turma.getHorarioMaterias().remove(this);
        return this;
    }

    public void setTurmas(Set<Turma> turmas) {
        this.turmas = turmas;
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
        HorarioMateria horarioMateria = (HorarioMateria) o;
        if (horarioMateria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), horarioMateria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HorarioMateria{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFim='" + getHoraFim() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
