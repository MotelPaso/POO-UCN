package Taller3;

import java.util.ArrayList;

public class PrioridadImpacto implements PrioridadStrategy {
	
	

	@Override
	public ArrayList<Tarea> asignarPrioridad(ArrayList<Tarea> listaTareas) {
		
		ArrayList<Tarea> listaBug = new ArrayList<>();
		ArrayList<Tarea> listaFeature = new ArrayList<>();
		ArrayList<Tarea> listaDocu = new ArrayList<>();
		
		for (Tarea tarea : listaTareas) {
			if (tarea instanceof Bug) listaBug.add(tarea);
			if (tarea instanceof Feature) listaFeature.add(tarea);
			if (tarea instanceof Documentacion) listaDocu.add(tarea);
		}
		ArrayList<Tarea> nuevasTareas = new ArrayList<>();
		nuevasTareas.addAll(listaBug);
		nuevasTareas.addAll(listaFeature);
		nuevasTareas.addAll(listaDocu);
		return nuevasTareas;
	}

}
