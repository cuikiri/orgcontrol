package br.com.jhisolution.ong.control.web.rest.dto;

import java.time.LocalDate;

import br.com.jhisolution.ong.control.domain.Pessoa;

public class PessoaDTO {
	private Long id;
    private String nome;
    private LocalDate nascimento;
    private UserDTO user;
    
    public PessoaDTO() {}
    
    public PessoaDTO(Long id, String nome, LocalDate nascimento, UserDTO user) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.user = user;
	}
	
    public static PessoaDTO getInstance() {
    	return new PessoaDTO();
    }
    
    public static PessoaDTO getInstance(Long id, String nome, LocalDate nascimento, UserDTO user) {
    	return new PessoaDTO(id, nome, nascimento, user);
    }
    
    public static PessoaDTO getInstance(Pessoa pessoa) {
    	return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getNascimento(), null);
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

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
    
    
}
