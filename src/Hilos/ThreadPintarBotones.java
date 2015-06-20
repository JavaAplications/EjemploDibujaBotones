package Hilos;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import BBDD.Conexion;
import Objetos.btn_Radiobase;

public class ThreadPintarBotones extends Thread{
	
	Conexion con;
	
	JPanel jPanel1;
	int cantidad,cantidadOnline;
	
	boolean go=true;
	static public btn_Radiobase btn_Radio;
	int CantidadKA;
	private btn_Radiobase[] vectorBotones;
	private ResultSet rs,rsHAB,rsAlarmas;
	
	public ThreadPintarBotones(btn_Radiobase[] vectorBotones){
		con=new Conexion();
		this.vectorBotones=vectorBotones;
	
	}
	
	
	
	public void Detener(){
		
		go=false;
		
		
	}
	
	public void  run(){
		
		con=new Conexion();
		int cant=0;
		int RadioOnLine = 0;
		rs=con.ConsultarRadiosOnline();
		rsHAB=con.ConsultaHab();
		rsAlarmas=con.ConsultarTodasLasAlarmas();// devuelve toda slas alarmas
		boolean control=false;
		boolean alarmado=false;
	
		try {
			while(rsHAB.next()){
				
				int RadioHAB=rsHAB.getInt("IdRadios");	
							
				while(rs.next()){
				
			   
				RadioOnLine=rs.getInt("IdRadios");	
				int CantidadKA=rs.getInt("Cantidad");
					
					if(RadioHAB==RadioOnLine){	
						 while(rsAlarmas.next()){
							 int IdRadioAlarma=rsAlarmas.getInt("IdRadios");
							
							 if(RadioOnLine==IdRadioAlarma){
							 alarmado=true;}
						    }
						 
						 rsAlarmas.beforeFirst();
						control=true;	
						cant=CantidadKA;
					}
					
				
			}
			rs.beforeFirst();
					
			if(control){
				
				vectorBotones[RadioHAB-1].setForeground(Color.BLACK);
				
			//	System.out.println("RadioHAB: "+RadioHAB );
				if(alarmado){
					vectorBotones[RadioHAB-1].setBackground(Color.yellow);
					
				}else{
					if(cant>2){
					vectorBotones[RadioHAB-1].setBackground(Color.GREEN);
							}
					else{
						vectorBotones[RadioHAB-1].setBackground(Color.ORANGE);
							}
					
				}
				alarmado=false;
				control=false;
			}else
				{
				vectorBotones[RadioHAB-1].setBackground(Color.RED);
				vectorBotones[RadioHAB-1].setForeground(Color.WHITE);
			
				}
				
			
		}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	
	
	
		
	   con.Desconectar();
		
		
		
	}
	
	
	
	
	
	
	}
	
	
	


