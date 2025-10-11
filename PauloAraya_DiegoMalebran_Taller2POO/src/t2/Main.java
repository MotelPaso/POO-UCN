package t2;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter; // para escribirle al archivo
import java.io.IOException;
import java.security.NoSuchAlgorithmException; // para la contraseña
import java.time.LocalDate; // para mostrar la fecha de hoy


public class Main {
    // inicializar listas de objetos
    private static ArrayList<User> listaUsuarios = new ArrayList<>();
    private static ArrayList<PC> listaPC = new ArrayList<>();
    private static ArrayList<Puerto> listaPuertos = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
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
            pc.calcularClase(); 
            // se hace aqui porque no puede cambiar la ip de un computador
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

            for (Puerto port : listaPuertos) { // buscamos el puerto
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

    private static void mostrarMenu(Scanner s) throws IOException, NoSuchAlgorithmException {
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
            for (User u : listaUsuarios) {
                if (u.getUsername().equals(user)) {
                    usuarioActual = u; // para el log
                    encontrado = usuarioActual.revisarContraseña(s);
                    break;
                    // revisamos el nivel del usuario para mandarlo a su respectivo menu
                }
            }

            if (!encontrado) {
                System.out.println("Credenciales incorrectas, reingrese el usuario o ingrese 0 para salir.");
                user = s.nextLine();
            }
            else {
            	System.out.println("Iniciando sesion...");
            	status = usuarioActual.getAdmin();
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
                default:
                	break;
            }
        } while (!exit);
        // utilizamos !exit para que immediatamente se salga del programa al salir
        // del menuAdmin/menuUser.
        System.out.println("Adios!");

    }

