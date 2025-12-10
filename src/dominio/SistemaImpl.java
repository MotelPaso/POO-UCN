package dominio;

import logica.*;

import java.util.ArrayList;

public class SistemaImpl implements Sistema {

	private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
	private ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
	private ArrayList<Curso> listaCursos = new ArrayList<>();
	private ArrayList<Certificacion> listaCertificaciones = new ArrayList<>();

	private static Sistema instancia = null;

	public SistemaImpl() {
	}

	public static Sistema getInstancia() {

		if (instancia == null) {
			instancia = new SistemaImpl();
		}
		return instancia;
	}

	@Override
	public void crearUsuario(String[] p) {
		Usuario u = FactoryUsuarios.crearUsuario(p);
		listaUsuarios.add(u);
	}

	@Override
	public void crearEstudiante(String[] p) {
		Usuario u = FactoryUsuarios.crearUsuario(p);
		if (u instanceof Estudiante) {
			listaEstudiantes.add((Estudiante) u);
		}
		listaUsuarios.add(u);
	}

	@Override
	public void guardarCurso(String[] p) {
		String nrc = p[0];
		String nombre = p[1];
		int semestre = Integer.parseInt(p[2]);
		int creditos = Integer.parseInt(p[3]);
		String area = p[4];
		Curso prerequisitos = null;
		if (p.length > 5) { // si tiene prerequisitos
			prerequisitos = buscarCursoPorNRC(p[5]); // guardamos el requisito en su objeto
		}
		Curso c = new Curso(nrc, nombre, semestre, creditos, area, prerequisitos);
		listaCursos.add(c);
	}

	@Override
	public void guardarNotasEstudiante(String[] p) {
		Curso c = buscarCursoPorNRC(p[1]);
		Estudiante estudiante = buscarEstudiantePorRut(p[0]);
		c.registrarDatosParaEstudiante(p);
		estudiante.agregarCurso(c);
	}

	@Override
	public void guardarCertificaciones(String[] p) {
		String id = p[0];
		String nombre = p[1];
		String descripcion = p[2];
		int requisitos = Integer.parseInt(p[3]);
		int duracion = Integer.parseInt(p[4]);
		Certificacion cert = new Certificacion(id, nombre, descripcion, requisitos, duracion);
		listaCertificaciones.add(cert);
	}

	@Override
	public void guardarAsignaturasCertificaciones(String[] p) {
		Certificacion cert = buscarCertificacionPorId(p[0]);
		Curso curso = buscarCursoPorNRC(p[1]);
		// si existen, agregamos un curso a la certificacion.
		if (cert != null && curso != null)
			cert.addCurso(curso);
	}

	@Override
	public void guardarRegistrosEstudiantes(String[] p) {
		Estudiante estudiante = buscarEstudiantePorRut(p[0]);
		Certificacion cert = buscarCertificacionPorId(p[1]);
		cert.addDatosCertificacion(p);
		estudiante.agregarCertificacion(cert);
	}

	@Override
	public boolean revisarUsuario(String[] datosUsuario) {

		for (Usuario u : listaUsuarios) {
			if (u.getNombreUsuario().equals(datosUsuario[0]) && u.getPassword().equals(datosUsuario[1])) {
				return true;
			}
		}
		for (Estudiante u : listaEstudiantes) {
			if (u.getCorreo().equals(datosUsuario[0]) && u.getContraseña().equals(datosUsuario[1])) {
				return true;
			}
		}

		return false;
	}

	@Override
	public int getNivelAcceso(String[] datosUsuario) {
		// a este punto, deberia llegar un usuario que ya existe
		// asi que no debemos revisar si existe realmente
		if (datosUsuario[0].contains("@")) {
			return 3;
		}
		Usuario u = buscarUsuario(datosUsuario[0]);
		// aqui buscamos su rol admin/coordinador

		return switch (u.getRol().toLowerCase()) {
		case "admin" -> 1;
		case "coordinador" -> 2;
		case "estudiante" -> 3;
		default -> 0;
		};
	}

	@Override
	public void crearCuentas(String[] datos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarCuentas(String nombre) {
		// TODO Auto-generated method stub

	}

	@Override
	public int eliminarCuentaUsuario(String nombre) {
		for (Usuario u : listaUsuarios) {
			if (u.getNombreUsuario().equals(nombre) && u.getRol().equals("Admin")){
				return 2;
			}
			if (u.getNombreUsuario().equals(nombre) && !u.getRol().equals("Admin")){
				listaUsuarios.remove(u);
				return 1;
			}
		}
		return 0;

	}

	@Override
	public void resetContraseña(String nombre, String nuevaContra) {
		// TODO Auto-generated method stub

	}

	@Override
	public String[] getInformacionEstudiante(String correo) {
		Estudiante e = buscarEstudiantePorCorreo(correo);
		return e.getInformacion();
	}
	
	@Override
	public double[] getPromediosEstudiante(String correo) {
		Estudiante e = buscarEstudiantePorCorreo(correo);
		return e.getPromedios();
	}
	
	@Override
	public String[][] getMalla() {

		String[][] datosMalla = new String[12][8];
		int j = 0;
		int temp = 0;
		for (Curso c: listaCursos) {
			if (c.getSemestre()-1 != temp) {
				j = 0;
			}
			datosMalla[j][c.getSemestre()-1] = c.getNombre(); 
			temp = c.getSemestre()-1;
			j++;
		}
		return datosMalla;
	}
	
	@Override
	public ArrayList<String> getCursados(String correo) {
		Estudiante e = buscarEstudiantePorCorreo(correo);
		ArrayList<String> cursados = new ArrayList<>(); 
		for (Curso c : e.getRamosCursados()) {
			if (c.getEstado().equals("Aprobada"))
			cursados.add(c.getNombre());
		}
		return cursados;
	}
	
	@Override
	public ArrayList<String> getEnProceso(String correo) {
		Estudiante e = buscarEstudiantePorCorreo(correo);
		ArrayList<String> cursados = new ArrayList<>(); 
		for (Curso c : e.getRamosCursados()) {
			if (c.getEstado().equals("Cursando"))
			cursados.add(c.getNombre());
		}
		return cursados;
	}
	
	@Override
	public String getDatosCertificaciones(String correo) {
		
		String datos = "<html>";
		for (Certificacion c : listaCertificaciones) {
			datos += "-- "+c.getDatosCertificacion() + "<br>";
		}
		datos += "</html>";
		return datos;
	}
	
	
	private Usuario buscarUsuario(String nombre) {
		for (Usuario u : listaUsuarios) {
			if (u.getNombreUsuario().equals(nombre))
				return u;
		}
		return null;
	}

	private Estudiante buscarEstudiantePorRut(String rut) {
		for (Estudiante e : listaEstudiantes) {
			if (e.getRut().equals(rut))
				return e;
		}
		return null;
	}

	private Estudiante buscarEstudiantePorCorreo(String correo) {
		for (Estudiante e : listaEstudiantes) {
			if (e.getCorreo().equals(correo))
				return e;
		}
		return null;
	}

	private Curso buscarCursoPorNRC(String nrc) {
		for (Curso c : listaCursos) {
			if (c.getNrc().equals(nrc))
				return c;
		}
		return null;
	}

	private Certificacion buscarCertificacionPorId(String id) {
		for (Certificacion c : listaCertificaciones) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}

}
