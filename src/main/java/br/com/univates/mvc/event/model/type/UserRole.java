package br.com.univates.mvc.event.model.type;

/**
 * @author Arthur
 */
public enum UserRole {

	ADMIN("ADM", "Administrador"),
	PADRAO("PAD", "Padão");

	private String id;
	private String name;

	private UserRole(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
