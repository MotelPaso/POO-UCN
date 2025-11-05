package Taller3;

import java.time.LocalDate;
import java.util.*;

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
		String responsable = pp[2];
		
		Proyecto p = new Proyecto(id, nombre, responsable);
		listaProyectos.add(p);
	}

	@Override
	public void guardarTareas(String[] p) {
		
		// TODO: finish
		
		String proyecto= p[0];
		String id = p[1];
		String tipo = p[2];
		String descripcion = p[3];
		String estado = p[4];
		String responsable = p[5];
		String complejidad = p[6];
		LocalDate fecha = LocalDate.parse(p[7]);
				
		//Usuario usuario = buscarresponsable(responsable);
		//Proyecto proyeto = buscarproyecto(proyecto);

		//Tarea t = new Tarea(proyeto, usuario, id, tipo, descripcion, estado, complejidad, fecha);
		
		//tareas.add(t);
				
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
	public boolean login(String username, String password) {
		// TODO: hashing
		Usuario usuario = buscarUsuario(username);
	    
	    if (usuario != null ) { // si el usuario existe
	        return true;
	    }
	    
	    return false;
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

}

