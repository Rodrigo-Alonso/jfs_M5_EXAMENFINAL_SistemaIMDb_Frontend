package cl.edutecno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.edutecno.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void update(User userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User userDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String signIn(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String signUp(User userDTO) {
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:8080/api/v1/users/signup", 
				HttpMethod.POST, 
				null, 
				new ParameterizedTypeReference<String>() {
				});
		return response.getBody() ;
	}

}
