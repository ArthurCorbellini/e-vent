package br.com.univates.mvc.event.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.univates.mvc.event.model.entity.Evento;
import br.com.univates.mvc.event.model.entity.User;
import br.com.univates.mvc.event.model.repository.EventoRepository;
import br.com.univates.mvc.event.model.repository.UserRepository;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private EventoRepository eventoRepo;		
	@Autowired
	private UserRepository userRepo;	
	
	@GetMapping("eventos")
	public String home(Model model) {
		model.addAttribute("eventos", eventoRepo.findAll());
		return "user/home";
	}

	@GetMapping("eventos/{selected}")
	public String selectNavBar(@PathVariable("selected") String selected, Principal loggedUser, Model model) {
		List<Evento> eventos = new ArrayList<Evento>();
		
		if (selected == null) {
			eventos = eventoRepo.findAll();
		} else {
			eventos = eventoRepo.findAllByUsernameUser(loggedUser.getName());

			if (selected.equals("inscritos")) {
				eventos = eventos.stream().filter(p -> p.getFim().after(Calendar.getInstance())).collect(Collectors.toList());
			} else if(selected.equals("finalizados")) {
				eventos = eventos.stream().filter(p -> p.getFim().before(Calendar.getInstance())).collect(Collectors.toList());
			}			
		}
		
		model.addAttribute("eventos", eventos);
		model.addAttribute("selected", selected);
		return "user/home";
	}
	
	@GetMapping(value="eventos/inscrever/{id}")
	public String inscrever(@PathVariable("id") Long id, Principal loggedUser) {		
		 Evento evento = eventoRepo.findById(id).get();
		 User user = userRepo.findById(loggedUser.getName()).get();
		 
		 evento.getUsers().add(user);
		
		 eventoRepo.save(evento);
	    	    
	    return "redirect:/user/eventos";
	}
	
	@GetMapping(value="eventos/cancelarInscricao/{id}")
	public String cancelarInscricao(@PathVariable("id") Long id, Principal loggedUser) {
		 Evento evento = eventoRepo.findById(id).get();
		 User user = userRepo.findById(loggedUser.getName()).get();
		 
		 evento.removeUser(user);
		 user.removeEvento(evento);
	    
		 eventoRepo.save(evento);
		 userRepo.save(user);
	    
	    return "redirect:/user/eventos";
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/user/home";
	}
}
