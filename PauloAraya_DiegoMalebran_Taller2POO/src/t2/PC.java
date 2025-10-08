package t2;
import java.util.ArrayList;
public class PC {
	private String ID;
	private String IP;
	private String sistema;
	private ArrayList<Puerto> puertosAbiertos;
	private ArrayList<Puerto> puertosCerrados;
	private int riesgo;
	
	public PC(String ID, String IP, String sistema) {;
		this.ID = ID;
		this.IP = IP;
		this.sistema = sistema;
		this.puertosAbiertos = new ArrayList<>();
		this.puertosCerrados = new ArrayList<>();
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

	public void setID(String iD) {
		ID = iD;
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

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public void setRiesgo(int riesgo) {
		this.riesgo = riesgo;
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
		System.out.println();
	}
	@Override
	public String toString() {
		return "PC [ID=" + ID + ", IP=" + IP + ", sistema=" + sistema + ", puertosAbiertos=" + puertosAbiertos
				+ ", puertosCerrados=" + puertosCerrados + "]";
	}
	
	
}
