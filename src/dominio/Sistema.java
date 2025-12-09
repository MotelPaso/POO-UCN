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
	
	
	
	// Funciones para el menu Coordinador
	
	
	
	// Funciones para el menu Estudiante
}
