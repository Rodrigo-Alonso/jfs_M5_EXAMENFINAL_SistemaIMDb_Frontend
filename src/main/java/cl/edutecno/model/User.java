package cl.edutecno.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	private Integer id;
	private String username;
	private String email;
	private String password;
	private String passwordConfirmation;
	private Role role;
}
