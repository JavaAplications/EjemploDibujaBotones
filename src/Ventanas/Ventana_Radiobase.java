package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javafx.scene.control.ProgressBar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextField;

import BBDD.Conexion;
import Hilos.ThreadGrafRadiosIDs;

import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

public class Ventana_Radiobase extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField edit_Contacto;
	private JTextField edit_Telefono;
	private JTextField edit_Ciudad;
	private JTextField edit_Provincia;
	private JTextField textField_4;
    private int IdRadio;
    private int AlarmaIntrusion=2;
    private int AlarmaApertura=3;
    
    private int AlarmaEnergia=4;
    
    private int AlarmaVibracion=13;
    
    private int AlarmaReinicio=7;
    
    private int AlarmaBateriaBaja=10;
    private JButton btnResetAlarmas;
   private JPanel panel;
    private JProgressBar barra;
    private JLabel label;
    private JCheckBox chBox_Habilitacion,chckbxModificarDatos;
	Conexion con;
	private JTextField edit_NombreRadiobase;
	private JPanel panel_Intrusion,panel_Vibracion,panel_En_Baterias,panel_Apertura,panel_Reinicio,panel_BateriaBaja;
	private JProgressBar progressBar_NivelBateria;
	private JLabel lblClave;
	private JTextField edit_Clave;
	
	public Ventana_Radiobase(int IdRadio) {
		setResizable(false);
		
		this.IdRadio=IdRadio;
		configuracion();
		CargarDatos();
		Listeners();
	
	}

	private void Listeners() {
	
		chckbxModificarDatos.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent evento) {
				boolean valorbool=chckbxModificarDatos.isSelected();
				
				edit_Ciudad.setEditable(valorbool);
				edit_Telefono.setEditable(valorbool);
				edit_Provincia.setEditable(valorbool);
				edit_Contacto.setEditable(valorbool);
				chBox_Habilitacion.setEnabled(valorbool);
				edit_NombreRadiobase.setEditable(valorbool);
				
				String nombre=edit_NombreRadiobase.getText().toString();
				String telefono=edit_Telefono.getText().toString();
				String localidad=edit_Ciudad.getText().toString();
				String provincia=edit_Provincia.getText().toString();
				String contacto=edit_Contacto.getText().toString();
				boolean habilitacion=chBox_Habilitacion.isSelected();
				
				
			if(!valorbool){
				con=new Conexion();
				con.ActualizarRadiobase(IdRadio,nombre, telefono, localidad, provincia, contacto, habilitacion);
				con.Desconectar();
			   panel=VentanaPrueba.panel;
			   label=VentanaPrueba.lbl_CantidadRadios;
			   barra=VentanaPrueba.progressBar_CargaRadios;
				
				ThreadGrafRadiosIDs graficar=new ThreadGrafRadiosIDs(panel,label,barra);
				graficar.start();
				
				
			}
				
			
		
			}
		});
		
		btnResetAlarmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Reconocer TODAS las ALarmas ?", "ALARMAS REMOTAS",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
				       con=new Conexion();
						if(n==JOptionPane.OK_OPTION){
							if(con.ClearAlarmaIdRadio(IdRadio)){
								panel_En_Baterias.setBackground(Color.GREEN);
								panel_Apertura.setBackground(Color.GREEN);
								panel_Vibracion.setBackground(Color.GREEN);
								panel_BateriaBaja.setBackground(Color.GREEN);
								panel_Intrusion.setBackground(Color.GREEN);
								panel_Reinicio.setBackground(Color.GREEN);
								
							}
							
						}
						con.Desconectar();
				
			}
		});
		
		
		panel_Intrusion.addMouseListener(new MouseListener() {
			
		
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
					
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Limpiar la Alarma", "ALARMA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(n==JOptionPane.OK_OPTION){
					
			
					
				}
				
			
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	

		panel_Apertura.addMouseListener(new MouseListener() {
			
		
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Limpiar la Alarma", "ALARMA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(n==JOptionPane.OK_OPTION){
					
					
				}
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		panel_En_Baterias.addMouseListener(new MouseListener() {
			
		
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Limpiar la Alarma", "ALARMA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(n==JOptionPane.OK_OPTION){
				//	if(con.ClearAlarmaIdRadio(IdRadio,AlarmaEnergia)){panel_En_Baterias.setBackground(Color.GREEN);}
					
				}
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		panel_Vibracion.addMouseListener(new MouseListener() {
			
		
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Limpiar la Alarma", "ALARMA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(n==JOptionPane.OK_OPTION){
					
				
				}
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		panel_BateriaBaja.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Limpiar la Alarma", "ALARMA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(n==JOptionPane.OK_OPTION){
					
					
				}
				
			}

				
			
		});
		
		panel_Reinicio.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent arg0) {
				Object[] options = { "OK", "Cancelar" };
				int n= JOptionPane.showOptionDialog(null, "Desea Limpiar la Alarma", "ALARMA",
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0]);
				
				if(n==JOptionPane.OK_OPTION){
					
					
				}}
		});
		
		
	}

	private void CargarDatos(){
		con=new Conexion();
		ResultSet rs=con.ConsultarInfoRadiobase(IdRadio);
		ResultSet rsAlarmas=con.ConsultarAlarmaIdRadio(IdRadio);
		try {
			while(rs.next()){
				edit_Contacto.setText(rs.getString("ContacRadio"));
				edit_Ciudad.setText(rs.getString("LocRadio"));
     			edit_Provincia.setText(rs.getString("ProvRadio"));
     			edit_Telefono.setText(rs.getString("TelRadio"));
     			chBox_Habilitacion.setSelected(rs.getBoolean("Habilitacion"));
     			edit_NombreRadiobase.setText(rs.getString("NomRadio"));
					
			}
			
				while(rsAlarmas.next()){
					
					int Alarma=rsAlarmas.getInt("IdAlarmas");
					System.out.println("Alarma: "+Alarma);
				switch (Alarma) {
				case 2:
					panel_Intrusion.setBackground(Color.YELLOW);
					
						break;
					case 3:
						panel_Apertura.setBackground(Color.YELLOW);
						break;
					case 4:
						panel_En_Baterias.setBackground(Color.YELLOW);
						break;
					case 13:
						panel_Vibracion.setBackground(Color.YELLOW);
						break;
					case 7:
						panel_Reinicio.setBackground(Color.YELLOW);
						break;
					case 10:
						panel_BateriaBaja.setBackground(Color.YELLOW);
						break;
					

				default:
					//panel_Intrusion.setBackground(Color.GREEN);///////// ver cambio
					break;
				}
					
					
				}
				
			
			
			con.Desconectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
 	private void configuracion() {
		// TODO Auto-generated method stub
		
		
		
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 608);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion de la Radiobase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 448, 558);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPersonalACargo = new JLabel("Llamar a: ");
		lblPersonalACargo.setBounds(10, 55, 56, 19);
		panel.add(lblPersonalACargo);
		
		JLabel lblNewLabel = new JLabel("N\u00B0 Telefono:");
		lblNewLabel.setBounds(10, 81, 72, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad:");
		lblNewLabel_1.setBounds(234, 81, 66, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblProvincia = new JLabel("Provincia: ");
		lblProvincia.setBounds(235, 110, 66, 24);
		panel.add(lblProvincia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Alarmas Remotas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 290, 428, 187);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		panel_Intrusion = new JPanel();
		panel_Intrusion.setBorder(new TitledBorder(null, "INTRUSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Intrusion.setBackground(new Color(0, 255, 0));
		panel_Intrusion.setBounds(10, 20, 103, 46);
		panel_1.add(panel_Intrusion);
		panel_Intrusion.setLayout(new BorderLayout(0, 0));
		
		panel_Apertura = new JPanel();
		panel_Apertura.setBorder(new TitledBorder(null, "APERTURA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Apertura.setBackground(new Color(0, 255, 0));
		panel_Apertura.setBounds(161, 20, 103, 46);
		panel_1.add(panel_Apertura);
		
		panel_Vibracion = new JPanel();
		panel_Vibracion.setBorder(new TitledBorder(null, "VIBRACION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vibracion.setBackground(new Color(0, 255, 0));
		panel_Vibracion.setBounds(315, 20, 103, 46);
		panel_1.add(panel_Vibracion);
		
		panel_En_Baterias = new JPanel();
		panel_En_Baterias.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "EN BATERIAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_En_Baterias.setBackground(new Color(0, 255, 0));
		panel_En_Baterias.setBounds(10, 78, 103, 46);
		panel_1.add(panel_En_Baterias);
		panel_En_Baterias.setLayout(new BorderLayout(0, 0));
		
		panel_Reinicio = new JPanel();
		panel_Reinicio.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "REINICIO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Reinicio.setBackground(new Color(0, 255, 0));
		panel_Reinicio.setBounds(161, 77, 103, 46);
		panel_1.add(panel_Reinicio);
		
		panel_BateriaBaja = new JPanel();
		panel_BateriaBaja.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "BATERIA BAJA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_BateriaBaja.setBackground(new Color(0, 255, 0));
		panel_BateriaBaja.setBounds(315, 78, 103, 46);
		panel_1.add(panel_BateriaBaja);
		
		btnResetAlarmas = new JButton("RECONOCER ALARMAS");
		btnResetAlarmas.setBounds(10, 135, 201, 41);
		panel_1.add(btnResetAlarmas);
		
		JButton btnFotosYVideos = new JButton("FOTOS Y VIDEOS");
		btnFotosYVideos.setBounds(221, 135, 197, 41);
		panel_1.add(btnFotosYVideos);
		
		
		edit_Contacto = new JTextField();
		edit_Contacto.setEditable(false);
		edit_Contacto.setBounds(80, 54, 358, 20);
		panel.add(edit_Contacto);
		edit_Contacto.setColumns(10);
		
		edit_Telefono = new JTextField();
		edit_Telefono.setEditable(false);
		edit_Telefono.setColumns(10);
		edit_Telefono.setBounds(80, 85, 137, 20);
		panel.add(edit_Telefono);
		
		edit_Ciudad = new JTextField();
		edit_Ciudad.setEditable(false);
		edit_Ciudad.setText("                         ");
		edit_Ciudad.setColumns(10);
		edit_Ciudad.setBounds(301, 83, 137, 20);
		panel.add(edit_Ciudad);
		
		edit_Provincia = new JTextField();
		edit_Provincia.setEditable(false);
		edit_Provincia.setText("                         ");
		edit_Provincia.setColumns(10);
		edit_Provincia.setBounds(301, 114, 137, 20);
		panel.add(edit_Provincia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Enviar Comando GSM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(10, 488, 428, 59);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 24, 146, 24);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(258, 20, 150, 32);
		panel_2.add(btnEnviar);
		
		chBox_Habilitacion = new JCheckBox("Habilitar Radiobase");
		chBox_Habilitacion.setEnabled(false);
		chBox_Habilitacion.setBounds(10, 149, 152, 23);
		panel.add(chBox_Habilitacion);
		
		
		
		
		
		chckbxModificarDatos = new JCheckBox("Modificar Datos");
		chckbxModificarDatos.setBounds(286, 149, 152, 23);
		panel.add(chckbxModificarDatos);
		
		JLabel lblNewLabel_2 = new JLabel("Estaci\u00F3n:");
		lblNewLabel_2.setBounds(10, 24, 46, 14);
		panel.add(lblNewLabel_2);
		
		edit_NombreRadiobase = new JTextField();
		edit_NombreRadiobase.setFont(new Font("Tahoma", Font.BOLD, 12));
		edit_NombreRadiobase.setEditable(false);
		edit_NombreRadiobase.setBounds(80, 21, 358, 20);
		panel.add(edit_NombreRadiobase);
		edit_NombreRadiobase.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Telemetr\u00EDa GSM", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 179, 428, 100);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		progressBar_NivelBateria = new JProgressBar();
		progressBar_NivelBateria.setBounds(111, 26, 91, 17);
		panel_3.add(progressBar_NivelBateria);
		progressBar_NivelBateria.setStringPainted(true);
		progressBar_NivelBateria.setForeground(new Color(50, 205, 50));
		progressBar_NivelBateria.setToolTipText("");
		progressBar_NivelBateria.setBackground(Color.BLACK);
		progressBar_NivelBateria.setValue(50);
		
		JLabel lblBateria = new JLabel("Bateria:");
		lblBateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblBateria.setBounds(10, 21, 50, 24);
		panel_3.add(lblBateria);
		
		JLabel lblAlmacenamiento = new JLabel("Almacenamiento: ");
		lblAlmacenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlmacenamiento.setBounds(10, 54, 91, 24);
		panel_3.add(lblAlmacenamiento);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(50);
		progressBar.setToolTipText("");
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.RED);
		progressBar.setBackground(Color.BLACK);
		progressBar.setBounds(111, 59, 91, 17);
		panel_3.add(progressBar);
		
		JButton btnNewButton = new JButton("Medicion");
		btnNewButton.setBounds(276, 26, 142, 52);
		panel_3.add(btnNewButton);
		
		lblClave = new JLabel("Clave:");
		lblClave.setBounds(10, 111, 72, 24);
		panel.add(lblClave);
		
		edit_Clave = new JTextField();
		edit_Clave.setEditable(false);
		edit_Clave.setColumns(10);
		edit_Clave.setBounds(80, 115, 137, 20);
		panel.add(edit_Clave);
	}
}
