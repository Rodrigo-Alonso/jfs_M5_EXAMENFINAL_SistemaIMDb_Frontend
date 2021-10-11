package cl.edutecno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public RedirectView signUp(@ModelAttribute UserDTO userDTO) {
		ResponseEntity respuestaDTO = authService.signUp(userDTO);
		respuestaDTO.getStatusCodeValue();
		
		return new RedirectView("login");
	}
	
}
