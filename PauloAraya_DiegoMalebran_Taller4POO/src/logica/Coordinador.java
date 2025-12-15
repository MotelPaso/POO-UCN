/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 21.661.740-1
 * ICCI 
 */



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
