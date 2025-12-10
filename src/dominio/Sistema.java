package dominio;

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
	
	void modificarCuentas(String nombre);
	
	void eliminarCuentas(String nombre);
	
	void resetContraseña(String nombre, String nuevaContra);

	
	
	
	
	// Funciones para el menu Coordinador
	
	
	
	// Funciones para el menu Estudiante
	
	String[] getInformacionEstudiante(String correo);

	double[] getPromediosEstudiante(String correo);
}
