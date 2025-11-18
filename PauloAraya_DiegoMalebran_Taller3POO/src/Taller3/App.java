/* Paulo Araya - ICCI
 * 21.918.080-2
 * Diego Malebran - ICCI
 * 21.661.740-1
 * 
 */
package Taller3;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

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
			System.out.print("--- MENÚ ADMINISTRADOR ---\r\n"
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
				menuAdminProyectos(sistema, s);
				break;
			}
			case "3":{
				menuAdminTareas(sistema, s);
				break;
			}
			case "4": {
				System.out.println("Elige la estrategia para asignar prioridades:");
				System.out.println("1. Estrategia por fecha. (mas antigua primero)" + 
								"\n2. Estrategia por impacto. (Bug, Feature, Documentacion)" +
								"\n3. Estrategia por complejidad. (menos compleja primero)" +
								"\n0. Mantener la estrategia actual.");
				String nuevaEstrategia = s.nextLine();
				System.out.println(sistema.cambiarStrategy(nuevaEstrategia));
				break;
			}
			case "5":{
				System.out.println("Generando reportes...");
				System.out.println(sistema.generarReportes());
				break;
			}
			case "0":{
				System.out.println("Adios!");
				break;
			}
			default:
				System.out.println("Opcion no encontrada, volviendo al menu...");
			}
		} while (!opcion.equals("0"));
	}
	
	private static void menuAdminProyectos(Sistema sistema, Scanner s) {
		System.out.println(sistema.mostrarProyectos());
		System.out.println("Elige una opcion: \n" + 
						"1. Agregar un proyecto \n" + 
						"2. Eliminar un proyecto \n"
						+ "Ingrese su opcion: ");
		String respuesta = s.nextLine();

		switch (respuesta) {
		case "1" -> agregar(sistema, s, "proyecto");
		case "2" -> eliminar(sistema, s, "proyecto");
		default -> System.out.println("Opcion no encontrada, volviendo al menu...");
		}
	}

	private static void menuAdminTareas(Sistema sistema, Scanner s) {
		System.out.println(sistema.mostrarTareas());
		System.out.println("Elige una opcion: \n" +
						"1. Agregar una tarea \n" +
						"2. Eliminar una tarea \n" +
						"Ingrese su opcion: ");
    	String respuesta = s.nextLine().toLowerCase();
    	
    	switch(respuesta) {
    		case "1" -> agregar(sistema , s, "tarea");
    		case "2" -> eliminar(sistema, s, "tarea");
    		default -> System.out.println("Opcion no encontrada, volviendo al menu...");
    	}
	}

	private static void agregar(Sistema sistema, Scanner s, String tipo) {
		if (tipo.equalsIgnoreCase("proyecto")) {
			System.out.print("Ingrese el nombre del nuevo proyecto: ");
			String nombre = s.nextLine();
			System.out.print("Ingrese su responsable: ");
			String responsable = s.nextLine();
			sistema.guardarProyectos(new String[] {"",nombre,responsable});
			System.out.println("Proyecto creado exitosamente.");
			
		} else if (tipo.equalsIgnoreCase("tarea")) {
			System.out.println(sistema.mostrarProyectos());
			System.out.println("Elija un proyecto por su id: (PRXXX)");
			String idProyecto = s.nextLine();
			System.out.print("Ingrese el tipo de la tarea: (Bug, Feature, Complejidad) ");
			String tipoTarea = s.nextLine();
			System.out.print("Descripcion: ");
			String descripcion = s.nextLine();
			String estado = "Pendiente";
			System.out.print("Colaborador responsable: ");
			String colab = s.nextLine();
			System.out.println("Complejidad: (Alta, Media, Baja)");
			String complejidad = s.nextLine();
			String fecha = LocalDate.now().toString();
			sistema.guardarTareas(new String[] {idProyecto,"",tipoTarea, descripcion, estado, colab, complejidad, fecha});
			System.out.println("Tarea creada exitosamente.");
		}
	}

	private static void eliminar(Sistema sistema, Scanner s, String tipo) {
		if (tipo.equalsIgnoreCase("proyecto")) {
			System.out.println(sistema.mostrarProyectos());
			System.out.print("Elige el proyecto por su id: ");
			String id = s.nextLine();
			System.out.println("Esta seguro que quiere eliminar el proyecto? (Y/N)");
			String respuesta = s.nextLine();
			
			switch (respuesta.toLowerCase()){
				case "y": {
					System.out.println(sistema.eliminarProyecto(id));
					break;
				}
				case "n": {
					System.out.println("Accion cancelada, volviendo al menu...");
				}
			}
			
		} else if (tipo.equalsIgnoreCase("tarea")) {
			System.out.println(sistema.mostrarProyectosyTareas());
			System.out.print("Elija la tarea por su id: ");
			String id = s.nextLine();
			System.out.println("Esta seguro que quiere eliminar la tarea? (Y/N)");
			String respuesta = s.nextLine();
			
			switch (respuesta.toLowerCase()){
				case "y": {
					System.out.println(sistema.eliminarTarea(id));
					break;
				}
				case "n": {
					System.out.println("Accion cancelada, volviendo al menu...");
				}
			}
		}
	}
}
