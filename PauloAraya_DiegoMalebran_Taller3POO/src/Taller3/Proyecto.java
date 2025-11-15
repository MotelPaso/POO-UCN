package Taller3;
import java.util.ArrayList;
public class Proyecto {
	
	private String id;
	private String nombre;
	private Usuario responsable;
	private ArrayList<Tarea> tareasProyecto;
	public Proyecto(String id, String nombre, Usuario responsable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
		this.tareasProyecto = new ArrayList<>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	public ArrayList<Tarea> getTareasProyecto() {
		return tareasProyecto;
	}
	public void addTarea(Tarea t) {
		this.tareasProyecto.add(t);
	}
	
	@Override
	public String toString() {
		String datos = id + 
				", Proyecto " + nombre + 
				", de " + responsable.getUsername();
		return datos;
	}
	
	
	
	
	
	

}
