package br.com.jhisolution.ong.control.web.rest.dto;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.jhisolution.ong.control.domain.Authority;
import br.com.jhisolution.ong.control.domain.Foto;
import br.com.jhisolution.ong.control.domain.FotoAvatar;
import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.domain.User;
/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private boolean activated = false;
    private String langKey;
    private Set<String> authorities;
    private FotoDTO foto;
    private FotoIconDTO fotoIcon;
    private FotoAvatarDTO fotoAvatar;
    private Pessoa pessoa;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()), null, null, null);
    }

    public UserDTO(String login, String firstName, String lastName,
        String email, boolean activated, String langKey, Set<String> authorities) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }

    public UserDTO(String login, String firstName, String lastName,
        String email, boolean activated, String langKey, Set<String> authorities, 
        FotoDTO foto, FotoIconDTO fotoIcon, FotoAvatarDTO fotoAvatar) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
        this.foto = foto;
        this.fotoIcon = fotoIcon;
        this.fotoAvatar = fotoAvatar;
    }
    
    public static UserDTO getInstance() {
    	return new UserDTO();
    }
    
    public static UserDTO getInstance(User user) {
    	return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
    	        user.getEmail(), user.getActivated(), user.getLangKey(), null, 
    	        null, null, null);
    }
    
    public static UserDTO getInstanceWithFoto(User user) {
    	
    	FotoDTO fotoDto = FotoDTO.getInstance(Optional.ofNullable(user.getFoto()).orElse(Foto.getInstance()));
    	
    	return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
    	        user.getEmail(), user.getActivated(), user.getLangKey(), null, 
    	        fotoDto, null, null);
    }
    
    public static UserDTO getInstanceWithFotoAvatar(User user) {
    	
    	FotoAvatarDTO fotoDto = FotoAvatarDTO.getInstance(Optional.ofNullable(user.getFotoAvatar()).orElse(FotoAvatar.getInstance()));
    	
    	return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
    	        user.getEmail(), user.getActivated(), user.getLangKey(), null, 
    	        null, null, fotoDto);
    }
    
    public static UserDTO getInstanceWithAuthoritiesFoto(User user) {
    	
    	FotoDTO fotoDto = FotoDTO.getInstance(Optional.ofNullable(user.getFoto()).orElse(Foto.getInstance()));
    	
    	return new UserDTO(user.getLogin(), user.getFirstName(), user.getLastName(),
    	        user.getEmail(), user.getActivated(), user.getLangKey(), 
    	        user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()), 
    	        fotoDto, null, null);
    }
    
    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            "}";
    }

	public FotoDTO getFoto() {
		return foto;
	}

	public void setFoto(FotoDTO foto) {
		this.foto = foto;
	}

	public FotoIconDTO getFotoIcon() {
		return fotoIcon;
	}

	public void setFotoIcon(FotoIconDTO fotoIcon) {
		this.fotoIcon = fotoIcon;
	}

	public FotoAvatarDTO getFotoAvatar() {
		return fotoAvatar;
	}

	public void setFotoAvatar(FotoAvatarDTO fotoAvatar) {
		this.fotoAvatar = fotoAvatar;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}
}