    private static boolean menuUser(Scanner s, User usuarioActual) throws IOException {
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
            pc.mostrar();
        }
    }

    private static void escanearPC(Scanner s, User usuarioActual) throws IOException {
        mostrarPC(); // muestra todos los pcs
        System.out.println("Elige un pc con este formato PCXXX");
        String opcion = s.nextLine();
        boolean encontrado = false;
        PC escaneado = null;
        for (PC pc : listaPC) {
            if (opcion.equals(pc.getID())) {
                encontrado = true;
                escaneado = pc;
            }
        }
        if (!encontrado) {
            System.out.println("PC no encontrado... Volviendo al menu principal");
            return;
        }
        System.out.println("Iniciando escaneo...");
        escaneado.calcularRiesgo(); // se calcula el riesgo
        String usuario = usuarioActual.getUsername();
        LocalDate fecha = LocalDate.now(); // Añade la fecha de hoy
        String datosPC = escaneado.getInfoPC();
        String escaneo = "Fecha escaneo: " + fecha +
                "\nNombre usuario: " + usuario +
                "\nDatos del PC:\n" + datosPC +
                "\n"; // se guardan todos los datos de forma String
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
            escritor.write("====================\n");
            escritor.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error con la escritura al archivo.");
            System.out.println("Revisa si existe en el path ./taller2/reportes.txt");
        }

        System.out.println("Datos exportados exitosamente!");
    }

    private static void verPuertos() {
        ArrayList<Puerto> puertosAbiertos = new ArrayList<>();
        for (PC pc : listaPC) {
            for (Puerto port : pc.getPuertosAbiertos()) {
                /*
                 * lo hacemos asi para estar seguros que los puertos estan abiertos.
                 * podria ser que algun puerto no este abierto en ningun PC
                 * y que este en listaPuertos.
                 */
                if (!puertosAbiertos.contains(port)) {
                    puertosAbiertos.add(port);
                }
            }
        }
        System.out.println("Listado de puertos abiertos:");
        for (Puerto port : puertosAbiertos) {
        	port.mostrar();
        }
    }

    private static void ordenarIPs() {
        ArrayList<PC> listaPCOrdenIP = new ArrayList<>();
        // usar la misma lista cambia el orden en otras funciones
        String[] clases = {"A", "B", "C"};
        for(int i = 0; i < clases.length; i++) {
        	for (PC pc : listaPC) {
        		if (pc.getClase().equals(clases[i])) { 
        			listaPCOrdenIP.add(pc); // primero A, luego B, luego C
        		}
        	}
        }
        System.out.println("PCs ordenados por su clase:");
        for (PC pc: listaPCOrdenIP) {
        	pc.mostrar();
        	System.out.println("Clase: " + pc.getClase());
        }
    }

    private static boolean menuAdmin(Scanner s) {
    	// recordar que se trabaja en string para evitar excepciones
        String opcion = "";
        do {
            System.out.print("===============================\r\n"
                    + "Menu Admin:\r\n"
                    + "1. Mostrar lista completa de PCs.\r\n"
                    + "2. Administrar PCs\r\n"
                    + "3. Mostrar clasificacion de PCS segun riesgo.\r\n"
                    + "0. Salir.\r\n"
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
                    System.out.print("Opcion invalida, reingrese.");
            }
        } while (!opcion.equals("0"));
        return true;

    }

    private static void mostrarAdminPC() {
        System.out.println("Mostrando lista de PCs...");
        for (PC pc : listaPC) {
            pc.mostrarAdmin();
        }
    }

    private static void administrarPC(Scanner s) {
    	// creamos un submenu para mejorar la experiencia de uso :)
        String opcion = "";
        do {
            System.out.print("=====================\r\n"
                    + "Administración de PC\r\n"
                    + "=====================\r\n"
                    + "1. Agregar un PC\r\n"
                    + "2. Eliminar un PC\r\n"
                    + "0. Volver al menu\r\n"
                    + "Elija su opcion: ");
            opcion = s.nextLine();
            switch (opcion) {
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
        String ultimoPC = listaPC.get(listaPC.size() - 1).getID();
        // la id de los pc es PCXXX, asi que quitamos el PC, dejando solo numeros
        int ultimaId = Integer.parseInt(ultimoPC.substring(2)) + 1;
        String id = "PC0" + ultimaId; // juntamos otra vez el PC con los numeros
        System.out.print("Ingrese su ip: ");
        String ip = s.nextLine();
        System.out.print("Ingrese su Sistema Operativo: ");
        String SO = s.nextLine();
        PC nuevo = new PC(id, ip, SO);
        System.out.println("Los datos de su nuevo pc son:");
        nuevo.mostrar();
        System.out.println("Desea agregar puertos? (Y/N)");
        String opcion = s.nextLine();
        if (opcion.equals("Y")) { 
        	System.out.println("Mostrando lista de puertos disponibles: ");
        	for (Puerto port: listaPuertos) {
        		port.mostrar();
        	}
        	ArrayList<Puerto> puertosAbiertos = new ArrayList<>();
        	int numPort = 0; 
        	
        	do {
        		System.out.print("Ingrese los puertos que desea abrir, ingrese -1 para terminar: ");
        		numPort = s.nextInt();
        		s.nextLine();
        		for(Puerto port: listaPuertos) {
        			if (port.getID() == numPort && !puertosAbiertos.contains(port)) {
        				// si existe el puerto y si no esta ya abierto
        				puertosAbiertos.add(port);
        			}
        		}
        	} while (numPort != -1);
        	nuevo.setPuertosAbiertos(puertosAbiertos);
        }
        nuevo.calcularClase();
        nuevo.calcularRiesgo();
        System.out.println("Los datos avanzados de su pc son: ");
        nuevo.mostrarAdmin();
        
        listaPC.add(nuevo);
        System.out.println("Volviendo al menu...");
    }

    private static void eliminarPC(Scanner s) {
        mostrarAdminPC();
        System.out.print("Ingrese el id del pc que desea eliminar con este formato: PCXXX ");
        String busqueda = s.nextLine();
        if (!busqueda.contains("PC")) { // tiene que si o si tener PCXXX
            System.out.println("Id no valida, debe ser del formato PCXXX");
        } else {
            boolean encontrado = false;
            for (PC pc : listaPC) {
                if (pc.getID().equals(busqueda)) {
                    System.out.println(pc.getID() + "eliminado.");
                    listaPC.remove(pc); // ya no se puede acceder al pc.
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
    	// mostramos la clasificacion de cada pc
    	// dependiendo de su cantidad de puertosAbiertos
        ArrayList<PC> riesgoBajo = new ArrayList<>();
        ArrayList<PC> riesgoMedio = new ArrayList<>();
        ArrayList<PC> riesgoAlto = new ArrayList<>();

        for (PC pc : listaPC) {
    		pc.calcularRiesgo();
    		String riesgo = pc.getRiesgo();
    		switch (riesgo) {
        		case "Bajo":
        			riesgoBajo.add(pc);
        			break;
        		case "Medio":
        			riesgoMedio.add(pc);
        			break;
        		case "Alto":
        			riesgoAlto.add(pc);
        			break;
            }
        }
        System.out.println("PC de bajo riesgo:");
        for (PC pc : riesgoBajo) {
            pc.mostrarAdmin();
        }
        System.out.println("==============\nPC de medio riesgo:");
        for (PC pc : riesgoMedio) {
            pc.mostrarAdmin();
        }
        System.out.println("==============\nPC de alto riesgo:");
        for (PC pc : riesgoAlto) {
            pc.mostrarAdmin();
        }

    }

}
