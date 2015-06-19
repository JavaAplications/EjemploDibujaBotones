package BBDD;

import java.sql.*;
import java.util.Vector;

public class Conexion {
Connection con=null;

public Conexion() {
		
	}

public  Connection Conectar(String nombre){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con= DriverManager.getConnection("jdbc:mysql://localhost/bdradiobases","root","");	
		
	} catch (Exception e) {
		System.out.println("No se pudo conectar "+nombre+" a las BBDD");
	}
	return con;
}
/*
public void InsertarOnline(boolean IdOnLine,int IdRadiobase){

	
	con=Conectar("InsertarOnline");
	
	PreparedStatement pst;
	try {
		pst = con.prepareStatement("INSERT INTO online (IdOnline) VALUES (?) WHERE `IdRadios`='"+IdRadiobase+"'");
	
		pst.setBoolean(1,IdOnLine);
		
		pst.execute();
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
	
	
}
*/

public ResultSet RadiobasesConectadas(){
	
	ResultSet rs=null;
	
	
	
	
	
	
	return rs;
}

public void InsertarCheckedByIdRadio(int IdRadios){

	
	con=Conectar("InsertarCheckedByIdRadio");
	
	PreparedStatement pst;
	try {
		pst = con.prepareStatement("UPDATE eventos SET `Checked` = ? WHERE `IdRadios`='"+IdRadios+"'");
	
		pst.setBoolean(1,true);
		
		pst.execute();
		//con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public void InsertarCheckedByIdEvento(int IdEvento){
	con=Conectar("InsertarCheckedByIdEvento");
	
	PreparedStatement pst;
	try {
		pst = con.prepareStatement("UPDATE eventos SET `Checked` = ? WHERE `IdEvento`='"+IdEvento+"'");
	
		pst.setBoolean(1,true);
		
		pst.execute();
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

public ResultSet ConsultarRadiosOnline(){

con=Conectar("ConsultarRadiosOnline");
	
    Statement st;
	ResultSet rs = null;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT keepalive.IdRadios,COUNT(*) as 'Cantidad' FROM keepalive LEFT JOIN radiobases ON keepalive.IdRadios=radiobases.IdRadios WHERE keepalive.TimeKA > DATE_ADD(now(),INTERVAL -60 SECOND) AND(radiobases.Habilitacion) GROUP BY keepalive.IdRadios ");
	
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
              }
	
	return rs;
}



public ResultSet ConsultarRadiosOffline(){

con=Conectar("ConsultarRadiosOffline");
	
    Statement st;
	ResultSet rs = null;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT keepalive.IdRadios,COUNT(*) as 'Cantidad' FROM keepalive LEFT JOIN radiobases ON keepalive.IdRadios=radiobases.IdRadios WHERE keepalive.TimeKA < DATE_ADD(now(),INTERVAL -60 SECOND) AND(radiobases.Habilitacion) GROUP BY keepalive.IdRadios ");
		
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
              }
	
	return rs;
}




public ResultSet ConsultarInfoRadiobase(int IdRadiobase){

con=Conectar("ConsultarInfoRadiobase");
	
    Statement st;
	ResultSet rs = null;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM `radiobases` WHERE `IdRadios`='"+IdRadiobase+"'");
	//	con.close();
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
              }
	
	return rs;
}

public String ConsultarNombre(int IdRadiobase)
{
	con=Conectar("ConsultarNombre");
	Statement st;
	ResultSet rs=null;
	String NombreRadio = null;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT `NomRadio` FROM `radiobases` WHERE `IdRadios`='"+IdRadiobase+"'");
		while(rs.next()){
			NombreRadio=  rs.getString("NomRadio");
				
					
		}
		
	
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 NombreRadio="Desconocido";
	}
	
	return NombreRadio;
}

public ResultSet ConsultaHab(){
	ResultSet rs=null;
con=Conectar("ConsultaHab");
	
    Statement st;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM `radiobases` WHERE `Habilitacion`=true");
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
              }

	return rs;
}


public ResultSet ConsultaDeshab(){
	ResultSet rs=null;
con=Conectar("ConsultaDeshab");
	
    Statement st;
	
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM `radiobases` WHERE `Habilitacion`=false");
		
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
              }

	return rs;
}


public boolean ConsultarHabilitado(int IdRadiobase)
{
	con=Conectar("ConsultarHabilitado");
	Statement st;
	ResultSet rs=null;
	boolean hab = false;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT `Habilitacion` FROM `radiobases` WHERE `IdRadios`='"+IdRadiobase+"'");
		while(rs.next()){
			hab=  rs.getBoolean("Habilitacion");
				
					
		}

	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			hab=false;
	}
	
	return hab;
}

public int CantidadRadiobases()
{
	con=Conectar("CantidadRadiobases");
	Statement st;
	ResultSet rs=null;
	int cantidad = 0 ;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM `radiobases`");
		
		rs.last();
		cantidad=rs.getRow();
		
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cantidad=0;
	}
	
	return cantidad;
}

public ResultSet ConsultarAlarmasOnline(){
	ResultSet rs=null;
	con=Conectar("ConsultarAlarmasOnline");
	Statement st;
	try {
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM `eventos` WHERE `Checked` =false" );
		
	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("sin alarmas");
	}
	
		return rs;
	
	
}

public void Desconectar(){
	
	try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}


}
