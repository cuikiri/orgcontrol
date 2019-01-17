package br.com.jhisolution.ong.control.web.rest.dto;

import br.com.jhisolution.ong.control.domain.FotoIcon;

public class FotoIconDTO {
	private Long id;
    private byte[] conteudo;
    private String conteudoContentType;
    
    public FotoIconDTO(){}
	public FotoIconDTO(Long id, byte[] conteudo, String conteudoContentType) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.conteudoContentType = conteudoContentType;
	}
	
	public static FotoIconDTO getInstance() {
		return new FotoIconDTO();
	}
	
	public static FotoIconDTO getInstance(FotoIcon foto) {
		return new FotoIconDTO(foto.getId(), foto.getConteudo(), foto.getConteudoContentType());
	}
	
	public static FotoIconDTO getInstance(Long id, byte[] conteudo, String conteudoContentType) {
		return new FotoIconDTO(id, conteudo, conteudoContentType);
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
