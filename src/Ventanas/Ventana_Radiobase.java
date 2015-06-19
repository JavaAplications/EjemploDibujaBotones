package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Ventana_Radiobase extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	
	public Ventana_Radiobase() {
		
		configuracion();
		
	
	}


	private void configuracion() {
		// TODO Auto-generated method stub
		
		
		
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion de la Radiobase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 398);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPersonalACargo = new JLabel("Llamar a: ");
		lblPersonalACargo.setBounds(10, 22, 72, 19);
		panel.add(lblPersonalACargo);
		
		JLabel lblNewLabel = new JLabel("N\u00B0 Telefono:");
		lblNewLabel.setBounds(10, 48, 72, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad:");
		lblNewLabel_1.setBounds(234, 19, 66, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblProvincia = new JLabel("Provincia: ");
		lblProvincia.setBounds(235, 48, 66, 24);
		panel.add(lblProvincia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alarmas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 120, 394, 150);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_Intrucion = new JPanel();
		panel_Intrucion.setBorder(new TitledBorder(null, "INTRUSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Intrucion.setBackground(new Color(0, 250, 154));
		panel_Intrucion.setBounds(20, 22, 103, 46);
		panel_1.add(panel_Intrucion);
		
		JPanel panel_Apertura = new JPanel();
		panel_Apertura.setBorder(new TitledBorder(null, "APERTURA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Apertura.setBackground(new Color(0, 250, 154));
		panel_Apertura.setBounds(143, 22, 103, 46);
		panel_1.add(panel_Apertura);
		
		JPanel panel_Vibracion = new JPanel();
		panel_Vibracion.setBorder(new TitledBorder(null, "VIBRACION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vibracion.setBackground(new Color(0, 250, 154));
		panel_Vibracion.setBounds(269, 22, 103, 46);
		panel_1.add(panel_Vibracion);
		
		JPanel panel_En_Baterias = new JPanel();
		panel_En_Baterias.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "EN BATERIAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_En_Baterias.setBackground(new Color(0, 250, 154));
		panel_En_Baterias.setBounds(20, 80, 103, 46);
		panel_1.add(panel_En_Baterias);
		panel_En_Baterias.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Alarma1 = new JPanel();
		panel_Alarma1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alarma 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Alarma1.setBackground(new Color(0, 250, 154));
		panel_Alarma1.setBounds(143, 79, 103, 46);
		panel_1.add(panel_Alarma1);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alarma 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_7.setBackground(new Color(0, 250, 154));
		panel_7.setBounds(269, 80, 103, 46);
		panel_1.add(panel_7);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(70, 83, 91, 26);
		panel.add(progressBar);
		progressBar.setStringPainted(true);
		progressBar.setForeground(new Color(50, 205, 50));
		progressBar.setToolTipText("");
		progressBar.setBackground(Color.BLACK);
		progressBar.setValue(50);
		
		JLabel lblBateria = new JLabel("Bateria:");
		lblBateria.setBounds(10, 83, 50, 24);
		panel.add(lblBateria);
		
		JLabel lblUltimaActualizacion = new JLabel("Ultima Actualizacion:");
		lblUltimaActualizacion.setBounds(237, 85, 108, 24);
		panel.add(lblUltimaActualizacion);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setBounds(10, 281, 91, 36);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(80, 21, 137, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(80, 52, 137, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setText("                         ");
		textField_2.setColumns(10);
		textField_2.setBounds(306, 21, 98, 20);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setText("                         ");
		textField_3.setColumns(10);
		textField_3.setBounds(306, 50, 98, 20);
		panel.add(textField_3);
		
		JButton btnResetAlarmas = new JButton("Reset");
		btnResetAlarmas.setBounds(111, 281, 91, 36);
		panel.add(btnResetAlarmas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Enviar Comando al Telefono", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(20, 328, 384, 59);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 24, 146, 24);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(224, 11, 150, 40);
		panel_2.add(btnEnviar);
	}
}
