package clases;

import patrones.VisitorCalcularCostos;

public class Matte extends Papel {

	public Matte(String tamaño, String tipo, String aColor) {
		super(tamaño, tipo, aColor);
		this.mlTinta = getCantidadTinta();
	}
	
	public double getCantidadTinta() {
		return switch(tamaño.toLowerCase()) {
		case "a4" -> 0.5;
		case "a3" -> 0.9;
		case "postal" -> 0.26;
		default -> 0;
		};
	}
	@Override
	public int getCantidadCartuchos() {
		return aColor ? 6 : 2;
	}
	@Override
	public void visitar(VisitorCalcularCostos v) {
		v.visitar(this);
		
	}

}
