package br.com.jhisolution.ong.control.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Post.
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 512)
    @Column(name = "titulo", length = 512, nullable = false)
    private String titulo;

    @NotNull
    @Size(max = 8192)
    @Column(name = "conteudo", length = 8192, nullable = false)
    private String conteudo;

    @Column(name = "data_cadastro")
    private ZonedDateTime dataCadastro;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    @JoinColumn(unique = true)
    private Imagem imagem;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    @JoinColumn(unique = true)
    private ImagemAvatar imagemAvatar;

    @OneToOne(cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.LAZY)
    @JoinColumn(unique = true)
    private ImageIcon imagemIcon;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comentario> comentarios = new HashSet<>();
    
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "grupo_post",
               joinColumns = @JoinColumn(name="posts_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="grupos_id", referencedColumnName="id"))
    private Set<Grupo> grupos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public ImagemAvatar getImagemAvatar() {
        return imagemAvatar;
    }

    public void setImagemAvatar(ImagemAvatar imagemAvatar) {
        this.imagemAvatar = imagemAvatar;
    }

    public ImageIcon getImagemIcon() {
        return imagemIcon;
    }

    public void setImagemIcon(ImageIcon imageIcon) {
        this.imagemIcon = imageIcon;
    }

    public Set<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        if(post.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Post{" +
            "id=" + id +
            ", titulo='" + titulo + "'" +
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

	public Boolean getHabilitado() {
		return habilitado;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}
}
