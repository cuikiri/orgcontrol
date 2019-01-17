package br.com.jhisolution.ong.control.web.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.jhisolution.ong.control.domain.User;

public class ComentarioDTO implements Serializable{
	private static final long serialVersionUID = -2419434102049993083L;
	private Long id;
    private String conteudo;
    private ZonedDateTime dataCadastro;
    private Boolean habilitado;
    private Set<RespostaDTO> respostas = new HashSet<>();
	private User user;
	
	public ComentarioDTO(){}
	
	public ComentarioDTO(Long id, String conteudo, ZonedDateTime dataCadastro, Boolean habilitado,
			Set<RespostaDTO> respostas, User user) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCadastro = dataCadastro;
		this.habilitado = habilitado;
		this.respostas = respostas;
		this.user = user;
	}
	
	public static ComentarioDTO getInsance() {
		return new ComentarioDTO();
	}
	
	public static ComentarioDTO getInstance(Long id, String conteudo, ZonedDateTime dataCadastro, Boolean habilitado,
			Set<RespostaDTO> respostas, User user) {
		return new ComentarioDTO(id, conteudo, dataCadastro, habilitado, respostas, user);
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

	public Set<RespostaDTO> getRespostas() {
		return respostas;
	}

	public void setRespostas(Set<RespostaDTO> respostas) {
		this.respostas = respostas;
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
		ComentarioDTO other = (ComentarioDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
