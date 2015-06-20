package Timers;

import Hilos.ThreadGrafRadiosIDs;
import Hilos.ThreadPintarBotones;

public class TemporizadorPintar extends Thread {
	ThreadPintarBotones pintar;
	boolean stop=true;
	
	public TemporizadorPintar(){
		
		
		
	}
	
	public void Detener(){
		
		stop=false;
		
	}
	public void run(){
		while(stop){
		
			pintar=new ThreadPintarBotones(ThreadGrafRadiosIDs.VectorBotones);
			pintar.start();
			pintar=null;
		try {
			
			Thread.sleep(500);
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	}
	

}
