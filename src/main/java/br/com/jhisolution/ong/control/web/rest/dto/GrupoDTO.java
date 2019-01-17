package br.com.jhisolution.ong.control.web.rest.dto;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.jhisolution.ong.control.domain.Grupo;
import br.com.jhisolution.ong.control.web.rest.util.UtilDTO;

public class GrupoDTO {
    private Long id;
    private String nome;
    private ZonedDateTime dataCadastro;
    private Boolean habilitado;
    private Set<PostDTO> posts = new HashSet<>();
    private Set<PessoaDTO> pessoas = new HashSet<>();
    private UserDTO user;
    
    public GrupoDTO(){}
    
	public GrupoDTO(Long id, String nome, ZonedDateTime dataCadastro, Boolean habilitado, Set<PostDTO> posts,
			Set<PessoaDTO> pessoas, UserDTO user) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.habilitado = habilitado;
		this.posts = posts;
		this.pessoas = pessoas;
		this.user = user;
	}
	
	public static GrupoDTO getInstance() {
		return new GrupoDTO();
	}
	
	public static GrupoDTO getInstance(Grupo grupo) {
		return new GrupoDTO(grupo.getId(), grupo.getNome(), grupo.getDataCadastro(), grupo.isHabilitado(), null, null, null);
	}
	
	public static GrupoDTO getInstanceWithPessoas(Grupo grupo) {
		return new GrupoDTO(grupo.getId(), grupo.getNome(), grupo.getDataCadastro(), grupo.isHabilitado(), null, UtilDTO.setPessoaToSetPessoaDto(grupo.getPessoas()), null);
	}
	
	public static GrupoDTO getInstance(Long id, String nome, ZonedDateTime dataCadastro, Boolean habilitado, Set<PostDTO> posts,
			Set<PessoaDTO> pessoas, UserDTO user) {
		return new GrupoDTO(id, nome, dataCadastro, habilitado, posts, pessoas, user);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Set<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(Set<PostDTO> posts) {
		this.posts = posts;
	}

	public Set<PessoaDTO> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<PessoaDTO> pessoas) {
		this.pessoas = pessoas;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
    
}
