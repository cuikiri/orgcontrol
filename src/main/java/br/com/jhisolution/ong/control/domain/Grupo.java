package br.com.jhisolution.ong.control.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Grupo.
 */
@Entity
@Table(name = "grupo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Grupo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_cadastro")
    private ZonedDateTime dataCadastro;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @ManyToMany(mappedBy = "grupos", fetch=FetchType.LAZY)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(fetch=FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "grupo_pessoa",
               joinColumns = @JoinColumn(name="grupos_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="pessoas_id", referencedColumnName="id"))
    private Set<Pessoa> pessoas = new HashSet<>();
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Grupo nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ZonedDateTime getDataCadastro() {
        return dataCadastro;
    }

    public Grupo dataCadastro(ZonedDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
        return this;
    }

    public void setDataCadastro(ZonedDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean isHabilitado() {
        return habilitado;
    }

    public Grupo habilitado(Boolean habilitado) {
        this.habilitado = habilitado;
        return this;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public Grupo posts(Set<Post> posts) {
        this.posts = posts;
        return this;
    }

    public Grupo addPost(Post post) {
        this.posts.add(post);
        post.getGrupos().add(this);
        return this;
    }

    public Grupo removePost(Post post) {
        this.posts.remove(post);
        post.getGrupos().remove(this);
        return this;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public Grupo pessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
        return this;
    }

    public Grupo addPessoa(Pessoa pessoa) {
        this.pessoas.add(pessoa);
        pessoa.getGrupos().add(this);
        return this;
    }

    public Grupo removePessoa(Pessoa pessoa) {
        this.pessoas.remove(pessoa);
        pessoa.getGrupos().remove(this);
        return this;
    }

    public void setPessoas(Set<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grupo grupo = (Grupo) o;
        if (grupo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, grupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Grupo{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", dataCadastro='" + dataCadastro + "'" +
            ", habilitado='" + habilitado + "'" +
            '}';
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}
}
