package patrones;

import clases.Inventario;
import clases.Regalo;

public class StrategyMal implements StrategyEmpaquetado {

	@Override
	public void empaquetar(Regalo r, Inventario i) {
		r.isEmpaquetado(true);
		r.setTiempoEntrega(5);
		
	}

}
