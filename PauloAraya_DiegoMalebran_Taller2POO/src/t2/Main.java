package t2;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	private static ArrayList<User> listaUsuarios = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException{
		Scanner s = new Scanner(System.in);
		cargarArchivos();
		mostrarMenu(s);
	}

	private static void cargarArchivos() throws FileNotFoundException{
		Scanner u = new Scanner(new File("usuarios.txt"));
		cargarUsers(u);
	}

	private static void cargarUsers(Scanner u) {
		User user = null; // quitar error en add a lista
		while(u.hasNextLine()) {
			String[] partes = u.nextLine().split(";");
			String username = partes[0];
			String contra = partes[1];
			String rol = partes[2];
			user = new User(username, contra, rol);
			listaUsuarios.add(user);
		}
	}

	private static void mostrarMenu(Scanner s) {
		String status = "";
		boolean encontrado = false;
		boolean exit = false;
		System.out.print("Bienvenido a SecureNet LTDA\r\n"
				+ "Ingrese su usuario: ");
		String user = s.nextLine();
		do {
			for(User u : listaUsuarios) {
				if (u.getUsername().equals(user)) {
					encontrado = true;
					status = u.getAdmin();
				}
			}
			
			if(!encontrado) {
				System.out.println("Usuario no encontrado, reingrese el usuario o ingrese 0 para salir.");
				user = s.nextLine();
			}
			if (user.equals("0")) {
				exit = true;
			}
			switch(status) {
			case "ADMIN":
				exit = menuAdmin(s);
				break;
			case "USER":
				exit = menuUser();
				break;
			}
		} while (!exit);
		System.out.println("Adios!");
		
	}

	private static boolean menuUser() {
		System.out.println("Encontrado!, user");
		return true;
	}

	private static boolean menuAdmin(Scanner s) {
		System.out.println("Encontrado!");
		String opcion = "";
		do {
			System.out.print("===============================\r\n"
					+ "Menu Admin:\r\n"
					+ "1. Mostrar lista completa de PCs.\r\n"
					+ "2. Agregar un PC a la lista.\r\n"
					+ "3. Eliminar un PC de la lista.\r\n"
					+ "4. Mostrar clasificacion de PCS segun riesgo.\r\n"
					+ "0. Volver al menu anterior.\r\n"
					+ "===============================\r\n"
					+ "Ingrese su opcion: ");
			opcion = s.nextLine();
			switch (opcion) {
			case "1": 
				mostrarAdminPC();
			case "2": 
				agregarPC(s);
			case "3": 
				eliminarPC(s);
			case "4":
				mostrarClasificacion();
			case "0":
				System.out.println("Volviendo al menu...");
				break;
			default:
				System.out.print("Opcion invalida, reingrese: ");;
			}
			
		} while (!opcion.equals("0"));
		return true;
		
	}

	private static void mostrarClasificacion() {
		// TODO Auto-generated method stub
		
	}

	private static void eliminarPC(Scanner s) {
		// TODO Auto-generated method stub
		
	}

	private static void agregarPC(Scanner s) {
		// TODO Auto-generated method stub
		
	}

	private static void mostrarAdminPC() {
		// TODO Auto-generated method stub
		
	}

}
