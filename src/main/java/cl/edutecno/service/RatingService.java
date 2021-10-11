package cl.edutecno.service;

import java.util.List;

import cl.edutecno.DTO.RatingDTO;

public interface RatingService {
	
	List<RatingDTO> findAllRatingsByShowId(Integer id);
	Double avgRatingByShowId(Integer id);
}
