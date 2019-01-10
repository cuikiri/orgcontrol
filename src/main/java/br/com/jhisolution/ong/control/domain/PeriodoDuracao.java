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
 * A PeriodoDuracao.
 */
@Entity
@Table(name = "periodo_duracao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PeriodoDuracao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @ManyToOne
    @JsonIgnoreProperties("periodoDuracaos")
    private CalendarioInstituicao calendarioInstituicao;

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

    public PeriodoDuracao nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public PeriodoDuracao dataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
        return this;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public PeriodoDuracao dataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
        return this;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObs() {
        return obs;
    }

    public PeriodoDuracao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public CalendarioInstituicao getCalendarioInstituicao() {
        return calendarioInstituicao;
    }

    public PeriodoDuracao calendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicao = calendarioInstituicao;
        return this;
    }

    public void setCalendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicao = calendarioInstituicao;
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
        PeriodoDuracao periodoDuracao = (PeriodoDuracao) o;
        if (periodoDuracao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), periodoDuracao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PeriodoDuracao{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dataInicio='" + getDataInicio() + "'" +
            ", dataFim='" + getDataFim() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
