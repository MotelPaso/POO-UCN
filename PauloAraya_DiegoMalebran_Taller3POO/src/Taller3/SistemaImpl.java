package Taller3;

import java.util.ArrayList;

public class SistemaImpl implements Sistema{
	
	private static SistemaImpl Instancia;
	private static ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private static ArrayList<Proyecto> listaProyectos = new ArrayList<>();
	private static ArrayList<Tarea> listaTareas = new ArrayList<>();

	public SistemaImpl() {}

	public static SistemaImpl getInstancia() {
		
		if (Instancia == null) {
			
			Instancia = new SistemaImpl();
		}
		
		return Instancia;
	}

	@Override
	public void crearUsuarios(String[] p) {
		Usuario u = factoryUsuarios.crear(p);
		listaUsuarios.add(u);
	}

	@Override
	public void guardarProyectos(String[] pp) {

		String id = pp[0];
		String nombre = pp[1];
		Usuario responsable = buscarUsuario(pp[2]); 
		
		if (responsable == null) { // Si el usuario no existe en nuestra base de datos
			String[] datosResponsable = {pp[2], "", "Administrador"};
			responsable = factoryUsuarios.crear(datosResponsable); 
			// Crearemos un nuevo usuario que no tocara nada por ahora
		}
		Proyecto p = new Proyecto(id, nombre, responsable);
		listaProyectos.add(p);
	}

	

	private Proyecto buscarProyecto(String proyecto) {
		for (Proyecto p : listaProyectos) {
			if(p.getId().equalsIgnoreCase(proyecto)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Usuario login(String username, String password) {
		// TODO: hashing
		Usuario usuario = buscarUsuario(username);
		
		if (usuario != null && usuario.getContraseña().equals(password)) {
			return usuario;
		}
	    return null;
	}


	@Override
	public String verProyectosDisponibles() {
	    String resultado = "--- Lista de Proyectos ---\n"; 

	    for (Proyecto p : listaProyectos) {
	        resultado += "ID: " + p.getId() +" | Nombre: " + p.getNombre();
	        
	        // si tiene un responsable
	        if (p.getResponsable() != null) {
	            resultado += " | Responsable: " + p.getResponsable();
	        }
	        resultado += "\n";
	    }
	    
	    return resultado; 
	}

	private Usuario buscarUsuario(String username) {
		for (Usuario user : listaUsuarios) {
			if (user.getUsername().equals(username)){
				return user;
			}
		}
		return null;
	}
	// funciones de tareas
	
	@Override
	public void guardarTareas(String[] datos) {

		Proyecto proyecto = buscarProyecto(datos[0]);
		Usuario responsable = buscarUsuario(datos[5]);
		
		Tarea t = FactoryTareas.crearTarea(proyecto, responsable, datos);
		listaTareas.add(t);
		responsable.addTarea(t);
		proyecto.addTarea(t);

	}

	@Override
	public String getTareasAsignadas(Colaborador colab) {
		ArrayList<Tarea> tareasAsignadas = colab.getTareasAsignadas();
		String datos = "\nTareas asignadas a " + colab.getUsername() + "\n";
		for (Tarea tarea : tareasAsignadas) {
			datos += tarea.toString() + "\n";
		}
		return datos;
	}

	@Override
	public String cambioEstadoTarea(String idTarea, String nuevoEstado, Colaborador colab) {
		// pasar numero a estado
		nuevoEstado = switch (nuevoEstado) {
		case "1" -> "Pendiente";
		case "2" -> "En progreso";
		case "3" -> "Completada";
		default -> "null";
		};
		for (Tarea tarea : listaTareas) {
			if (tarea.getId().contains(idTarea) && colab.getTareasAsignadas().contains(tarea)) {
				String estado = tarea.getEstado();
				
				if (estado.equals(nuevoEstado) || nuevoEstado.equals("null")) {
					return "No se ha podido cambiar el estado...";
				}
				tarea.setEstado(nuevoEstado);
				return "Estado cambiado existosamente \nNuevo estado: " + tarea.getEstado();
			}
		}
		return "Tarea no asignada a este usuario! No la puedes modificar";
	}

	@Override
	public String accionPorTarea(String idTarea) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String mostrarProyectosyTareas() {
		String datos = "";
		for (Proyecto proyecto : listaProyectos) {
			datos += proyecto.toString() + "\n";
			for( Tarea tarea: proyecto.getTareasProyecto()) {
				datos += tarea.toString() + "\n";
			}
		}
		return datos;
	}

}

