package t2;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.security.MessageDigest;

public class Main {
	// inicializar listas utiles
    private static ArrayList<User> listaUsuarios = new ArrayList<>();
    private static ArrayList<PC> listaPC = new ArrayList<>();
    private static ArrayList<Puerto> listaPuertos = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        Scanner v = new Scanner(new File("vulnerabilidades.txt"));
        cargarPuertos(v); // meter puertos a ArrayList
        v.close();
        Scanner p = new Scanner(new File("puertos.txt"));
        addPuertosAPC(p); // añadir los puertos a cada PC
        p.close();
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
    
    private static void addPuertosAPC(Scanner p) {
        while (p.hasNextLine()) {
            String[] partes = p.nextLine().split("\\|");
            int idPuerto = Integer.parseInt(partes[1]);
            String estado = partes[2];
            Puerto puerto = null;
            
            for (Puerto port: listaPuertos) { // buscamos el puerto
            	if (port.getID() == idPuerto) {
            		puerto = port; // lo asignamos a una variable
            		break;
            	}
            }
            for (PC pc : listaPC) {
                if (pc.getID().equals(partes[0])) { // si la id es igual a la id del archivo
                    pc.addPuerto(puerto, estado); // lo añadimos a su respectivo pc
                    break;
                }
            }
        }
    }

    

