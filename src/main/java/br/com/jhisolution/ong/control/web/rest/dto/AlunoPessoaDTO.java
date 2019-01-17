package br.com.jhisolution.ong.control.web.rest.dto;

public class AlunoPessoaDTO {
	private Long id;
	private String apelido;
	private String nome;
	private Long idUnidade;
	private String nomeUnidade;
	private Long idInstituicao;
	private String nomeInstituicao;
	
	// Construtores
	public AlunoPessoaDTO() {
		super();
	}
	
	public AlunoPessoaDTO(Long id, String apelido, String nome, Long idUnidade,
			String nomeUnidade, Long idInstituicao, String nomeInstituicao) {
		super();
		this.id = id;
		this.apelido = apelido;
		this.nome = nome;
		this.idUnidade = idUnidade;
		this.nomeUnidade = nomeUnidade;
		this.idInstituicao = idInstituicao;
		this.nomeInstituicao = nomeInstituicao;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getIdUnidade() {
		return idUnidade;
	}
	public void setIdUnidade(Long idUnidade) {
		this.idUnidade = idUnidade;
	}
	public String getNomeUnidade() {
		return nomeUnidade;
	}
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}
	public Long getIdInstituicao() {
		return idInstituicao;
	}
	public void setIdInstituicao(Long idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getInstituicaoUnidadeNome() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.nomeInstituicao);
		sb.append(" - ");
		sb.append(this.nomeUnidade);
		sb.append(" - ");
		sb.append(this.nome);
		return sb.toString(); 
	}
}
