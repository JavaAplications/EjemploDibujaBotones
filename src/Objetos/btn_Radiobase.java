package Objetos;

import javax.swing.JButton;

public class btn_Radiobase extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ID;
	String Telefono,Nombre,AcargoDE;
	public boolean alarmado=false;

//	static public boolean alarmado;
	public void setAlarmado(boolean alarmado) {
		this.alarmado = alarmado;
	}


	public btn_Radiobase(){
		
	
	}


	public String getTelefono() {
		return Telefono;
	}


	public void setTelefono(String telefono) {
		Telefono = telefono;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public String getAcargoDE() {
		return AcargoDE;
	}


	public void setAcargoDE(String acargoDE) {
		AcargoDE = acargoDE;
	}


	public boolean isAlarmado() {
		return alarmado;
	}


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	
}
