/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 
 * ICCI 
 */

package dominio;

public class App {
	public static void main(String[] args) {
		Sistema sistema = SistemaImpl.getInstancia();
		sistema.cargarArchivos();
	}
}
