package br.com.jhisolution.ong.control.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    
    @Column(name = "nascimento")
    private LocalDate nascimento;

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
    
    @OneToOne(mappedBy = "pessoa", fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;
    
    @ManyToMany(mappedBy = "pessoas", fetch=FetchType.LAZY)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Grupo> grupos = new HashSet<>();
    
    @OneToMany(mappedBy = "pessoa", fetch=FetchType.LAZY)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> alunos = new HashSet<>();

    public Pessoa() {}
    
    public static Pessoa getInstance() {
    	return new Pessoa();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<Telefone> telefones) {
        this.telefones = telefones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Set<Aviso> getAvisos() {
        return avisos;
    }

    public void setAvisos(Set<Aviso> avisos) {
        this.avisos = avisos;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Aluno getAlunoMae() {
        return alunoMae;
    }

    public void setAlunoMae(Aluno aluno) {
        this.alunoMae = aluno;
    }

    public Aluno getAlunoPai() {
        return alunoPai;
    }

    public void setAlunoPai(Aluno aluno) {
        this.alunoPai = aluno;
    }

    public Aluno getAlunoIrmao() {
        return alunoIrmao;
    }

    public void setAlunoIrmao(Aluno aluno) {
        this.alunoIrmao = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pessoa pessoa = (Pessoa) o;
        if(pessoa.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            '}';
    }

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
}
