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
    
   private JPanel panel;
    private JProgressBar barra;
    private JLabel label;
    private JCheckBox chBox_Habilitacion,chckbxModificarDatos;
	Conexion con;
	private JTextField edit_NombreRadiobase;
	private JPanel panel_Intrusion,panel_Vibracion,panel_En_Baterias,panel_Apertura,panel_Reinicio,panel_BateriaBaja;
	private JProgressBar progressBar_NivelBateria;
	
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
					
					if(con.ClearAlarmaIdRadio(IdRadio,AlarmaIntrusion)){panel_Intrusion.setBackground(Color.GREEN);}
					
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
					
					if(con.ClearAlarmaIdRadio(IdRadio,AlarmaApertura)){panel_Apertura.setBackground(Color.GREEN);}
					
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
					if(con.ClearAlarmaIdRadio(IdRadio,AlarmaEnergia)){panel_En_Baterias.setBackground(Color.GREEN);}
					
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
					
					if(con.ClearAlarmaIdRadio(IdRadio,AlarmaVibracion)){panel_Vibracion.setBackground(Color.GREEN);}
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
					
					if(con.ClearAlarmaIdRadio(IdRadio,AlarmaBateriaBaja)){panel_BateriaBaja.setBackground(Color.GREEN);}
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
					
					if(con.ClearAlarmaIdRadio(IdRadio,AlarmaReinicio)){panel_Reinicio.setBackground(Color.GREEN);}
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
		setBounds(100, 100, 450, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Informacion de la Radiobase", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 414, 432);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPersonalACargo = new JLabel("Llamar a: ");
		lblPersonalACargo.setBounds(10, 55, 66, 19);
		panel.add(lblPersonalACargo);
		
		JLabel lblNewLabel = new JLabel("N\u00B0 Telefono:");
		lblNewLabel.setBounds(10, 81, 72, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ciudad:");
		lblNewLabel_1.setBounds(234, 52, 66, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblProvincia = new JLabel("Provincia: ");
		lblProvincia.setBounds(235, 81, 66, 24);
		panel.add(lblProvincia);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Alarmas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 160, 394, 150);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		panel_Intrusion = new JPanel();
		panel_Intrusion.setBorder(new TitledBorder(null, "INTRUSION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Intrusion.setBackground(new Color(0, 255, 0));
		panel_Intrusion.setBounds(20, 22, 103, 46);
		panel_1.add(panel_Intrusion);
		panel_Intrusion.setLayout(new BorderLayout(0, 0));
		
		panel_Apertura = new JPanel();
		panel_Apertura.setBorder(new TitledBorder(null, "APERTURA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Apertura.setBackground(new Color(0, 255, 0));
		panel_Apertura.setBounds(143, 22, 103, 46);
		panel_1.add(panel_Apertura);
		
		panel_Vibracion = new JPanel();
		panel_Vibracion.setBorder(new TitledBorder(null, "VIBRACION", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Vibracion.setBackground(new Color(0, 255, 0));
		panel_Vibracion.setBounds(269, 22, 103, 46);
		panel_1.add(panel_Vibracion);
		
		panel_En_Baterias = new JPanel();
		panel_En_Baterias.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "EN BATERIAS", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_En_Baterias.setBackground(new Color(0, 255, 0));
		panel_En_Baterias.setBounds(20, 80, 103, 46);
		panel_1.add(panel_En_Baterias);
		panel_En_Baterias.setLayout(new BorderLayout(0, 0));
		
		panel_Reinicio = new JPanel();
		panel_Reinicio.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "REINICIO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_Reinicio.setBackground(new Color(0, 255, 0));
		panel_Reinicio.setBounds(143, 79, 103, 46);
		panel_1.add(panel_Reinicio);
		
		panel_BateriaBaja = new JPanel();
		panel_BateriaBaja.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "BATERIA BAJA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_BateriaBaja.setBackground(new Color(0, 255, 0));
		panel_BateriaBaja.setBounds(269, 80, 103, 46);
		panel_1.add(panel_BateriaBaja);
		
		progressBar_NivelBateria = new JProgressBar();
		progressBar_NivelBateria.setBounds(70, 116, 91, 26);
		panel.add(progressBar_NivelBateria);
		progressBar_NivelBateria.setStringPainted(true);
		progressBar_NivelBateria.setForeground(new Color(50, 205, 50));
		progressBar_NivelBateria.setToolTipText("");
		progressBar_NivelBateria.setBackground(Color.BLACK);
		progressBar_NivelBateria.setValue(50);
		
		JLabel lblBateria = new JLabel("Bateria:");
		lblBateria.setBounds(10, 116, 50, 24);
		panel.add(lblBateria);
		
		JButton btnNewButton = new JButton("Modificar");
	
		btnNewButton.setBounds(10, 317, 91, 36);
		panel.add(btnNewButton);
		
		edit_Contacto = new JTextField();
		edit_Contacto.setEditable(false);
		edit_Contacto.setBounds(80, 54, 137, 20);
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
		edit_Ciudad.setBounds(306, 54, 98, 20);
		panel.add(edit_Ciudad);
		
		edit_Provincia = new JTextField();
		edit_Provincia.setEditable(false);
		edit_Provincia.setText("                         ");
		edit_Provincia.setColumns(10);
		edit_Provincia.setBounds(306, 83, 98, 20);
		panel.add(edit_Provincia);
		
		JButton btnResetAlarmas = new JButton("Reset");
		btnResetAlarmas.setBounds(111, 317, 91, 36);
		panel.add(btnResetAlarmas);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Enviar Comando al Telefono", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(20, 360, 384, 59);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setBounds(10, 24, 146, 24);
		panel_2.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(224, 11, 150, 40);
		panel_2.add(btnEnviar);
		
		chBox_Habilitacion = new JCheckBox("Habilitacion");
		chBox_Habilitacion.setEnabled(false);
		chBox_Habilitacion.setHorizontalAlignment(SwingConstants.CENTER);
		chBox_Habilitacion.setBounds(307, 116, 97, 23);
		panel.add(chBox_Habilitacion);
		
		
		
		
		
		chckbxModificarDatos = new JCheckBox("Modificar Datos");
		chckbxModificarDatos.setBounds(264, 324, 128, 23);
		panel.add(chckbxModificarDatos);
		
		JLabel lblNewLabel_2 = new JLabel("Estaci\u00F3n:");
		lblNewLabel_2.setBounds(10, 24, 46, 14);
		panel.add(lblNewLabel_2);
		
		edit_NombreRadiobase = new JTextField();
		edit_NombreRadiobase.setFont(new Font("Tahoma", Font.BOLD, 12));
		edit_NombreRadiobase.setEditable(false);
		edit_NombreRadiobase.setBounds(80, 21, 324, 20);
		panel.add(edit_NombreRadiobase);
		edit_NombreRadiobase.setColumns(10);
	}
}
