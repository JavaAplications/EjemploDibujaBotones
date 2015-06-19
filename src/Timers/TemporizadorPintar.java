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
		
		try {
			Thread.sleep(1000);
			pintar=new ThreadPintarBotones(ThreadGrafRadiosIDs.VectorBotones);
			pintar.start();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	}
	

}
