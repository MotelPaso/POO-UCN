package main;

public interface Sistema {

	void cargarRegalos(String[] p);
	
	String calcularTiempoTotal();

	String cambiarModo();

	int empaquetar(int numEmpaquetados);

	String getDatosNext(int actualEmpaquetados);
	
	String checkStock();

}
