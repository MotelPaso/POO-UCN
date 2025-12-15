package p1;

import java.util.ArrayList;
import java.util.Scanner;

public class Chat {
	private int id;
	private String titulo;
	private ArrayList<Usuario> usersInChat;
	private ArrayList<Mensaje> mensajes;
	public Chat(int id, String titulo) {
		this.id = id;
		this.titulo = titulo;
		this.mensajes = new ArrayList<Mensaje>();
		this.usersInChat = new ArrayList<Usuario>();
	}
	
	public int getId() {
		return id;
	}

	public ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}
	public void addUser(Usuario user) {
		if (!usersInChat.contains(user)) {			
			this.usersInChat.add(user);
		}
		else {
			System.out.println("usuario ya esta en el chat.");
		}
	}
	public void addUsers(ArrayList<Usuario> user) {
		this.usersInChat = user;
	}
	public void addMensaje(Mensaje mensaje) {
		this.mensajes.add(mensaje);
	}
	
	public String mostrar() {
		return this.id + ". " + this.titulo;
	}
	
	public boolean mostrarDetalles() {
		System.out.println(this.id + ". " + this.titulo);
		System.out.println("Usuarios en este chat: ");
		for (Usuario user: usersInChat) {
			user.mostrar();
		}
		System.out.println("Mensajes");
		String nombre = "";
		for(Mensaje msj : mensajes) {
			int id = msj.getUserId();
			for (Usuario user: usersInChat) {
				if (id == user.getId()) {
					nombre = user.getNombre();
				}
			}
			msj.mostrar(nombre);
		}
		return true;
	}
	
	public boolean editar(Scanner s) {
		System.out.println("Editando " + this.titulo + "!");
		System.out.print("Elija su nuevo titulo: ");
		String titulo = s.nextLine();
		this.titulo = titulo;
		System.out.println("Nuevo titulo = " + this.titulo);
		System.out.println("Desea agregar a un usuario? (Y/N) ");
		String respuesta = s.nextLine();
		if (respuesta.equals("Y")){
			return true;
		}
		else {
			return false;
			
		}
	}
	
}
