package t2;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    private static ArrayList<User> listaUsuarios = new ArrayList<>();
    private static ArrayList<PC> listaPC = new ArrayList<>();
    private static ArrayList<Puerto> listaPuertos = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        cargarArchivos();
        mostrarMenu(s);
    }

    private static void cargarArchivos() throws FileNotFoundException {
        Scanner u = new Scanner(new File("usuarios.txt"));
        cargarUsers(u);
        u.close();
        Scanner l = new Scanner(new File("pcs.txt"));
        cargarPCS(l);
        l.close();
        Scanner p = new Scanner(new File("puertos.txt"));
        cargarPuertos(p);
        p.close();
        Scanner v = new Scanner(new File("vulnerabilidades.txt"));
        cargarVulnerabilidad(v);
        v.close();
        System.out.println("Archivos cargados!");
    }

    private static void cargarUsers(Scanner u) {
        User user = null; // quitar error en add a lista
        while (u.hasNextLine()) {
            String[] partes = u.nextLine().split(";");
            String username = partes[0];
            String contra = partes[1];
            String rol = partes[2];
            user = new User(username, contra, rol);
            listaUsuarios.add(user);
        }
    }

    private static void cargarPCS(Scanner l) {
        PC pc = null;
        while (l.hasNextLine()) {
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
                if (pc.getID().equals(partes[0])) {
                    pc.addPuerto(Integer.parseInt(partes[1]), partes[2]);
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
        String status = "";
        boolean encontrado = false;
        boolean exit = false;
        System.out.print("Bienvenido a SecureNet LTDA\r\n"
                + "Ingrese su usuario: ");
        String user = s.nextLine();
        do {
            for (User u : listaUsuarios) {
                if (u.getUsername().equals(user)) {
                    encontrado = true;
                    status = u.getAdmin();
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
        System.out.println("Adios!");

    }

    private static boolean menuUser(Scanner s) {
        System.out.println("Encontrado!, user");
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
                case "2":
                    agregarPC(s);
                case "3":
                    eliminarPC(s);
                case "4":
                    mostrarClasificacion();
                case "0":
                    break;
                default:
                    System.out.print("Opcion invalida, reingrese: ");
                    ;
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
    private static void agregarPC(Scanner s) {
    	// TODO Auto-generated method stub
    	
    }
    private static void eliminarPC(Scanner s) {
        // TODO Auto-generated method stub

    }


    private static void mostrarClasificacion() {
    	// TODO Auto-generated method stub
    	
    }

}
