package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
 * A DiaNaoUtil.
 */
@Entity
@Table(name = "dia_nao_util")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DiaNaoUtil implements Serializable {

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

    @OneToMany(mappedBy = "diaNaoUtil")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MotivoDiaNaoUtil> motivoDiaNaoUtils = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("diaNaoUtils")
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

    public DiaNaoUtil nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public DiaNaoUtil data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Set<MotivoDiaNaoUtil> getMotivoDiaNaoUtils() {
        return motivoDiaNaoUtils;
    }

    public DiaNaoUtil motivoDiaNaoUtils(Set<MotivoDiaNaoUtil> motivoDiaNaoUtils) {
        this.motivoDiaNaoUtils = motivoDiaNaoUtils;
        return this;
    }

    public DiaNaoUtil addMotivoDiaNaoUtil(MotivoDiaNaoUtil motivoDiaNaoUtil) {
        this.motivoDiaNaoUtils.add(motivoDiaNaoUtil);
        motivoDiaNaoUtil.setDiaNaoUtil(this);
        return this;
    }

    public DiaNaoUtil removeMotivoDiaNaoUtil(MotivoDiaNaoUtil motivoDiaNaoUtil) {
        this.motivoDiaNaoUtils.remove(motivoDiaNaoUtil);
        motivoDiaNaoUtil.setDiaNaoUtil(null);
        return this;
    }

    public void setMotivoDiaNaoUtils(Set<MotivoDiaNaoUtil> motivoDiaNaoUtils) {
        this.motivoDiaNaoUtils = motivoDiaNaoUtils;
    }

    public CalendarioInstituicao getCalendarioInstituicao() {
        return calendarioInstituicao;
    }

    public DiaNaoUtil calendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
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
        DiaNaoUtil diaNaoUtil = (DiaNaoUtil) o;
        if (diaNaoUtil.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), diaNaoUtil.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DiaNaoUtil{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
