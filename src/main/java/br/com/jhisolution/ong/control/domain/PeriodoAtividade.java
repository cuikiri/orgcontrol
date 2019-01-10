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
 * A PeriodoAtividade.
 */
@Entity
@Table(name = "periodo_atividade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PeriodoAtividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
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
    private ParteBloco parteBloco;

    @ManyToMany(mappedBy = "periodoAtividades")
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

    public PeriodoAtividade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public PeriodoAtividade horaInicio(String horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public PeriodoAtividade horaFim(String horaFim) {
        this.horaFim = horaFim;
        return this;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getObs() {
        return obs;
    }

    public PeriodoAtividade obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public ParteBloco getParteBloco() {
        return parteBloco;
    }

    public PeriodoAtividade parteBloco(ParteBloco parteBloco) {
        this.parteBloco = parteBloco;
        return this;
    }

    public void setParteBloco(ParteBloco parteBloco) {
        this.parteBloco = parteBloco;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public PeriodoAtividade turmas(Set<Turma> turmas) {
        this.turmas = turmas;
        return this;
    }

    public PeriodoAtividade addTurma(Turma turma) {
        this.turmas.add(turma);
        turma.getPeriodoAtividades().add(this);
        return this;
    }

    public PeriodoAtividade removeTurma(Turma turma) {
        this.turmas.remove(turma);
        turma.getPeriodoAtividades().remove(this);
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
        PeriodoAtividade periodoAtividade = (PeriodoAtividade) o;
        if (periodoAtividade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), periodoAtividade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PeriodoAtividade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFim='" + getHoraFim() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
