package br.com.univates.mvc.event.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.univates.mvc.event.model.type.UserRole;

/**
 * @author Arthur
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	private String username;
	private String password;
	private boolean enabled;
    @Enumerated(EnumType.STRING)
	private UserRole role;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_eventos",
				uniqueConstraints  = @UniqueConstraint(columnNames = {"username_user", "id_evento"}),
				joinColumns        = @JoinColumn(name = "username_user", referencedColumnName = "username"),
				inverseJoinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"))
	private List<Evento> eventos;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	public void addEvento(Evento e) {
		this.eventos.add(e);
		e.getUsers().add(this);
	}
	
	public void removeEvento(Evento e) {
		this.eventos.removeIf(p -> p.getId().equals(e.getId()));
		e.getUsers().removeIf(p -> p.getUsername().equals(this.getUsername()));	
	}

}
