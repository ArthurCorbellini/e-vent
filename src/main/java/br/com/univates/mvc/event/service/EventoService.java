package br.com.univates.mvc.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.univates.mvc.event.model.repository.EventoRepository;

/**
 * @author Arthur
 */
@Service
public class EventoService {

	@Autowired
	private EventoRepository eventoRepo;	

	public boolean renderBtnInscrever(Long id) {
		String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();

		return !eventoRepo.findAllByUsernameUser(loggedUser).stream().anyMatch(p -> p.getId().equals(id));
	}

}
