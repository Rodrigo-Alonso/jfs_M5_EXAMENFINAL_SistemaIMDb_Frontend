package cl.edutecno.service;

import java.util.List;

import cl.edutecno.model.User;

public interface UserService {
	
	void update(User userDTO);
	List<User> findAll();
	User findById(Integer id);
	void delete(User userDTO);
	String signIn(String username, String password);
	String signUp(User userDTO);
}
