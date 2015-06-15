package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BBDD.Conexion;
import Hilos.ThreadGrafRadios;


import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;

public class VentanaPrueba extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton boton,btn_Iniciar;
	private JButton btn_CrearRadio;
	JPanel panel;
	Conexion con;
	ThreadGrafRadios graficar;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton btn_Configuracion;
	private JButton btn_Online;
	ThreadGrafRadios HiloOnLine;
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
				
				HiloOnLine=new ThreadGrafRadios(panel);
				HiloOnLine.start();
				
				}else{
					btn_Iniciar.setText("Encender");
					HiloOnLine.detener();	
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
		
		panel.setLayout(new GridLayout(10,100,3,3));
		
		
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);
		
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
				
			
			}
		});
		panel_3.add(btn_Online);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	
	
}