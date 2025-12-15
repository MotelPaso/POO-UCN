package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.*;
import main.*;

public class GUI extends JFrame{
	private JLabel labelModo;
	private JLabel nextPedido;
	private JLabel labelTiempoTotal;
	private JLabel inventarioLabel;
	private static Inventario i = Inventario.getInstancia();
	private int numEmpaquetados = 0;
	
	public GUI() {
		Sistema sistema = SistemaImpl.getInstancia();
		
		setTitle("Sistema de Empaquetado de Regalos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(800,800);
		
		JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
		
		labelModo = new JLabel("Modo Actual: Bien Portado"); 
		// iniciamos con el modo bien portado
		panel.add(labelModo);
		
		JButton btnCambiarModo = new JButton("Cambiar modo de empaquetamiento");
		btnCambiarModo.addActionListener((e) -> {
			String modoActual = sistema.cambiarModo();
			labelModo.setText(modoActual);
		});
		panel.add(btnCambiarModo);
		
		inventarioLabel = new JLabel("Inventario: " + i.mostrar());
		panel.add(inventarioLabel);
		
		JLabel siguienteRegalo = new JLabel("Siguiente regalo a empaquetar:");
		
		panel.add(siguienteRegalo);
		nextPedido = new JLabel(sistema.getDatosNext(0));
		panel.add(nextPedido);
		
		JButton btnEmpaquetar = new JButton("Empaquetar regalo");
		
		btnEmpaquetar.addActionListener((e) -> {
			int actualEmpaquetados = numEmpaquetados;
			numEmpaquetados = sistema.empaquetar(numEmpaquetados);
			if (actualEmpaquetados == numEmpaquetados) {
				siguienteRegalo.setText(("Que se te han acabado las cosas, cambia de modo"));
			} else {
				siguienteRegalo.setText(("Siguiente regalo a empaquetar:"));
				nextPedido.setText(sistema.getDatosNext(numEmpaquetados));
			}
			
			inventarioLabel.setText("Inventario: " + i.mostrar());
			labelTiempoTotal.setText((sistema.calcularTiempoTotal()));
		});
		
		panel.add(btnEmpaquetar);
		
		labelTiempoTotal = new JLabel(sistema.calcularTiempoTotal());
		panel.add(labelTiempoTotal);
		
		add(panel);
		pack();
		setVisible(true);
	}

}
