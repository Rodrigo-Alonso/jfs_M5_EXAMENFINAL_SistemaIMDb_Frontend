package cl.edutecno.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	
	ROLE_ADMIN("Admin"), ROLE_CLIENT("Client");
	
	private final String displayValue;
}
