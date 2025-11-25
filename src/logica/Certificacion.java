package logica;

import java.util.ArrayList;

public class Certificacion {

    private String id;
    private String nombre;
    private String descripcion;
    private int requisitos;
    private int duracion;
    private ArrayList<Curso> cursosAsociados;

    // Estudiante:
    private String fechaObtencion;
    private String estado;
    private int progreso;

    public Certificacion(String id, String nombre, String descripcion, int requisitos, int duracion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.requisitos = requisitos;
        this.duracion = duracion;
        this.cursosAsociados = new ArrayList<>();
    }

    public static Certificacion addDatosCertificacion(Certificacion c, String[] p) {
        Certificacion certAgregar = c;
        String fechaObtencion = p[2];
        String estado = p[3];
        int progreso = Integer.parseInt(p[4]);
        certAgregar.setFechaObtencion(fechaObtencion);
        certAgregar.setEstado(estado);
        certAgregar.setProgreso(progreso);
        return certAgregar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(int requisitos) {
        this.requisitos = requisitos;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getFechaObtencion() {
        return fechaObtencion;
    }

    public void setFechaObtencion(String fechaObtencion) {
        this.fechaObtencion = fechaObtencion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getProgreso() {
        return progreso;
    }

    public void setProgreso(int progreso) {
        this.progreso = progreso;
    }

    public ArrayList<Curso> getCursosAsociados() {
        return cursosAsociados;
    }

    public void addCurso(Curso curso) {
        cursosAsociados.add(curso);
    }

}