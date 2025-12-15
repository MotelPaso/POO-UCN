package p1;

public class Mensaje {
	private int id;
	private int chatId;
	private String fecha;
	private int userId;
	private String content;
	
	public Mensaje(int id, int chatId, String fecha, int userId, String content) {
		this.id = id;
		this.chatId = chatId;
		this.fecha = fecha;
		this.userId = userId;
		this.content = content;
	}
	
	public int getUserId() {
		return this.userId;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	
	public void mostrar(String nombre) {
		System.out.println("["+this.content + ": " + nombre + " " + this.fecha + "]");
	}
	
	
}
