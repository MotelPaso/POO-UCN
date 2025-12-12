package logica;

import java.util.ArrayList;

public class RevisarProgresoVisitor implements Visitor {

	@Override
	public String visitar(Certificacion cert, Estudiante estudiante) {
		String datos = "Certificación: " + cert.getNombre() + "<br>";
        datos += "Progreso: " + cert.getProgreso() + "<br>";
        
        if (cert.getProgreso() == 100) {
            datos += "Fecha de completacion: " + cert.getFechaObtencion() + "<br>";
            return datos;
        }

        // Obtener cursos aprobados por el estudiante
        ArrayList<String> ramosAprobados = new ArrayList<>();
        for (Curso c : estudiante.getRamosCursados()) {
            if (c.getEstado().equals("Aprobada")) {
                ramosAprobados.add(c.getNombre());
            }
        }
        
        datos += "Asignaturas Pendientes:<br>";
        
        // Comparar cursos asociados a la certificación con los aprobados del estudiante
        for (Curso cRequerido : cert.getCursosAsociados()) {
            if (!ramosAprobados.contains(cRequerido.getNombre())) {
                datos += "- " + cRequerido.getNombre() + " (" + cRequerido.getNrc() + ")<br>";
            }
        }
        
        datos += "Requisitos Mínimos (Créditos): " + cert.getRequisitos() + " créditos. <br>";
        datos += "Duración: " + cert.getDuracion() + " años. <br>";
        
        return datos;
	}

}
