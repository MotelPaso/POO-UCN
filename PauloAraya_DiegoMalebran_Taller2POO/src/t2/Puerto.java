package t2;

public class Puerto {
	private int puerto;
	private String nombre;
	private String desc;
	
	public Puerto(int puerto, String nombre, String desc) {
		this.puerto = puerto;
		this.nombre = nombre;
		this.desc = desc;
	}
	public int getID() {
		return puerto;
	}
	public String getNombre() {
		return nombre;
	}

	public String getDesc() {
		return desc;
	}
	
	public void mostrar() {
		System.out.println(this.puerto + " " + this.nombre 
				+ "\nDescripcion: " + this.desc);
	}
	
	
}
