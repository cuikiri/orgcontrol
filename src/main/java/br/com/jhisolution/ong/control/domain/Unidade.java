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
 * A Unidade.
 */
@Entity
@Table(name = "unidade")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Unidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @OneToOne    @JoinColumn(unique = true)
    private Endereco endereco;

    @OneToOne    @JoinColumn(unique = true)
    private TipoUnidade tipoUnidade;

    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> alunos = new HashSet<>();
    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bloco> blocos = new HashSet<>();
    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Email> emails = new HashSet<>();
    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Eleicao> eleicaos = new HashSet<>();
    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Telefone> telefones = new HashSet<>();
    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Colaborador> colaboradors = new HashSet<>();
    @OneToMany(mappedBy = "unidade")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CalendarioInstituicao> calendarioInstituicaos = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("unidades")
    private Instituicao instituicao;

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

    public Unidade nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Unidade endereco(Endereco endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoUnidade getTipoUnidade() {
        return tipoUnidade;
    }

    public Unidade tipoUnidade(TipoUnidade tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
        return this;
    }

    public void setTipoUnidade(TipoUnidade tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public Unidade alunos(Set<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Unidade addAluno(Aluno aluno) {
        this.alunos.add(aluno);
        aluno.setUnidade(this);
        return this;
    }

    public Unidade removeAluno(Aluno aluno) {
        this.alunos.remove(aluno);
        aluno.setUnidade(null);
        return this;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Set<Bloco> getBlocos() {
        return blocos;
    }

    public Unidade blocos(Set<Bloco> blocos) {
        this.blocos = blocos;
        return this;
    }

    public Unidade addBloco(Bloco bloco) {
        this.blocos.add(bloco);
        bloco.setUnidade(this);
        return this;
    }

    public Unidade removeBloco(Bloco bloco) {
        this.blocos.remove(bloco);
        bloco.setUnidade(null);
        return this;
    }

    public void setBlocos(Set<Bloco> blocos) {
        this.blocos = blocos;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public Unidade emails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    public Unidade addEmail(Email email) {
        this.emails.add(email);
        email.setUnidade(this);
        return this;
    }

    public Unidade removeEmail(Email email) {
        this.emails.remove(email);
        email.setUnidade(null);
        return this;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<Eleicao> getEleicaos() {
        return eleicaos;
    }

    public Unidade eleicaos(Set<Eleicao> eleicaos) {
        this.eleicaos = eleicaos;
        return this;
    }

    public Unidade addEleicao(Eleicao eleicao) {
        this.eleicaos.add(eleicao);
        eleicao.setUnidade(this);
        return this;
    }

    public Unidade removeEleicao(Eleicao eleicao) {
        this.eleicaos.remove(eleicao);
        eleicao.setUnidade(null);
        return this;
    }

    public void setEleicaos(Set<Eleicao> eleicaos) {
        this.eleicaos = eleicaos;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Unidade telefones(Set<Telefone> telefones) {
        this.telefones = telefones;
        return this;
    }

    public Unidade addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
        telefone.setUnidade(this);
        return this;
    }

    public Unidade removeTelefone(Telefone telefone) {
        this.telefones.remove(telefone);
        telefone.setUnidade(null);
        return this;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<Colaborador> getColaboradors() {
        return colaboradors;
    }

    public Unidade colaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
        return this;
    }

    public Unidade addColaborador(Colaborador colaborador) {
        this.colaboradors.add(colaborador);
        colaborador.setUnidade(this);
        return this;
    }

    public Unidade removeColaborador(Colaborador colaborador) {
        this.colaboradors.remove(colaborador);
        colaborador.setUnidade(null);
        return this;
    }

    public void setColaboradors(Set<Colaborador> colaboradors) {
        this.colaboradors = colaboradors;
    }

    public Set<CalendarioInstituicao> getCalendarioInstituicaos() {
        return calendarioInstituicaos;
    }

    public Unidade calendarioInstituicaos(Set<CalendarioInstituicao> calendarioInstituicaos) {
        this.calendarioInstituicaos = calendarioInstituicaos;
        return this;
    }

    public Unidade addCalendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicaos.add(calendarioInstituicao);
        calendarioInstituicao.setUnidade(this);
        return this;
    }

    public Unidade removeCalendarioInstituicao(CalendarioInstituicao calendarioInstituicao) {
        this.calendarioInstituicaos.remove(calendarioInstituicao);
        calendarioInstituicao.setUnidade(null);
        return this;
    }

    public void setCalendarioInstituicaos(Set<CalendarioInstituicao> calendarioInstituicaos) {
        this.calendarioInstituicaos = calendarioInstituicaos;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public Unidade instituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
        return this;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
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
        Unidade unidade = (Unidade) o;
        if (unidade.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), unidade.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Unidade{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
