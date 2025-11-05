package Taller3;

public interface Sistema {

	void crearUsuarios(String[] p);

	void guardarProyectos(String[] p);

	void guardarTareas(String[] p);

	Usuario login(String username, String password);

	String verProyectosDisponibles();


}
