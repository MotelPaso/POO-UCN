/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 
 * ICCI 
 */

package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws FileNotFoundException {
		Sistema sistema = SistemaImpl.getInstancia();
		cargarArchivos(sistema);
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
