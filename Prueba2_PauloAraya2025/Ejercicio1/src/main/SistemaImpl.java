package main;

import java.util.ArrayList;

import clases.Inventario;
import clases.Regalo;
import patrones.*;

public class SistemaImpl implements Sistema {

	private ArrayList<Regalo> listaRegalos = new ArrayList<>();
	private Regalo regaloActual = null;
	private boolean quedanRegalos = true;
	private Inventario i = Inventario.getInstancia();
	private StrategyEmpaquetado modo = new StrategyBien();
	private static Sistema instancia = null;
	
	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new SistemaImpl();
		}
		return instancia;
	}

	@Override
	public void cargarRegalos(String[] p) {
		String objeto = p[0];
		double superficie = Double.parseDouble(p[1]);
		String nombre = p[2];
		
		Regalo r = new Regalo(objeto, superficie, nombre);
		listaRegalos.add(r); // cargamos todos los regalos a la lista de los regalos
	}

	@Override
	public String calcularTiempoTotal() {
		// esta parte no es muy eficiente
		VisitorTiempoTotal v = new VisitorTiempoTotal();
		for (Regalo regalo : listaRegalos) { // calculamos todos los regalos cada vez
			if(regalo.enPaquete()) {				
				regalo.visitar(v); 
				// pero es util por si queremos cambiar 
				// el estado de algun regalo en alguna actualizacion del programa
			}
		}
		return v.getTiempoTotal();
		
	}

	@Override
	public String cambiarModo() { // toggle
		if (modo instanceof StrategyBien) {
			modo = new StrategyMal();
			return "Modo Actual: Mal portado.";
		} else {
			modo = new StrategyBien();
			return "Modo Actual: Bien portado.";
		}
		
	}

	@Override
	public int empaquetar(int numEmpaquetados) {
		if (quedanRegalos) {			
			modo.empaquetar(regaloActual, i);
		}
		if (regaloActual.enPaquete()) {
			return numEmpaquetados += 1;
		} else {
			return numEmpaquetados; // si no se ha podido empaquetar, mantenemos el numero igual
		}
		
		
	}

	@Override
	public String getDatosNext(int posicionEnCola) {
		if (posicionEnCola >= listaRegalos.size() - 1){
			quedanRegalos = false;
			return "se te han acabao los regalos tio"; // si estamos en el ultimo regalo
		}
		
		regaloActual = listaRegalos.get(posicionEnCola);
		return regaloActual.toString();
	}

	@Override
	public String checkStock() {
		return quedanRegalos ?  "" : "se han acabao los regalos" ;
	}
	
}
