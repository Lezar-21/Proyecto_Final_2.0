package Clases;

import Ventanas.MiPanel;
import Ventanas.Ventana;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.awt.Color;

public class Consumidor extends Thread{

    int[] x = {662,630,690,706,617,635,55};
    int[] y = {83,142,180,100,68,71,39,44};
    boolean durmiendo = true;
    boolean activo = false;
    MiPanel mp;

    public Consumidor(MiPanel mpx){
        this.mp = mpx;
    }

    @Override
    public void run(){
        try {   
            while(true){
                this.consumir();
                sleep(5000);
            }   
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
    
    public void dibujarConsumidor(Graphics2D g2, int abajo){

        //torso
        g2.drawLine(x[0] , y[0] + abajo , x[0], y[1] + abajo );
        //piernas
        g2.drawLine(x[0] ,y[1] + abajo , x[1], y[2] + abajo );
        g2.drawLine(x[0], y[1] + abajo , x[2], y[2] + abajo );
        //brazos
        g2.drawLine(x[0], y[3] + abajo , x[3], y[4] + abajo );
        g2.drawLine(x[0], y[3] + abajo , x[4], y[5] + abajo );
        //cabeza
        g2.setColor(Color.BLACK); 
        g2.fillOval(x[5],y[6] + abajo , x[6], y[7] );
    }
    
    public void dibujarActivo(Graphics2D g2, int abajo){
//    	int[] x = {662,630,690,706,617,635,55};
//      int[] y = {83,142,180,100,68,71,39,44};
        g2.drawLine(x[0] , y[0] + abajo , x[0], y[1] + abajo );
        
        g2.drawLine(x[0] ,y[1] + abajo , x[1], y[2] + abajo );
        g2.drawLine(x[0], y[1] + abajo , x[2], y[2] + abajo );
        
        g2.drawLine(x[0], y[3] + abajo , 692, 94 + abajo );
        g2.drawLine(692, 94 + abajo , 671, 129 + abajo );
        
        g2.drawLine(x[0], y[3] + abajo , 632, 94 + abajo );
        g2.drawLine(632, 94 + abajo , 653, 129 + abajo );
        
        g2.setColor(Color.BLACK); 
        g2.fillOval(x[5],y[6] + abajo , x[6], y[7] );
    }

    public void consumir(){
        try {
            Ventana.monitor.pop(this);
         } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public void dibujarDormido(Graphics2D g2, int abajo) {
    	int p1 = 687, p2 = 61 + abajo, p3 = 698, p4 = 51 + abajo;
    	
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
    
    public boolean isDurmiendo() {
		return durmiendo;
	}
    
    public void setDurmiendo(boolean dormido) {
    	this.durmiendo = dormido;
    }
    
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Consumidor [x=" + Arrays.toString(x) + ", y=" + Arrays.toString(y) + ", mp=" + mp
				+ "]";
	}
}
