package t2;
import java.util.ArrayList;
public class PC {
	private String ID;
	private String IP;
	private String sistema;
	private ArrayList<Puerto> puertosAbiertos;
	private ArrayList<Puerto> puertosCerrados;
	private String riesgo;
	
	public PC(String ID, String IP, String sistema) {;
		this.ID = ID;
		this.IP = IP;
		this.sistema = sistema;
		this.puertosAbiertos = new ArrayList<>();
		this.puertosCerrados = new ArrayList<>();
		this.riesgo = "";
	}
	
	public void addPuerto(Puerto puerto, String status) {
		if (status.equals("Abierto")) {
			this.puertosAbiertos.add(puerto);
		}
		else {
			this.puertosCerrados.add(puerto);
		}
	}

	public String getID() {
		return ID;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getSistema() {
		return sistema;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}
	
	public String getRiesgo() {
		return this.riesgo;
	}
	
	public ArrayList<Puerto> getPuertosAbiertos(){
		return this.puertosAbiertos;
		
	}
	
	public void calcularRiesgo() {
		int vulnerabilidades = this.puertosAbiertos.size();
		switch(vulnerabilidades) {
		case 0, 1:
			this.setRiesgo("Bajo");
			break;
		case 2:
			this.setRiesgo("Medio");
			break;
		case 3:
			this.setRiesgo("Alto");
			break;
		default:
			this.setRiesgo("Alto");
			break;
		}
	}
	
	public void mostrar() {
		System.out.println(this.ID+ ":");
		System.out.println(this.IP+ ": Sistema Operativo: " + this.sistema);
		System.out.print("Puertos Abiertos: ");
		for(Puerto puerto: puertosAbiertos) {
			System.out.print(puerto.getID() +"; ");
		}
		System.out.print("\nPuertos Cerrados: ");
		for(Puerto puerto: puertosCerrados) {
			System.out.print(puerto.getID() +"; ");
		}
		System.out.println("Nivel de Riesgo: " + this.riesgo);
	}
	public String getInfoPC() {
		String open = "";
		String closed = "";
		for(Puerto puerto: puertosAbiertos) {
			open += puerto.getID() + "; ";
		}
		for(Puerto puerto: puertosCerrados) {
			closed += puerto.getID() + "; ";
		}
		return this.ID + 
				"\nIP: " + this.IP + " SO: " + this.sistema + 
				"\nPuertos abiertos: " + open + 
				"\nPuertos cerrados: " + closed +
				"\nNivel de Riesgo: " + this.riesgo;
	}
	@Override
	public String toString() {
		return "PC [ID=" + ID + ", IP=" + IP + ", sistema=" + sistema + ", puertosAbiertos=" + puertosAbiertos
				+ ", puertosCerrados=" + puertosCerrados + "]";
	}
	
	
}
