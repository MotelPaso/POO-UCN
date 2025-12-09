package logica;

public class Coordinador extends Usuario {
    private String areaCoordinacion;

    public Coordinador(String nombreUsuario, String password, String rol, String areaCoordinacion) {
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
