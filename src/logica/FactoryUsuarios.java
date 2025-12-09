package logica;

public class FactoryUsuarios {

	public static Usuario crearUsuario(String[] p) {
		String nombreUsuario = p[0];
		String contraseña = p[1];
		String rol = p[2];

		if (p.length > 4) {
			rol = "estudiante";
		}

		Usuario u = null;
		switch (rol.toLowerCase()) {
		case "admin":
			u = new Administrador(nombreUsuario, contraseña, rol);
			break;
		case "coordinador":
			u = new Coordinador(nombreUsuario, contraseña, rol, p[3]);
			break;
		case "estudiante":
			String rut = p[0];
			String nombre = p[1];
			String carrera = p[2];
			String semestre = p[3];
			String correo = p[4];
			contraseña = p[5];
			u = new Estudiante(correo, contraseña, rut, nombre, carrera, semestre);
			break;
		}
		;
		return u;
	}

}
