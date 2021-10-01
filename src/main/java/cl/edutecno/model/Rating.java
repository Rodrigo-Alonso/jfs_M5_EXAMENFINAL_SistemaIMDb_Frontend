package cl.edutecno.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rating {
	
	private Integer id;
	private int rating;
	private Show show;
	private User user;
}
