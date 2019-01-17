package br.com.jhisolution.ong.control.web.rest.dto;

import br.com.jhisolution.ong.control.domain.FotoAvatar;

public class FotoAvatarDTO {
	private Long id;
    private byte[] conteudo;
    private String conteudoContentType;
    
    public FotoAvatarDTO(){}
	public FotoAvatarDTO(Long id, byte[] conteudo, String conteudoContentType) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.conteudoContentType = conteudoContentType;
	}
	
	public static FotoAvatarDTO getInstance() {
		return new FotoAvatarDTO();
	}
	
	public static FotoAvatarDTO getInstance(FotoAvatar foto) {
		return new FotoAvatarDTO(foto.getId(), foto.getConteudo(), foto.getConteudoContentType());
	}
	
	public static FotoAvatarDTO getInstance(Long id, byte[] conteudo, String conteudoContentType) {
		return new FotoAvatarDTO(id, conteudo, conteudoContentType);
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

}
