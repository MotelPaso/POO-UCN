package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import dominio.Sistema;
import dominio.SistemaImpl;

public class GUI extends JFrame {

	private Sistema sistema = SistemaImpl.getInstancia();

	public GUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
	}

	public void inicioSesion() {
		JPanel inicio = new JPanel();
		inicio.setLayout(new BoxLayout(inicio, BoxLayout.PAGE_AXIS));
		inicio.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel userLabel = new JLabel("Ingrese aqui su correo / nombre de usuario");
		JTextField username = new JTextField(20);
		JLabel passLabel = new JLabel("Ingrese aqui su contraseña");
		JTextField password = new JTextField(30);
		JButton login = new JButton("Ingresar");
		JLabel error = new JLabel();

		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.setAlignmentX(Component.CENTER_ALIGNMENT);

		username.setMaximumSize(new Dimension(300, 50));
		password.setMaximumSize(new Dimension(300, 50));
		username.setText("sofia.morales@alumnos.ucn.cl");
		password.setText("sofia222");
		login.addActionListener((e) -> {
			String[] datosUsuario = { username.getText(), password.getText() };
			boolean logged = sistema.revisarUsuario(datosUsuario);
			if (logged) {
				int acceso = sistema.getNivelAcceso(datosUsuario);
				getContentPane().removeAll();
				switch (acceso) {
				case 1 -> menuAdmin(datosUsuario[0]);
				case 2 -> menuCoordinador(datosUsuario[0]);
				case 3 -> menuEstudiante(datosUsuario[0]);
				}
				revalidate();
				repaint();
			} else {
				username.setText("");
				password.setText("");
				error.setText("Credenciales incorrectas...");
			}
		});

		inicio.add(userLabel);
		inicio.add(username);
		inicio.add(passLabel);
		inicio.add(password);
		inicio.add(login);
		inicio.add(error);

		getContentPane().add(inicio);
		setVisible(true);
	}

	public void menuEstudiante(String correo) {
		String nombres = correo.split("@")[0]; // nombre.apellido
		JLabel titulo = new JLabel("¡Bienvenido " + nombres.split("\\.")[0] + " " + nombres.split("\\.")[1] + "!");
		JButton logout = new JButton("Cerrar Sesión");
		logout.addActionListener((e) -> {
			dispose();
		});
		JPanel menuEstudiante = new JPanel(new BorderLayout());

		JButton perfil = new JButton("Ver mi Perfil");
		perfil.addActionListener((e) -> {
			mostrarPerfil(correo);
			revalidate();
			repaint();
		});

		JButton malla = new JButton("Ver mi Malla");
		malla.addActionListener((e) -> {
			mostrarMalla(correo);
			revalidate();
			repaint();

		});
		JButton inscripcion = new JButton("Inscribirme a una Certificacion");
		inscripcion.addActionListener((e) -> {

		});
		JButton progreso = new JButton("Ver mi Progreso en mi Certificacion");
		progreso.addActionListener((e) -> {

		});
		JPanel botonera = new JPanel();

		botonera.setLayout(new BoxLayout(botonera, BoxLayout.PAGE_AXIS));
		botonera.add(perfil);
		botonera.add(malla);
		botonera.add(inscripcion);
		botonera.add(progreso);

		menuEstudiante.add(titulo, BorderLayout.NORTH);
		menuEstudiante.add(botonera, BorderLayout.CENTER);
		menuEstudiante.add(logout, BorderLayout.SOUTH);
		getContentPane().add(menuEstudiante);
		setSize(400, 400);
	}
	
	private void mostrarPerfil(String correo) {
		getContentPane().removeAll();
		// get datos del sistema
		String[] informacionEstudiante = sistema.getInformacionEstudiante(correo);
		double[] promedios = sistema.getPromediosEstudiante(correo);

		JPanel main = new JPanel(new BorderLayout());

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		
		JLabel perfil = new JLabel(informacionEstudiante[0]);
		leftPanel.add(perfil);
		JLabel mostrarMalla = new JLabel(informacionEstudiante[1]);
		rightPanel.add(mostrarMalla);

		String datosPromedios = "<html>Promedios por semestre: <br>";
		JLabel promedioGeneral = new JLabel("Promedio General: " + promedios[0]);
		for (int i = 1; i < promedios.length; i++) {
			datosPromedios += "Promedio de " + Math.round(promedios[i] * 100.0) / 100.0     + " en el semestre N" + i + ".<br>";
		}

		JLabel promediosPerSemestre = new JLabel(datosPromedios + "</html>");

		JButton volver = new JButton("Volver al menu principal");
		volver.addActionListener((e) -> {
			getContentPane().removeAll();
			menuEstudiante(correo);
			revalidate();
		    repaint();
		});
		leftPanel.add(promedioGeneral);
		leftPanel.add(promediosPerSemestre);

		main.add(leftPanel, BorderLayout.WEST);
		main.add(rightPanel, BorderLayout.EAST);
		main.add(volver, BorderLayout.SOUTH);

		getContentPane().add(main);
		setSize(750,600);
		revalidate();
		repaint();
	}
	
	private void mostrarMalla(String correo) {

	    getContentPane().removeAll();
	    String[][] ramosMalla = sistema.getMalla();
	    ArrayList<String> cursados = sistema.getCursados(correo);
	    ArrayList<String> enProceso = sistema.getEnProceso(correo);
	    String[] numeroSemestres = {"1","2","3","4","5","6","7","8"};

	    JPanel main = new JPanel(new BorderLayout());
	    JLabel titulo = new JLabel("Malla curricular:");
	    javax.swing.JTable tablaMalla = new javax.swing.JTable(ramosMalla, numeroSemestres);
	    tablaMalla.setRowHeight(40);

	    tablaMalla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
	        @Override
	        public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            
	            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	            String nombreRamo = (String) value;

	            if (!isSelected) {
	                if (nombreRamo != null && nombreRamo.trim().isEmpty()) {
	                    c.setBackground(Color.WHITE); 
	                } else if (nombreRamo != null && cursados.contains(nombreRamo)) {
	                    c.setBackground(Color.GREEN); // ramo pasado verde
	                } else if (nombreRamo != null && enProceso.contains(nombreRamo)) {
	                    c.setBackground(Color.YELLOW); // ramo en proceso weko
	                } else {
	                    c.setBackground(Color.WHITE); // ramo echado rojo
	                }
	            }
	            return c;
	        }
	    });
	    
	    JScrollPane scrollPane = new JScrollPane(tablaMalla); 
	    
	    JButton volver = new JButton("Volver al Menú Estudiante");
	    volver.addActionListener(e -> {
	        getContentPane().removeAll();
	        menuEstudiante(correo);
	        revalidate();
	        repaint();
	    });
	    
	    main.add(titulo, BorderLayout.NORTH);
	    main.add(scrollPane, BorderLayout.CENTER);
	    main.add(volver, BorderLayout.SOUTH);
	    
	    getContentPane().add(main);
	    setSize(750,600);
	    revalidate();
	    repaint();
	}

	public void menuCoordinador(String username) {
		JLabel coor = new JLabel("¡Bienvenido " + username + "!");
		JButton logout = new JButton("Cerrar Sesión");
		JButton gestionCertificados = new JButton("Gestion de Cerficaciones");
		JButton metricas = new JButton("Panel de metricas y analisis");
		JButton gestionEstudiantes = new JButton("Gestion de Estudiantes");

		JPanel menuCoordinador = new JPanel(new BorderLayout());
		JPanel botonera = new JPanel();

		botonera.setLayout(new BoxLayout(botonera, BoxLayout.PAGE_AXIS));
		botonera.add(gestionCertificados);
		botonera.add(metricas);
		botonera.add(gestionEstudiantes);

		menuCoordinador.add(coor, BorderLayout.NORTH);
		menuCoordinador.add(botonera, BorderLayout.CENTER);
		menuCoordinador.add(logout, BorderLayout.SOUTH);

		getContentPane().add(menuCoordinador);
	}

	public void menuAdmin(String username) {
		JLabel admin = new JLabel("¡Bienvenido " + username + "!");
		JButton logout = new JButton("Cerrar Sesión");
		JButton crear = new JButton("Crear cuentas");
		JButton modificar = new JButton("Modificar cuentas");
		JButton eliminar = new JButton("Eliminar cuenta");
		JButton restablecer = new JButton("Restablecer cuenta");

		JPanel menuAdmin = new JPanel(new BorderLayout());
		JPanel botonera = new JPanel();

		botonera.setLayout(new BoxLayout(botonera, BoxLayout.PAGE_AXIS));
		botonera.add(crear);
		botonera.add(modificar);
		botonera.add(eliminar);
		botonera.add(restablecer);

		menuAdmin.add(admin, BorderLayout.NORTH);
		menuAdmin.add(botonera, BorderLayout.CENTER);
		menuAdmin.add(logout, BorderLayout.SOUTH);

		getContentPane().add(menuAdmin);
	}
}
