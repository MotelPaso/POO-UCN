package clases;

import patrones.VisitorCalcularCostos;

public abstract class Papel {
	protected String tamaño;
	protected String tipo;
	protected boolean aColor;
	protected double mlTinta;
	protected boolean impreso;
	public Papel(String tamaño, String tipo, String aColor) {
		super();
		this.tamaño = tamaño;
		this.tipo = tipo;
		this.aColor = aColor.toLowerCase().equals("color"); // si es a color, marcara True.
		this.impreso = false;
	}
	public String getTamaño() {
		return tamaño;
	}
	public void setTamaño(String tamaño) {
		this.tamaño = tamaño;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isaColor() {
		return aColor;
	}
	public void setaColor(boolean aColor) {
		this.aColor = aColor;
	}
	public boolean isImpreso() {
		return impreso;
	}
	public void setImpreso(boolean impreso) {
		this.impreso = impreso;
	}
	public abstract void visitar(VisitorCalcularCostos v);
	
	public abstract int getCantidadCartuchos();
	
	
	
}
