package clases;

import patrones.VisitorCalcularCostos;

public class ProLuster extends Papel {

	public ProLuster(String tamaño, String tipo, String aColor) {
		super(tamaño, tipo, aColor);
		this.mlTinta = getCantidadTinta();
	}
	
	public double getCantidadTinta() {
		return switch(tamaño.toLowerCase()) {
		case "a4" -> 0.75;
		case "a3" -> 2.1;
		case "postal" -> 0.38;
		default -> 0;
		};
	}
	@Override
	public int getCantidadCartuchos() {
		return aColor ? 12 : 3;
	}
	@Override
	public void visitar(VisitorCalcularCostos v) {
		v.visitar(this);
		
	}
}
