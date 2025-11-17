package Taller3;

public class FactoryUsuarios {

	public static Usuario crear(String[] p) {

		String username = p[0].strip();
		String contra = p[1].strip();
		String rol = p[2].strip();

		Usuario user = switch (rol.toLowerCase()) {

		case "administrador" -> new Administrador(username, contra, rol);
		case "colaborador" -> new Colaborador(username, contra, rol);
		default -> null;
		};
		return user;
		
	}
}