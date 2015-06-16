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
		
		this.vectorBotones=vectorBotones;
		con=new Conexion();
		
	}
	
	
	
	public void Detener(){
		
		go=false;
		
		
	}
	
	public void  run(){
		
		int lonVector=vectorBotones.length;
		
	while(go){	
		
		for(int i=0;i<lonVector;i++){
			
        if(con.ConsultarHabilitado(i)){		
        	
       
            	if(ConsultaSiOnline(i+1)){
					if(2>CantidadKA) {vectorBotones[i].setBackground(Color.ORANGE);}
					else{
						vectorBotones[i].setBackground(Color.GREEN);}
				}else{
					vectorBotones[i].setBackground(Color.RED);}
		      	}
			else{
				vectorBotones[i].setBackground(Color.GRAY);}
		}
	}
		
	}
	
	
	
	
	private boolean ConsultaSiOnline(int IdRadio){
	
			
		boolean OnLine=false;
	
		
		ResultSet rs=con.ConsultarRadiosOnline();
		
		int RadioRS;
		try {
			while(rs.next()){
				RadioRS=rs.getInt("IdRadios");
					if(IdRadio==RadioRS){
						CantidadKA=rs.getInt("Cantidad");
						
						OnLine=true;
				}
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return OnLine;
		
		
		
		
	}
	
	
	
	
	

}
