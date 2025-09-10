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
	
	// Arrays con la información de los experimentos 

	private static String[] idExp = new String[4];
	private static String[] descExp = new String[4];
	private static String[] metricas = new String[4];
	private static String[] predicciones = new String[4];
	
	// Matrices para almacenar los resultados

	private static int[][] matrizDocente = 
		{{0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0}};
	private static int[][] matrizConfusion = 
		{{0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0}};
		 // TP , FP , TN, FN
	private static float[][] matrizMetricas =
		{{0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0},
		 {0,0,0,0}};
		 // Acc, Pre, Rec, F1
	private static float[] promedios = {0,0,0,0};
	public static void main(String[] args) throws FileNotFoundException {
		getDatos();
		Scanner s = new Scanner(System.in);
		mostrarMenuGlobal(s);
		s.close();
	}
	// Funciones Archivos
	public static void getDatos() throws FileNotFoundException {
		Scanner exp = new Scanner(new File("experimentos.txt"));
		getExperimentos(exp);
		exp.close();
		Scanner pre = new Scanner(new File("predicciones.txt"));
		getMatConfusion(pre);
		pre.close();
		Scanner met = new Scanner(new File("metricas.txt"));
		getMetricas(met);
		met.close();
		Scanner ver = new Scanner(new File("verificacion_docente_confusiones.csv"));
		getMatDocentes(ver);
		ver.close();
		getPromedios();
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
			i++;
		}
		for (i = 0; i < matrizConfusion.length; i++) {
			int TP = matrizConfusion[i][0];
			int FP = matrizConfusion[i][1];
			int TN = matrizConfusion[i][2];
			int FN = matrizConfusion[i][3];
			float Acc = (float) (TP + TN)/(TP + FP + TN + FN);
			float Pre = (float) TP / (TP + FP);
			float Rec = (float) TP / (TP + FN);
			float F1 = (float) 2 * (Pre * Rec) / (Pre + Rec);
			matrizMetricas[i][0] = Acc;
			matrizMetricas[i][1] = Pre;
			matrizMetricas[i][2] = Rec;
			matrizMetricas[i][3] = F1;
		}
	}
	public static void getMatConfusion(Scanner pre) {
		while(pre.hasNextLine()) {
			String l = pre.nextLine();
			String[] p = l.split(";");
			for (int i = 0; i < matrizConfusion[0].length; i++) { // 4
				while(p[0].equals(idExp[i])) {
					if (p[1].equals("1") && p[2].equals("1")){ // TP
						matrizConfusion[i][0] += 1;
					}
					else if (p[1].equals("0") && p[2].equals("1")) { // FP
						matrizConfusion[i][1] += 1;
					}
					else if (p[1].equals("0") && p[2].equals("0")) { // TN
						matrizConfusion[i][2] += 1;
					}
					else if (p[1].equals("1") && p[2].equals("0")) { // FN
						matrizConfusion[i][3] += 1;
					}
					break;
				}
			}
		}
	}
	public static void getMatDocentes(Scanner ver) {
		String[] partes = ver.nextLine().split(",");
		
		for (int i = 0; i < predicciones.length; i++) {
			predicciones[i] = partes[i+1];
		}
		int i = 0;
		while(ver.hasNextLine()) {
			String l = ver.nextLine();
			String[] p = l.split(",");
			for(int j = 0; j < matrizDocente.length; j++) {
				matrizDocente[i][j] = Integer.valueOf(p[j+1]);
			}
			i++;
		}
	}
	public static void getPromedios(){
		int i = 0;
		for (int prom = 0; prom < promedios.length; prom++) {
			float suma = 0;
			for(int j = 0; j < matrizMetricas.length; j++){
				suma += matrizMetricas[j][i];
			}
			promedios[prom] = (float) (suma / 4);
			i++;
		}
	}
	// Funciones Menu
	public static void mostrarMenuGlobal(Scanner s){
		String opcion;
		do {
			System.out.println("Analisis modelos IA");
			System.out.println("----------------------");
			System.out.println("1. Admin");
			System.out.println("2. Usuario");
			System.out.println("0. Salir del programa.");
			System.out.println("----------------------");
			System.out.print("Ingrese seleccion: ");
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
			System.out.println("----------------------");
			System.out.println("Menu admin.");
			System.out.println("1. Ver matriz de metricas.");
			System.out.println("2. Ver experimento con mayor F1 Score.");
			System.out.println("3. Mostrar promedio global de cada metrica.");
			System.out.println("4. Comparar dos experimentos.");
			System.out.println("5. Comparar tabla csv con datos de metricas.");
			System.out.println("0. Volver al menu anterior.");
			System.out.println("----------------------");
			System.out.print("Ingrese seleccion: ");
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
					compararExp(s);
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
			System.out.println("----------------------");
			System.out.println("Menu User.");
			System.out.println("1. Ver lista de experimentos.");
			System.out.println("2. Mostrar matriz de un experimento");
			System.out.println("3. Ver metricas de un experimento");
			System.out.println("4. Ver promedio Accuracy de todos los modelos");
			System.out.println("0. Volver al menu anterior.");
			System.out.println("----------------------");
			System.out.print("Ingrese seleccion: ");
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
	public static void imprimirMatriz() {
		System.out.print("nExp | ");
		for (String s : metricas) {
			System.out.print(s + " | ");
		}
		System.out.println("");
		for (int i = 0; i < matrizMetricas.length; i++){
			System.out.print(idExp[i] + " | ");
			for (float met : matrizMetricas[i]) {
				System.out.print(met + " | ");
			}
			System.out.println("");
		}
		
	}
	public static void mostrarMayorFscore() {
		float mayor = 0;
		int indice = 0;
		for(int i = 0; i < matrizMetricas.length; i++){
			if (matrizMetricas[i][3] > mayor){
				mayor = matrizMetricas[i][3];
				indice = i;
			}
		}
		System.out.println("El experimento con mayor F1 Score es: " + idExp[indice] + " con un F1 Score de " + mayor);
		
	}
	public static void mostrarPromedioGlobal() {
		System.out.println("Imprimiendo promedios globales...");
		for (int i = 0; i < promedios.length; i++){
			System.out.println(metricas[i] + ": " + promedios[i]);
		}
		
	}
	public static void compararExp(Scanner s) {
		mostrarExp();
		System.out.print("Elija un experimento: ");
		int comp1 = s.nextInt();
		s.nextLine();
		mostrarExp();
		System.out.print("Elija el siguiente experimento a comparar: ");
		int comp2 = s.nextInt();
		s.nextLine();
		if ((comp1 >= 1 && comp1 <= 4) && (comp2 >= 1 && comp2 <= 4) ) { // tienen que estar dentro de rangos validos
			if (comp1 == comp2) { // y no ser iguales
				System.out.println("No se puede comparar el mismo experimento");
			}
			else {
				System.out.println("Metricas \t" + idExp[comp1-1] + "\t" + idExp[comp2-1]);
				for (int i = 0; i < metricas.length; i++) {
					System.out.println(metricas[i] + "\t" + matrizMetricas[comp1-1][i] + "\t" + matrizMetricas[comp2-1][i]);
				}
			}
		}
		else {
			System.out.println("Opcion invalida, volviendo al menu...");
		}
	}
	public static void compararTablaMatriz() {
		
		for (String pred : predicciones) {
			System.out.print("| " + pred + " ");
		}
		System.out.println("");
		for (int i = 0; i < matrizConfusion.length; i++) {
			System.out.print("| ");
			for(int j = 0; j < matrizConfusion[i].length; j++) {
				System.out.print(matrizConfusion[i][j] + " | " );
			}
			System.out.print("\t| ");
			for(int j = 0; j < matrizConfusion[i].length; j++) {
				System.out.print(matrizDocente[i][j] + " | " );
			}
			System.out.println("");
		}
		
		boolean diferencia = false;
		for (int i = 0; i < matrizConfusion.length; i++) {
			for(int j = 0; j < matrizConfusion[i].length; j++) {
				 if (matrizConfusion[i][j] != matrizDocente[i][j]) {
					 System.out.println("Diferencia encontrada en el experimento " + (i+1));
					 System.out.println(predicciones[j] + " no son iguales.");
					 diferencia = true;
				 }
			}
		}
		if (!diferencia) {
			System.out.println("Los datos de la tabla son iguales.");
		}
		
	}

	// Menu User.
	public static void mostrarExp() {
		System.out.println("Listado de Experimentos: ");
		for (int i = 0; i < idExp.length; i++) {
			System.out.println(idExp[i] + " " + descExp[i]);
		}
		System.out.println("");
		
	}
	

	public static void mostrarMatriz(Scanner s) {
		System.out.println("Elija el experimento que desea revisar: ");
		for (int i = 0; i < idExp.length; i++) {
			System.out.println(idExp[i] + ": " + descExp[i]);
		}
		System.out.print("Ingrese numero: ");
		int opcion = s.nextInt();
		s.nextLine();
		int i;
		if (opcion >= 1 && opcion <= 4) {
			System.out.println("nExp | TP | NP | TN | FN |");
			System.out.print(idExp[opcion-1] + " | ");
			for (i = 0; i < matrizConfusion[opcion-1].length; i++){
				System.out.print(matrizConfusion[opcion-1][i] + " | ");
			}
		
			System.out.println("");
		}
		else{
			System.out.println("Opcion invalida, volviendo al menu...");
		}
	}
	
	//mostrar metricas de los experimentos
	public static void mostrarMetricas(Scanner s) {
		System.out.println("Elija el experimento que desea revisar: ");
		for (int i = 0; i < idExp.length; i++) {
			System.out.println(idExp[i] + ": " + descExp[i]);
		}
		System.out.print("Ingrese numero: ");
		int opcion = s.nextInt();
		s.nextLine();
		int i;
		if (opcion >= 1 && opcion <= 4) {
			System.out.print("nExp | ");
			for (String met : metricas) {
				System.out.print(met + " | ");
			}
			System.out.println("");
			System.out.print(idExp[opcion-1] + " | ");
			for (i = 0; i < matrizMetricas[opcion-1].length; i++){
				System.out.print(matrizMetricas[opcion-1][i] + " | ");
			}
		
			System.out.println("");
		}
		else{
			System.out.println("Opcion invalida, volviendo al menu...");
		}
	}
	
	// Mostrar el promedio de Accuracy de todos los modelos

	public static void mostrarPromedios() {
		System.out.println("El promedio Accuracy de todos los modelos es: " + promedios[0]);
	}
}
