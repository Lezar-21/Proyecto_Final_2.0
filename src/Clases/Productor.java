package Clases;

import Ventanas.MiPanel;
import Ventanas.Ventana;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.awt.Color;

public class Productor extends Thread{
    int[] x = {132,100,160,176,87,105,55};
    int[] y = {83,142,180,100,68,71,39,44};
    boolean durmiendo = true;

    MiPanel mp;
    public Productor(MiPanel mpx){
        this.mp = mpx;
    }
  
    @Override
    public void run(){
        while(true){
            try {
            	this.producir();
            	sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }     
        }
    }
    
    public void dibujarProductor(Graphics2D g2, int abajo){
        
        g2.drawLine(x[0] , y[0] + abajo , x[0], y[1] + abajo );
        g2.drawLine(x[0] ,y[1] + abajo , x[1], y[2] + abajo );
        g2.drawLine(x[0], y[1] + abajo , x[2], y[2] + abajo );
        g2.drawLine(x[0], y[3] + abajo , x[3], y[4] + abajo );
        g2.drawLine(x[0], y[3] + abajo , x[4], y[5] + abajo );
        g2.setColor(Color.BLACK); 
        g2.fillOval(x[5],y[6] + abajo , x[6], y[7] );
    }

    public void producir(){
        try{
            Ventana.monitor.push(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void dibujarDormido(Graphics2D g2, int abajo) {
    	int p1 = 160, p2 = 60 + abajo, p3 = 168, p4 = 51 + abajo;
    	
    	//dibuja la onomatopella "zzz"
    	for(int i =0; i<3; i++) {
    		
        	g2.drawLine(p1, p2, p3, p2);
        	g2.drawLine(p1, p2, p3, p4);
        	g2.drawLine(p1, p4, p3, p4);
        	
        	p1 += 10;
        	p2 -= 26;
        	p3 += 10;
        	p4 -= 26;
        	
    	}
    }

	@Override
	public String toString() {
		return "Productor [x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + ", mp=" + mp + "]";
	}

	public boolean isDurmiendo() {
		return durmiendo;
	}
	
	public void setDurmiendo(boolean dormido) {
    	this.durmiendo = dormido;
    }

}
