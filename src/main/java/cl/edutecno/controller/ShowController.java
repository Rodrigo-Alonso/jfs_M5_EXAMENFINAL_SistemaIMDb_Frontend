package cl.edutecno.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cl.edutecno.DTO.RatingDTO;
import cl.edutecno.DTO.ShowDTO;
import cl.edutecno.DTO.UserDTO;
import cl.edutecno.service.RatingService;
import cl.edutecno.service.ShowService;
import cl.edutecno.service.UserService;

@Controller
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unchecked")
	@GetMapping({"/home"})
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		
		//Obtiene credenciales de la sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//Setear datos de Rol e Id en Map
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		if (principal.get("idUser") == null) {
			UserDTO userDTO = userService.findUserByUsername((String) principal.get("username"));
			principal.put("role", userDTO.getRole());
			principal.put("idUser", userDTO.getId());
			principal.put("email", userDTO.getEmail());
		}
		
		//Obtener todos los datos de la tabla con una consulta
//		List<Map<Integer, List<String>>> response = showService.findShowsByAvgRating();
		
		//Obtener los datos con dos consultas
//		List<ShowDTO> showList = showService.findAllShows();
//		List<Double> ratingsList = new ArrayList<>();
//		for (ShowDTO showDTO : showList) {
//			int id = showDTO.getId();
//			ratingsList.add(showService.avgRatingsByShowId(id));
//		}
		mav.addObject("show", new ShowDTO());
		mav.addObject("showsAndRating", showService.findAllShowsAndAvgRating());
		mav.addObject("user", auth.getPrincipal());
		mav.setViewName("admin/home");
		return mav;
		
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/addShow")
	public RedirectView addShow(@ModelAttribute ShowDTO showDTO) {
		ResponseEntity response = showService.addShow(showDTO);
		if (response.getStatusCode().equals(HttpStatus.CREATED)) {
			return new RedirectView("/home");
		}else {
			return null;
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/deleteShow")
	public RedirectView deleteShow(@RequestParam Integer idShow) {
		ShowDTO showDTO = new ShowDTO();
		showDTO.setId(idShow);
		ResponseEntity response = showService.deleteShow(showDTO);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return new RedirectView("/home");
		}else {
			return null;
		}
	}
	
	@GetMapping("/editShow")
	public ModelAndView editShow(@RequestParam Integer idShow) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		mav.addObject("show", showService.findShowById(idShow));
		mav.addObject("user", auth.getPrincipal());
		mav.setViewName("admin/editShow");
		return mav;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/updateShow")
	public RedirectView updateShow(@ModelAttribute ShowDTO showDTO) {
		ResponseEntity response = showService.updateShow(showDTO);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			return new RedirectView("/home");
		}else {
			return null;
		}
	}
	
	@GetMapping("/ratingsShow")
	public ModelAndView ratingsShow(@RequestParam Integer idShow, String showTitle, String showNetwork) {
		ModelAndView mav = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ShowDTO showDTO = new ShowDTO();
		showDTO.setId(idShow);
		showDTO.setShowTitle(showTitle);
		showDTO.setShowNetwork(showNetwork);
		
		mav.addObject("rating", new RatingDTO());
		mav.addObject("show", showDTO);
		mav.addObject("ratings", ratingService.findAllRatingsByShowId(idShow));
		mav.addObject("avgRating", ratingService.avgRatingByShowId(idShow));
		mav.addObject("user", auth.getPrincipal());
		mav.setViewName("admin/ratingsShow");
		return mav;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/addRating")
	public RedirectView addRating(@ModelAttribute RatingDTO ratingDTO, RedirectAttributes attributes) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Map<String, Object> principal = (Map<String, Object>) auth.getPrincipal();
		UserDTO userDTO = new UserDTO();
		userDTO.setId(Integer.parseInt(principal.get("idUser").toString()));
		ratingDTO.setUser(userDTO);
		
		//Setear atributos para redireccionar
		attributes.addAttribute("idShow", ratingDTO.getShow().getId());
		attributes.addAttribute("showTitle", ratingDTO.getShow().getShowTitle());
		attributes.addAttribute("showNetwork", ratingDTO.getShow().getShowNetwork());
		
		ResponseEntity response = ratingService.addRating(ratingDTO);
		if (response.getStatusCode().equals(HttpStatus.CREATED)) {
			return new RedirectView("/ratingsShow");
		}else {
			return null;
		}

	}
	

}
