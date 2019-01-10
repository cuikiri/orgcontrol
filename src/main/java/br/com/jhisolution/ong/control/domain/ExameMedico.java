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
 * A ExameMedico.
 */
@Entity
@Table(name = "exame_medico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExameMedico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome_medico", length = 50, nullable = false)
    private String nomeMedico;

    @Size(max = 50)
    @Column(name = "crm_medico", length = 50)
    private String crmMedico;

    @Size(max = 50)
    @Column(name = "especialidade_medico", length = 50)
    private String especialidadeMedico;

    @NotNull
    @Column(name = "idade_paciente", nullable = false)
    private Integer idadePaciente;

    @Size(max = 1000)
    @Column(name = "obs", length = 1000)
    private String obs;

    @OneToMany(mappedBy = "exameMedico")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Biotipo> biotipos = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "exame_medico_doenca",
               joinColumns = @JoinColumn(name = "exame_medicos_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "doencas_id", referencedColumnName = "id"))
    private Set<Doenca> doencas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("exameMedicos")
    private DadosMedico dadosMedico;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public ExameMedico nomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
        return this;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getCrmMedico() {
        return crmMedico;
    }

    public ExameMedico crmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
        return this;
    }

    public void setCrmMedico(String crmMedico) {
        this.crmMedico = crmMedico;
    }

    public String getEspecialidadeMedico() {
        return especialidadeMedico;
    }

    public ExameMedico especialidadeMedico(String especialidadeMedico) {
        this.especialidadeMedico = especialidadeMedico;
        return this;
    }

    public void setEspecialidadeMedico(String especialidadeMedico) {
        this.especialidadeMedico = especialidadeMedico;
    }

    public Integer getIdadePaciente() {
        return idadePaciente;
    }

    public ExameMedico idadePaciente(Integer idadePaciente) {
        this.idadePaciente = idadePaciente;
        return this;
    }

    public void setIdadePaciente(Integer idadePaciente) {
        this.idadePaciente = idadePaciente;
    }

    public String getObs() {
        return obs;
    }

    public ExameMedico obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<Biotipo> getBiotipos() {
        return biotipos;
    }

    public ExameMedico biotipos(Set<Biotipo> biotipos) {
        this.biotipos = biotipos;
        return this;
    }

    public ExameMedico addBiotipo(Biotipo biotipo) {
        this.biotipos.add(biotipo);
        biotipo.setExameMedico(this);
        return this;
    }

    public ExameMedico removeBiotipo(Biotipo biotipo) {
        this.biotipos.remove(biotipo);
        biotipo.setExameMedico(null);
        return this;
    }

    public void setBiotipos(Set<Biotipo> biotipos) {
        this.biotipos = biotipos;
    }

    public Set<Doenca> getDoencas() {
        return doencas;
    }

    public ExameMedico doencas(Set<Doenca> doencas) {
        this.doencas = doencas;
        return this;
    }

    public ExameMedico addDoenca(Doenca doenca) {
        this.doencas.add(doenca);
        doenca.getExameMedicos().add(this);
        return this;
    }

    public ExameMedico removeDoenca(Doenca doenca) {
        this.doencas.remove(doenca);
        doenca.getExameMedicos().remove(this);
        return this;
    }

    public void setDoencas(Set<Doenca> doencas) {
        this.doencas = doencas;
    }

    public DadosMedico getDadosMedico() {
        return dadosMedico;
    }

    public ExameMedico dadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
        return this;
    }

    public void setDadosMedico(DadosMedico dadosMedico) {
        this.dadosMedico = dadosMedico;
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
        ExameMedico exameMedico = (ExameMedico) o;
        if (exameMedico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exameMedico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExameMedico{" +
            "id=" + getId() +
            ", nomeMedico='" + getNomeMedico() + "'" +
            ", crmMedico='" + getCrmMedico() + "'" +
            ", especialidadeMedico='" + getEspecialidadeMedico() + "'" +
            ", idadePaciente=" + getIdadePaciente() +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
