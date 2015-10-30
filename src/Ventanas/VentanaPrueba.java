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
	JButton btn_Iniciar;

	btn_Radiobase prueba;// es el boton que crea cada radiobase
	static public JPanel panel;
	Conexion con;
	
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	
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
		
		
		
	}




	private void CrearComponentes(){
		
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
		
		/////////////////////////// dibujar todo
		
		 		HiloOnLineID=new ThreadGrafRadiosIDs(panel,lbl_CantidadRadios,progressBar_CargaRadios);
		 		
		 		
		 		btn_Iniciar   =  new JButton("Encender");
		 		
		 		panel_1.add(btn_Iniciar);
				HiloOnLineID.start();
		
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
}
