package logica;

import java.util.ArrayList;

public class Estudiante extends Usuario {
	private String rut;
	private String nombre;
	private String carrera;
	private String semestre;
	private ArrayList<Curso> ramosCursados;
	private ArrayList<Certificacion> certificaciones;

	public Estudiante(String correo, String contraseña, String rut, String nombre, String carrera, String semestre) {
		super(correo, contraseña, "estudiante");
		this.rut = rut;
		this.nombre = nombre;
		this.carrera = carrera;
		this.semestre = semestre;
		this.ramosCursados = new ArrayList<>();
		this.certificaciones = new ArrayList<>();
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getCorreo() {
		return nombreUsuario;
	}

	public void setCorreo(String correo) {
		this.nombreUsuario = correo;
	}

	public String getContraseña() {
		return password;
	}

	public void setContraseña(String contraseña) {
		this.password = contraseña;
	}

	public void agregarCurso(Curso c) {
		ramosCursados.add(c);
	}

	public void agregarCertificacion(Certificacion cert) {
		certificaciones.add(cert);
	}
}
