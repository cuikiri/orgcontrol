package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AgendaColaborador.
 */
@Entity
@Table(name = "agenda_colaborador")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AgendaColaborador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nome", length = 50)
    private String nome;

    @Size(max = 5)
    @Column(name = "hora_inicio", length = 5)
    private String horaInicio;

    @Size(max = 5)
    @Column(name = "hora_fim", length = 5)
    private String horaFim;

    @Size(max = 5)
    @Column(name = "hora_almoco_inicio", length = 5)
    private String horaAlmocoInicio;

    @Size(max = 5)
    @Column(name = "hora_almoco_fim", length = 5)
    private String horaAlmocoFim;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("")
    private DiaSemana diaSemana;

    @ManyToOne
    @JsonIgnoreProperties("agendaColaboradors")
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

    public AgendaColaborador nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public AgendaColaborador horaInicio(String horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public AgendaColaborador horaFim(String horaFim) {
        this.horaFim = horaFim;
        return this;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public String getHoraAlmocoInicio() {
        return horaAlmocoInicio;
    }

    public AgendaColaborador horaAlmocoInicio(String horaAlmocoInicio) {
        this.horaAlmocoInicio = horaAlmocoInicio;
        return this;
    }

    public void setHoraAlmocoInicio(String horaAlmocoInicio) {
        this.horaAlmocoInicio = horaAlmocoInicio;
    }

    public String getHoraAlmocoFim() {
        return horaAlmocoFim;
    }

    public AgendaColaborador horaAlmocoFim(String horaAlmocoFim) {
        this.horaAlmocoFim = horaAlmocoFim;
        return this;
    }

    public void setHoraAlmocoFim(String horaAlmocoFim) {
        this.horaAlmocoFim = horaAlmocoFim;
    }

    public String getObs() {
        return obs;
    }

    public AgendaColaborador obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public DiaSemana getDiaSemana() {
        return diaSemana;
    }

    public AgendaColaborador diaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
        return this;
    }

    public void setDiaSemana(DiaSemana diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public AgendaColaborador colaborador(Colaborador colaborador) {
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
        AgendaColaborador agendaColaborador = (AgendaColaborador) o;
        if (agendaColaborador.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), agendaColaborador.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AgendaColaborador{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFim='" + getHoraFim() + "'" +
            ", horaAlmocoInicio='" + getHoraAlmocoInicio() + "'" +
            ", horaAlmocoFim='" + getHoraAlmocoFim() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
