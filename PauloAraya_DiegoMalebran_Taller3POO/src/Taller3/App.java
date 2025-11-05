package Taller3;

import java.util.*;
import java.io.*;

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		Sistema sistema = SistemaImpl.getInstancia();
		cargarArchivos(sistema);
		mostrarMenu(sistema);
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
	
	private static void mostrarMenu(Sistema sistema) {
		
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
		
		switch(logueado.getRol().toLowerCase()) {
			case "administrador" -> menuAdministrador(sistema, (Administrador) logueado, s);
			case "colaborador" -> menuColaborador(sistema, (Colaborador) logueado, s);
			default -> System.out.println("Rol no encontrado en nuestro sistema\n Adios!");
		}
		
	}

	private static void menuColaborador(Sistema sistema, Colaborador logueado, Scanner s) {
	
		String opcion;
		do {
			System.out.println("\n--- MENÚ COLABORADOR ---");
	        System.out.println("1. Ver proyectos disponibles");
	        System.out.println("2. Ver tareas asignadas");   
	        System.out.println("3. Actualizar estado de una tarea"); 
	        System.out.println("4. Acciones con respecto a una tarea");
	        System.out.println("0. Cerrar Sesión");
	        System.out.print("Seleccione una opción: ");
	        opcion = s.nextLine();
	        
	        switch(opcion) {
		        case "1":{
	                System.out.println(sistema.verProyectosDisponibles());
	                break;
		        }
		        case "2":{
		        	System.out.print(sistema.getTareasAsignadas(logueado));
		        	break;
		        }
		        case "3":{
		        	System.out.println(sistema.getTareasAsignadas(logueado));
		        	System.out.print("Elige una tarea por su id: ");
		        	String tarea = s.nextLine();
		        	System.out.println("Estados:\r\n"
		        			+ "1. Pendiente\r\n"
		        			+ "2. En Progreso\r\n"
		        			+ "3. Completada");
		        	System.out.print("Ingrese opcion (numero): ");
		        	String nuevoEstado = s.nextLine();
		        	System.out.println(sistema.cambioEstadoTarea(tarea, nuevoEstado, logueado));
		        	break;
		        }
		        
		        case "4":{
		        	System.out.println(sistema.getTareasAsignadas(logueado));
		        	System.out.print("Elige una tarea por su id: ");
		        	String tarea = s.nextLine();
		        	System.out.println(sistema.accionPorTarea(tarea));
		        	break;
		        }
		        case "0":{
		        	System.out.println("Adios!");
		        	break;
		        }
		        default: System.out.println("Opcion no encontrada, volviendo al menu...");
	        }
		} while (!opcion.equals("0"));
		
	}

	private static void menuAdministrador(Sistema sistema, Administrador logueado, Scanner s) {
		// TODO Auto-generated method stub
		
	}
	
	private static void menuTareas(Scanner s) {
		System.out.println("Quieres Agregar o Eliminar: ");
    	String respuesta = s.nextLine().toLowerCase();
    	
    	switch(respuesta) {
    		case "eliminar" -> eliminarTarea(s);
    		case "agregar" -> addTarea(s);
    		default -> System.out.println("Opcion no encontrada, volviendo al menu...");
    	}
	}

	private static void addTarea(Scanner s) {
		
	}

	private static void eliminarTarea(Scanner s) {
		
	}


	

}
