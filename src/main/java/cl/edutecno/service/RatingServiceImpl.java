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

import cl.edutecno.DTO.RatingDTO;
import cl.edutecno.DTO.UserDTO;

@Service
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<RatingDTO> findAllRatingsByShowId(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		headers.setBearerAuth(principal.get("token").toString());

		HttpEntity<UserDTO> request = new HttpEntity<>(headers);
		
		String url = "http://localhost:8080/api/v1/shows/ratingsByShow/" + id;
		
		ResponseEntity<List<RatingDTO>> response = restTemplate.exchange(
				url, 
				HttpMethod.GET, 
				request, 
				new ParameterizedTypeReference<List<RatingDTO>>(){});
		
		return response.getBody();
	}

	@Override
	public Double avgRatingByShowId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
