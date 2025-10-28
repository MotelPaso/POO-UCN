package Taller3;

public interface Sistema {

	void crearUsuarios(String[] p);

	void guardarProyectos(String id, String nombre, String responsable);

	void guardarTareas(String[] p);

	Usuario login(String username, String password);

	String verProyectosDisponibles();


}
