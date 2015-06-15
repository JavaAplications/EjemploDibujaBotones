package Hilos;

import java.sql.ResultSet;
import java.sql.SQLException;

import BBDD.Conexion;

public class ThreadAlarmas extends Thread{
	
	Conexion con;
	
	public ThreadAlarmas(){
		con.Conectar();
		
		
		
	}
	
	public void run(){
		
		
		
		
		
		
		
		
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
