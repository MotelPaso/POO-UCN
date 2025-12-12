/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 21.661.740-1
 * ICCI 
 */

package dominio;

import java.util.ArrayList;

public interface Sistema {

	// Funciones para la creacion de objetos
	
	void crearUsuario(String[] p);

	void crearEstudiante(String[] p);

	void guardarCurso(String[] p);
	
	void guardarNotasEstudiante(String[] p);

	void guardarCertificaciones(String[] p);

	void guardarAsignaturasCertificaciones(String[] p);

	void guardarRegistrosEstudiantes(String[] p);

	
	// Inicio de sesion
	
	boolean revisarUsuario(String[] datosUsuario);

	int getNivelAcceso(String[] datosUsuario);
	
	
	// Funciones para el menu Administrador
	
	void crearCuentas(String[] datos);
	
	void modificarCuentas(String nombre, String[] nuevosDatos);
	
	int eliminarCuentaUsuario(String nombre);
	
	void resetContraseña(String nombre, String nuevaContra);
	
	int buscarCuenta(String username);

	
	
	
	
	// Funciones para el menu Coordinador
	
	
	
	// Funciones para el menu Estudiante
	
	String[] getInformacionEstudiante(String correo);

	double[] getPromediosEstudiante(String correo);

	String[][] getMalla();

	ArrayList<String> getCursados(String correo);

	ArrayList<String> getEnProceso(String correo);

	String getDatosCertificaciones(String correo);

	
}
