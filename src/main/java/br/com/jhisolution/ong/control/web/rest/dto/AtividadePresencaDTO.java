package br.com.jhisolution.ong.control.web.rest.dto;

import java.io.Serializable;
import java.util.Arrays;

public class AtividadePresencaDTO implements Serializable {
	private static final long serialVersionUID = 2042164940683662296L;
	private Long id;
	private String apelido;
	private String nome;
	private String login;
	private byte[] foto;
	
	public AtividadePresencaDTO() {
		super();
	}
	public AtividadePresencaDTO(Long id, String apelido, String nome, String login, byte[] foto) {
		super();
		this.id = id;
		this.apelido = apelido;
		this.nome = nome;
		this.login = login;
		this.foto = foto;
	}
	
	public AtividadePresencaDTO(Long id, String apelido, String nome, String login) {
		super();
		this.id = id;
		this.apelido = apelido;
		this.nome = nome;
		this.login = login;
	}
	
	public static AtividadePresencaDTO getInstance() {
		return new AtividadePresencaDTO();
	}
	
	public static AtividadePresencaDTO getInstance(Long id, String apelido, String nome, String login, byte[] foto) {
		return new AtividadePresencaDTO(id, apelido, nome, login, foto);
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apelido == null) ? 0 : apelido.hashCode());
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		AtividadePresencaDTO other = (AtividadePresencaDTO) obj;
		if (apelido == null) {
			if (other.apelido != null)
				return false;
		} else if (!apelido.equals(other.apelido))
			return false;
		if (!Arrays.equals(foto, other.foto))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
