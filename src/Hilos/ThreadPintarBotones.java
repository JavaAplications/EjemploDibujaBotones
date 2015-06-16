package Hilos;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPanel;

import BBDD.Conexion;
import Objetos.btn_Radiobase;

public class ThreadPintarBotones extends Thread{
	
	Conexion con;
	int RadioHabilitada;
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
		//  System.out.println("longitud :"+lonVector);
		 // int c=0;
	while(go){	
	   ResultSet rs= con.ConsultaHab();
	    boolean consulta=false;
	 	for(int cont=0;cont<lonVector;cont++){
			
	
			try {
				while(rs.next()){
				
				RadioHabilitada=rs.getInt("IdRadios");	
			//	System.out.println(" RadioHabilitada: "+RadioHabilitada);
			//	System.out.println(" cont: "+cont);
					if(cont+1==RadioHabilitada){
						consulta=true;
				//		System.out.println(" consulta: "+consulta);
						
						}	
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		////////////////////////////////////////////////////////////	
			if(consulta)
			{	
          	if(ConsultaSiOnline(cont+1))
		  		{
            	
            		if(	vectorBotones[cont].isAlarmado()){            			
            		}else{
            			if(2>CantidadKA) {vectorBotones[cont].setBackground(Color.ORANGE);
            				}else{
    						vectorBotones[cont].setBackground(Color.GREEN);
    						
    						}
            			vectorBotones[cont].setForeground(Color.BLACK);
            		}
             	}else{
					vectorBotones[cont].setBackground(Color.RED);
					vectorBotones[cont].setForeground(Color.WHITE);
					}
		      	}
			else{
				vectorBotones[cont].setBackground(Color.GRAY);
				vectorBotones[cont].setForeground(Color.WHITE);
				}
			
			//System.out.println("llego al  fuinal del FOR"+cont);
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
