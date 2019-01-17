package br.com.jhisolution.ong.control.web.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import br.com.jhisolution.ong.control.domain.Post;
import br.com.jhisolution.ong.control.domain.User;

public class PostDTO implements Serializable{
	private static final long serialVersionUID = 7800543163602339363L;
	private Long id;
    private String titulo;
    private String conteudo;
    private ZonedDateTime dataCadastro;
    private Boolean habilitado;
    private ImagemDTO imagem;
    private ImagemAvatarDTO imagemAvatar;
    private ImageIconDTO imagemIcon;
    private UserDTO user;
    private Set<ComentarioDTO> comentarios = new HashSet<>();
    
    public PostDTO(){
    	
    }
	public PostDTO(Long id, String titulo, String conteudo, ZonedDateTime dataCadastro, Boolean habilitado,
			ImagemDTO imagem, ImagemAvatarDTO imagemAvatar, ImageIconDTO imagemIcon, Set<ComentarioDTO> comentarios, UserDTO user) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.conteudo = conteudo;
		this.dataCadastro = dataCadastro;
		this.habilitado = habilitado;
		this.imagem = imagem;
		this.imagemAvatar = imagemAvatar;
		this.imagemIcon = imagemIcon;
		this.comentarios = comentarios;
		this.user = user;
	}
    
	public static PostDTO getInstance() {
    	return new PostDTO();
    }
	
    public static PostDTO getInstance(Long id, String titulo, String conteudo, ZonedDateTime dataCadastro, Boolean habilitado,
			ImagemDTO imagem, ImagemAvatarDTO imagemAvatar, ImageIconDTO imagemIcon, Set<ComentarioDTO> comentarios, UserDTO user) {
    	return new PostDTO(id, titulo, conteudo, dataCadastro, habilitado, imagem, imagemAvatar, imagemIcon, comentarios, user);
    }
    
    public static PostDTO getInstance(Post post) {
    	return new PostDTO(post.getId(), post.getTitulo(), post.getConteudo(), post.getDataCadastro(), post.getHabilitado(), null, null, null, null, null);
    }
    
    public static PostDTO getInstanceWithImagemAndUserFotoAvatar(Post post) {
    	
    	ImagemDTO imagemDto = ImagemDTO.getInstance(post.getImagem());
        
        UserDTO userDto = UserDTO.getInstanceWithFotoAvatar(
        		Optional.ofNullable(post.getUser()).orElse(User.getInstance()));
        
    	return new PostDTO(post.getId(), post.getTitulo(), post.getConteudo(), post.getDataCadastro(), post.getHabilitado(), imagemDto, null, null, null, userDto);
    }
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	public ImagemDTO getImagem() {
		return imagem;
	}
	public void setImagem(ImagemDTO imagem) {
		this.imagem = imagem;
	}
	public ImagemAvatarDTO getImagemAvatar() {
		return imagemAvatar;
	}
	public void setImagemAvatar(ImagemAvatarDTO imagemAvatar) {
		this.imagemAvatar = imagemAvatar;
	}
	public ImageIconDTO getImagemIcon() {
		return imagemIcon;
	}
	public void setImagemIcon(ImageIconDTO imagemIcon) {
		this.imagemIcon = imagemIcon;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conteudo == null) ? 0 : conteudo.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((habilitado == null) ? 0 : habilitado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result + ((imagemAvatar == null) ? 0 : imagemAvatar.hashCode());
		result = prime * result + ((imagemIcon == null) ? 0 : imagemIcon.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		PostDTO other = (PostDTO) obj;
		if (conteudo == null) {
			if (other.conteudo != null)
				return false;
		} else if (!conteudo.equals(other.conteudo))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (habilitado == null) {
			if (other.habilitado != null)
				return false;
		} else if (!habilitado.equals(other.habilitado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagem == null) {
			if (other.imagem != null)
				return false;
		} else if (!imagem.equals(other.imagem))
			return false;
		if (imagemAvatar == null) {
			if (other.imagemAvatar != null)
				return false;
		} else if (!imagemAvatar.equals(other.imagemAvatar))
			return false;
		if (imagemIcon == null) {
			if (other.imagemIcon != null)
				return false;
		} else if (!imagemIcon.equals(other.imagemIcon))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	public Set<ComentarioDTO> getComentarios() {
		return comentarios;
	}
	public void setComentarios(Set<ComentarioDTO> comentarios) {
		this.comentarios = comentarios;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
}
