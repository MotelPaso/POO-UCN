/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 21.661.740-1
 * ICCI
 */

package dominio;

import java.util.ArrayList;

public interface Sistema {

    /**
     * Crea un nuevo usuario en el sistema con los parámetros especificados.
     *
     * @param p array de String contiendo los parámetros del usuario
     *          (nombre, correo, contraseña, etc.)
     */
    void crearUsuario(String[] p);

    /**
     * Crea un nuevo estudiante en el sistema con los parámetros especificados.
     *
     * @param p array de String contiendo los parámetros del estudiante
     *          (nombre, correo, ID estudiante, programa académico, etc.)
     */
    void crearEstudiante(String[] p);

    /**
     * Guarda un curso en el sistema con los parámetros especificados.
     *
     * @param p array de String contiendo los parámetros del curso
     *          (código curso, nombre, créditos, semestre, etc.)
     */
    void guardarCurso(String[] p);

    /**
     * Guarda las notas de un estudiante en el sistema.
     *
     * @param p array de String contiendo los parámetros de notas
     *          (ID estudiante, código curso, calificación, etc.)
     */
    void guardarNotasEstudiante(String[] p);

    /**
     * Guarda una certificación en el sistema con los parámetros especificados.
     *
     * @param p array de String contiendo los parámetros de la certificación
     *          (ID certificación, nombre, descripción, etc.)
     */
    void guardarCertificaciones(String[] p);

    /**
     * Guarda la relación entre asignaturas y certificaciones.
     *
     * @param p array de String contiendo los parámetros de asociación
     *          (ID certificación, código asignatura, etc.)
     */
    void guardarAsignaturasCertificaciones(String[] p);

    /**
     * Guarda los registros académicos de estudiantes en el sistema.
     *
     * @param p array de String contiendo los parámetros de registros
     *          (ID estudiante, datos académicos, historial, etc.)
     */
    void guardarRegistrosEstudiantes(String[] p);

    // Inicio de sesion

    /**
     * Revisamos si el usuario existe en la base de datos
     *
     * @param datosUsuario array de String que contiene el nombre y la contraseña
     *                     como texto
     * @return true si existe el usuario
     *         false si no existe
     */
    boolean revisarUsuario(String[] datosUsuario);

    /**
     * Revisamos el nivel de acceso del usuario, puede ser admin, coordinador o
     * estudiante.
     *
     * @param datosUsuario array de String que contiene el nivel del usuario
     * @return 1 admin
     *         2 coordinador
     *         3 estudiante
     *         0 no encontrado
     */
    int getNivelAcceso(String[] datosUsuario);

    // Funciones para el menu Administrador

    /**
     * Creamos una nueva cuenta usando los datos de la GUI
     *
     * @param datos : datos para la creacion de usuario
     *              si es un Estudiante, hay mas datos.
     */
    void crearCuentas(String[] datos);

    /**
     * Modificamos una cuenta ya existente, se puede cambiar el nombre, contraseña y
     * el areaCoordinacion si es un coordinador
     *
     * @param nombre      userName a quien cambiar los datos
     * @param nuevosDatos datos cambiados [nombre,contraseña, area]
     */
    void modificarCuentas(String nombre, String[] nuevosDatos);

    int eliminarCuentaUsuario(String nombre);

    int buscarCuenta(String username);

    // Funciones para el menu Coordinador

    /**
     * Devuelve los datos de todos los estudiantes que tengan al menos una
     * certificacion completada
     *
     * @return String de datos de todos los estudiantes, en formato HTML para la GUI
     */
    String getEstudiantesCompletados();

    /**
     * Genera los certificacdos de todos los estudiantes que hayan completado al
     * menos una certificacion, y las carga al archivo certificado.txt
     *
     *
     *
     */
    void generarCertificados();

    // Funciones para el menu Estudiante
    /**
     * Obtiene la información académica completa de un estudiante.
     *
     * @param correo String con el correo electrónico del estudiante
     * @return array de String conteniendo la información del estudiante
     *         (nombre, ID, carrera, semestre actual, ramos.)
     *         null si el estudiante no existe
     */
    String[] getInformacionEstudiante(String correo);

    /**
     * Obtiene los promedios de un estudiante.
     *
     * @param correo String con el correo estudiante
     * @return array de double conteniendo los promedios del estudiante
     *         [promedioGeneral, promedioSemestralN]
     *         null si el estudiante no existe
     */

    double[] getPromediosEstudiante(String correo);

    /**
     * Busca en la lista de Cursos y ordena los ramos por su numero de semestre y
     * los devuelve para mostrarlos en una tabla en la GUI.
     *
     * @return Array de dos dimensiones que contiene los nombres de los ramos
     *         ordenados por semestre
     */
    String[][] getMalla();

    /**
     * Busca un estudiante, y devuelve un lista de los ramos aprobados del
     * estudiante
     *
     * @param correo Correo del estudiante a revisar
     * @return lista de ramosAprobados del estudiante
     */
    ArrayList<String> getCursados(String correo);

    /**
     * Busca un estudiante, y devuelve un lista de los ramos que esta cursando
     * actualmente
     *
     * @param correo Correo del estudiante a revisar
     * @return lista de ramosEnCurso del estudiante
     */
    ArrayList<String> getEnProceso(String correo);

    /**
     * Devuelve los datos de una certificacion en el formato
     * nombre + desc + requisitos + creditos + cursosEnCertificacion
     *
     * @return String con los datos nombre + desc + requisitos + creditos +
     *         cursosEnCertificacion
     */
    String getDatosCertificaciones();

    /**
     * Obtiene el progreso de todas las certificaciones de un estudiante.
     *
     * @param correo String con el correo electrónico del estudiante
     * @return String con el progreso de certificaciones del estudiante
     *         nombre certificación + cursos completados + cursos pendientes y
     *         porcentaje de avance
     *         null si el estudiante no existe o no tiene certificaciones inscritas
     */
    String getProgresoCertificaciones(String correo);

    /**
     * Inscribe un estudiante en una certificación.
     *
     * @param correo          String con el correo electrónico del estudiante
     * @param nombreCert	  String con el nombre de la certificacion a inscribir
     * @return String con el mensaje de resultado de la inscripción
     *         mensaje de éxito si la inscripción se realizó correctamente
     *         mensaje de error si el estudiante ya está inscrito, la certificación
     *         no existe o el estudiante no cumple los requisitos previos
     */
    String inscribirCertificacion(String correo, String nombreCert);

}
