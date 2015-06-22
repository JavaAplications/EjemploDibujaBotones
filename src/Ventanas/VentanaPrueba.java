package Ventanas;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BBDD.Conexion;
import Hilos.ThreadGrafRadiosIDs;
import Hilos.ThreadPintarBotones;
import Objetos.btn_Radiobase;
import Timers.TemporizadorPintar;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class VentanaPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TemporizadorPintar timerPintar;
	JButton btn_InsertarRadiosOnline,btn_Iniciar;

	btn_Radiobase prueba;// es el boton que crea cada radiobase
	
	private JButton btn_PintarRadOnline;
	static public JPanel panel;
	Conexion con;
	
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btn_Configuracion;
	private JButton btn_Alarmas;
	
	ThreadGrafRadiosIDs HiloOnLineID;
	public static JProgressBar progressBar_CargaRadios;
	public static JLabel lbl_CantidadRadios;
	public VentanaPrueba() {
		
		getContentPane().setLayout(new BorderLayout());
		
	this.setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 450, 300);
	
		CrearComponentes();
		
		AccionesBotones();
	

		   TemporizadorPintar timerPintar= new  TemporizadorPintar();
			timerPintar.start();
		
	}
	
	
	
	
	private void AccionesBotones() {
		
		btn_Iniciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(btn_Iniciar.getText().toString().equals("Encender"))
				{	btn_Iniciar.setText("Detener");
				
					}else{
					btn_Iniciar.setText("Encender");
					
				}
			}
		});
		
		btn_PintarRadOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			

				   TemporizadorPintar timerPintar= new  TemporizadorPintar();
					timerPintar.start();
				
			}
		});
		
		btn_Alarmas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(btn_Alarmas.getText().toString().equals("Alarmas ON")){
					btn_Alarmas.setText("Alarmas OFF");
			}else{
					btn_Alarmas.setText("Alarmas ON");
					
				}
				
			}
		});
		
		btn_InsertarRadiosOnline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			   
				
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
		
		progressBar_CargaRadios = new JProgressBar();
		progressBar_CargaRadios.setForeground(new Color(0, 0, 255));
		progressBar_CargaRadios.setStringPainted(true);
		panel_1.add(progressBar_CargaRadios);
		
		lbl_CantidadRadios = new JLabel("0 RadioBases");
		panel_1.add(lbl_CantidadRadios);
		
		panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.WEST);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		btn_PintarRadOnline =  new JButton("Pintar Online");
		
		panel_3.add(btn_PintarRadOnline);
		
		btn_Configuracion = new JButton("CONFIGURACION");
		btn_Configuracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				
			}
		});
		panel_3.add(btn_Configuracion);
		
		btn_Alarmas = new JButton("Alarmas ON");
	
		panel_3.add(btn_Alarmas);
		
		/////////////////////////// dibujar todo
		
		 		HiloOnLineID=new ThreadGrafRadiosIDs(panel,lbl_CantidadRadios,progressBar_CargaRadios);
		 		
		 		
		 		btn_Iniciar   =  new JButton("Encender");
		 		
		 		panel_1.add(btn_Iniciar);
				HiloOnLineID.start();
		
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
}
