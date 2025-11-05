package Taller3;

import java.util.*;
import java.io.*;

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		Sistema sistema = SistemaImpl.getInstancia();
		cargarArchivos(sistema);
		menu(sistema);
	}

	private static void cargarArchivos(Sistema sistema) throws FileNotFoundException {
		// Leer cada archivo y guardar los usuarios, proyectos y tareas.
		Scanner s = new Scanner(new File("usuarios.txt")); 
		while(s.hasNextLine()) {
			
			String [] p = s.nextLine().strip().split("\\|");
			sistema.crearUsuarios(p);
		}
		s.close();
		
		s = new Scanner(new File("proyectos.txt"));
		while(s.hasNextLine()) {
				
			String [] pp = s.nextLine().strip().split("\\|");
			sistema.guardarProyectos(pp);
		}
		s.close();
		
		s = new Scanner(new File("tareas.txt"));
		while(s.hasNextLine()) {
			String [] p = s.nextLine().strip().split("\\|");
			sistema.guardarTareas(p);
		}
		s.close();
	}
	
	
	
	private static void menu(Sistema sistema) {
		
		Scanner s = new Scanner(System.in);
		
		Usuario logueado = null;
		System.out.println("--- INICIO DE SESIÓN ---");
		do {
			
	        System.out.print("Username: ");
	        String username = s.nextLine();
	        System.out.print("Contraseña: ");
	        String password = s.nextLine();
	        
	        logueado = sistema.login(username, password);
	        
	        if (logueado == null) {
	        	System.out.println("Credenciales incorrectas, vuelva a ingresar.");
	        }
			
		} while (logueado == null);
		
		System.out.println("¡Bienvenido, " + logueado.getUsername() + "!");
		
		if ("Administrador".equalsIgnoreCase(logueado.getRol())) {
		    menuAdministrador(sistema, (Administrador) logueado, s);
		} else { 
		    menuColaborador(sistema, (Colaborador) logueado, s);
		}
	}

	private static void menuColaborador(Sistema sistema, Colaborador logueado, Scanner s) {
	
		String opcion;
		do {
			System.out.println("\n--- MENÚ COLABORADOR ---");
	        System.out.println("1 Ver proyectos disponibles");
	        System.out.println("2 Ver tareas asignadas");   
	        System.out.println("3 Actualizar estado de una tarea"); 
	        System.out.println("0 Cerrar Sesión");
	        System.out.print("Seleccione una opción: ");
	        opcion = s.nextLine();
	        
	        switch(opcion) {
		        case "1":{
	                System.out.println(sistema.verProyectosDisponibles());
	                break;
		        }
		        case "2":{
		        	// TODO: Mover a funcion aparte
		        	System.out.println("Quieres Agregar o Eliminar: ");
		        	String respuesta = s.nextLine().toLowerCase();
		        	switch(respuesta) {
		        		case "eliminar":{
			        		System.out.println(sistema.verProyectosDisponibles());
			        		break;
		        		}
		        		case "agregar":{
		        			//TODO: agregar
		        			break;
		        		}
		        		default: System.out.println("Opcion no encontrada, volviendo al menu...");
		        	}
		        	break; 	
		        }
		        case "3":{
		        	//TODO: Actualizar tareas.
		        }
		        case "0":{
		        	System.out.println("Adios!");
		        }
		        default: System.out.println("Opcion no encontrada, volviendo al menu...");
	        }
		} while (!opcion.equals("0"));
		
	}

	private static void menuAdministrador(Sistema sistema, Administrador logueado, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	

}
