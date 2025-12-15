package p1;

import java.util.ArrayList;

public class Usuario {
	private int id;
	private String nombre;
	private String correo;
	private ArrayList<Mensaje> mensajes;
	
	public Usuario(int id, String nombre, String correo) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.mensajes = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void addMensaje(Mensaje mensaje) {
		this.mensajes.add(mensaje);
	}
	
	public int getNumMensajes() {
		return mensajes.size();
	}
	
	public void mostrar() {
		System.out.println(this.id + ". " + this.nombre);
	}
	

	
}
