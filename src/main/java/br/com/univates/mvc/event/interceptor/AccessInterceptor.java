package br.com.univates.mvc.event.interceptor;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.univates.mvc.event.model.entity.Acesso;
import br.com.univates.mvc.event.model.repository.AcessoRepository;

/**
 * @author Arthur
 */
@SuppressWarnings("deprecation")
public class AccessInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AcessoRepository acessoRepo;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String username = request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null;		
		request.setAttribute("acesso", new Acesso(request.getRequestURI(), LocalDateTime.now(), username));

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Acesso acesso = (Acesso) request.getAttribute("acesso");
		acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));
		
		acessoRepo.save(acesso);				
	}

}
