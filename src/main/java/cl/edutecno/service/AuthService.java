package cl.edutecno.service;

import org.springframework.http.ResponseEntity;

import cl.edutecno.DTO.UserDTO;

public interface AuthService {
	
	@SuppressWarnings("rawtypes")
	ResponseEntity signIn(String username, String password);
	@SuppressWarnings("rawtypes")
	ResponseEntity signUp(UserDTO userDTO);

}
