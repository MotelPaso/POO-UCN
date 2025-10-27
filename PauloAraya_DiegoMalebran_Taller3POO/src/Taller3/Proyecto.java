package Taller3;

public class Proyecto {
	
	private String id;
	private String nombre;
	private Usuario responsable;
	public Proyecto(String id, String nombre, Usuario responsable) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.responsable = responsable;
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
	
	
	

}
