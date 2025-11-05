package Taller3;


public abstract class Usuario {
	
	protected String Username,Contraseña,Rol;

	public Usuario(String username, String contraseña, String rol) {
		this.Username = username;
		this.Contraseña = contraseña;
		this.Rol = rol;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}

	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		Rol = rol;
	}

	protected abstract void addTarea(Tarea t);
}