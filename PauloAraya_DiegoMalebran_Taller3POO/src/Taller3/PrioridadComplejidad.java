package Taller3;

import java.util.ArrayList;

public class PrioridadComplejidad implements PrioridadStrategy {

	@Override
	public ArrayList<Tarea> asignarPrioridad(ArrayList<Tarea> listaTareas) {
		
		ArrayList<Tarea> listaBaja = new ArrayList<>();
		ArrayList<Tarea> listaMedia = new ArrayList<>();
		ArrayList<Tarea> listaAlta = new ArrayList<>();
		
		for (Tarea tarea : listaTareas) {
			System.out.println(tarea.getComplejidad());
			switch(tarea.getComplejidad().toLowerCase()) {
			case "baja" -> listaBaja.add(tarea);
			case "media" -> listaMedia.add(tarea);
			case "alta" -> listaAlta.add(tarea);
			}
		}
		ArrayList<Tarea> nuevasTareas = new ArrayList<>();
		nuevasTareas.addAll(listaBaja);
		nuevasTareas.addAll(listaMedia);
		nuevasTareas.addAll(listaAlta);
		return nuevasTareas;
		// TODO Auto-generated method stub

	}

}
