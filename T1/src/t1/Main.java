package t1;

import java.util.*;
import java.io.*;

public class Main {
	private static ArrayList<String> idExp = new ArrayList<>();
	public static void main(String[] args) throws FileNotFoundException {
		getDatos();
	}
	public static void getDatos() throws FileNotFoundException{
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
	}
	public static void getMetricas(Scanner met) {
		System.out.println(2);
	}
	public static void getPredicciones(Scanner pre) {
		System.out.println(3);
	}
	public static void getVerificacion(Scanner ver) {
		System.out.println(4);
	}
}
