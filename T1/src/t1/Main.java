/* Paulo Araya
 * 21.918.080-2
 * Diego Malebran
 * 
 * ICCI
 */
package t1;

import java.util.*;
import java.io.*;

public class Main {
	private static ArrayList<String> idExp = new ArrayList<>();
	private static ArrayList<String> descExp = new ArrayList<>();
	
	private static ArrayList<String> metricas = new ArrayList<>();
	
	private static int[][] matrizMetricas = new int[4][4];
	public static void main(String[] args) throws FileNotFoundException {
		getDatos();
	}
	public static void getDatos() throws FileNotFoundException {
		Scanner exp = new Scanner(new File("experimentos.txt"));
		getExperimentos(exp);
		exp.close();
		Scanner met = new Scanner(new File("metricas.txt"));
		getMetricas(met);
		met.close();
		Scanner pre = new Scanner(new File("predicciones.txt"));
		getPredicciones(pre);
		pre.close();
		Scanner ver = new Scanner(new File("verificacion_docente_confusiones.csv"));
		getVerificacion(ver);
		ver.close();
	}
	public static void getExperimentos(Scanner exp) {
		System.out.println(1);
		while(exp.hasNextLine()) {
			String l = exp.nextLine();
			String[] p = l.split(";");
			idExp.add(p[0]);
			descExp.add(p[1]);
		}
	}
	public static void getMetricas(Scanner met) {
		System.out.println(2);
		while(met.hasNextLine()) {
			String l = met.nextLine();
			metricas.add(l);
		}
	}
	public static void getPredicciones(Scanner pre) {
		System.out.println(3);
		while(pre.hasNextLine()) {
			String l = pre.nextLine();
			String[] p = l.split(";");
			for(int i = 0; i<idExp.size(); i++) {
				if (p[0].equals(idExp.get(i))){
					System.out.println(p[0]);
				}
			}
		}
	}
	public static void getVerificacion(Scanner ver) {
		System.out.println(4);
	}
}
