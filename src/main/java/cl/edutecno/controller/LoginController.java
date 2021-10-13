package cl.edutecno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.edutecno.DTO.UserDTO;
import cl.edutecno.service.AuthService;

@Controller
public class LoginController {
	
	@Autowired
	private AuthService authService;
	
	@GetMapping({"/", "/login"})
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("user", new UserDTO());
		mav.setViewName("login");
		return mav;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public RedirectView signUp(@RequestParam String username, String email, String password, String passwordConfirmation) {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setEmail(email);
		userDTO.setPassword(password);
		userDTO.setPasswordConfirmation(passwordConfirmation);
		ResponseEntity response = authService.signUp(userDTO);
		if (response.getStatusCode().equals(HttpStatus.CREATED)) {
			return new RedirectView("/login");
		}else {
			return null;
		}
		
	}
	
}
