package logica;

public class Curso {
	private String nrc;
	private String nombre;
	private int semestre;
	private int creditos;
	private String area;
	private Curso prerequisitos;
	// Para el estudiante:
	private double notaFinal;
	private String estado;
	private String semestreCursado;

	public Curso(String nrc, String nombre, int semestre, int creditos, String area, Curso prerequisitos) {
		this.nrc = nrc;
		this.nombre = nombre;
		this.semestre = semestre;
		this.creditos = creditos;
		this.area = area;
		this.prerequisitos = prerequisitos;
	}

	public void registrarDatosParaEstudiante(String[] p) {
		double notaFinal = Double.parseDouble(p[2]);
		String estado = p[3];
		String semestreCursado = p[4];
		this.setNotaFinal(notaFinal);
		this.setEstado(estado);
		this.setSemestreCursado(semestreCursado);
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getSemestre() {
		return semestre;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Curso getPrerequisitos() {
		return prerequisitos;
	}

	public void setPrerequisitos(Curso prerequisitos) {
		this.prerequisitos = prerequisitos;
	}

	public double getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(double notaFinal) {
		this.notaFinal = notaFinal;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSemestreCursado() {
		return semestreCursado;
	}

	public void setSemestreCursado(String semestreCursado) {
		this.semestreCursado = semestreCursado;
	}

	public String[] getDatosCurso() {
		return new String[] {nrc, nombre, String.valueOf(creditos)};
	}

	public String[] getDatosEstudiante() {
		// TODO Auto-generated method stub
		return new String[] {nrc, nombre, String.valueOf(creditos), estado, String.valueOf(notaFinal)};
	}

}
