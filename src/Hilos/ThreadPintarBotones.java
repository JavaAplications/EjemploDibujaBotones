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
	ResultSet rs;
	boolean go=true;
	static public btn_Radiobase btn_Radio;
	int CantidadKA;
	btn_Radiobase[] vectorBotones;
	
	public ThreadPintarBotones(btn_Radiobase[] vectorBotones){
		con=new Conexion();
		this.vectorBotones=vectorBotones;
	
	}
	
	
	
	public void Detener(){
		
		go=false;
		
		
	}
	
	public void  run(){
		long antes =System.currentTimeMillis();
		int lonVector=vectorBotones.length;
		



	
		con=new Conexion();
			
		ResultSet rs=con.ConsultarRadiosOnline();

		ResultSet rsHAB=con.ConsultaHab();
		boolean control=false;
	
		try {
			while(rsHAB.next()){
				int RadioHAB=rsHAB.getInt("IdRadios");	
			while(rs.next()){
				int RadioOnLine=rs.getInt("IdRadios");	
				
					
					if(RadioHAB==RadioOnLine){	
						control=true;			
					}
					
					}
			rs.beforeFirst();
						
			if(control){
				vectorBotones[RadioHAB-1].setBackground(Color.GREEN);
				control=false;	
			}else{vectorBotones[RadioHAB-1].setBackground(Color.RED);
			
				}
			
			
		}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	
	
	
		
	   con.Desconectar();
		
		
		
	}
	
	
	
	
	
	
	}
	
	
	


