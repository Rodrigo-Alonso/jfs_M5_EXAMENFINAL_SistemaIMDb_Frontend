package cl.edutecno.service;


import java.util.List;
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

import cl.edutecno.DTO.ShowDTO;
import cl.edutecno.DTO.UserDTO;
import cl.edutecno.DTO.custom.ShowAvgRatingDTO;

@Service
public class ShowServiceImpl implements ShowService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShowDTO> findAllShows() {
		
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());
		//Agrega header con token al request
		HttpEntity<UserDTO> request = new HttpEntity<>(headers);
		
		ResponseEntity<List<ShowDTO>> response = restTemplate.exchange(
				"http://localhost:8080/api/v1/shows", 
				HttpMethod.GET, 
				request, 
				new ParameterizedTypeReference<List<ShowDTO>>(){});
		
		return response.getBody();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShowAvgRatingDTO> findAllShowsAndAvgRating() {
		
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<UserDTO> request = new HttpEntity<>(headers);
		
		ResponseEntity<List<ShowAvgRatingDTO>> response = restTemplate.exchange(
				"http://localhost:8080/api/v1/shows/showsAndRatings", 
				HttpMethod.GET, 
				request, 
				new ParameterizedTypeReference<List<ShowAvgRatingDTO>>(){});
		
		return response.getBody();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResponseEntity addShow(ShowDTO showDTO) {
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<ShowDTO> request = new HttpEntity<>(showDTO, headers);
		
//		ResponseEntity<String> response = restTemplate.postForEntity(
//				"http://localhost:8080/api/v1/shows", request, String.class);
		
		ResponseEntity response = restTemplate.exchange(
				"http://localhost:8080/api/v1/shows", 
				HttpMethod.POST, 
				request, 
				new ParameterizedTypeReference<String>(){});
		
		return response;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseEntity deleteShow(ShowDTO showDTO) {
		
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<ShowDTO> request = new HttpEntity<>(showDTO, headers);

		ResponseEntity response = restTemplate.exchange(
				"http://localhost:8080/api/v1/shows", 
				HttpMethod.DELETE, 
				request, 
				Void.class);
		
		//Otra alternativa
		//restTemplate.delete(url);
		
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShowDTO findShowById(Integer id) {
		
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<UserDTO> request = new HttpEntity<>(headers);
		
		String url = "http://localhost:8080/api/v1/shows/" + id;
		
		ResponseEntity<ShowDTO> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request, 
				new ParameterizedTypeReference<ShowDTO>(){});
		
		return response.getBody();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResponseEntity updateShow(ShowDTO showDTO) {
		
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<ShowDTO> request = new HttpEntity<>(showDTO, headers);

		ResponseEntity response = restTemplate.exchange(
				"http://localhost:8080/api/v1/shows", 
				HttpMethod.PUT, 
				request, 
				Void.class);
		
		return response;
	}

	

}
