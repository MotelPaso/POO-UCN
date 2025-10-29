package Taller3;

import java.time.LocalDate;
import java.util.*;

public class SistemaImpl implements Sistema{
	
	private static SistemaImpl Instancia;
	private static ArrayList<Usuario>usuarios = new ArrayList<>();
	private static ArrayList<Proyecto>proyectos = new ArrayList<>();
	private static ArrayList<Tarea>tareas = new ArrayList<>();

	public SistemaImpl() {}

	public static SistemaImpl getInstancia() {
		
		if (Instancia == null) {
			
			Instancia = new SistemaImpl();
		}
		
		return Instancia;
	}

	@Override
	public void crearUsuarios(String[] p) {
		// TODO: Refactor
		// es necesario el else?, se supone que todos los usuarios vienen del archivo y van a estar correctos
		Usuario u = factoryUsuarios.crear(p);
		if (u != null) { 
	        usuarios.add(u);
	    } else {
	        System.err.println("Error Crítico: No se pudo crear el usuario a partir de la línea: " + Arrays.toString(p));
	    }
		
	}

	@Override
	public void guardarProyectos(String id, String nombre, String responsable) {

		Usuario usuario = buscarResponsable(responsable);
		// si el usuario no esta en la lista de usuarios, podria causar problemas
		// probablemente sea mejor guardarlo como String
		Proyecto p = new Proyecto(id, nombre, usuario);
		proyectos.add(p);
	}

	private Usuario buscarResponsable(String responsable) {
		// Si el usuario existe, devolverlo
		// si no, return null
		// TODO: Refactor
	    for (Usuario u : usuarios) {
	        if(u.getUsername().equalsIgnoreCase(responsable)) { 
	            return u;
	        }
	    }
	    return null;
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
		for (Proyecto p : proyectos) {
			if(p.getId().equalsIgnoreCase(proyecto)) {
				return p;
			}
		}
		return null;
	}

	@Override
	public Usuario login(String username, String password) {
		// TODO: hashing
		Usuario usuario = buscarResponsable(username); 
	    
	    if (usuario != null && usuario.getContraseña().equals(password)) {
	        return usuario;
	    }
	    
	    return null;
	}

	@Override
	public String verProyectosDisponibles() {
	    String resultado = "--- Lista de Proyectos ---\n"; 

	    for (Proyecto p : proyectos) {
	        resultado += "ID: " + p.getId() +" | Nombre: " + p.getNombre();
	        
	        // si tiene un responsable
	        if (p.getResponsable() != null) {
	            resultado += " | Responsable: " + p.getResponsable().getUsername();
	        }
	        resultado += "\n";
	    }
	    
	    return resultado; 
	}


}

