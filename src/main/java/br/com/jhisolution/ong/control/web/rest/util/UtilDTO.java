package br.com.jhisolution.ong.control.web.rest.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import br.com.jhisolution.ong.control.domain.Authority;
import br.com.jhisolution.ong.control.domain.Comentario;
import br.com.jhisolution.ong.control.domain.Grupo;
import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.domain.Post;
import br.com.jhisolution.ong.control.domain.Resposta;
import br.com.jhisolution.ong.control.web.rest.dto.AuthorityDTO;
import br.com.jhisolution.ong.control.web.rest.dto.ComentarioDTO;
import br.com.jhisolution.ong.control.web.rest.dto.GrupoDTO;
import br.com.jhisolution.ong.control.web.rest.dto.PessoaDTO;
import br.com.jhisolution.ong.control.web.rest.dto.PostDTO;
import br.com.jhisolution.ong.control.web.rest.dto.RespostaDTO;

public class UtilDTO {
	
	public static Set<AuthorityDTO> setAuthorityToSetAuthorityDto(Set<Authority> authoritys) {
		
		Set<AuthorityDTO> authoritysDto = new HashSet<>();
		
		if (Optional.ofNullable(authoritys).isPresent()) {
			authoritys.forEach(authority -> {
				authoritysDto.add(AuthorityDTO.getInstance(authority));
			});
		}
		
		return authoritysDto;
	}
	
	public static Set<PessoaDTO> setPessoaToSetPessoaDto(Set<Pessoa> pessoas) {
		
		Set<PessoaDTO> pessoasDto = new HashSet<>();
		
		if (Optional.ofNullable(pessoas).isPresent()) {
			pessoas.forEach(pessoa -> {
				pessoasDto.add(PessoaDTO.getInstance(pessoa));
			});
		}
		
		return pessoasDto;
	}
	
	public static Set<PostDTO> setPostToSetPostDto(Set<Post> posts) {
		
		Set<PostDTO> postsDto = new HashSet<>();
		
		if (Optional.ofNullable(posts).isPresent()) {
			posts.forEach(post -> {
				postsDto.add(PostDTO.getInstance(post));
			});
		}
		
		return postsDto;
	}
	
	public static List<PostDTO> listPostToListPostDto(List<Post> posts) {
		
		List<PostDTO> postsDto = new ArrayList<>();
		
		if (Optional.ofNullable(posts).isPresent()) {
			posts.forEach(post -> {
				postsDto.add(PostDTO.getInstance(post));
			});
		}
		
		return postsDto;
	}
	
	
	public static Set<ComentarioDTO> setComentarioToComentarioDto(Set<Comentario> comentarios) {
		
		Set<ComentarioDTO> comentariosDTO = new HashSet<>();
		
		if (comentarios != null) {
			for (Comentario comentario : comentarios) {
				comentariosDTO.add(comentarioToComentarioDTO(comentario));
			}
		}
		
		return comentariosDTO;
		
	}
	
	public static Set<RespostaDTO> setRespostaToSetRespostaDto(Set<Resposta> respostas) {
		
		Set<RespostaDTO> respostasDTO = new HashSet<>();
		
		if (respostas != null) {
			for (Resposta resposta : respostas) {
				respostasDTO.add(respostaToRespostaDTO(resposta));
			}
		}
		
		return respostasDTO;
	}
	
	public static List<RespostaDTO> setRespostaToListRespostaDto(List<Resposta> respostas) {
		
		List<RespostaDTO> respostasDTO = new ArrayList<>();
		
		if (respostas != null) {
			for (Resposta resposta : respostas) {
				respostasDTO.add(respostaToRespostaDTO(resposta));
			}
		}
		
		return respostasDTO;
	}
	
	public static Set<RespostaDTO> setRespostaToSetRespostaDto(List<Resposta> respostas) {
		
		Set<RespostaDTO> respostasDTO = new HashSet<>();
		
		if (respostas != null) {
			for (Resposta resposta : respostas) {
				respostasDTO.add(respostaToRespostaDTO(resposta));
			}
		}
		
		return respostasDTO;
	}
	
	public static List<GrupoDTO> setGrupoToSetGrupoDto(List<Grupo> grupos) {
		
		List<GrupoDTO> gruposDTO = new ArrayList<>();
		
		if (grupos != null) {
			for (Grupo grupo : grupos) {
				gruposDTO.add(GrupoDTO.getInstance(grupo));
			}
		}
		
		return gruposDTO;
	}
	
	public static RespostaDTO respostaToRespostaDTO(Resposta resposta) {
		
		RespostaDTO respostaDTO = null;
		
		if (resposta != null) {
			respostaDTO = RespostaDTO.getInstance(resposta.getId(), resposta.getConteudo(), resposta.getDataCadastro(), resposta.getHabilitado(), null, resposta.getUser());
		}
		
		return respostaDTO;
	}

	public static ComentarioDTO comentarioToComentarioDTO(Comentario comentario) {
		
		ComentarioDTO comentarioDTO = null;
		
		if (comentario != null) {
			comentarioDTO = ComentarioDTO.getInstance(comentario.getId(), comentario.getConteudo(), comentario.getDataCadastro(), comentario.isHabilitado() , null, comentario.getUser()); 
		}
		
		return comentarioDTO;
	}
	
	public static ComentarioDTO comentarioToComentarioDTOWithRespostas(Comentario comentario) {
		
		ComentarioDTO comentarioDTO = null;
		
		if (comentario != null) {
			Set<RespostaDTO> respostas = new HashSet<>();
			if (comentario.getRespostas() != null) {
				for (Resposta resposta : comentario.getRespostas()) {
					respostas.add(respostaToRespostaDTO(resposta));
				}
			}
			comentarioDTO = ComentarioDTO.getInstance(comentario.getId(), comentario.getConteudo(), comentario.getDataCadastro(), comentario.isHabilitado() , respostas, comentario.getUser()); 
		}
		
		return comentarioDTO;
	}
}
