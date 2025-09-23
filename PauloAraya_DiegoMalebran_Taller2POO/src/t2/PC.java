package t2;
import java.util.ArrayList;
public class PC {
	private String ID;
	private String IP;
	private String sistema;
	private ArrayList<Integer> puertosAbiertos;
	private ArrayList<Integer> puertosCerrados;
	
	public PC(String ID, String IP, String sistema) {;
		this.ID = ID;
		this.IP = IP;
		this.sistema = sistema;
		this.puertosAbiertos = new ArrayList<>();
		this.puertosCerrados = new ArrayList<>();
	}
	
	public void addPuerto(int puerto, String status) {
		if (status.equals("abierto")) {
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
	
	
}
