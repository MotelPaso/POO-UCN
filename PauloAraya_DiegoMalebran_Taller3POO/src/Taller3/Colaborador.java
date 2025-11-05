package Taller3;

import java.util.ArrayList;

public class Colaborador extends Usuario{

	private ArrayList<Tarea> tareasAsignadas;
	
	public Colaborador(String username, String contraseña, String rol) {
		super(username, contraseña, rol);
		this.tareasAsignadas = new ArrayList<>();
		// TODO Auto-generated constructor stub
	}
	
	public void addTarea(Tarea tarea) {
		this.tareasAsignadas.add(tarea);
	}

	public ArrayList<Tarea> getTareasAsignadas() {
		return tareasAsignadas;
	}
	

}