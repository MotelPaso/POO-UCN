package dominio;
import logica.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


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
    public void cargarArchivos() {
        // Guardamos usuarios (admin y colab)
        try (Scanner s = new Scanner(new File("usuarios.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                Usuario u = FactoryUsuarios.crearUsuario(p);
                listaUsuarios.add(u);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }
        // Guardamos estudiantes
        try (Scanner s = new Scanner(new File("estudiantes.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                String rut = p[0];
                String nombre = p[1];
                String carrera = p[2];
                String semestre = p[3];
                String correo = p[4];
                String contraseña = p[5];
                Estudiante e = new Estudiante(rut, nombre, carrera, semestre, correo, contraseña);
                listaEstudiantes.add(e);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }
        // Guardamos cursos
        try (Scanner s = new Scanner(new File("cursos.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                String nrc = p[0];
                String nombre = p[1];
                int semestre = Integer.parseInt(p[2]);
                int creditos = Integer.parseInt(p[3]);
                String area = p[4];
                String prerequisitos = "";
                if (p.length > 5) {
                    prerequisitos = p[5];
                }

                Curso c = new Curso(nrc, nombre, semestre, creditos, area, prerequisitos);
                listaCursos.add(c);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }
        // Guardamos las notas de los estudiantes, añadiendo los cursos a cada
        // estudiante
        try (Scanner s = new Scanner(new File("notas.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                Curso c = null; // Creamos un curso
                for (Curso curso : listaCursos) {
                    if (curso.getNrc().equals(p[1])) {
                        c = Curso.registrarCursoEstudiante(curso, p);
                        // le agregamos los datos del estudiante al curso
                        break;
                    }
                }
                for (Estudiante e : listaEstudiantes) {
                    if (e.getRut().equals(p[0])) {
                        e.addCurso(c);
                        // le agregamos el curso al estudiante
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }
        // Guardamos certificaciones
        try (Scanner s = new Scanner(new File("certificaciones.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                String id = p[0];
                String nombre = p[1];
                String descripcion = p[2];
                int requisitos = Integer.parseInt(p[3]);
                int duracion = Integer.parseInt(p[4]);
                Certificacion cert = new Certificacion(id, nombre, descripcion, requisitos, duracion);
                listaCertificaciones.add(cert);
            }
        } catch (Exception e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }
        // Guardamos los cursos asociados a las certificaciones
        try (Scanner s = new Scanner(new File("asignaturas_certificaciones.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                String idCertificacion = p[0];
                String nrcCurso = p[1];
                Certificacion cert = null;
                for (Certificacion c : listaCertificaciones) {
                    if (c.getId().equals(idCertificacion)) {
                        cert = c;
                        break;
                    }
                }
                Curso curso = null;
                for (Curso c : listaCursos) {
                    if (c.getNrc().equals(nrcCurso)) {
                        curso = c;
                        break;
                    }
                }
                // Aquí se podría agregar el curso a la certificación si fuera necesario
                if (cert != null && curso != null) {
                    cert.addCurso(curso);
                }
            }
        } catch (Exception e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }

        // Guardamos las certificaciones de los estudiantes
        try (Scanner s = new Scanner(new File("registros.txt"))) {
            while (s.hasNextLine()) {
                String[] p = s.nextLine().split(";");
                String rutEstudiante = p[0];
                String idCertificacion = p[1];
                Estudiante est = null;
                for (Estudiante e : listaEstudiantes) {
                    if (e.getRut().equals(rutEstudiante)) {
                        est = e;
                        break;
                    }
                }
                Certificacion cert = null;
                for (Certificacion c : listaCertificaciones) {
                    if (c.getId().equals(idCertificacion)) {
                        c = Certificacion.addDatosCertificacion(c, p);
                        break;
                    }
                }
                est.addCertificacion(cert);
            }
        } catch (Exception e) {
            System.out.println("Los archivos no han podido ser guardados...");
        }
        System.out.println("Archivos cargados correctamente!.");

    }
}
