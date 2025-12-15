package patrones;

import clases.*;

public class FactoryPapel {

	public static Papel crearNuevoPapel(String size, String tipo, String color) {
		
		Papel p = switch (tipo.toLowerCase()) {
		case "glossy" -> new Glossy(size, tipo, color);
		case "matte" -> new Matte(size, tipo, color);
		case "pro luster" -> new ProLuster(size, tipo, color);
		default -> null;
		};
		
		return p;
	}

}
