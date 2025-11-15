package Taller3;


public class Documentacion extends Tarea {

	public Documentacion(Proyecto proyecto, Usuario responsable, String id, String tipo, String descripcion,
			String estado, String complejidad, String fecha) {
		super(proyecto, responsable, id, tipo, descripcion, estado, complejidad, fecha);
	}
	@Override
	public void visitar(Visitor v) {
		v.visitar(this);
	}

}
