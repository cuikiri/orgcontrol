package br.com.jhisolution.ong.control.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Resposta.
 */
@Entity
@Table(name = "resposta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Resposta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 8192)
    @Column(name = "conteudo", length = 8192, nullable = false)
    private String conteudo;

    @Column(name = "data_cadastro")
    private ZonedDateTime dataCadastro;

    @Column(name = "habilitado")
    private Boolean habilitado;

    @ManyToOne(fetch=FetchType.LAZY)
    private Comentario comentario;
    
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

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Resposta resposta = (Resposta) o;
        if(resposta.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, resposta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Resposta{" +
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

	public Boolean getHabilitado() {
		return habilitado;
	}
}
