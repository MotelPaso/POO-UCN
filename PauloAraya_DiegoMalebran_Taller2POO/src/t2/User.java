package t2;

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
}
