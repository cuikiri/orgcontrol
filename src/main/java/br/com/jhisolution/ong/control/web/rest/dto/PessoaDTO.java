package br.com.jhisolution.ong.control.web.rest.dto;

import java.time.LocalDate;

import br.com.jhisolution.ong.control.domain.Pessoa;

public class PessoaDTO {
	private Long id;
    private String nome;
    private LocalDate nascimento;
    private String rg;
    private String cpf;
    private UserDTO user;
    
    public PessoaDTO() {}
    
    public PessoaDTO(Long id, String nome, LocalDate nascimento, String rg, String cpf, UserDTO user) {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.rg = rg;
		this.cpf = cpf;
		this.user = user;
	}
	
    public static PessoaDTO getInstance() {
    	return new PessoaDTO();
    }
    
    public static PessoaDTO getInstance(Long id, String nome, LocalDate nascimento, String rg, String cpf, UserDTO user) {
    	return new PessoaDTO(id, nome, nascimento, rg, cpf, user);
    }
    
    public static PessoaDTO getInstance(Pessoa pessoa) {
    	return new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getNascimento(), pessoa.getRg(), pessoa.getCpf(), null);
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

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
    
    
}
