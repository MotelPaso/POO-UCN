package main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App extends JFrame {

	public App() {
		Sistema sistema = SistemaImpl.getInstancia();
		setTitle("Impresionador-inador");
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(4, 2, 10, 10));
		JLabel lblSize = new JLabel("  Selecciona el tamaño");
		String[] opcionesSize = { "A4", "A3", "Postal" };
		JComboBox<String> comboSize = new JComboBox<>(opcionesSize);

		JLabel lblTipo = new JLabel("");
		String[] opcionesTipo = { "Glossy", "Matte", "Pro Luster" };
		JComboBox<String> comboTipo = new JComboBox<>(opcionesTipo);

		JLabel lblColor = new JLabel("");
		String[] opcionesColor = { "Color", "Blanco y Negro" };
		JComboBox<String> comboColor = new JComboBox<>(opcionesColor);

		JButton btnCosto = new JButton("Costo de impresiones");
		JButton btnImprimir = new JButton("Imprimir");

		add(lblSize);
		add(comboSize);

		add(lblTipo);
		add(comboTipo);

		add(lblColor);
		add(comboColor);

		add(btnCosto);
		add(btnImprimir);

		btnImprimir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sizeSelect = (String) comboSize.getSelectedItem();
				String tipoSelect = (String) comboTipo.getSelectedItem();
				String colorSelect = (String) comboColor.getSelectedItem();
				sistema.imprimirNuevoPapel(sizeSelect, tipoSelect, colorSelect);

			}
		});
		btnCosto.addActionListener((e) -> {
			JFrame mostrarCostos = new JFrame("Mensaje");
			JPanel main = new JPanel(new BorderLayout());
			JLabel costos = new JLabel(sistema.revisarCostosImpresion());
			JButton btnAceptar = new JButton("Aceptar");
			

			btnAceptar.addActionListener((h) -> mostrarCostos.dispose());
			main.add(costos, BorderLayout.CENTER);
			main.add(btnAceptar, BorderLayout.SOUTH);
			mostrarCostos.getContentPane().add(main);
			mostrarCostos.setSize(500, 200);
			mostrarCostos.setVisible(true);

		});

	}

	public static void main(String[] args) {
		JFrame ventana = new App();
		ventana.setVisible(true);
		System.out.println(55000/80);
		

	}

}
