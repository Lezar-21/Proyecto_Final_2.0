package Clases;

//import java.io.InterruptedException;
import java.util.ArrayList;
import java.util.LinkedList;

import Ventanas.MiPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Monitor {
    final int NUM_MAX = 10;
    final int TIME_SLEEP = 5000;
    public LinkedList<Articulo> cola = new LinkedList<>();
    public int cont = 0; 
    //private boolean productoEliminado = false;
    
    MiPanel mp;
    public Monitor(MiPanel mpx){
        this.mp = mpx;
    }

    public synchronized void push(Productor paux) throws InterruptedException{
        while(cont == NUM_MAX){
            wait();
        }
        Articulo nuevoArticulo = new Articulo(cont,mp);
        
        paux.setDurmiendo(false);
        paux.setActivo(true);
        //parte grafica
        mp.nuevoArticulo(nuevoArticulo);
        mp.repaint();
        
        
        System.out.println("Productor: "+Thread.currentThread().getName()+" produce en "+ cont);
        
        //sirve para dar un espacio entre cada thread
		Thread.sleep(TIME_SLEEP);
		
		nuevoArticulo.setNuevo(false);
        cola.add(nuevoArticulo);
        
		paux.setDurmiendo(true);
		paux.setActivo(false);
        cont ++;
        notify();
    }

    //elimina el frente
    public synchronized Articulo pop(Consumidor caux) throws InterruptedException{
        notify();
        while(cont <= 0){
            wait();
        }
        Articulo eliminar = cola.pop();
        
        caux.setDurmiendo(false);
        caux.setActivo(true);
        //elimina el articulo en el panel
        mp.quitarArticulo(eliminar);
        //productoEliminado = true;
        mp.repaint();
        
        System.out.println("El consumidor: "+Thread.currentThread().getName()+" consume "+eliminar.index);
        
        //sirve para dar un espacio entre cada thread
      	Thread.sleep(TIME_SLEEP);
      		        
        //se actualizan los indices
        int i = 0;
        for(Articulo a : cola) {
        	a.setIndex(i);
        	i++;
        }
        
        caux.setDurmiendo(true);
        caux.setActivo(false);
        cont --;
        return eliminar;
    }
    
    public void dibujarMonitor(Graphics2D g2) {
    	int x = 242, y=160, width = 54, height = 54,sumx=0, sumy=0;
    	boolean abajo = false;
    	float[] pattern = {10f,10f};
    	Stroke stroke = new BasicStroke(10f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,1f,pattern,50f);
    	g2.setColor(Color.RED);
    	g2.setStroke(stroke);
    	g2.drawRect(227, 140, 340, 250);
    	g2.setStroke(new BasicStroke());
    	g2.setColor(Color.GREEN);
    	g2.fillRect(227, 140, 340, 250);
    	g2.setColor(Color.BLACK);
    	
    	for(int i=0; i<NUM_MAX;i++) {
    		
    		g2.drawRect(x+sumx, y+sumy, width, height);
    		
    		if(i==4) {
    			y += 160;
    			abajo = true;
    		}else if(i<4) {
    			sumx += 63;
    		}else if(i>4) {
    			sumx -= 63;
    		}
    		
    		if(abajo == false) {
    			sumy += 53;
    			abajo = true;
    		}else {
    			sumy -= 53;
    			abajo = false;
    		}
    	}
    }
    
    public int get_MAX() {
    	return NUM_MAX;
    }

}
