package Hilos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BBDD.Conexion;
import Objetos.btn_Radiobase;
import Ventanas.Ventana_Radiobase;

public class ThreadGrafRadiosIDs extends Thread implements ActionListener{
	Conexion con;
    
	JPanel jPanel1;
	int cantidad,cantidadOnline,IdRadio;
	String nombre;
	static public btn_Radiobase btn_Radio;
	int CantidadKA;
	public static btn_Radiobase[] VectorBotones;
	int CantidadRadiobases;
	
	
	public ThreadGrafRadiosIDs(JPanel jPanel1,int CantidadRadiobases){
		
		this.jPanel1=jPanel1;
		this.CantidadRadiobases=CantidadRadiobases;
		
		
	}
	
	
	public void run(){
		jPanel1.removeAll(); 
		con=new Conexion();
		System.out.println("conectar bbdd ThreadGrafRadiosIDs");
				
		int cantidad=CantidadRadiobases;
		
		
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
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btn_Radio.setToolTipText(Info);
		btn_Radio.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if((JButton)arg0.getSource()==btn_Radio){
					
					System.out.println(btn_Radio.getText().toString());
				};
			}
			
		});
	
		btn_Radio.addActionListener(this);
		
		VectorBotones[i-1]=btn_Radio;
		
		
			jPanel1.add(btn_Radio);
		
			
			btn_Radio.setVisible(true);
			
			
		}
		
		
	
		SwingUtilities.updateComponentTreeUI(jPanel1);
		ThreadPintarBotones pintar=new ThreadPintarBotones(VectorBotones);
		pintar.start();
		
		
	
	con.Desconectar();
//		
	}


	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0.getActionCommand());	
		
		int Id=arg0.getActionCommand().indexOf(" ");
		String nuero=arg0.getActionCommand().substring(3, Id);
		int IdRadioBase=Integer.parseInt(nuero);
		System.out.println(VectorBotones[IdRadioBase-1].getToolTipText());
		VectorBotones[IdRadioBase-1].setBackground(Color.GREEN);
		JOptionPane.showMessageDialog(null, "ALARMA CARAJO", VectorBotones[IdRadioBase-1].getText(), JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
	
	
	
	

}
