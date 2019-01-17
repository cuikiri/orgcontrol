package br.com.jhisolution.ong.control.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ImagemAvatar.
 */
@Entity
@Table(name = "imagem_avatar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ImagemAvatar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "conteudo")
    private byte[] conteudo;

    @Column(name = "conteudo_content_type")
    private String conteudoContentType;
    
 // Construtores
    public ImagemAvatar() {
	}
    
	public ImagemAvatar(byte[] conteudo, String conteudoContentType) {
		super();
		this.conteudo = conteudo;
		this.conteudoContentType = conteudoContentType;
	}

	// Fábricas
	public static ImagemAvatar getInstance() {
		return new ImagemAvatar();
	}

	public static ImagemAvatar getInstance(byte[] conteudo, String conteudoContentType) {
		return new ImagemAvatar(conteudo, conteudoContentType);
	}
	
	// Getter´s and Setter´s

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
        ImagemAvatar imagemAvatar = (ImagemAvatar) o;
        if(imagemAvatar.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, imagemAvatar.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ImagemAvatar{" +
            "id=" + id +
            ", conteudo='" + conteudo + "'" +
            ", conteudoContentType='" + conteudoContentType + "'" +
            '}';
    }

}
