package cl.edutecno.controller;

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
import org.springframework.web.servlet.view.RedirectView;

import cl.edutecno.DTO.ShowDTO;
import cl.edutecno.service.RatingService;
import cl.edutecno.service.ShowService;

@Controller
public class ShowController {
	
	@Autowired
	private ShowService showService;
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping({"/home"})
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		
		//Obtiene credenciales de la sesion
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
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
		mav.addObject("username", auth.getPrincipal());
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
		mav.addObject("username", auth.getPrincipal());
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
		
		mav.addObject("ratings", ratingService.findAllRatingsByShowId(idShow));
		mav.addObject("username", auth.getPrincipal());
		
		return null;
		
	}
	

}
