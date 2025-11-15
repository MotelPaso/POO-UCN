package Taller3;

public interface Sistema {

	void crearUsuarios(String[] p);

	void guardarProyectos(String[] p);

	void guardarTareas(String[] p);

	Usuario login(String username, String password);
	
	// Menu Colaborador

	String verProyectosDisponibles();

	String getTareasAsignadas(Colaborador colab);

	String cambioEstadoTarea(String idTarea, String nuevoEstado, Colaborador colab);

	String accionPorTarea(String idTarea);

	// Menu Administrador
	
	String mostrarProyectosyTareas();
	
	String mostrarProyectos();
	
	String eliminarProyecto(String id);
	
	String eliminarTarea(String id);
}
