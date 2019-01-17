package br.com.jhisolution.ong.control.web.rest.dto;

import java.io.Serializable;
import java.util.Arrays;

import br.com.jhisolution.ong.control.domain.ImageIcon;

public class ImageIconDTO implements Serializable{
	private static final long serialVersionUID = -2124794313145429813L;
	private Long id;
    private byte[] conteudo;
    private String conteudoContentType;
	
    public ImageIconDTO() {
    	
    }
    public ImageIconDTO(Long id, byte[] conteudo, String conteudoContentType) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.conteudoContentType = conteudoContentType;
	}
    
    public static ImageIconDTO getInstance() {
    	return new ImageIconDTO();
    }
    
    public static ImageIconDTO getInstance(Long id, byte[] conteudo, String conteudoContentType) {
    	return new ImageIconDTO(id, conteudo, conteudoContentType);
    }
    
    public static ImageIconDTO getInstance(ImageIcon imagem) {
    	return new ImageIconDTO(imagem.getId(), imagem.getConteudo(), imagem.getConteudoContentType());
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(conteudo);
		result = prime * result + ((conteudoContentType == null) ? 0 : conteudoContentType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageIconDTO other = (ImageIconDTO) obj;
		if (!Arrays.equals(conteudo, other.conteudo))
			return false;
		if (conteudoContentType == null) {
			if (other.conteudoContentType != null)
				return false;
		} else if (!conteudoContentType.equals(other.conteudoContentType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}
