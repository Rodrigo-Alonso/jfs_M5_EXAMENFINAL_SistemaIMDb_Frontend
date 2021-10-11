package cl.edutecno.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleDTO {
	
	ROLE_ADMIN("Admin"), ROLE_CLIENT("Client");
	
	private final String displayValue;
}
