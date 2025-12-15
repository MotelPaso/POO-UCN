package patrones;

import clases.*;

public class StrategyBien implements StrategyEmpaquetado {

	@Override
	public void empaquetar(Regalo r, Inventario i) {
		if (i.getCantPapelRegalo() > 0 && i.getCantEtiquetas() > 0) {
			i.setCantPapelRegalo(i.getCantPapelRegalo() - 1);
			i.setCantEtiquetas(i.getCantEtiquetas() - 1 );
			r.isEmpaquetado(true);
			r.setTiempoEntrega(1);
		}
		
		

	}

}
