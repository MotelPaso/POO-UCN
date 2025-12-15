package main;

import java.util.ArrayList;

import clases.*;
import patrones.*;

public class SistemaImpl implements Sistema {
	
	private static Sistema instancia = null;
	private ArrayList<Papel> listaImpresiones = new ArrayList<>();
	
	public static Sistema getInstancia() {
		if (instancia == null) {
			instancia = new SistemaImpl();
		}
		return instancia;
	}

	@Override
	public void imprimirNuevoPapel(String size, String tipo, String color) {
		Papel p = FactoryPapel.crearNuevoPapel(size, tipo, color);
		listaImpresiones.add(p);
	}

	@Override
	public String revisarCostosImpresion() {
		VisitorCalcularCostos v = new VisitorCalcularCostos();
		
		for (Papel p: listaImpresiones) {
			p.visitar(v);
		}
		return v.getDatosImpresion();
		
	}
	


}
