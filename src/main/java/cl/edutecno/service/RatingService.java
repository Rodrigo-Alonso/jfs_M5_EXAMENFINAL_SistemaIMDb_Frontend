package cl.edutecno.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import cl.edutecno.DTO.RatingDTO;

public interface RatingService {
	
	List<RatingDTO> findAllRatingsByShowId(Integer id);
	Double avgRatingByShowId(Integer id);
	@SuppressWarnings("rawtypes")
	ResponseEntity addRating(RatingDTO ratingDTO);
}
