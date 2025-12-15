package main;

import java.io.File;
import java.util.Scanner;

import gui.GUI;

public class App {

	public static void main(String[] args) {
		Sistema sistema = SistemaImpl.getInstancia();
		cargarDatos(sistema);
		GUI gui = new GUI();
	}
	private static void cargarDatos(Sistema sistema) {
		try (Scanner s = new Scanner(new File("pedidos.txt"))){
			while (s.hasNext()) {				
				String[] p = s.nextLine().split(",");
				sistema.cargarRegalos(p);
			}
		} catch (Exception e) {
			System.out.println("el archivo no ha podido ser leido...");
			e.printStackTrace();
		}
	}

}
