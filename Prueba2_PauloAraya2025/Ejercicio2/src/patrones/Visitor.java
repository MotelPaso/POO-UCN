package patrones;

import clases.*;

public interface Visitor {

	void visitar(Glossy p);
	void visitar(Matte p);
	void visitar(ProLuster p);
}
