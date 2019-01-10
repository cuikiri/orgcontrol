package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A ObservacaoCoordenador.
 */
@Entity
@Table(name = "observacao_coordenador")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ObservacaoCoordenador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data", nullable = false)
    private LocalDate data;

    @Size(max = 1000)
    @Column(name = "nome", length = 1000)
    private String nome;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToOne(mappedBy = "observacaoCoordenador")
    @JsonIgnore
    private Diario diario;

    @ManyToOne
    @JsonIgnoreProperties("observacaoCoordenadors")
    private Bimestre bimestre;

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

    public ObservacaoCoordenador data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public ObservacaoCoordenador nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObs() {
        return obs;
    }

    public ObservacaoCoordenador obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Diario getDiario() {
        return diario;
    }

    public ObservacaoCoordenador diario(Diario diario) {
        this.diario = diario;
        return this;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Bimestre getBimestre() {
        return bimestre;
    }

    public ObservacaoCoordenador bimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
        return this;
    }

    public void setBimestre(Bimestre bimestre) {
        this.bimestre = bimestre;
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
        ObservacaoCoordenador observacaoCoordenador = (ObservacaoCoordenador) o;
        if (observacaoCoordenador.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), observacaoCoordenador.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ObservacaoCoordenador{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            ", nome='" + getNome() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
