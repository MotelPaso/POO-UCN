package presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		JPanel menuEstudiante = new JPanel(new BorderLayout());

		JButton perfil = new JButton("Ver mi Perfil");
		JButton malla = new JButton("Ver mi Malla");
		JButton inscripcion = new JButton("Inscribirme a una Certificacion");
		JButton progreso = new JButton("Ver mi Progreso en mi Certificacion");
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
