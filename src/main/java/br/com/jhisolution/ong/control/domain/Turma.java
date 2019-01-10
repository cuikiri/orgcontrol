package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Turma.
 */
@Entity
@Table(name = "turma")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Column(name = "ano", nullable = false)
    private Integer ano;

    @OneToMany(mappedBy = "turma")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Diario> diarios = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private Ensino ensino;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PeriodoDuracao periodoDuracao;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PeriodoSemana periodoSemana;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TipoCurso tipoCurso;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Curso curso;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Modulo modulo;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "turma_horario_materia",
               joinColumns = @JoinColumn(name = "turmas_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "horario_materias_id", referencedColumnName = "id"))
    private Set<HorarioMateria> horarioMaterias = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "turma_periodo_atividade",
               joinColumns = @JoinColumn(name = "turmas_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "periodo_atividades_id", referencedColumnName = "id"))
    private Set<PeriodoAtividade> periodoAtividades = new HashSet<>();

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

    public Turma nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public Turma ano(Integer ano) {
        this.ano = ano;
        return this;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Set<Diario> getDiarios() {
        return diarios;
    }

    public Turma diarios(Set<Diario> diarios) {
        this.diarios = diarios;
        return this;
    }

    public Turma addDiario(Diario diario) {
        this.diarios.add(diario);
        diario.setTurma(this);
        return this;
    }

    public Turma removeDiario(Diario diario) {
        this.diarios.remove(diario);
        diario.setTurma(null);
        return this;
    }

    public void setDiarios(Set<Diario> diarios) {
        this.diarios = diarios;
    }

    public Ensino getEnsino() {
        return ensino;
    }

    public Turma ensino(Ensino ensino) {
        this.ensino = ensino;
        return this;
    }

    public void setEnsino(Ensino ensino) {
        this.ensino = ensino;
    }

    public PeriodoDuracao getPeriodoDuracao() {
        return periodoDuracao;
    }

    public Turma periodoDuracao(PeriodoDuracao periodoDuracao) {
        this.periodoDuracao = periodoDuracao;
        return this;
    }

    public void setPeriodoDuracao(PeriodoDuracao periodoDuracao) {
        this.periodoDuracao = periodoDuracao;
    }

    public PeriodoSemana getPeriodoSemana() {
        return periodoSemana;
    }

    public Turma periodoSemana(PeriodoSemana periodoSemana) {
        this.periodoSemana = periodoSemana;
        return this;
    }

    public void setPeriodoSemana(PeriodoSemana periodoSemana) {
        this.periodoSemana = periodoSemana;
    }

    public TipoCurso getTipoCurso() {
        return tipoCurso;
    }

    public Turma tipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
        return this;
    }

    public void setTipoCurso(TipoCurso tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public Curso getCurso() {
        return curso;
    }

    public Turma curso(Curso curso) {
        this.curso = curso;
        return this;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public Turma modulo(Modulo modulo) {
        this.modulo = modulo;
        return this;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public Set<HorarioMateria> getHorarioMaterias() {
        return horarioMaterias;
    }

    public Turma horarioMaterias(Set<HorarioMateria> horarioMaterias) {
        this.horarioMaterias = horarioMaterias;
        return this;
    }

    public Turma addHorarioMateria(HorarioMateria horarioMateria) {
        this.horarioMaterias.add(horarioMateria);
        horarioMateria.getTurmas().add(this);
        return this;
    }

    public Turma removeHorarioMateria(HorarioMateria horarioMateria) {
        this.horarioMaterias.remove(horarioMateria);
        horarioMateria.getTurmas().remove(this);
        return this;
    }

    public void setHorarioMaterias(Set<HorarioMateria> horarioMaterias) {
        this.horarioMaterias = horarioMaterias;
    }

    public Set<PeriodoAtividade> getPeriodoAtividades() {
        return periodoAtividades;
    }

    public Turma periodoAtividades(Set<PeriodoAtividade> periodoAtividades) {
        this.periodoAtividades = periodoAtividades;
        return this;
    }

    public Turma addPeriodoAtividade(PeriodoAtividade periodoAtividade) {
        this.periodoAtividades.add(periodoAtividade);
        periodoAtividade.getTurmas().add(this);
        return this;
    }

    public Turma removePeriodoAtividade(PeriodoAtividade periodoAtividade) {
        this.periodoAtividades.remove(periodoAtividade);
        periodoAtividade.getTurmas().remove(this);
        return this;
    }

    public void setPeriodoAtividades(Set<PeriodoAtividade> periodoAtividades) {
        this.periodoAtividades = periodoAtividades;
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
        Turma turma = (Turma) o;
        if (turma.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), turma.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Turma{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", ano=" + getAno() +
            "}";
    }
}
