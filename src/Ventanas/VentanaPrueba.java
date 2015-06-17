package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BBDD.Conexion;
import Hilos.ThreadAlarmas;
import Hilos.ThreadGrafRadiosIDs;
import Hilos.ThreadPintarBotones;
import Objetos.btn_Radiobase;

import javax.swing.JLabel;

public class VentanaPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton btn_InsertarRadiosOnline,btn_Iniciar;

	btn_Radiobase prueba;// es el boton que crea cada radiobase
	
	private JButton btn_VaciarTablaOnline;
	JPanel panel;
	Conexion con;
	
	ThreadAlarmas CheckAlarmas;
	private JPanel panel_1;
	private JLabel lbl_ID;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btn_Configuracion;
	private JButton btn_Alarmas;
	
	ThreadGrafRadiosIDs HiloOnLineID;
	public VentanaPrueba() {
		
		getContentPane().setLayout(new BorderLayout());
		
	this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
	
		CrearComponentes();
		
		AccionesBotones();
	
	}
	
	
	
	
	private void AccionesBotones() {
		
		btn_Iniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(btn_Iniciar.getText().toString().equals("Encender"))
				{	btn_Iniciar.setText("Detener");
				con=new  Conexion();
			 int cantidad=con.CantidadRadiobases();
			
			 		con.Desconectar();
				HiloOnLineID=new ThreadGrafRadiosIDs(panel,cantidad);
				HiloOnLineID.start();
					}else{
					btn_Iniciar.setText("Encender");
				}
			}
		});
		
		btn_VaciarTablaOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con=new  Conexion();
				con.vaciarRadiosOnlines();
				
				
			}
		});
		
		btn_Alarmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(btn_Alarmas.getText().toString().equals("Alarmas ON")){
					btn_Alarmas.setText("Alarmas OFF");
				CheckAlarmas=new ThreadAlarmas(ThreadGrafRadiosIDs.VectorBotones);
				CheckAlarmas.start();}else{
					btn_Alarmas.setText("Alarmas ON");
					CheckAlarmas.detener();
				}
				
			}
		});
		
		btn_InsertarRadiosOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con=new  Conexion();
				con.InsertarRadiosOnline();
				
			}
		});
		
	}




	private void CrearComponentes(){
		btn_InsertarRadiosOnline =  new JButton("Insertar Radios Online");
	
		getContentPane().add(btn_InsertarRadiosOnline, BorderLayout.EAST);
		
		panel=new JPanel();
		ScrollPane barras=new ScrollPane();
		getContentPane().add(barras,BorderLayout.CENTER);
		barras.add(panel);
		
		panel.setLayout(new GridLayout(15,10,3,3));
		
		
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lbl_ID = new JLabel("ID");
		panel_1.add(lbl_ID);
		
		
		btn_Iniciar   =  new JButton("Encender");
		panel_1.add(btn_Iniciar);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		btn_VaciarTablaOnline =  new JButton("Vaciar Tabla Online");
		
		panel_3.add(btn_VaciarTablaOnline);
		
		btn_Configuracion = new JButton("CONFIGURACION");
		panel_3.add(btn_Configuracion);
		
		btn_Alarmas = new JButton("Alarmas ON");
	
		panel_3.add(btn_Alarmas);
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
}
