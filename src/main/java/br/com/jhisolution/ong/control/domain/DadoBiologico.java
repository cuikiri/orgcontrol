package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DadoBiologico.
 */
@Entity
@Table(name = "dado_biologico")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DadoBiologico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "valor", length = 50, nullable = false)
    private String valor;

    @Size(max = 100)
    @Column(name = "obs", length = 100)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private TipoadoBiologico tipoadoBiologico;

    @OneToOne(mappedBy = "dadoBiologico")
    @JsonIgnore
    private DadosMedico dadosMedico;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public DadoBiologico valor(String valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public DadoBiologico obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public TipoadoBiologico getTipoadoBiologico() {
        return tipoadoBiologico;
    }

    public DadoBiologico tipoadoBiologico(TipoadoBiologico tipoadoBiologico) {
        this.tipoadoBiologico = tipoadoBiologico;
        return this;
    }

    public void setTipoadoBiologico(TipoadoBiologico tipoadoBiologico) {
        this.tipoadoBiologico = tipoadoBiologico;
    }

    public DadosMedico getDadosMedico() {
        return dadosMedico;
    }

    public DadoBiologico dadosMedico(DadosMedico dadosMedico) {
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
        DadoBiologico dadoBiologico = (DadoBiologico) o;
        if (dadoBiologico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dadoBiologico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DadoBiologico{" +
            "id=" + getId() +
            ", valor='" + getValor() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
