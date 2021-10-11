package cl.edutecno.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDTO {
	
	private Integer id;
	private int rating;
	private ShowDTO show;
	private UserDTO user;
}
