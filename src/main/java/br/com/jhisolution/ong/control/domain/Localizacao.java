package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Localizacao.
 */
@Entity
@Table(name = "localizacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Localizacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1000)
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "altitude")
    private Float altitude;

    @OneToOne(mappedBy = "localizacao")
    @JsonIgnore
    private Endereco endereco;

    @OneToOne(mappedBy = "localizacao")
    @JsonIgnore
    private Bloco bloco;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Localizacao descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Localizacao latitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Localizacao longitude(Float longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getAltitude() {
        return altitude;
    }

    public Localizacao altitude(Float altitude) {
        this.altitude = altitude;
        return this;
    }

    public void setAltitude(Float altitude) {
        this.altitude = altitude;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Localizacao endereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public Localizacao bloco(Bloco bloco) {
        this.bloco = bloco;
        return this;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
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
        Localizacao localizacao = (Localizacao) o;
        if (localizacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), localizacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Localizacao{" +
            "id=" + getId() +
            ", descricao='" + getDescricao() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            ", altitude=" + getAltitude() +
            "}";
    }
}
