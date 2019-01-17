package br.com.jhisolution.ong.control.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A Comentario.
 */
@Entity
@Table(name = "comentario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Comentario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 8192)
    @Column(name = "conteudo", length = 8192, nullable = false)
    private String conteudo;

    @Column(name = "data_cadastro", nullable = true)
    private ZonedDateTime dataCadastro;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @OneToMany(mappedBy = "comentario", fetch=FetchType.LAZY)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Resposta> respostas = new HashSet<>();

    @ManyToOne(fetch=FetchType.LAZY)
    private Post post;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ZonedDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(ZonedDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Set<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(Set<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comentario comentario = (Comentario) o;
        if(comentario.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, comentario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Comentario{" +
            "id=" + id +
            ", conteudo='" + conteudo + "'" +
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
}
