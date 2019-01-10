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
 * A CalendarioInstituicao.
 */
@Entity
@Table(name = "calendario_instituicao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CalendarioInstituicao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToMany(mappedBy = "calendarioInstituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DiaNaoUtil> diaNaoUtils = new HashSet<>();
    @OneToMany(mappedBy = "calendarioInstituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PeriodoDuracao> periodoDuracaos = new HashSet<>();
    @OneToMany(mappedBy = "calendarioInstituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PlanejamentoInstituicao> planejamentoInstituicaos = new HashSet<>();
    @OneToMany(mappedBy = "calendarioInstituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PlanejamentoEnsino> planejamentoEnsinos = new HashSet<>();
    @OneToMany(mappedBy = "calendarioInstituicao")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PlanejamentoAtividade> planejamentoAtividades = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("calendarioInstituicaos")
    private Instituicao instituicao;

    @ManyToOne
    @JsonIgnoreProperties("calendarioInstituicaos")
    private Unidade unidade;

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

    public CalendarioInstituicao nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public CalendarioInstituicao obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Set<DiaNaoUtil> getDiaNaoUtils() {
        return diaNaoUtils;
    }

    public CalendarioInstituicao diaNaoUtils(Set<DiaNaoUtil> diaNaoUtils) {
        this.diaNaoUtils = diaNaoUtils;
        return this;
    }

    public CalendarioInstituicao addDiaNaoUtil(DiaNaoUtil diaNaoUtil) {
        this.diaNaoUtils.add(diaNaoUtil);
        diaNaoUtil.setCalendarioInstituicao(this);
        return this;
    }

    public CalendarioInstituicao removeDiaNaoUtil(DiaNaoUtil diaNaoUtil) {
        this.diaNaoUtils.remove(diaNaoUtil);
        diaNaoUtil.setCalendarioInstituicao(null);
        return this;
    }

    public void setDiaNaoUtils(Set<DiaNaoUtil> diaNaoUtils) {
        this.diaNaoUtils = diaNaoUtils;
    }

    public Set<PeriodoDuracao> getPeriodoDuracaos() {
        return periodoDuracaos;
    }

    public CalendarioInstituicao periodoDuracaos(Set<PeriodoDuracao> periodoDuracaos) {
        this.periodoDuracaos = periodoDuracaos;
        return this;
    }

    public CalendarioInstituicao addPeriodoDuracao(PeriodoDuracao periodoDuracao) {
        this.periodoDuracaos.add(periodoDuracao);
        periodoDuracao.setCalendarioInstituicao(this);
        return this;
    }

    public CalendarioInstituicao removePeriodoDuracao(PeriodoDuracao periodoDuracao) {
        this.periodoDuracaos.remove(periodoDuracao);
        periodoDuracao.setCalendarioInstituicao(null);
        return this;
    }

    public void setPeriodoDuracaos(Set<PeriodoDuracao> periodoDuracaos) {
        this.periodoDuracaos = periodoDuracaos;
    }

    public Set<PlanejamentoInstituicao> getPlanejamentoInstituicaos() {
        return planejamentoInstituicaos;
    }

    public CalendarioInstituicao planejamentoInstituicaos(Set<PlanejamentoInstituicao> planejamentoInstituicaos) {
        this.planejamentoInstituicaos = planejamentoInstituicaos;
        return this;
    }

    public CalendarioInstituicao addPlanejamentoInstituicao(PlanejamentoInstituicao planejamentoInstituicao) {
        this.planejamentoInstituicaos.add(planejamentoInstituicao);
        planejamentoInstituicao.setCalendarioInstituicao(this);
        return this;
    }

    public CalendarioInstituicao removePlanejamentoInstituicao(PlanejamentoInstituicao planejamentoInstituicao) {
        this.planejamentoInstituicaos.remove(planejamentoInstituicao);
        planejamentoInstituicao.setCalendarioInstituicao(null);
        return this;
    }

    public void setPlanejamentoInstituicaos(Set<PlanejamentoInstituicao> planejamentoInstituicaos) {
        this.planejamentoInstituicaos = planejamentoInstituicaos;
    }

    public Set<PlanejamentoEnsino> getPlanejamentoEnsinos() {
        return planejamentoEnsinos;
    }

    public CalendarioInstituicao planejamentoEnsinos(Set<PlanejamentoEnsino> planejamentoEnsinos) {
        this.planejamentoEnsinos = planejamentoEnsinos;
        return this;
    }

    public CalendarioInstituicao addPlanejamentoEnsino(PlanejamentoEnsino planejamentoEnsino) {
        this.planejamentoEnsinos.add(planejamentoEnsino);
        planejamentoEnsino.setCalendarioInstituicao(this);
        return this;
    }

    public CalendarioInstituicao removePlanejamentoEnsino(PlanejamentoEnsino planejamentoEnsino) {
        this.planejamentoEnsinos.remove(planejamentoEnsino);
        planejamentoEnsino.setCalendarioInstituicao(null);
        return this;
    }

    public void setPlanejamentoEnsinos(Set<PlanejamentoEnsino> planejamentoEnsinos) {
        this.planejamentoEnsinos = planejamentoEnsinos;
    }

    public Set<PlanejamentoAtividade> getPlanejamentoAtividades() {
        return planejamentoAtividades;
    }

    public CalendarioInstituicao planejamentoAtividades(Set<PlanejamentoAtividade> planejamentoAtividades) {
        this.planejamentoAtividades = planejamentoAtividades;
        return this;
    }

    public CalendarioInstituicao addPlanejamentoAtividade(PlanejamentoAtividade planejamentoAtividade) {
        this.planejamentoAtividades.add(planejamentoAtividade);
        planejamentoAtividade.setCalendarioInstituicao(this);
        return this;
    }

    public CalendarioInstituicao removePlanejamentoAtividade(PlanejamentoAtividade planejamentoAtividade) {
        this.planejamentoAtividades.remove(planejamentoAtividade);
        planejamentoAtividade.setCalendarioInstituicao(null);
        return this;
    }

    public void setPlanejamentoAtividades(Set<PlanejamentoAtividade> planejamentoAtividades) {
        this.planejamentoAtividades = planejamentoAtividades;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public CalendarioInstituicao instituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
        return this;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public CalendarioInstituicao unidade(Unidade unidade) {
        this.unidade = unidade;
        return this;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
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
        CalendarioInstituicao calendarioInstituicao = (CalendarioInstituicao) o;
        if (calendarioInstituicao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), calendarioInstituicao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CalendarioInstituicao{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
