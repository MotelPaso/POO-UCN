/* Paulo Araya Rojo
 * 21.918.080-2
 * Diego Malebran
 * 21.661.740-1
 * ICCI 
 */


package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

import dominio.Sistema;
import dominio.SistemaImpl;

public class GUI extends JFrame {
	
	private Sistema sistema = SistemaImpl.getInstancia();

	public GUI() {
		setTitle("Sistema Curricular UCN");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
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
		username.setText("juan.perez@alumnos.ucn.cl");
		password.setText("contraseña123");
		login.addActionListener((_) -> {
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
		logout.addActionListener((_) -> {
			dispose();
		});
		JPanel menuEstudiante = new JPanel(new BorderLayout());

		JButton perfil = new JButton("Ver mi Perfil");
		perfil.addActionListener((_) -> {
			mostrarPerfil(correo);
			revalidate();
			repaint();
		});

		JButton malla = new JButton("Ver mi Malla");
		malla.addActionListener((_) -> {
			mostrarMalla(correo);
			revalidate();
			repaint();

		});
		JButton inscripcion = new JButton("Inscribirme a una Certificacion");
		inscripcion.addActionListener((_) -> {
			mostrarInscripcion(correo);
			revalidate();
			repaint();

		});
		JButton progreso = new JButton("Ver mi Progreso en mi Certificacion");
		progreso.addActionListener((_) -> {
			mostrarProgresoCertificaciones(correo);
			revalidate();
			repaint();
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
		setLocationRelativeTo(null);
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
		JLabel promedioGeneral = new JLabel("Promedio General: " + Math.round(promedios[0] * 100.0) / 100.0);
		for (int i = 1; i < promedios.length; i++) {
			datosPromedios += "Promedio de " + Math.round(promedios[i] * 100.0) / 100.0 + " en el semestre N" + i
					+ ".<br>";
		}

		JLabel promediosPerSemestre = new JLabel(datosPromedios + "</html>");

		JButton volver = new JButton("Volver al menu principal");
		volver.addActionListener((_) -> {
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
		setLocationRelativeTo(null);
		setSize(750, 600);
		revalidate();
		repaint();
	}

	private void mostrarMalla(String correo) {

		getContentPane().removeAll();
		String[][] ramosMalla = sistema.getMalla();
		ArrayList<String> cursados = sistema.getCursados(correo);
		ArrayList<String> enProceso = sistema.getEnProceso(correo);
		String[] numeroSemestres = { "1", "2", "3", "4", "5", "6", "7", "8" };

		JPanel main = new JPanel(new BorderLayout());
		JLabel titulo = new JLabel("Malla curricular:");
		javax.swing.JTable tablaMalla = new javax.swing.JTable(ramosMalla, numeroSemestres);
		tablaMalla.setRowHeight(40);

		// TODO: entender como funciona esto
		tablaMalla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				String nombreRamo = (String) value;

				if (!isSelected) {
					if (nombreRamo != null && cursados.contains(nombreRamo)) {
						c.setBackground(Color.GREEN); // ramo pasado verde
					} else if (nombreRamo != null && enProceso.contains(nombreRamo)) {
						c.setBackground(Color.YELLOW); // ramo en proceso weko
					} else {
						c.setBackground(Color.WHITE);
					}
				}
				return c;
			}
		});

		JScrollPane scrollPane = new JScrollPane(tablaMalla);

		JButton volver = new JButton("Volver al Menú Estudiante");
		volver.addActionListener(_ -> {
			getContentPane().removeAll();
			menuEstudiante(correo);
			revalidate();
			repaint();
		});

		main.add(titulo, BorderLayout.NORTH);
		main.add(scrollPane, BorderLayout.CENTER);
		main.add(volver, BorderLayout.SOUTH);

		getContentPane().add(main);
		setSize(1050, 700);
		setLocationRelativeTo(null);
		revalidate();
		repaint();
	}

	private void mostrarInscripcion(String correo) {
		getContentPane().removeAll();
		String datosCertificaciones = sistema.getDatosCertificaciones(correo);

		JLabel titulo = new JLabel("<html><h3>Inscripcion a Certificaciones</h3></html>");
		JPanel main = new JPanel(new BorderLayout());
		JPanel top = new JPanel();
		JPanel bot = new JPanel();
		bot.setLayout(new BoxLayout(bot, BoxLayout.LINE_AXIS));

		JLabel lineasDisponibles = new JLabel(datosCertificaciones);
		top.add(lineasDisponibles); 

		
		// Lógica para elegir e inscribir (Strategy)
		JLabel labelSeleccion = new JLabel("Seleccione la certificación a inscribir");
		String[] lineas = {"Sistemas Inteligentes", "Ciberseguridad", "Desarrollo de Software"};
		JComboBox<String> lineaSelect = new JComboBox<>(lineas);
		JButton inscribir = new JButton("Inscribir");
		JLabel resultado = new JLabel("");
		
		inscribir.addActionListener((_) -> {
			String mensaje = sistema.inscribirCertificacion(correo, (String) lineaSelect.getSelectedItem()); 
			
			resultado.setText("<html>" + mensaje + "</html>");
		});

		
		bot.add(labelSeleccion);
		bot.add(lineaSelect);
		bot.add(inscribir);
		bot.add(resultado);
		
		JButton volver = new JButton("Volver al menu principal");
		volver.addActionListener((_) -> {
			getContentPane().removeAll();
			menuEstudiante(correo);
			revalidate();
			repaint();
		});

		top.add(bot);
		main.add(titulo, BorderLayout.NORTH);
		main.add(top, BorderLayout.CENTER);
		main.add(volver, BorderLayout.SOUTH);

		getContentPane().add(main);
		setSize(1250, 1050);
		setLocationRelativeTo(null);
		revalidate();
		repaint();

	}
	
	private void mostrarProgresoCertificaciones(String correo) {
		getContentPane().removeAll();
		
		String progreso = sistema.getProgresoCertificaciones(correo);
		
		JPanel main = new JPanel(new BorderLayout());
		JLabel datosProgreso = new JLabel(progreso);
		
		JButton volver = new JButton("Volver al menu principal");
		volver.addActionListener((_) -> {
			getContentPane().removeAll();
			menuEstudiante(correo);
			revalidate();
			repaint();
		});
		main.add(datosProgreso, BorderLayout.CENTER);
		main.add(volver, BorderLayout.SOUTH);
		
		getContentPane().add(main);
		setSize(800, 700);
		setLocationRelativeTo(null);
		revalidate();
		repaint();
	}

	public void menuCoordinador(String username) {
		JLabel coor = new JLabel("¡Bienvenido " + username + "!");
		JButton logout = new JButton("Cerrar Sesión");
		logout.addActionListener((_) -> {
			dispose();
		});
		JButton gestionCertificados = new JButton("Gestion de Cerficaciones");
		JButton metricas = new JButton("Panel de metricas y analisis");
		JButton gestionEstudiantes = new JButton("Gestion de Estudiantes");

		gestionCertificados.addActionListener((_) -> {
			gestionCertificados(username);
			revalidate();
			repaint();
		});
		
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

	private void gestionCertificados(String username) {
		getContentPane().removeAll();
		
		String estudiantes = sistema.getEstudiantesCompletados();
		
		JPanel main = new JPanel(new BorderLayout());
		JPanel top = new JPanel(new BorderLayout());
		JPanel bot = new JPanel(new BorderLayout());
		bot.setLayout(new BoxLayout(bot, BoxLayout.LINE_AXIS));
		
		JLabel estudiantesCompletados = new JLabel("<html> Estudiantes Completados: <br>" + estudiantes );
		
		JButton generar = new JButton("Generar certificados");
		generar.addActionListener((_) -> sistema.generarCertificados());
		
		top.add(estudiantesCompletados, BorderLayout.WEST);
		top.add(generar, BorderLayout.EAST);
		
		JLabel tituloModificar = new JLabel("Elija la linea que desea modificar");
		String[] lineasDisponibles = {"Sistemas Inteligentes", "Ciberseguridad", "Desarrollo de Software"};
		JComboBox<String> lineaSelect = new JComboBox<>(lineasDisponibles);
		JButton modificar = new JButton("Modificar");
		
		lineaSelect.setMaximumSize(new Dimension(300,50));
		modificar.setMaximumSize(new Dimension(100,50));

		
		bot.add(tituloModificar);
		bot.add(lineaSelect);
		bot.add(modificar);
		
		modificar.addActionListener((_) -> {
			modificarCertificados(username, (String) lineaSelect.getSelectedItem());
			revalidate();
			repaint();
		});;
		
		JButton volver = new JButton("Volver al Menú Coordinador");
		volver.addActionListener(_ -> {
			getContentPane().removeAll();
			menuCoordinador(username);
			revalidate();
			repaint();
		});
		
		main.add(top, BorderLayout.NORTH);
		main.add(bot, BorderLayout.CENTER);
		main.add(volver, BorderLayout.SOUTH);
		
		getContentPane().add(main);
		setSize(600,400);
		
	}

	private void modificarCertificados(String username, String lineaSelect) {
		getContentPane().removeAll();
		
		JPanel main = new JPanel(new BorderLayout());
		
		JLabel estudiantesCompletados = new JLabel();
		
		JButton aceptar = new JButton("Cambiar linea de certificados");
		
		aceptar.addActionListener((_) -> {
			
		});
		
	}

	public void menuAdmin(String username) {
		JLabel admin = new JLabel("¡Bienvenido " + username + "!");
		JButton logout = new JButton("Cerrar Sesión");
		logout.addActionListener((_) -> {
			dispose();
		});
		JButton crear = new JButton("Crear cuentas");
		JButton modificar = new JButton("Modificar cuentas");
		JButton eliminar = new JButton("Eliminar cuenta");

		JPanel menuAdmin = new JPanel(new BorderLayout());
		JPanel botonera = new JPanel();

		botonera.setLayout(new BoxLayout(botonera, BoxLayout.PAGE_AXIS));
		crear.addActionListener((_) -> {
			mostrarCrear();

		});
		modificar.addActionListener((_) -> {
			mostrarModificar(username);
			revalidate();
			repaint();
		});
		eliminar.addActionListener((_) -> {
			mostrarEliminar(username);
			revalidate();
			repaint();

		});
		botonera.add(crear);
		botonera.add(modificar);
		botonera.add(eliminar);

		menuAdmin.add(admin, BorderLayout.NORTH);
		menuAdmin.add(botonera, BorderLayout.CENTER);
		menuAdmin.add(logout, BorderLayout.SOUTH);

		getContentPane().add(menuAdmin);
		setLocationRelativeTo(null);
	}

	private void mostrarCrear() {

	}

	private void mostrarModificar(String adminActual) {
		getContentPane().removeAll();

		JPanel main = new JPanel(new BorderLayout());
		main.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JPanel buscar = new JPanel();
		buscar.setLayout(new BoxLayout(buscar, BoxLayout.PAGE_AXIS));
		JLabel tituloBusqueda = new JLabel("Ingrese el nombre de la cuenta que desea modificar");
		JTextField username = new JTextField(20);
		username.setMaximumSize(new Dimension(300, 50));
		tituloBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);

		buscar.add(tituloBusqueda);
		buscar.add(username);
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
		JButton cancelar = new JButton("Cancelar");
		JButton aceptar = new JButton("Buscar cuenta");
		botonera.add(aceptar);
		botonera.add(cancelar);
		
		JLabel error = new JLabel("");
		buscar.add(error);
		error.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		aceptar.addActionListener((_) -> {
			switch (sistema.buscarCuenta(username.getText())) {
			case 0 -> error.setText("Cuenta no encontrada en el sistema.");
			case 1 -> mostrarPanelModificarEstudiante(username.getText(), adminActual);
			case 2 -> mostrarPanelModificarCoordinador(username.getText(), adminActual);
			case 3 -> error.setText("No puedes cambiarle los datos a otro administrador.");
			}
		});
		
		
		cancelar.addActionListener((_) -> {
			getContentPane().removeAll();
			menuAdmin(adminActual);
			revalidate();
			repaint();
		});
		main.add(buscar, BorderLayout.CENTER);
		main.add(botonera, BorderLayout.SOUTH);

		getContentPane().add(main);
		setLocationRelativeTo(null);
	}

	private void mostrarPanelModificarCoordinador(String nombre, String adminActual) {
		revalidate();
		repaint();
		getContentPane().removeAll();
		JPanel main = new JPanel(new BorderLayout());
		JPanel modificar = new JPanel();
		modificar.setLayout(new BoxLayout(modificar, BoxLayout.PAGE_AXIS));
		
		JLabel titulo = new JLabel("Rellene unicamente los campos a modificar.");
		JLabel labelNombre = new JLabel("Nuevo nombre de usuario:");
		JTextField username = new JTextField(20);
		
		JLabel labelPass = new JLabel("Nueva contraseña:");
		JTextField password = new JTextField(20);
		
		String[] areas = {"Sistemas Inteligentes", "Ciberseguridad", "Desarrollo de Software"};
		JLabel labelArea = new JLabel("Cambio de area:");
		JComboBox<String> areaSelect = new JComboBox<>(areas);
		
		JLabel cambio = new JLabel("");
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
		JButton cancelar = new JButton("Cancelar");
		JButton aceptar = new JButton("Realizar cambios");
		botonera.add(aceptar);
		botonera.add(cancelar);
		
		cancelar.addActionListener((_) -> {
			getContentPane().removeAll();
			menuAdmin(adminActual);
			revalidate();
			repaint();
		});
		
		aceptar.addActionListener((_) -> {
			String[] nuevosDatos = {username.getText(), password.getText(), (String) areaSelect.getSelectedItem()};
			sistema.modificarCuentas(nombre, nuevosDatos);
			cambio.setText("Cambio realizado correctamente.");
		});
		
		username.setMaximumSize(new Dimension(300, 50));
		password.setMaximumSize(new Dimension(300, 50));
		areaSelect.setMaximumSize(new Dimension(300, 50));
		
		modificar.add(titulo);
		modificar.add(labelNombre);
		modificar.add(username);
		modificar.add(labelPass);
		modificar.add(password);
		modificar.add(labelArea);
		modificar.add(areaSelect);
		modificar.add(cambio);
		
		main.add(modificar, BorderLayout.CENTER);
		main.add(botonera, BorderLayout.SOUTH);
		
		getContentPane().add(main);
		setLocationRelativeTo(null);
		revalidate();
		repaint();
	}

	private void mostrarPanelModificarEstudiante(String nombre, String adminActual) {
		revalidate();
		repaint();
		getContentPane().removeAll();
		JPanel main = new JPanel(new BorderLayout());
		JPanel modificar = new JPanel();
		modificar.setLayout(new BoxLayout(modificar, BoxLayout.PAGE_AXIS));
		JLabel titulo = new JLabel("Rellene unicamente los campos a modificar.");
		JLabel labelNombre = new JLabel("Nuevo nombre de usuario:");
		JTextField username = new JTextField(20);
		
		JLabel labelPass = new JLabel("Nueva contraseña:");
		JTextField password = new JTextField(20);
		
		JLabel cambio = new JLabel("");
		
		JPanel botonera = new JPanel();
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
		JButton cancelar = new JButton("Cancelar");
		JButton aceptar = new JButton("Realizar cambios");
		botonera.add(aceptar);
		botonera.add(cancelar);
		
		cancelar.addActionListener((_) -> {
			getContentPane().removeAll();
			menuAdmin(adminActual);
			revalidate();
			repaint();
		});
		
		aceptar.addActionListener((_) -> {
			String[] nuevosDatos = {username.getText(), password.getText()};
			sistema.modificarCuentas(nombre, nuevosDatos);
			cambio.setText("Cambio realizado correctamente.");
		});
		
		username.setMaximumSize(new Dimension(300, 50));
		password.setMaximumSize(new Dimension(300, 50));
		
		modificar.add(titulo);
		modificar.add(labelNombre);
		modificar.add(username);
		modificar.add(labelPass);
		modificar.add(password);
		modificar.add(cambio);
		
		main.add(modificar, BorderLayout.CENTER);
		main.add(botonera, BorderLayout.SOUTH);
		
		getContentPane().add(main);
		setLocationRelativeTo(null);
		revalidate();
		repaint();
		
	}

	private void mostrarEliminar(String adminActual) {
		getContentPane().removeAll();

		JPanel main = new JPanel(new BorderLayout());
		main.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel buscar = new JPanel();
		buscar.setLayout(new BoxLayout(buscar, BoxLayout.PAGE_AXIS));
		JLabel tituloBusqueda = new JLabel("Ingrese el nombre de la cuenta que desea eliminar");
		JTextField username = new JTextField(20);
		username.setMaximumSize(new Dimension(300, 50));
		tituloBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);

		buscar.add(tituloBusqueda);
		buscar.add(username);

		JLabel error = new JLabel("");
		buscar.add(error);
		error.setAlignmentX(Component.CENTER_ALIGNMENT);

		JPanel botonera = new JPanel();
		botonera.setLayout(new BoxLayout(botonera, BoxLayout.LINE_AXIS));
		JButton cancelar = new JButton("Cancelar");
		JButton aceptar = new JButton("Eliminar Cuenta");
		botonera.add(aceptar);
		botonera.add(cancelar);

		aceptar.addActionListener((_) -> {
			error.setText(
					switch (sistema.eliminarCuentaUsuario(username.getText())) {
						case 0 -> "Ha habido un error... Intente nuevamente.";
						case 1 -> "Usuario removido correctamente";
						case 2 -> "No puedes remover un admin...";
						default -> "";
					});
		});
		cancelar.addActionListener((_) -> {
			getContentPane().removeAll();
			menuAdmin(adminActual);
			revalidate();
			repaint();
		});

		main.add(buscar, BorderLayout.CENTER);
		main.add(botonera, BorderLayout.SOUTH);

		getContentPane().add(main);
		setLocationRelativeTo(null);
	}

}
