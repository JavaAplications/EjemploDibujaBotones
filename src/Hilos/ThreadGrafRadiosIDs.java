package Hilos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BBDD.Conexion;
import Objetos.btn_Radiobase;
import Ventanas.Ventana_Radiobase;

public class ThreadGrafRadiosIDs extends Thread{
	Conexion con;
    
	JPanel jPanel1;
	int cantidad,cantidadOnline,IdRadio;
	String nombre;
	static public btn_Radiobase btn_Radio;
	int CantidadKA;
	public static btn_Radiobase[] VectorBotones;
	
	
	public ThreadGrafRadiosIDs(JPanel jPanel1){
		
		this.jPanel1=jPanel1;
		
		
	}
	
	
	public void run(){
		con=new Conexion();
		System.out.println("conectar bbdd ThreadGrafRadiosIDs");
		
		
		
		int cantidad=con.CantidadRadiobases();
		
		jPanel1.removeAll(); 
	VectorBotones=new btn_Radiobase[cantidad];
	
		for( int i=1;i<cantidad+1;i++){
			
			IdRadio=i;
			
		    nombre=con.ConsultarNombre(i);
	      
			btn_Radio=new btn_Radiobase();
			
		btn_Radio.setText("Id:"+i+" - "+nombre);
		btn_Radio.setID(i);
		btn_Radio.setAlarmado(false);
		ResultSet rs=con.ConsultarInfoRadiobase(i);
		String Info=null;
		try {
			while(rs.next()){
				
				Info = rs.getString("NomRadio")+" | ";
				Info= Info + "Contacto: "+rs.getString("ContacRadio")+" | ";
				Info= Info + "Tel: "+rs.getString("TelRadio")+" | ";
				Info= Info + "Local: "+rs.getString("LocRadio")+" | ";
				Info= Info + "Prov: "+rs.getString("ProvRadio");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btn_Radio.setToolTipText(Info);
	
		
		VectorBotones[i-1]=btn_Radio;
		
		
		
		
		
			jPanel1.add(btn_Radio);
		
			
			btn_Radio.setVisible(true);
			
			
		}
		
		
	
		SwingUtilities.updateComponentTreeUI(jPanel1);
		ThreadPintarBotones pintar=new ThreadPintarBotones(VectorBotones);
		pintar.start();
		
		
	
	//con.Desconectar();
//		
	}
	
	
	
	
	
	

}
