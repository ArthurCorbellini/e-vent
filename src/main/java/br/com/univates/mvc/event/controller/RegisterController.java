package br.com.univates.mvc.event.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.univates.mvc.event.controller.dto.RequestNewUserDTO;
import br.com.univates.mvc.event.model.entity.User;
import br.com.univates.mvc.event.model.repository.UserRepository;

/**
 * @author Arthur
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserRepository userRepo;	
	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping
	public String register(RequestNewUserDTO requisicao) {
		return "register";
	}
	
	@PostMapping("novo")
	public String novo(@Valid RequestNewUserDTO request, BindingResult result) {
		if(result.hasErrors()) 
			return "register";
		
		User user = request.toUser();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
//		userRepo.save(user);
		
		return "redirect:/login";	
	}
	
}
