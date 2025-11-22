package Taller3;

public class VisitorAccionTarea implements Visitor {

    @Override
    public void visitar(Bug t) {
        System.out.println("Bug: Afecta criticalidad del proyecto");

    }

    @Override
    public void visitar(Feature t) {
    	System.out.println("Feature: Impacta en la estimacion del tiempo");

    }

    @Override
    public void visitar(Documentacion t) {
    	System.out.println("Documentacion: Mejora la calidad del proyecto");

    }

}