    private static void mostrarMenu(Scanner s) throws IOException {
    	// Para todos los menus se utilizan strings, aunque se pida un numero
    	// esto es para evitar excepciones y mantener la logica concisa
        String status = "";
        boolean encontrado = false;
        boolean exit = false;
        System.out.print("Bienvenido a SecureNet LTDA\r\n"
                + "Ingrese su usuario: ");
        String user = s.nextLine();
        User usuarioActual = null;
        do {
        	// TODO: Incluir uso de contraseña
        	// un hashmap serviria bastante, pero no se puede utilizar...
            for (User u : listaUsuarios) {
                if (u.getUsername().equals(user)) {
                	usuarioActual = u;
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
                    exit = menuUser(s, usuarioActual);
                    break;
            }
        } while (!exit);
        // utilizamos !exit para que immediatamente se salga del programa al salir
        // del menuAdmin/menuUser.
        System.out.println("Adios!");

    }
    private static boolean menuUser(Scanner s, User usuarioActual) throws IOException {
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
                    mostrarPC();
                    break;
                }
                case "2": {
                    escanearPC(s, usuarioActual);
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
    private static void mostrarPC() {
    	for (PC pc : listaPC) {
    		pc.calcularRiesgo();
    		pc.mostrar();
    	}
    	
    }
    private static void escanearPC(Scanner s, User usuarioActual) throws IOException {
    	mostrarPC();
    	System.out.println("Elige un pc con este formato PCXXX");
    	String opcion = s.nextLine();
    	boolean encontrado = false;
    	PC escaneado = null;
    	for (PC pc: listaPC) {
    		if (opcion.equals(pc.getID())){
    			encontrado = true;
    			escaneado = pc;
    		}
    	}
    	if (!encontrado) {
    		System.out.println("PC no encontrado... Volviendo al menu principal");
    		return;
    	}
    	System.out.println("Iniciando escaneo...");
    	escaneado.calcularRiesgo(); // el riesgo puede haber cambiado 
    	String usuario = usuarioActual.getUsername();
    	String fecha = "";
    	String datosPC = escaneado.getInfoPC();
    	String escaneo ="Fecha escaneo: " + fecha + 
    			"\nNombre usuario: " + usuario + 
    			"\nDatos del PC:\n" + datosPC +
    			"\n";
    	System.out.println("Escaneo terminado, mostrando datos...");
    	System.out.print(escaneo);
    	System.out.println("Exportando datos a reportes.txt");
    	
    	try {
    		File reportes = new File("reportes.txt");
    		FileWriter escritor = new FileWriter(reportes, true); 
    		// true significa que va a añadir al final del archivo
    		if (!reportes.exists()) {
    			reportes.createNewFile();
    		}
    		escritor.write(escaneo);
    		escritor.write("====================");
    		escritor.close();
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error con la escritura al archivo.");
			System.out.println("Revisa si existe en el path ./taller2/reportes.txt");
		}
    	
    	System.out.println("Datos exportados exitosamente!");
    }

    private static void verPuertos() {
        ArrayList<Puerto> puertosAbiertos = new ArrayList<>();
        for(PC pc: listaPC) {
        	for(Puerto port: pc.getPuertosAbiertos()) {
        		/* 
        		 * lo hacemos asi para estar seguros que los puertos estan abiertos.
        		 * podria ser que algun puerto no este abierto en ningun PC
        		 * y que este en listaPuertos.
        		 */
        		if(!puertosAbiertos.contains(port)) {
        			puertosAbiertos.add(port);
        		}
        	}
        }
        System.out.println("Listado de puertos abiertos:");
        for(Puerto port: listaPuertos) {
        	if (puertosAbiertos.contains(port)) {
        		port.mostrar();
        	}
        }

    }
    private static void ordenarIPs() {

    	int n = listaPC.size();
    	boolean intercambio;
    	
    	for (int i = 0; i < n-1; i++) {
    		intercambio = false;
    		
    		for (int j = 0; j < n-i-1; j++) {
    			PC pc1 = listaPC.get(j);
    			PC pc2 = listaPC.get(j+1);
    			
    			if(compararips(pc1.getIP(),pc2.getIP())>0) {
    				listaPC.set(j, pc2);
    				listaPC.set(j+1, pc1);
    				intercambio = true;	
    		}
    		}
    		
    		if(!intercambio) {
    			break;
    		}
		}    	
    }
    private static int compararips(String ip, String ip2) {
    	
    	String [] p1 = ip.split("\\.");
    	String [] p2 = ip2.split("\\.");

    	ArrayList<Integer> n1 = new ArrayList<>();
    	ArrayList<Integer> n2 = new ArrayList<>();

    	for (int i = 0; i < 4; i++) {
            n1.add(Integer.parseInt(p1[i]));
            n2.add(Integer.parseInt(p2[i]));
        }
    	
    	for (int i = 0; i < 4; i++) {
            int num1 = n1.get(i);
            int num2 = n2.get(i);

            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
    	return 0;
	}
	private static boolean menuAdmin(Scanner s) {
        System.out.println("Encontrado!");
        String opcion = "";
        do {
            System.out.print("===============================\r\n"
                    + "Menu Admin:\r\n"
                    + "1. Mostrar lista completa de PCs.\r\n"
                    + "2. Administrar PCs\r\n"
                    + "3. Mostrar clasificacion de PCS segun riesgo.\r\n"
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
    	ArrayList<PC> riesgoBajo = new ArrayList<>();
    	ArrayList<PC> riesgoMedio = new ArrayList<>();
    	ArrayList<PC> riesgoAlto = new ArrayList<>();
    	
    	for(PC pc: listaPC) {
    		pc.calcularRiesgo();
    		String riesgo = pc.getRiesgo();
    		switch(riesgo) {
    		case "Bajo":
    			riesgoBajo.add(pc);
    		case "Medio":
    			riesgoMedio.add(pc);
    		case "Alto":
    			riesgoAlto.add(pc);
    		}
    	}
    	System.out.println("PC de bajo riesgo:");
    	for(PC pc: riesgoBajo) {
    		pc.mostrar();
    	}
    	System.out.println("==============\nPC de medio riesgo:");
    	for(PC pc: riesgoMedio) {
    		pc.mostrar();
    	}
    	System.out.println("==============\nPC de alto riesgo:");
    	for(PC pc: riesgoAlto) {
    		pc.mostrar();
    	}
    	
    }

}
