/* Paulo Araya
 * 21.918.080-2
 * Diego Malebran
 * 
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
		System.out.println(1);
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
		System.out.println(2);
		int i = 0;
		while(met.hasNextLine()) {
			String l = met.nextLine();
			metricas[i] = l;
		}
	}
	public static void getPredicciones(Scanner pre) {
		System.out.println(3);
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
		System.out.println(4);
	}
	
	public static void mostrarMenuGlobal(Scanner s){
		String opcion;
		String opciones = "1 2 0";
		do {
			System.out.println("Analisis modelos IA");
			System.out.println("----------------------");
			System.out.println("1. Admin");
			System.out.println("2. Usuario");
			System.out.println("0. Salir del programa.");
			System.out.println("----------------------");
			opcion = s.next();
			s.nextLine();
			if (!opciones.contains(opcion)){
				System.out.println("Opcion invalida, ingrese nuevamente.");
			}
			switch (opcion){
				case "1":
					menuAdmin(s);
					break;
				case "2":
					menuUser(s);
					break;
			}
		} while(opcion == "0");
	}
	public static void menuAdmin(Scanner s) {
		String opcion;
		String opciones = "1 2 3 4 5 0";
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
			if (!opciones.contains(opcion));
				System.out.println("Opcion invalida, ingrese nuevamente.");
		} while(!opcion.equals("0"));
	}
	public static void menuUser(Scanner s){
		String opcion;
		String opciones = "1 2 3 4 0";
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
			if (!opciones.contains(opcion)){
				System.out.println("Opcion invalida, ingrese nuevamente.");
			}
			
		} while(!opcion.equals("0"));
	}
}
