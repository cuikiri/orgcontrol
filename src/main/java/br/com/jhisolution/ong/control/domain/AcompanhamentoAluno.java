package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A AcompanhamentoAluno.
 */
@Entity
@Table(name = "acompanhamento_aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AcompanhamentoAluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @NotNull
    @Size(max = 50)
    @Column(name = "numero", length = 50, nullable = false)
    private String numero;

    @Size(max = 50)
    @Column(name = "obs", length = 50)
    private String obs;

    @OneToOne    @JoinColumn(unique = true)
    private FotoAcompanhamentoAluno fotoAcompanhamentoAluno;

    @OneToOne    @JoinColumn(unique = true)
    private TipoAcompanhamentoAluno tipoAcompanhamentoAluno;

    @ManyToOne
    @JsonIgnoreProperties("acompanhamentoAlunos")
    private Aluno aluno;

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

    public AcompanhamentoAluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public AcompanhamentoAluno numero(String numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObs() {
        return obs;
    }

    public AcompanhamentoAluno obs(String obs) {
        this.obs = obs;
        return this;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public FotoAcompanhamentoAluno getFotoAcompanhamentoAluno() {
        return fotoAcompanhamentoAluno;
    }

    public AcompanhamentoAluno fotoAcompanhamentoAluno(FotoAcompanhamentoAluno fotoAcompanhamentoAluno) {
        this.fotoAcompanhamentoAluno = fotoAcompanhamentoAluno;
        return this;
    }

    public void setFotoAcompanhamentoAluno(FotoAcompanhamentoAluno fotoAcompanhamentoAluno) {
        this.fotoAcompanhamentoAluno = fotoAcompanhamentoAluno;
    }

    public TipoAcompanhamentoAluno getTipoAcompanhamentoAluno() {
        return tipoAcompanhamentoAluno;
    }

    public AcompanhamentoAluno tipoAcompanhamentoAluno(TipoAcompanhamentoAluno tipoAcompanhamentoAluno) {
        this.tipoAcompanhamentoAluno = tipoAcompanhamentoAluno;
        return this;
    }

    public void setTipoAcompanhamentoAluno(TipoAcompanhamentoAluno tipoAcompanhamentoAluno) {
        this.tipoAcompanhamentoAluno = tipoAcompanhamentoAluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public AcompanhamentoAluno aluno(Aluno aluno) {
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
        AcompanhamentoAluno acompanhamentoAluno = (AcompanhamentoAluno) o;
        if (acompanhamentoAluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), acompanhamentoAluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AcompanhamentoAluno{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", numero='" + getNumero() + "'" +
            ", obs='" + getObs() + "'" +
            "}";
    }
}
