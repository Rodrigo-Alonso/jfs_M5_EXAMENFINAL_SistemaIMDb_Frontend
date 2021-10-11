package cl.edutecno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.edutecno.DTO.RoleDTO;
import cl.edutecno.DTO.UserDTO;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity signIn(String username, String password) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setPassword(password);
		//Objeto que sera enviado
		HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
		//(url, objeto a enviar, clase de respuesta)
		return restTemplate.postForEntity("http://localhost:8080/api/v1/users/signin", request, String.class);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity signUp(UserDTO userDTO) {
		userDTO.setRole(RoleDTO.ROLE_CLIENT);
		HttpEntity<UserDTO> request = new HttpEntity<>(userDTO);
		
		return restTemplate.postForEntity("http://localhost:8080/api/v1/users/signup", request, String.class);
	}

}
