package cl.edutecno.service;

import cl.edutecno.DTO.UserDTO;

public interface UserService {
	
	UserDTO findUserByUsername(String username);
}
