package Taller3;

import java.util.*;
import java.io.*;

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		Sistema sistema = SistemaImpl.getInstancia();
		cargarArchivos(sistema);
		menu(sistema);
		
		
	}

	private static void menu(Sistema sistema) {
		
		Scanner s = new Scanner(System.in);
		
		Usuario logueado = null;
		
		do {
			
			System.out.println("--- INICIO DE SESIÓN ---");
	        System.out.print("Username: ");
	        String username = s.nextLine();
	        System.out.print("Contraseña: ");
	        String password = s.nextLine();
	        
	        logueado = sistema.login(username, password);
			
			
			
		}while(logueado == null);
		
		System.out.println("¡Bienvenido, " + logueado.getUsername() + "!");
		
		if ("Administrador".equalsIgnoreCase(logueado.getRol())) {
		    menuAdministrador(sistema, (Administrador) logueado, s);
		} else { 
		    menuColaborador(sistema, (Colaborador) logueado, s);
		}
			

	}

	private static void menuColaborador(Sistema sistema, Colaborador logueado, Scanner s) {
	
		String opcion ;
		
		do {
			
			System.out.println();
			System.out.println("--- MENÚ COLABORADOR ---");
	        System.out.println("1 Ver proyectos disponibles");
	        System.out.println("2 Ver tareas asignadas");   
	        System.out.println("3 Actualizar estado de una tarea"); 
	        System.out.println("0 Cerrar Sesión");
	        System.out.print("Seleccione una opción: ");
	        opcion = s.nextLine();
	        
	        switch(opcion) {
	        
	        case "1":{
	        	
	        	String listaProyectos = sistema.verProyectosDisponibles();
                System.out.println(listaProyectos);
                break;
	        	
	        }
	        
	        case "2":{
	        	
	        	System.out.println("Quieres Agregar o Eliminar: ");
	        	
	        	String respuesta = s.nextLine().toLowerCase();
	        	
	        	
	        	switch(respuesta) {
	        	
	        	
	        		case "eliminar":{
	        			
	        	   		String listaProyectos = sistema.verProyectosDisponibles();
		        		System.out.println(listaProyectos);
	        			
		        		break;
	        		

	        		}
	        	
	        	}
	        	
	        	
	        	break;
	        	
	       }
	        }

		}while(!opcion.equals("0"));
		
	}

	private static void menuAdministrador(Sistema sistema, Administrador logueado, Scanner s) {
		// TODO Auto-generated method stub
		
	}

	private static void cargarArchivos(Sistema sistema) throws FileNotFoundException {
		
		Scanner s = new Scanner(new File("usuarios.txt"));
		
		while(s.hasNextLine()) {
			
			String [] p = s.nextLine().strip().split("\\|");
			sistema.crearUsuarios(p);
		}
		
		s.close();
		
		s = new Scanner(new File("proyectos.txt"));
		
		while(s.hasNextLine()) {
						
			String [] pp = s.nextLine().strip().split("\\|");
		

			String id = pp[0];
			String nombre = pp[1];
			String responsable = pp[2];
			
			sistema.guardarProyectos(id,nombre,responsable);
				
			
			
		}
		
		s.close();
		
		s= new Scanner(new File("tareas.txt"));
		
		
		while(s.hasNextLine()) {
			
			
			String [] p = s.nextLine().strip().split("\\|");
			
			sistema.guardarTareas(p);
			
		}
	}

}
