package patrones;

import clases.Regalo;

public class VisitorTiempoTotal implements Visitor {

	private int tiempoTotal = 0;
	
	@Override
	public void visitar(Regalo r) {
		int tiempo = r.getTiempoEntrega();
		tiempoTotal += tiempo;
	}
	
	public String getTiempoTotal() {
		return "El tiempo para todas las entregas son de "+ tiempoTotal+ " min";
	}

}
