package clases;

public class Inventario {
	
	private int cantPapelRegalo;
	private int cantEtiquetas;
	private int cantPapelCraft;
	private static Inventario instancia = null;
	
	public static Inventario getInstancia() {
		if (instancia == null) {
			instancia = new Inventario();
		}
		return instancia;
	}
	
	
	public Inventario() {
		this.cantPapelRegalo = 20;
		this.cantEtiquetas = 15;
		this.cantPapelCraft = Integer.MAX_VALUE; // simulamos infinito
	}
	public int getCantPapelRegalo() {
		return cantPapelRegalo;
	}
	public int getCantEtiquetas() {
		return cantEtiquetas;
	}
	public int getCantPapelCraft() {
		return cantPapelCraft;
	}
	public void setCantPapelRegalo(int cantPapelRegalo) {
		this.cantPapelRegalo = cantPapelRegalo;
	}
	public void setCantEtiquetas(int cantEtiquetas) {
		this.cantEtiquetas = cantEtiquetas;
	}


	public String mostrar() {
		return "PapelRegalo: " + cantPapelRegalo + " m2 | Etiquetas: " + cantEtiquetas;
	}
	
	
	
	
	

}
