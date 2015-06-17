package Hilos;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import BBDD.Conexion;
import Objetos.btn_Radiobase;

public class ThreadAlarmas extends Thread{
	
	Conexion con;
	static public btn_Radiobase[] vectorBotones;
	boolean go=true;
	public static boolean alarmaRemota; 
	public ThreadAlarmas(btn_Radiobase[] vectorBotones){
	
		this.vectorBotones=vectorBotones;
		con=new Conexion();
		
		
	}
	
	public void detener(){
		
		go=false;
		
		
	}
	
	public void run(){
		
		System.out.println("check alarmas");
		ConsultarSiAlarmaChecked(vectorBotones);
		
	}
	
	

	
	private void ConsultarSiAlarmaChecked(btn_Radiobase[] vectorBotones){
		
		while(go)
		{	
			
		int alarma=1;//1 es OK de ahi en mas es alarma!!!
				
		ResultSet rs=con.ConsultarAlarmasOnline();// devuelve toda slas alarmas
		try {
			while(rs.next()){
				
				
				int rsRadio=rs.getInt("IdRadios")-1;
				int  rsAlarma=rs.getInt("IdAlarmas");
				int rsEvento=rs.getInt("IdEvento");
				
				switch (rsAlarma) {
				case 2:
					alarma=2;
					vectorBotones[rsRadio].setAlarmado(true);
					ThreadGrafRadiosIDs.VectorBotones[rsRadio].setAlarmado(true);
					vectorBotones[rsRadio].setBackground(Color.YELLOW);
					break;
				case 3:
					alarma=3;
					vectorBotones[rsRadio].setAlarmado(true);
					ThreadGrafRadiosIDs.VectorBotones[rsRadio].setAlarmado(true);
					vectorBotones[rsRadio].setBackground(Color.YELLOW);
					break;
				case 4:
					alarma=4;
						vectorBotones[rsRadio].setAlarmado(true);
					ThreadGrafRadiosIDs.VectorBotones[rsRadio].setAlarmado(true);
					vectorBotones[rsRadio].setBackground(Color.YELLOW);
					
					break;
				default:alarma=1;
				//vectorBotones[rsRadio].setAlarmado(false);
					break;
				}
				
				String nombre=con.ConsultarNombre(rsRadio+1);
				System.out.println(nombre+" - Evento:"+rsEvento+"- Alarma: "+rsAlarma);
				
			//	con.InsertarCheckedByIdEvento(rsEvento);
			
			}
				
			rs.close();
			//con.Desconectar();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	}
	
	};//while go
	
}
