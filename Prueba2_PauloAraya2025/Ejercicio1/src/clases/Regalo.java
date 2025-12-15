package clases;

import patrones.*;

public class Regalo {
	private String objeto;
	private double superficie;
	private String nombre;
	private boolean empaquetado;
	private int tiempoEntrega;
	public Regalo(String objeto, double superficie, String nombre) {
		this.objeto = objeto;
		this.superficie = superficie;
		this.nombre = nombre;
		this.empaquetado = false; // todavia no lo empaquetamos
	}
	public void isEmpaquetado(boolean b) {
		empaquetado = b;
	}
	public void setTiempoEntrega(int i) {
		tiempoEntrega = i;
		
	}
	public int getTiempoEntrega() {
		return tiempoEntrega;
	}
	
	public void visitar(Visitor v) {
		v.visitar(this);
	}
	public boolean enPaquete() {
		return empaquetado;
	}
	@Override
	public String toString() {
		return objeto + " para " + nombre + ", Superficie: " + superficie;
	}
	
	
	
	
	
	
}
