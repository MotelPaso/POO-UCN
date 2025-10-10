package t2;

import java.util.Scanner;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
	private String username;
	private String contra;
	private String admin;
	
	public User(String username, String contra, String admin) {
		this.username = username;
		this.contra = contra;
		this.admin = admin;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContra() {
		return contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
	
	public boolean revisarContraseña(Scanner s) throws NoSuchAlgorithmException {
		System.out.print("Ingrese su contraseña: ");
		String contraseña = s.nextLine();
		
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] hashed256 = sha.digest(contraseña.getBytes()); // primero, se hashea a SHA-256
		Encoder encoder = Base64.getEncoder();
		String hashed = encoder.encodeToString(hashed256); // luego, se pasa a Base64.
		
		if (hashed.equals(this.contra)) { // revisamos si son iguales
			return true;
		}
		return false;
	}
}
