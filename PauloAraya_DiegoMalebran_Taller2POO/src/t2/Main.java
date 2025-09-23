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
				exit = menuAdmin();
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

	private static boolean menuAdmin() {
		System.out.println("Encontrado!");
		return true;
		
	}

}
