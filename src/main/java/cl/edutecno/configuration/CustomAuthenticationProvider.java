package cl.edutecno.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import cl.edutecno.service.AuthService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private AuthService authService;

	@SuppressWarnings("rawtypes")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Map<String, Object> principal = new HashMap<>();
		ResponseEntity tokenSignIn = authService.signIn(username, password);
		
		principal.put("username", username);
		principal.put("token", tokenSignIn.getBody());
		
		if (tokenSignIn.getStatusCode().equals(HttpStatus.OK)) {
			return new UsernamePasswordAuthenticationToken(principal, password, new ArrayList<>());
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
