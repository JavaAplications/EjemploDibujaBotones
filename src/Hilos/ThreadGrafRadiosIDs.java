package Hilos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

import BBDD.Conexion;
import Objetos.btn_Radiobase;
import Timers.TemporizadorPintar;
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
	JLabel lbl_CantidadRadios;
	JProgressBar BarraProgresoCarga;
	
	public ThreadGrafRadiosIDs(JPanel jPanel1,JLabel lbl_CantidadRadios,JProgressBar BarraProgresoCarga){
		
		this.jPanel1=jPanel1;
		this.lbl_CantidadRadios=lbl_CantidadRadios;
		this.BarraProgresoCarga=BarraProgresoCarga;
		
	}
	

	private void BarradeProgreso(){

		
	}
	
	
	public void run(){
		
		jPanel1.removeAll(); 
		con=new Conexion();
		
		System.out.println("conectar bbdd ThreadGrafRadiosIDs");
		CantidadRadiobases=con.CantidadRadiobases();	
		int cantidad=CantidadRadiobases;
		
		lbl_CantidadRadios.setText(String.valueOf(cantidad));
		BarraProgresoCarga.setMaximum(cantidad);
		BarraProgresoCarga.setValue(0);
		
		
	VectorBotones=new btn_Radiobase[cantidad];
	int c=1;
		for( int i=1;i<cantidad+1;i++){
			
			IdRadio=i;
			BarraProgresoCarga.setValue(c);
			c++;
			
		    nombre=con.ConsultarNombre(i);
	      
			btn_Radio=new btn_Radiobase();
			
		btn_Radio.setText("Id:"+i+" - "+nombre);
		btn_Radio.setID(i);
		btn_Radio.setAlarmado(false);
		ResultSet rs=con.ConsultarInfoRadiobase(i);
		String Info=null;
		
		try {
			while(rs.next()){
				btn_Radio.setNombre( rs.getString("NomRadio"));
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
		
		btn_Radio.addActionListener(this);
		
		VectorBotones[i-1]=btn_Radio;
		
		
			jPanel1.add(btn_Radio);
		
	
		}

		SwingUtilities.updateComponentTreeUI(jPanel1);


			
	con.Desconectar();
	
	}


	public void actionPerformed(ActionEvent arg0) {
		int Id=arg0.getActionCommand().indexOf(" ");
		String nuero=arg0.getActionCommand().substring(3, Id);
		int IdRadioBase=Integer.parseInt(nuero);
		String ID=String.valueOf(VectorBotones[IdRadioBase-1].getID());
		String nombre=VectorBotones[IdRadioBase-1].getNombre();
			Ventana_Radiobase JFrameRadio=new Ventana_Radiobase(IdRadioBase);
			JFrameRadio.setTitle("ID:"+ID+" - "+nombre);
		JFrameRadio.setVisible(true);
		
		
	}
	
	
	
	
	
	

}
