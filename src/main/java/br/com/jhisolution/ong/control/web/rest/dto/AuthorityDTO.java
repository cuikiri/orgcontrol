package br.com.jhisolution.ong.control.web.rest.dto;

import br.com.jhisolution.ong.control.domain.Authority;

public class AuthorityDTO {
	private String name;

	public AuthorityDTO() {}
	
	public AuthorityDTO(String name) {
		this.name = name;
	}
	
	public static AuthorityDTO getInstance(String name) {
		return new AuthorityDTO(name);
	}
	
	public static AuthorityDTO getInstance(Authority authority) {
		return new AuthorityDTO(authority.getName());
	}
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
