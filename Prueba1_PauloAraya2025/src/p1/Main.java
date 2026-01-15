package p1;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	// Listas globales de datos
	private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private static ArrayList<Chat> listaChats = new ArrayList<>();
	private static ArrayList<Mensaje> listaMensajes = new ArrayList<>();
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(System.in);
		cargarArchivos();
		mostrarMenu(s);
	}

	private static void mostrarMenu(Scanner s) {
		String opcion = "";
		do {
			System.out.print("=========================\r\n"
					+ "Bienvenido al chat!\r\n"
					+ "1. Ver chats disponibles\r\n"
					+ "2. Mostrar usuarios con mayor actividad\r\n"
					+ "3. Mostrar las horas de mayor actividad\r\n"
					+ "4. Editar chat\r\n"
					+ "5. Enviar mensaje a varios chats\r\n"
					+ "0. Salir\r\n"
					+ "Elija su opcion: ");
			opcion = s.nextLine();
			switch(opcion) {
			case "1":
				/* Muestra los chats disponibles
			     * id. nombre
				 * usuarios
				 * mensajes
				 */
				mostrarChats(s);
				break;
			case "2":
				/* Muestra los 3 users con mayor actividad en los chats 
				 */
				mostrarUsersMayor();
				break;
			case "3":
				/* Muestra las horas con mayor actividad
				 */
				mostrarHorasMayor();
				break;
			case "4":
				/* Edita un chat especifico
				 */
				editarChat(s);
				break;
			case "5":
				/* Envia un mensaje semi-global, enviandolo a todos los chats elegidos.
				 */
				mensajeGlobal(s);
				break;
			case "0":
				break;
			default:
				System.out.println("Opcion invalida...");
			}
		} while (!opcion.equals("0"));
		System.out.println("Adios!");
	}

	private static void mensajeGlobal(Scanner s) {
		System.out.println("Ingrese el mensaje a enviar!");
		String mensaje = s.nextLine();
		int opcion = 0;
		ArrayList<Integer> chats = new ArrayList<Integer>();
		while (opcion != -1) {
			// es mejor parsear la linea entera a buscar el siguiente int con s.nextInt()
			try {
				System.out.println("Id de los chats a reenviar, ingrese -1 para terminar: ");
				opcion = Integer.parseInt(s.nextLine());
				chats.add(opcion);
			} catch (Exception e){
				throw new NumberFormatException("Tiene que ser un id valido...");
			}
			
		}
		for(Chat chat: listaChats) {
			chat.addMensaje(new Mensaje(300, chat.getId(), "hoy", 1000, mensaje));
		}
	}
	private static void editarChat(Scanner s) {
		System.out.println("Listado de chats: ");
		for(Chat chat: listaChats) {
			System.out.println(chat.mostrar());
		}
		System.out.println("Elija el chat que desea editar (id): ");
		String opcion = s.nextLine();
		try {			
			boolean cambio = listaChats.get(Integer.parseInt(opcion)).editar(s);
			if (cambio) {
				System.out.print("Ingrese el id del usuario que desea agregar! :");
				int nuevoUsuario = Integer.parseInt(s.nextLine());
				boolean added = false;
				for(Usuario user: listaUsuarios) {
					if (nuevoUsuario == user.getId()){
						listaChats.get(Integer.parseInt(opcion)).addUser(user);
						added = true;
						break;
					}
				}
				if (!added) {
					System.out.println("Usuario no encontrado...");
				}
			}
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Tiene que estar dentro de los chat disponibles");
		} catch (NumberFormatException e) {
			System.out.println("Tienes que poner su id, no su titulo...");
		}
	}

	private static void mostrarHorasMayor() {
		System.out.println("Horas de mayor actividad: ");
		ArrayList<Integer> contadores = new ArrayList<>();
		ArrayList<Integer> horasDisp = new ArrayList<>();
		ArrayList<Integer> horasTotales = new ArrayList<>();
		for (Mensaje mensajes : listaMensajes) {
			String[] fecha = mensajes.getFecha().split(":");
			int hora = Integer.parseInt(fecha[0]);
			if (!horasDisp.contains(hora)){					
				horasDisp.add(hora);
				contadores.add(1);
			}
			horasTotales.add(hora);
		}
		for (Integer hora: horasTotales) {
			int index = horasDisp.indexOf(hora);
			contadores.set(index, contadores.get(index) + 1);
		}
		int[] horas = {0,0,0};
		int[] mayores = {0,0,0};
		for (int i = 0; i < 3; i++) {
			int mayor = 0;
			int hora = 0;
			for (Integer cont : contadores) {
				if (cont > mayor){
					mayor = cont;
					hora = horasDisp.get(contadores.indexOf(mayor));
				}
			}
			mayores[i] = mayor;
			horas[i] = hora;
			int indiceMayor = contadores.indexOf(mayor);
			int indiceHora = horasDisp.indexOf(hora);
			contadores.remove(indiceMayor);
			horasDisp.remove(indiceHora);
		}
		for (int i = 0; i < 3; i++) {
			System.out.println(horas[i] + " " + mayores[i]);
		}
	}

	private static void mostrarUsersMayor() {
		System.out.println("Top de mensajes: ");
		ArrayList<Integer> mayores = new ArrayList<>();
		ArrayList<Usuario> usuarios = new ArrayList<>();
		for (int i = 0 ; i < 3; i++) {			
			int mayor = 0;
			Usuario[] temp = new Usuario[2];
			for(Usuario user : listaUsuarios) {
				if (user.getNumMensajes() > mayor && !mayores.contains(user.getNumMensajes())) {
					mayor = user.getNumMensajes();
					temp[0] = user;
				}
			}
			mayores.add(mayor);
			usuarios.add(temp[0]);
		}
		for (int j = 0; j < mayores.size(); j++) {			
			System.out.println(j+1 + ". " + usuarios.get(j).getNombre() + " con: " + mayores.get(j) + " mensajes"  );
		}
	}

	private static void mostrarChats(Scanner s) {
		System.out.println("Lista de chats!");
		for (Chat chat : listaChats) {
			System.out.println(chat.mostrar());
		}
		System.out.println("Eliga el chat que desea ver! ");
		String opcion = s.nextLine();
		boolean encontrado = false;
		for (Chat chat : listaChats) {
			if (opcion.equals(String.valueOf(chat.getId()))) {
				encontrado = chat.mostrarDetalles();
			}
		}
		if (!encontrado) {
			System.out.println("Chat no encontrado / no existe...");
		}
		
	}

	private static void cargarArchivos() throws FileNotFoundException {
		Scanner u = new Scanner(new File("users.txt"));
		cargarUsuarios(u);
		u.close();
		Scanner c = new Scanner(new File("chats.txt"));
		cargarChats(c);
		c.close();
		Scanner m = new Scanner(new File("msj.txt"));
		cargarMensajes(m);
		
	}

	private static void cargarMensajes(Scanner m) {
		while(m.hasNextLine()) {
			String[] partes = m.nextLine().split(",");
			int idMensaje = Integer.parseInt(partes[0]);
			int idChat = Integer.parseInt(partes[1]);
			String fecha = partes[2];
			int idRemitente = Integer.parseInt(partes[3]);
			String contenido = partes[4];
			
			Mensaje mensaje = new Mensaje(idMensaje, idChat, fecha, idRemitente, contenido);
			for (Usuario user: listaUsuarios) {
				if (user.getId() == idRemitente){
					user.addMensaje(mensaje);
				}
			}
			for (Chat chat : listaChats) {
				if (chat.getId() == idChat) {
					chat.addMensaje(mensaje);
				}
			}
			listaMensajes.add(mensaje);
		}
	}

	private static void cargarChats(Scanner c) {
		while(c.hasNextLine()) {
			String[] partes = c.nextLine().split(",");
			int id = Integer.parseInt(partes[0]);
			String titulo = partes[1];
			ArrayList<Integer> IdUsers = new ArrayList<>();
			ArrayList<Usuario> usersInChat = new ArrayList<>();
			Chat chat = new Chat(id, titulo);
			for (int i = 2; i < partes.length; i++) {
				IdUsers.add(Integer.parseInt(partes[i]));				
			}
			for (Usuario user : listaUsuarios) {
				if (IdUsers.contains(user.getId())){
					usersInChat.add(user);
				}
			}
			chat.addUsers(usersInChat);
			listaChats.add(chat);
		}
		
	}

	private static void cargarUsuarios(Scanner u) {
		while(u.hasNextLine()) {
			String[] partes = u.nextLine().split(",");
			Usuario user = new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]);
			listaUsuarios.add(user);
		}
		
	}

}
