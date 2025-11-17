package Taller3;

import java.util.ArrayList;

public class PrioridadFecha implements PrioridadStrategy {

	@Override
	public ArrayList<Tarea> asignarPrioridad(ArrayList<Tarea> listaTareas) {
		
		ArrayList<Tarea> nuevasTareas = new ArrayList<>();
		
		for (int i = 0; i < listaTareas.size(); i++) {
			int[] fechaMasAntigua = {9999,99,99}; // tarea muy nueva
			Tarea tareaMasAntigua = null;
			for (Tarea tarea : listaTareas) {
				if (nuevasTareas.contains(tarea)){
					continue;
				}
				int[] fechaActual = getFechaInt(tarea.getFecha().split("-"));
				
				for (int j = 0; j < fechaActual.length; j++) {
					if (fechaActual[j] < fechaMasAntigua[j]) {
						fechaMasAntigua = fechaActual;
						tareaMasAntigua = tarea;
						break;
					}
				}
			}
			nuevasTareas.add(tareaMasAntigua);
		}
		
		return nuevasTareas;
	}
	private int[] getFechaInt(String[] fechaString) {
		int[] fechaInt = new int[3];
		for (int i = 0; i < fechaString.length; i++) {
			fechaInt[i] = Integer.parseInt(fechaString[i]);
		}
		return fechaInt;
	}

}
