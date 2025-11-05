package Taller3;

public class FactoryTareas {

	public static Tarea crearTarea(Proyecto proyecto, Usuario usuario, String[] p) {
		String id = p[1];
		String tipo = p[2];
		String descripcion = p[3];
		String estado = p[4];
		String complejidad = p[6];
		String fecha = p[7];
		
		Tarea t = switch(tipo.toLowerCase()) {
		case "bug" -> new Bug(proyecto, usuario, id, tipo, descripcion, estado, complejidad, fecha);
		case "feature" -> new Feature(proyecto, usuario, id, tipo, descripcion, estado, complejidad, fecha);
		case "documentacion" -> new Documentacion(proyecto, usuario, id, tipo, descripcion, estado, complejidad, fecha);
		default -> null;
		};
		return t;
		
		
		
	}
	
}
