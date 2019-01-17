package br.com.jhisolution.ong.control.web.rest.dto;

import br.com.jhisolution.ong.control.domain.Foto;

public class FotoDTO {
	private Long id;
    private byte[] conteudo;
    private String conteudoContentType;
    
    public FotoDTO(){}
	public FotoDTO(Long id, byte[] conteudo, String conteudoContentType) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.conteudoContentType = conteudoContentType;
	}
	
	public static FotoDTO getInstance() {
		return new FotoDTO();
	}
	
	public static FotoDTO getInstance(Foto foto) {
		return new FotoDTO(foto.getId(), foto.getConteudo(), foto.getConteudoContentType());
	}
	
	public static FotoDTO getInstance(Long id, byte[] conteudo, String conteudoContentType) {
		return new FotoDTO(id, conteudo, conteudoContentType);
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
