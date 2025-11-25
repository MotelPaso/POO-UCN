package logica;

public class Colaborador extends Usuario {
    private String areaCoordinacion;

    public Colaborador(String nombreUsuario, String password, String rol, String areaCoordinacion) {
        super(nombreUsuario, password, rol);
        this.areaCoordinacion = areaCoordinacion;
    }

    public String getAreaCoordinacion() {
        return areaCoordinacion;
    }

    public void setAreaCoordinacion(String areaCoordinacion) {
        this.areaCoordinacion = areaCoordinacion;
    }

}
