package clases;

import patrones.VisitorCalcularCostos;

public class Glossy extends Papel {

	public Glossy(String tamaño, String tipo, String aColor) {
		super(tamaño, tipo, aColor);
		this.mlTinta = getCantidadTinta();
	}
	
	public double getCantidadTinta() {
		return switch(tamaño.toLowerCase()) {
		case "a4" -> 0.6;
		case "a3" -> 1;
		case "postal" -> 0.3;
		default -> 0;
		};
	}
	@Override
	public int getCantidadCartuchos() {
		return aColor ? 4 : 1;
	}

	@Override
	public void visitar(VisitorCalcularCostos v) {
		v.visitar(this);
		
	}

}
