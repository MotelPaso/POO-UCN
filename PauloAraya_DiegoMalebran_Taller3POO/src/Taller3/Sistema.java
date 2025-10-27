package Taller3;

public interface Sistema {

	void crearusuarios(String[] p);

	void guardarproyectos(String id, String nombre, String responsable);

	void guardartareas(String[] p);

	Usuario login(String username, String password);

	String verProyectosDisponibles();

}
