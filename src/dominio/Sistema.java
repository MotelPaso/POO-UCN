package dominio;

public interface Sistema {

	void crearUsuario(String[] p);

	void crearEstudiante(String[] p);

	void guardarCurso(String[] p);
	
	void guardarNotasEstudiante(String[] p);

	void guardarCertificaciones(String[] p);

	void guardarAsignaturasCertificaciones(String[] p);

	void guardarRegistrosEstudiantes(String[] p);
}
