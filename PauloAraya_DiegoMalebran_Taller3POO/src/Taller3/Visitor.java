package Taller3;

public interface Visitor {

	void visitar(Bug t);
	void visitar(Feature t);
	void visitar(Documentacion t);

}
