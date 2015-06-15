package Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	JButton boton,btn_Iniciar;

	btn_Radiobase prueba;// es el boton que crea cada radiobase
	
	private JButton btn_CrearRadio;
	JPanel panel;
	Conexion con;
	

	private JPanel panel_1;
	private JLabel lbl_ID;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btn_Configuracion;
	private JButton btn_Online;
	
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
			
				HiloOnLineID=new ThreadGrafRadiosIDs(panel);
				HiloOnLineID.start();
				
				
				}else{
					btn_Iniciar.setText("Encender");
				
				}
			}
		});
	}




	private void CrearComponentes(){
		boton =  new JButton("derecha");
		getContentPane().add(boton, BorderLayout.EAST);
		
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
		btn_CrearRadio =  new JButton("CREAR RADIOBASE");
		btn_CrearRadio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		panel_3.add(btn_CrearRadio);
		
		btn_Configuracion = new JButton("CONFIGURACION");
		panel_3.add(btn_Configuracion);
		
		btn_Online = new JButton("OnLine");
		btn_Online.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ThreadAlarmas CheckAlarmas=new ThreadAlarmas(ThreadGrafRadiosIDs.VectorBotones);
				CheckAlarmas.start();
				
			}
		});
		panel_3.add(btn_Online);
		
		
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
}
