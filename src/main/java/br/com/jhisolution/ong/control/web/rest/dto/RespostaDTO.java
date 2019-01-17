package br.com.jhisolution.ong.control.web.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;

import br.com.jhisolution.ong.control.domain.User;

public class RespostaDTO implements Serializable {
	private static final long serialVersionUID = -1750914019941576245L;
	private Long id;
    private String conteudo;
    private ZonedDateTime dataCadastro;
    private Boolean habilitado;
    private ComentarioDTO comentario;
    private User user;
    
    public RespostaDTO() {}
    
	public RespostaDTO(Long id, String conteudo, ZonedDateTime dataCadastro, Boolean habilitado,
			ComentarioDTO comentario, User user) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCadastro = dataCadastro;
		this.habilitado = habilitado;
		this.comentario = comentario;
		this.user = user;
	}
    
	public static RespostaDTO getInstance() {
		return new RespostaDTO();
	}
	
	public static RespostaDTO getInstance(Long id, String conteudo, ZonedDateTime dataCadastro, Boolean habilitado,
			ComentarioDTO comentario, User user) {
		return new RespostaDTO(id, conteudo, dataCadastro, habilitado, comentario, user);
	}

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

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public ComentarioDTO getComentario() {
		return comentario;
	}

	public void setComentario(ComentarioDTO comentario) {
		this.comentario = comentario;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		RespostaDTO other = (RespostaDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
