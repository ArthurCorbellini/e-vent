package br.com.univates.mvc.event.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.univates.mvc.event.model.repository.EventoRepository;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private EventoRepository eventoRepo;

	@GetMapping
	public String home(Model model, Principal loggedUser) {
		PageRequest paginacao = PageRequest.of(0, 10, Sort.by("inicio").descending());

		model.addAttribute("eventos", eventoRepo.findAll(paginacao));
		return "home";
	}

}
