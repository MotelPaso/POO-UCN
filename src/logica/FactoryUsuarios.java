package logica;

public class FactoryUsuarios {

    public static Usuario crearUsuario(String[] p) {
        String nombreUsuario = p[0];
        String contraseña = p[1];
        String rol = p[2];

        Usuario u = switch (rol.toLowerCase()) {
            case "administrador", "admin" ->
                new Administrador(nombreUsuario, contraseña, rol);
            case "colaborador", "colab", "col" ->
                new Colaborador(nombreUsuario, contraseña, rol, p[3]);
            default -> null;
        };

        return u;
    }

}
