package br.com.jhisolution.ong.control.web.rest.dto;

import java.io.Serializable;
import java.util.Date;

public class MatriculaDTO implements Serializable {
	private static final long serialVersionUID = -7173841884443774118L;

	private Long id;

    private Date data;

    private String obs;

    private Long idAluno;
    private String nomeAluno;

    private Long idTurma;
    private String nomeTurma;
    
    // Construtores
    public MatriculaDTO() {
    	super();
    }
    
	public MatriculaDTO(Long id, Date data, String obs, Long idAluno,
			String nomeAluno, Long idTurma, String nomeTurma) {
		super();
		this.id = id;
		this.data = data;
		this.obs = obs;
		this.idAluno = idAluno;
		this.nomeAluno = nomeAluno;
		this.idTurma = idTurma;
		this.nomeTurma = nomeTurma;
	}
	
	// Fábricas
	public static MatriculaDTO getInstance() {
		return new MatriculaDTO();
	}
	public static MatriculaDTO getInstance(Long id, Date data, String obs, Long idAluno,
			String nomeAluno, Long idTurma, String nomeTurma) {
		return new MatriculaDTO(id, data, obs, idAluno, nomeAluno, idTurma, nomeTurma);
	}
	//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public Long getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Long idTurma) {
		this.idTurma = idTurma;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
}
