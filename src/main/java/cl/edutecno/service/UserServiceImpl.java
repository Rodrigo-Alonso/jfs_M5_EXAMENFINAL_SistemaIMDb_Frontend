package cl.edutecno.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.edutecno.DTO.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public UserDTO findUserByUsername(String username) {
		
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<UserDTO> request = new HttpEntity<>(headers);
		
		String url = "http://localhost:8080/api/v1/users/findUser/" + username;
		
		ResponseEntity<UserDTO> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request, 
				new ParameterizedTypeReference<UserDTO>(){});
		
		return response.getBody();
	}

}
