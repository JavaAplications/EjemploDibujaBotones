package Hilos;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import BBDD.Conexion;
import Objetos.btn_Radiobase;

public class ThreadAlarmas extends Thread{
	
	Conexion con;
	btn_Radiobase[] vectorBotones;
	public ThreadAlarmas(btn_Radiobase[] vectorBotones){
		con=new Conexion();
		this.vectorBotones=vectorBotones;
		
		
		
	}
	
	public void run(){
		
		System.out.println("check alarmas");
		
		
		
		
		ConsultarSiAlarmaChecked(vectorBotones);
		
		
		
		
	}
	
	private void AlarmaLimpiada(int IdEvento){
		con.InsertarChecked(IdEvento);
		
		
	}

	
	private void ConsultarSiAlarmaChecked(btn_Radiobase[] vectorBotones){
		int alarma=1;//1 es OK de ahi en mas es alarma!!!
		int c=0;
		ResultSet rs=con.ConsultarAlarmasOnline();
		try {
			while(rs.next()){
				int rsRadio=rs.getInt("IdRadios")-1;
				int  rsAlarma=rs.getInt("IdAlarmas");
				switch (rsAlarma) {
				case 2:
					alarma=2;
					vectorBotones[rsRadio].setBackground(Color.yellow);
					break;
				case 3:
					alarma=3;
					vectorBotones[rsRadio].setBackground(Color.yellow);
					break;
				case 4:
					alarma=4;
					vectorBotones[rsRadio].setBackground(Color.yellow);
					break;
				default:alarma=1;
					break;
				}
				
				
			
				String nombre=con.ConsultarNombre(rsRadio);
				System.out.println(nombre+"- Alarma: "+rsAlarma);
				//con.InsertarChecked(IdEvento);
			c++;	
			}
				
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	}
	

}
