package cl.edutecno.DTO.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShowAvgRatingDTO {
	
	private Integer id;
	private String showTitle;
	private String showNetwork;
	private Double avgRating;
}
