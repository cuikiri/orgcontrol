package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DadosMedico.
 */
@Entity
@Table(name = "dados_medico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DadosMedico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private DadoBiologico dadoBiologico;

    @OneToMany(mappedBy = "dadosMedico")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProblemaFisico> problemaFisicos = new HashSet<>();
    @OneToMany(mappedBy = "dadosMedico")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Vacina> vacinas = new HashSet<>();
    @OneToMany(mappedBy = "dadosMedico")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ExameMedico> exameMedicos = new HashSet<>();
    @OneToOne(mappedBy = "dadosMedico")
    @JsonIgnore
    private Colaborador colaborador;

    @OneToOne(mappedBy = "dadosMedico")
    @JsonIgnore
    private Aluno aluno;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public DadosMedico data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getObs() {
        return obs;
    }

    public DadosMedico obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public DadoBiologico getDadoBiologico() {
        return dadoBiologico;
    }

    public DadosMedico dadoBiologico(DadoBiologico dadoBiologico) {
        this.dadoBiologico = dadoBiologico;
        return this;
    }

    public void setDadoBiologico(DadoBiologico dadoBiologico) {
        this.dadoBiologico = dadoBiologico;
    }

    public Set<ProblemaFisico> getProblemaFisicos() {
        return problemaFisicos;
    }

    public DadosMedico problemaFisicos(Set<ProblemaFisico> problemaFisicos) {
        this.problemaFisicos = problemaFisicos;
        return this;
    }

    public DadosMedico addProblemaFisico(ProblemaFisico problemaFisico) {
        this.problemaFisicos.add(problemaFisico);
        problemaFisico.setDadosMedico(this);
        return this;
    }

    public DadosMedico removeProblemaFisico(ProblemaFisico problemaFisico) {
        this.problemaFisicos.remove(problemaFisico);
        problemaFisico.setDadosMedico(null);
        return this;
    }

    public void setProblemaFisicos(Set<ProblemaFisico> problemaFisicos) {
        this.problemaFisicos = problemaFisicos;
    }

    public Set<Vacina> getVacinas() {
        return vacinas;
    }

    public DadosMedico vacinas(Set<Vacina> vacinas) {
        this.vacinas = vacinas;
        return this;
    }

    public DadosMedico addVacina(Vacina vacina) {
        this.vacinas.add(vacina);
        vacina.setDadosMedico(this);
        return this;
    }

    public DadosMedico removeVacina(Vacina vacina) {
        this.vacinas.remove(vacina);
        vacina.setDadosMedico(null);
        return this;
    }

    public void setVacinas(Set<Vacina> vacinas) {
        this.vacinas = vacinas;
    }

    public Set<ExameMedico> getExameMedicos() {
        return exameMedicos;
    }

    public DadosMedico exameMedicos(Set<ExameMedico> exameMedicos) {
        this.exameMedicos = exameMedicos;
        return this;
    }

    public DadosMedico addExameMedico(ExameMedico exameMedico) {
        this.exameMedicos.add(exameMedico);
        exameMedico.setDadosMedico(this);
        return this;
    }

    public DadosMedico removeExameMedico(ExameMedico exameMedico) {
        this.exameMedicos.remove(exameMedico);
        exameMedico.setDadosMedico(null);
        return this;
    }

    public void setExameMedicos(Set<ExameMedico> exameMedicos) {
        this.exameMedicos = exameMedicos;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public DadosMedico colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public DadosMedico aluno(Aluno aluno) {
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
        DadosMedico dadosMedico = (DadosMedico) o;
        if (dadosMedico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dadosMedico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DadosMedico{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
