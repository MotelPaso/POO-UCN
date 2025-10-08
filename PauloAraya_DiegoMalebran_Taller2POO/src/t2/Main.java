package t2;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Base64;
import java.security.MessageDigest;

public class Main {
	// inicializar listas utiles
    private static ArrayList<User> listaUsuarios = new ArrayList<>();
    private static ArrayList<PC> listaPC = new ArrayList<>();
    private static ArrayList<Puerto> listaPuertos = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        cargarArchivos();
        mostrarMenu(s);
    }
    // leer cada archivo por separado
    private static void cargarArchivos() throws FileNotFoundException {
        Scanner u = new Scanner(new File("usuarios.txt"));
        cargarUsers(u);
        u.close();
        Scanner l = new Scanner(new File("pcs.txt"));
        cargarPCS(l);
        l.close();
        Scanner p = new Scanner(new File("puertos.txt"));
        cargarPuertos(p); // añadir los puertos a cada PC
        p.close();
        Scanner v = new Scanner(new File("vulnerabilidades.txt"));
        cargarVulnerabilidad(v);
        v.close();
        System.out.println("Archivos cargados!");
    }

    private static void cargarUsers(Scanner u) {
        User user = null; // placeholder para que exista "algo" que meterle a la lista
        while (u.hasNextLine()) {
            String[] partes = u.nextLine().split(";");
            String username = partes[0];
            String contra = partes[1]; // hashed
            String rol = partes[2];
            user = new User(username, contra, rol);
            listaUsuarios.add(user);
        }
    }

    private static void cargarPCS(Scanner l) {
        PC pc = null;
        while (l.hasNextLine()) {
        	// se pone \\ porque el | es un operador en java
        	// si no se pone se toma literal y no lee bien el archivo
            String[] partes = l.nextLine().split("\\|");
            String id = partes[0];
            String ip = partes[1];
            String so = partes[2];
            pc = new PC(id, ip, so);
            listaPC.add(pc);
        }
    }

    private static void cargarPuertos(Scanner p) {
        while (p.hasNextLine()) {
            String[] partes = p.nextLine().split("\\|");
            for (PC pc : listaPC) {
                if (pc.getID().equals(partes[0])) { // si la id es igual a la id del archivo
                    pc.addPuerto(Integer.parseInt(partes[1]), partes[2]);
                    // addPuerto(numeroPuerto, abierto/cerrado)
                    break;
                }
            }
        }
    }

    private static void cargarVulnerabilidad(Scanner p) {
        Puerto puerto = null;
        while (p.hasNextLine()) {
            String[] partes = p.nextLine().split("\\|");
            int num = Integer.parseInt(partes[0]);
            String nombre = partes[1];
            String desc = partes[2];
            puerto = new Puerto(num, nombre, desc);
            listaPuertos.add(puerto);
        }
    }

    private static void mostrarMenu(Scanner s) {
    	// Para todos los menus se utilizan strings, aunque se pida un numero
    	// esto es para evitar excepciones y mantener la logica concisa
        String status = "";
        boolean encontrado = false;
        boolean exit = false;
        System.out.print("Bienvenido a SecureNet LTDA\r\n"
                + "Ingrese su usuario: ");
        String user = s.nextLine();
        do {
        	// TODO: Incluir uso de contraseña
        	// un hashmap serviria bastante, pero no se puede utilizar...
            for (User u : listaUsuarios) {
                if (u.getUsername().equals(user)) {
                    encontrado = true;
                    status = u.getAdmin(); 
                    // revisamos el nivel del usuario para mandarlo a cada
                    // menu
                }
            }

            if (!encontrado) {
                System.out.println("Usuario no encontrado, reingrese el usuario o ingrese 0 para salir.");
                user = s.nextLine();
            }
            
            if (user.equals("0")) {
                exit = true;
            }
            
            switch (status) {
                case "ADMIN":
                    exit = menuAdmin(s);
                    break;
                case "USER":
                    exit = menuUser(s);
                    break;
            }
        } while (!exit);
        // utilizamos !exit para que immediatamente se salga del programa al salir
        // del menuAdmin/menuUser.
        System.out.println("Adios!");

    }
    private static boolean menuUser(Scanner s) {
        System.out.println("Encontrado!, user"); // TODO: quitar
        String opcion;
        do {
            System.out.println("**Menu**\r\n"
                    + "0. salir\r\n"
                    + "1. Ver lista de PCs\r\n"
                    + "2. Escanear un PC\r\n"
                    + "3. Ver total de puertos abiertos en todos los PCs de la red\r\n"
                    + "4. Ordenar PCs según ip\r\n");
            System.out.print("ingrese opcion: ");
            opcion = s.nextLine();
            switch (opcion) {
                case "1": {
                    revisarPC();
                    break;
                }
                case "2": {
                    escanearPC();
                    break;
                }
                case "3": {
                    verPuertos();
                    break;
                }
                case "4": {
                    ordenarIPs();
                    break;
                }
                case "0": {
                    break;
                }
                default: {
                    System.out.println("Opcion INVALIDA!!!!");
                }
            }
        } while (!opcion.equals("0"));
        return true;
    }
    private static void revisarPC() {
    	// TODO Auto-generated method stub
    	
    }
    private static void escanearPC() {
    	// TODO Auto-generated method stub
    	
    }

    private static void verPuertos() {
        // TODO Auto-generated method stub

    }
    private static void ordenarIPs() {
    	// TODO Auto-generated method stub
    	
    }
    private static boolean menuAdmin(Scanner s) {
        System.out.println("Encontrado!");
        String opcion = "";
        do {
            System.out.print("===============================\r\n"
                    + "Menu Admin:\r\n"
                    + "1. Mostrar lista completa de PCs.\r\n"
                    + "2. Agregar un PC a la lista.\r\n"
                    + "3. Eliminar un PC de la lista.\r\n"
                    + "4. Mostrar clasificacion de PCS segun riesgo.\r\n"
                    + "0. Volver al menu anterior.\r\n"
                    + "===============================\r\n"
                    + "Ingrese su opcion: ");
            opcion = s.nextLine();
            switch (opcion) {
                case "1":
                    mostrarAdminPC();
                    break;
                case "2":
                    administrarPC(s);
                    break;
                case "3":
                	mostrarClasificacion();
                	break;
                case "0":
                    break;
                default:
                    System.out.print("Opcion invalida, reingrese: ");
            }
        } while (!opcion.equals("0"));
        return true;

    }

    private static void mostrarAdminPC() {
    	System.out.println("Mostrando lista de PCs...");
    	for (PC pc : listaPC) {
			System.out.println(pc);
		}
    }
    private static void administrarPC(Scanner s) {
    	String opcion = "";
    	do {
			System.out.print("=====================\r\n"
					+ "Administración de PC\r\n"
					+ "=====================\r\n"
					+ "1. Agregar un PC\r\n"
					+ "2. Eliminar un PC\r\n"
					+ "0. Volver al menu:\r\n"
					+ "Elija su opcion: ");
			opcion = s.nextLine();
			switch(opcion) {
			case "1":
				agregarPC(s);
				break;
			case "2":
				eliminarPC(s);
				break;
			case "0":
				System.out.println("Volviendo al menu...");
				break;
			default:
				System.out.println("Opcion invalida... Vuelva a elegir!");
			}
		} while (!opcion.equals("0"));
    	
    }
    
    private static void agregarPC(Scanner s) {
    	String ultimoPC = listaPC.get(listaPC.size()-1).getID();
    	// la id de los pc es PCXXX, asi que quitamos el PC, dejando solo numeros
    	int ultimaId = Integer.parseInt(ultimoPC.substring(2)) + 1;
    	String id = "PC0" + ultimaId; // juntamos otra vez el PC con los numeros
    	System.out.print("Ingrese su ip: ");
    	String ip = s.nextLine();
    	System.out.print("Ingrese su Sistema Operativo: ");
    	String SO = s.nextLine();
    	PC nuevo = new PC(id, ip, SO);
    	System.out.println("Los datos de su nuevo pc son:");
    	System.out.println(nuevo.toString());
    	System.out.println("Desea agregar puertos?");
    	// TODO: Add puertos abiertos o cerrados
    	listaPC.add(nuevo);
    	System.out.println("Volviendo al menu...");
    }
    
    private static void eliminarPC(Scanner s) {
    	mostrarAdminPC();
    	System.out.print("Ingrese el id del pc que desea eliminar con este formato: PCXXX ");
    	String busqueda = s.nextLine();
    	if (!busqueda.contains("PC0")) {
    		System.out.println("Id no valida, debe ser del formato PCXXX");
    	}
    	else {
    		boolean encontrado = false;
    		for(PC pc: listaPC ) {
    			if (pc.getID().equals(busqueda)){
    				System.out.println(pc.getID()+ "eliminado.");
    				listaPC.remove(pc); // ya no se puede acceder el pc.
    				encontrado = true;
    				break;
    			}
    		}
    		if (!encontrado) {
    			System.out.println("Id no encontrada...");
    		}
    	}
    }
    private static void mostrarClasificacion() {
    	// TODO Auto-generated method stub
    	
    }

}
