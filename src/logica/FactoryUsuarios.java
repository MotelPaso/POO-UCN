package logica;

public class FactoryUsuarios {

    public static Usuario crearUsuario(String[] p) {
        String nombreUsuario = p[0];
        String contraseña = p[1];
        String rol = p[2];

        Usuario u = switch (rol.toLowerCase()) {
            case "admin" ->
                new Administrador(nombreUsuario, contraseña, rol);
            case "coordinador" ->
                new Coordinador(nombreUsuario, contraseña, rol, p[3]);
            default -> null;
        };

        return u;
    }

}
