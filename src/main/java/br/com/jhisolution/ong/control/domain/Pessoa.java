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
 * A Pessoa.
 */
@Entity
@Table(name = "pessoa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "pessoa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Telefone> telefones = new HashSet<>();
    @OneToMany(mappedBy = "pessoa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Email> emails = new HashSet<>();
    @OneToMany(mappedBy = "pessoa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Endereco> enderecos = new HashSet<>();
    @OneToMany(mappedBy = "pessoa")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Documento> documentos = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pessoa_aviso",
               joinColumns = @JoinColumn(name = "pessoas_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "avisos_id", referencedColumnName = "id"))
    private Set<Aviso> avisos = new HashSet<>();

    @OneToOne(mappedBy = "pessoa")
    @JsonIgnore
    private Colaborador colaborador;

    @OneToOne(mappedBy = "pessoa")
    @JsonIgnore
    private Responsavel responsavel;

    @OneToOne(mappedBy = "pessoa")
    @JsonIgnore
    private Aluno aluno;

    @OneToOne(mappedBy = "mae")
    @JsonIgnore
    private Aluno alunoMae;

    @OneToOne(mappedBy = "pai")
    @JsonIgnore
    private Aluno alunoPai;

    @ManyToOne
    @JsonIgnoreProperties("irmaos")
    private Aluno alunoIrmao;

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

    public Pessoa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public Pessoa telefones(Set<Telefone> telefones) {
        this.telefones = telefones;
        return this;
    }

    public Pessoa addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
        telefone.setPessoa(this);
        return this;
    }

    public Pessoa removeTelefone(Telefone telefone) {
        this.telefones.remove(telefone);
        telefone.setPessoa(null);
        return this;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public Pessoa emails(Set<Email> emails) {
        this.emails = emails;
        return this;
    }

    public Pessoa addEmail(Email email) {
        this.emails.add(email);
        email.setPessoa(this);
        return this;
    }

    public Pessoa removeEmail(Email email) {
        this.emails.remove(email);
        email.setPessoa(null);
        return this;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public Pessoa enderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
        return this;
    }

    public Pessoa addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
        endereco.setPessoa(this);
        return this;
    }

    public Pessoa removeEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
        endereco.setPessoa(null);
        return this;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public Pessoa documentos(Set<Documento> documentos) {
        this.documentos = documentos;
        return this;
    }

    public Pessoa addDocumento(Documento documento) {
        this.documentos.add(documento);
        documento.setPessoa(this);
        return this;
    }

    public Pessoa removeDocumento(Documento documento) {
        this.documentos.remove(documento);
        documento.setPessoa(null);
        return this;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<Aviso> getAvisos() {
        return avisos;
    }

    public Pessoa avisos(Set<Aviso> avisos) {
        this.avisos = avisos;
        return this;
    }

    public Pessoa addAviso(Aviso aviso) {
        this.avisos.add(aviso);
        aviso.getPessoas().add(this);
        return this;
    }

    public Pessoa removeAviso(Aviso aviso) {
        this.avisos.remove(aviso);
        aviso.getPessoas().remove(this);
        return this;
    }

    public void setAvisos(Set<Aviso> avisos) {
        this.avisos = avisos;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Pessoa colaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
        return this;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public Pessoa responsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
        return this;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Pessoa aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aluno getAlunoMae() {
        return alunoMae;
    }

    public Pessoa alunoMae(Aluno aluno) {
        this.alunoMae = aluno;
        return this;
    }

    public void setAlunoMae(Aluno aluno) {
        this.alunoMae = aluno;
    }

    public Aluno getAlunoPai() {
        return alunoPai;
    }

    public Pessoa alunoPai(Aluno aluno) {
        this.alunoPai = aluno;
        return this;
    }

    public void setAlunoPai(Aluno aluno) {
        this.alunoPai = aluno;
    }

    public Aluno getAlunoIrmao() {
        return alunoIrmao;
    }

    public Pessoa alunoIrmao(Aluno aluno) {
        this.alunoIrmao = aluno;
        return this;
    }

    public void setAlunoIrmao(Aluno aluno) {
        this.alunoIrmao = aluno;
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
        Pessoa pessoa = (Pessoa) o;
        if (pessoa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pessoa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pessoa{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            "}";
    }
}
