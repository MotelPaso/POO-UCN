/* Paulo Araya
 * 21.918.080-2
 * Diego Malebran
 * 21.661.740-1
 * ICCI
 */
package t1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	private static String[] idExp = new String[4];
	private static String[] descExp = new String[4];
	private static String[] metricas = new String[4];
	private static int[][] matrizMetricas = 
		{{0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0}};
	
	public static void main(String[] args) throws FileNotFoundException {
		getDatos();
		Scanner s = new Scanner(System.in);
		mostrarMenuGlobal(s);
		s.close();
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
		int i = 0;
		while(exp.hasNextLine()) {
			String l = exp.nextLine();
			String[] p = l.split(";");
			idExp[i] = p[0];
			descExp[i] = p[1];
			i++;
		}
	}
	public static void getMetricas(Scanner met) {
		int i = 0;
		while(met.hasNextLine()) {
			String l = met.nextLine();
			metricas[i] = l;
		}
	}
	public static void getPredicciones(Scanner pre) {
		while(pre.hasNextLine()) {
			String l = pre.nextLine();
			String[] p = l.split(";");
			for (int i = 0; i < matrizMetricas[0].length; i++) { // 4
				while(p[0].equals(idExp[i])) {
					if (p[1].equals("1") && p[2].equals("1")){ // TP
						matrizMetricas[i][0] += 1;
					}
					else if (p[1].equals("0") && p[2].equals("1")) { // FP
						matrizMetricas[i][1] += 1;
					}
					else if (p[1].equals("0") && p[2].equals("0")) { // TN
						matrizMetricas[i][2] += 1;
					}
					else if (p[1].equals("1") && p[2].equals("0")) { // FN
						matrizMetricas[i][3] += 1;
					}
					break;
				}
			}
		}
	}
	public static void getVerificacion(Scanner ver) {
		while(ver.hasNextLine()) {
			String l = ver.nextLine();
			System.out.println(l);
			String[] p = l.split(",");
		}
	}
	
	public static void mostrarMenuGlobal(Scanner s){
		String opcion;
		do {
			System.out.println("Analisis modelos IA");
			System.out.println("----------------------");
			System.out.println("1. Admin");
			System.out.println("2. Usuario");
			System.out.println("0. Salir del programa.");
			System.out.println("----------------------");
			opcion = s.next();
			s.nextLine();
			switch (opcion){
				case "1":
					menuAdmin(s);
					break;
				case "2":
					menuUser(s);
					break;
				case "0":
					System.out.println("Adios!");
					break;
				default:
					System.out.println("Opcion invalida, ingrese nuevamente.");
			}
		} while(!opcion.equals("0"));
	}
	public static void menuAdmin(Scanner s) {
		String opcion;
		do {
			System.out.println("Menu admin.");
			System.out.println("----------------------");
			System.out.println("1. Ver matriz de metricas.");
			System.out.println("2. Ver experimento con mayor F1 Score.");
			System.out.println("3. Mostrar promedio global de cada metrica.");
			System.out.println("4. Comparar dos experimentos.");
			System.out.println("5. Comparar tabla csv con datos de metricas.");
			System.out.println("0. Volver al menu anterior.");
			System.out.println("----------------------");
			opcion = s.next();
			s.nextLine();
			switch (opcion){
				case "1":
					imprimirMatriz();
					break;
				case "2":
					mostrarMayorFscore();
					break;
				case "3":
					mostrarPromedioGlobal();
					break;
				case "4":
					compararExp();
					break;
				case "5":
					compararTablaMatriz();
					break;
				case "0":
					System.out.println("Volviendo al menu...");
					break;
				default:
					System.out.println("Opcion invalida, ingrese nuevamente.");
			}
		} while(!opcion.equals("0"));
	}
	public static void menuUser(Scanner s){
		String opcion;
		do {
			System.out.println("Menu User.");
			System.out.println("----------------------");
			System.out.println("1. Ver lista de experimentos.");
			System.out.println("2. Mostrar matriz de un experimento");
			System.out.println("3. Ver metricas de un experimento");
			System.out.println("4. Ver promedio Accuracy de todos los modelos");
			System.out.println("0. Volver al menu anterior.");
			System.out.println("----------------------");
			opcion = s.next();
			s.nextLine();
			switch (opcion) {
				case "1":
					mostrarExp();
					break;
				case "2":
					mostrarMatriz(s);
					break;
				case "3":
					mostrarMetricas(s);
					break;
				case "4":
					mostrarPromedios();
					break;
				case "0":
					System.out.println("Volviendo al menu...");
					break;
				default:
					System.out.println("Opcion invalida, ingrese nuevamente.");
			}
			
		} while(!opcion.equals("0"));
	}
	
	// Menu Admin.
	private static void compararExp() {
		// TODO Auto-generated method stub
		
	}
	private static void mostrarPromedioGlobal() {
		// TODO Auto-generated method stub
		
	}
	private static void mostrarMayorFscore() {
		// TODO Auto-generated method stub
		
	}
	private static void imprimirMatriz() {
		// TODO Auto-generated method stub
		
	}
	private static void compararTablaMatriz() {
		// TODO Auto-generated method stub
		
	}
	// Menu User.
	private static void mostrarPromedios() {
		// TODO Auto-generated method stub
		
	}
	private static void mostrarMetricas(Scanner s) {
		// TODO Auto-generated method stub
		
	}
	private static void mostrarMatriz(Scanner s) {
		// TODO Auto-generated method stub
		
	}
	private static void mostrarExp() {
		// TODO Auto-generated method stub
		
	}
}
