package Objetos;

import javax.swing.JButton;

public class btn_Radiobase extends JButton{
	
	int ID;
	
	public btn_Radiobase(int ID){
		
		this.ID=ID;
		
	}


	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	

}
