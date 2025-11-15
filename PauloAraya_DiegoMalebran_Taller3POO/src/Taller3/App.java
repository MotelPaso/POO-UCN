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
		        	// TODO: Visitor
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
		
		String opcion = "";
		do {
			System.out.println("--- MENÚ ADMINISTRADOR ---\r\n"
					+ "1. Ver lista completa de proyectos y tareas\r\n"
					+ "2. Administrar proyectos\r\n"
					+ "3. Administrar tareas\r\n"
					+ "4. Asignar estrategia de priorización\r\n"
					+ "5. Generar reporte de proyectos\r\n"
					+ "0. Cerrar sesión.\r\n"
					+ "\r\n"
					+ "Ingrese su opción: ");
			opcion = s.nextLine();
			
			switch (opcion) {
			case "1": {
				System.out.println(sistema.mostrarProyectosyTareas());
				break;
			}
			case "2": {
				System.out.println(sistema.mostrarProyectosyTareas());
				menuAdminProyectos(sistema, s);
				break;
			}
			case "3":{
				menuAdminTareas(sistema, s);
				break;
			}
			case "4": {
				break;
			}
			case "5":{
				break;
			}
			case "0":{
				System.out.println("Adios!");
			}
			default:
				System.out.println("Opcion no encontrada, volviendo al menu...");
			}
		} while (!opcion.equals("0"));
	}
	
	private static void menuAdminProyectos(Sistema sistema, Scanner s) {
		System.out.println("Elige una opcion: \n " + 
						"1. Agregar un proyecto \n" + 
						"2. Eliminar un proyecto \n"
						+ "Ingrese su opcion: ");
		String respuesta = s.nextLine().toLowerCase();

		switch (respuesta) {
		case "1" -> eliminar(sistema, s, "proyecto");
		case "2" -> agregar(sistema, s, "proyecto");
		default -> System.out.println("Opcion no encontrada, volviendo al menu...");
		}
	}

	private static void menuAdminTareas(Sistema sistema, Scanner s) {
		System.out.println("Elige una opcion: \n " +
						"1. Agregar una tarea \n" +
						"2. Eliminar una tarea \n" +
						"Ingrese su opcion: ");
    	String respuesta = s.nextLine().toLowerCase();
    	
    	switch(respuesta) {
    		case "1" -> eliminar(sistema , s, "tarea");
    		case "2" -> agregar(sistema, s, "tarea");
    		default -> System.out.println("Opcion no encontrada, volviendo al menu...");
    	}
	}

	private static void agregar(Sistema sistema, Scanner s, String tipo) {
		if (tipo.equalsIgnoreCase("proyecto")) {
			// TODO: Agregar cosas
		} else if (tipo.equalsIgnoreCase("tarea")) {
			// TODO: Agregar cosas
		}
	}

	private static void eliminar(Sistema sistema, Scanner s, String tipo) {
		if (tipo.equalsIgnoreCase("proyecto")) {
			// TODO: Eliminar cosas
		} else if (tipo.equalsIgnoreCase("tarea")) {
			// TODO: Eliminar cosas
		}
		
	}


	

}
