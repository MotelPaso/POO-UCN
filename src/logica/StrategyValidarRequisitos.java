/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 21.661.740-1
 * ICCI 
 */

package logica;

import java.util.ArrayList;

public class StrategyValidarRequisitos implements StrategyInscripcion {

	@Override
	public String validar(Estudiante estudiante, Certificacion cert) {
		
		for (Certificacion c : estudiante.getCertificaciones()) {
			if (c.getNombre().equals(cert.getNombre())) {
				return "Ya estás inscrito en esta certificación.";
			}
		}
		
        ArrayList<String> ramosAprobados = new ArrayList<>();
        for (Curso c : estudiante.getRamosCursados()) {
            if (c.getEstado().equals("Aprobada")) {
                ramosAprobados.add(c.getNrc());
            }
        }
        String requisitosFaltantes = "";
        for (Curso cRequerido : cert.getCursosAsociados()) {
            Curso prerrequisito = cRequerido.getPrerequisitos();
            if (prerrequisito != null) { // si tiene prerequisitos
                if (!ramosAprobados.contains(prerrequisito.getNrc())) {
                    requisitosFaltantes += "- Curso " + cRequerido.getNombre() + " (Falta: " + prerrequisito.getNombre() + ")<br>";
                }
            }
        }
        if (requisitosFaltantes.equals("")) {
            return "";
        } else {
            return "Ramos Pendientes:<br>" + requisitosFaltantes;
        }
	}

}
