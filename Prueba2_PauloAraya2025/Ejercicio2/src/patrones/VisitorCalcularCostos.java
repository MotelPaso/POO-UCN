package patrones;

import clases.*;

public class VisitorCalcularCostos implements Visitor {

	// G, P, PRo
	private int[] preciosBase = { 59_990, 79_990, 35_990 };
	private double precioTotal = 0;
	private int costoTinta = 54_990;
	private int cantFotos = 0;
	// B, C

	@Override
	public void visitar(Glossy p) {
		
		double precio = 0;
		switch (p.getTamaño().toLowerCase()) {
		case "a4": { 
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[0] / 50) + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		case "a3": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[1] / 50) + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		case "postal": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[2] / 50) + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		}
		precioTotal = precio;
		cantFotos++;
	}

	@Override
	public void visitar(Matte p) {
		
		double precio = 0;
		switch (p.getTamaño().toLowerCase()) {
		case "a4": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[0] / 50) * 1.20 + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		case "a3": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[1] / 50) * 1.20 + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		case "postal": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[2] / 50) * 1.20 + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		}
		precioTotal = precio;
		cantFotos++;

	}

	@Override
	public void visitar(ProLuster p) {
		double precio = 0;
		switch (p.getTamaño().toLowerCase()) {
		case "a4": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[0] / 50) * 1.60 + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		case "a3": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[1] / 50) * 1.60 + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		case "postal": {
			double mlTinta = p.getCantidadTinta();
			precio = (preciosBase[2] / 50) * 1.60 + ((costoTinta / 80) * (mlTinta * p.getCantidadCartuchos()));
		}
		}
		precioTotal = precio;
		cantFotos++;
	}
	
	public String getDatosImpresion() {
		return "<html>Se imprimieron " + cantFotos + " fotos<br> El costoTotal de las impresiones fue de $" + precioTotal + " CLP</html>";
	}

}
