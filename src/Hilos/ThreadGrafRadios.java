package Hilos;

import java.awt.Color;





import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.glass.ui.Timer;

import BBDD.Conexion;


public class ThreadGrafRadios extends Thread{
Conexion con;

JPanel jPanel1;
int cantidad,cantidadOnline;
ResultSet rs;
static public JButton btn_Radio;
int CantidadKA;

boolean go=true;

		public ThreadGrafRadios (JPanel jPanel1 ){
			this.jPanel1=jPanel1;
			
		}

		
		public void detener(){
			go=false;
			con.Desconectar();
			
		}

		public void run(){
			con=new Conexion();
			while(go){
			
				int cantidad=con.CantidadRadiobases();
			jPanel1.removeAll(); 
		
	

	
				for(int i=1;i<cantidad+1;i++){
					
				    String nombre=con.ConsultarNombre(i);
			      
					btn_Radio=new JButton("Id:"+i+" - "+nombre);
				
					
					if(con.ConsultarHabilitado(i)){		
						
						
						boolean pepe=ConsultaSiOnline(i);
						if(pepe){
							if(CantidadKA>1) {btn_Radio.setBackground(Color.ORANGE);}
							else{
								btn_Radio.setBackground(Color.GREEN);
							}
						}else{
							btn_Radio.setBackground(Color.RED);
						}
						
						System.out.println("Habilitado :"+pepe);
						}
					else{
						btn_Radio.setBackground(Color.GRAY);}
				btn_Radio.setToolTipText("cara de gay");
					jPanel1.add(btn_Radio);
					btn_Radio.setVisible(true);
					
				
				}
				
				
				SwingUtilities.updateComponentTreeUI(jPanel1);
				
				TimerCheckOnline(5);
			
				

		
		}//while	
			
			con.Desconectar();
	}
		
		private void TimerCheckOnline(int tiempo){
			
			
			try {
				Thread.sleep(tiempo*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
 		private boolean ConsultaSiOnline(int IdRadio){
		
 			int antes= (int) System.currentTimeMillis();
			boolean OnLine=false;
		
			con=new Conexion();
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
			
			int despues= (int) System.currentTimeMillis();
			System.out.println("Tiempo:"+String.valueOf(despues-antes));
			return OnLine;
			
			
			
			
		}
		
		
		}
