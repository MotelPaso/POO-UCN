package t2;

public class Puerto {
	private int puerto;
	private String nombre;
	private String desc;
	private boolean estado;
	
	public Puerto(int puerto, String nombre, String desc) {
		this.puerto = puerto;
		this.nombre = nombre;
		this.desc = desc;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
