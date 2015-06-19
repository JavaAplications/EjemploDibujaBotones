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
