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
		String rut = p[0];
		String nombre = p[1];
		String carrera = p[2];
		String semestre = p[3];
		String correo = p[4];
		String contraseña = p[5];
		Estudiante e = new Estudiante(rut, nombre, carrera, semestre, correo, contraseña);
		listaEstudiantes.add(e);
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

	private Estudiante buscarEstudiantePorRut(String rut) {
		for (Estudiante e : listaEstudiantes) {
			if (e.getRut().equals(rut))
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
