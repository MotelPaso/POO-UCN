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

		userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		username.setAlignmentX(Component.CENTER_ALIGNMENT);
		passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		password.setAlignmentX(Component.CENTER_ALIGNMENT);
		login.setAlignmentX(Component.CENTER_ALIGNMENT);

		username.setMaximumSize(new Dimension(300, 50));
		password.setMaximumSize(new Dimension(300, 50));

		login.addActionListener((e) -> {
			boolean logged = sistema.revisarUsuario(new String[] { username.getText(), password.getText() });
			if (logged) {
				int acceso = sistema.getNivelAcceso(new String[] { username.getText(), password.getText() });
				getContentPane().removeAll();
				switch (acceso) {
				case 1 -> menuAdmin();
				case 2 -> menuColab();
				case 3 -> menuEstudiante();
				case 0 -> System.out.println("Ha habido un error...");
				}
				revalidate();
				repaint();
			} else {
				username.setText("");
				password.setText("");
			}
		});

		inicio.add(userLabel);
		inicio.add(username);
		inicio.add(passLabel);
		inicio.add(password);
		inicio.add(login);

		getContentPane().add(inicio);
		setVisible(true);
	}

	public void menuEstudiante() {
		JLabel titulo = new JLabel("¡Bienvenido!");
		JButton logout = new JButton("Cerrar Sesión");
		JPanel menuEstudiante = new JPanel(new BorderLayout());

		menuEstudiante.add(titulo);
		menuEstudiante.add(logout);
	}

	public void menuColab() {

	}

	public void menuAdmin() {
		JLabel admin = new JLabel("¡Bienvenido Administrador!");
		JButton logout = new JButton("Cerrar Sesión");
		JPanel menuAdmin = new JPanel(new BorderLayout());
		menuAdmin.add(admin, BorderLayout.CENTER);
		menuAdmin.add(logout, BorderLayout.SOUTH);

		getContentPane().add(menuAdmin);
	}
}
