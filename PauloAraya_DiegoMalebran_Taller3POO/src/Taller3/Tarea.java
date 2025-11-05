package Taller3;

public abstract class Tarea {
	
	protected Proyecto proyecto;
	protected Usuario responsable;
	protected String id, tipo, descripcion, estado, complejidad, fecha;
	protected Tarea(Proyecto proyecto, Usuario responsable, String id, String tipo, String descripcion, String estado,
			String complejidad, String fecha2) {
		this.proyecto = proyecto;
		this.responsable = responsable;
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.estado = estado;
		this.complejidad = complejidad;
		this.fecha = fecha2;
	}
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getComplejidad() {
		return complejidad;
	}
	public void setComplejidad(String complejidad) {
		this.complejidad = complejidad;
	}
	@Override
	public String toString() {
		// TODO: Cambiar toString para algo mas bonito
		return "Proyecto:" + proyecto.getNombre() +", id=" + id + ", tipo=" + tipo + ", descripcion=" + descripcion + ", estado=" + estado + ", complejidad=" + complejidad + ", fecha="+ fecha + "]";
	}
	
	
	
	
	

	
	
}
