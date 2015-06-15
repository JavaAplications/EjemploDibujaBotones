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
        	int alarmado=ConsultarSiAlarmaChecked(i);	
        	
        	System.out.println("ID:"+i+" alarmado: "+alarmado);
				
			if(alarmado!=1){
						vectorBotones[i].setBackground(Color.YELLOW);
			}else{	
            	if(ConsultaSiOnline(i+1)){
					if(2>CantidadKA) {vectorBotones[i].setBackground(Color.ORANGE);}
					else{
						vectorBotones[i].setBackground(Color.GREEN);}
				}else{
					vectorBotones[i].setBackground(Color.RED);}
		      	}
			
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
	
	
	
	private int ConsultarSiAlarmaChecked(int IdRadio){
		int alarma=1;//1 es OK de ahi en mas es alarma!!!
		ResultSet rs=con.ConsultarAlarmasOnline();
		try {
			while(rs.next()){
			//	int IdEvento=rs.getInt("IdEvento");
				int  rsAlarma=rs.getInt("IdAlarmas");
				switch (rsAlarma) {
				case 2:
					alarma=2;
					break;
				case 3:
					alarma=3;
					break;
				case 4:
					alarma=4;
					break;
				default:alarma=1;
					break;
				}
				
				
				int rsRadio=rs.getInt("IdRadios");
				String nombre=con.ConsultarNombre(rsRadio);
				System.out.println(nombre+"- Alarma: "+rsAlarma);
				//con.InsertarChecked(IdEvento);
				}
				
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return alarma;
	
	}
	
	
	
	

}
