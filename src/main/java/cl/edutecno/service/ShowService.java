package cl.edutecno.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import cl.edutecno.DTO.ShowDTO;
import cl.edutecno.DTO.custom.ShowAvgRatingDTO;

public interface ShowService {
	
	List<ShowDTO> findAllShows();
	List<ShowAvgRatingDTO> findAllShowsAndAvgRating();
	@SuppressWarnings("rawtypes")
	ResponseEntity addShow(ShowDTO showDTO);
	@SuppressWarnings("rawtypes")
	ResponseEntity deleteShow(ShowDTO showDTO);
	ShowDTO findShowById(Integer id);
	@SuppressWarnings("rawtypes")
	ResponseEntity updateShow(ShowDTO showDTO);
}
