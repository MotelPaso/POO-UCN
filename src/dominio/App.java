/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 
 * ICCI 
 */

package dominio;

import presentacion.GUI;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
	
	private static GUI ventana = new GUI();
	
	public static void main(String[] args) throws FileNotFoundException {
		Sistema sistema = SistemaImpl.getInstancia();
		Scanner s = new Scanner(System.in);
		cargarArchivos(sistema);
		iniciarSesion(s, sistema);
	}

	private static void iniciarSesion(Scanner s, Sistema sistema) {
		boolean logged = false;
		do {
			System.out.println("===================");
			System.out.print("Ingrese su correo o nombre de usuario: ");
			String correo = s.nextLine();
			System.out.print("Ingrese su contraseña: ");
			String password = s.nextLine();
			logged = sistema.revisarUsuario(new String[] {correo, password});
			if (!logged) {
				System.out.println("Credenciales incorrectas...");
			} else {
				int acceso = sistema.getNivelAcceso(new String[] {correo, password});
				switch (acceso) {
				case 1 -> ventana.menuAdmin();
				case 2 -> ventana.menuColab();
				case 3 -> ventana.menuEstudiante();
				case 0 -> System.out.println("Ha habido un error...");
				}
			}
		} while (!logged);
	}

	

	private static void cargarArchivos(Sistema sistema) throws FileNotFoundException {
		Scanner s = new Scanner(new File("usuarios.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.crearUsuario(p);
		}
		s.close();
		s = new Scanner(new File("estudiantes.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.crearEstudiante(p);
		}
		s.close();
		s = new Scanner(new File("cursos.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.guardarCurso(p);
		}
		s.close();
		s = new Scanner(new File("notas.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.guardarNotasEstudiante(p);
		}
		s.close();
		s = new Scanner(new File("certificaciones.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.guardarCertificaciones(p);
		}
		s.close();
		s = new Scanner(new File("asignaturas_certificaciones.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.guardarAsignaturasCertificaciones(p);
		}
		s.close();
		s = new Scanner(new File("registros.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().split(";");
			sistema.guardarRegistrosEstudiantes(p);
		}
		s.close();
	}
}
