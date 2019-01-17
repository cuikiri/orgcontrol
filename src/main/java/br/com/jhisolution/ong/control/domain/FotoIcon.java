package br.com.jhisolution.ong.control.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A FotoIcon.
 */
@Entity
@Table(name = "foto_icon")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FotoIcon implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "conteudo")
    private byte[] conteudo;

    @Column(name = "conteudo_content_type")
    private String conteudoContentType;
    
    @OneToOne(mappedBy = "fotoIcon")
    @JsonIgnore
    private User user;
    
    public FotoIcon() {
		super();
	}
	
	public FotoIcon(byte[] conteudo, String conteudoContentType, User user) {
		super();
		this.conteudo = conteudo;
		this.conteudoContentType = conteudoContentType;
		this.user = user;
	}

	public static FotoIcon getInstance() {
		return new FotoIcon();
	}
	
	public static FotoIcon getInstance(byte[] conteudo, String conteudoContentType, User user) {
		return new FotoIcon(conteudo, conteudoContentType, user);
	}
	
	public static FotoIcon getInstance(byte[] conteudo, String conteudoContentType) {
		return new FotoIcon(conteudo, conteudoContentType, null);
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(byte[] conteudo) {
        this.conteudo = conteudo;
    }

    public String getConteudoContentType() {
        return conteudoContentType;
    }

    public void setConteudoContentType(String conteudoContentType) {
        this.conteudoContentType = conteudoContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FotoIcon fotoIcon = (FotoIcon) o;
        if(fotoIcon.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, fotoIcon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FotoIcon{" +
            "id=" + id +
            ", conteudo='" + conteudo + "'" +
            ", conteudoContentType='" + conteudoContentType + "'" +
            '}';
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
