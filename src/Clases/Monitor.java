package Clases;

//import java.io.InterruptedException;
import java.util.ArrayList;
import java.util.LinkedList;

import Ventanas.MiPanel;

import java.awt.Graphics2D;

public class Monitor {
    final int NUM_MAX = 10;
    final int TIME_SLEEP = 5000;
    public LinkedList<Articulo> cola = new LinkedList<>();
    public int cont = 0; 
    
    MiPanel mp;
    public Monitor(MiPanel mpx){
        this.mp = mpx;
    }

    public synchronized void push(Productor paux) throws InterruptedException{
        while(cont == NUM_MAX){
            wait();
        }
        Articulo nuevoArticulo = new Articulo(cont);
        cola.add(nuevoArticulo);
        
        paux.setDurmiendo(false);
        //parte grafica
        mp.nuevoArticulo(nuevoArticulo);
        mp.repaint();
        System.out.println("Productor: "+Thread.currentThread().getName()+" produce en "+ cont);
        
        //sirve para dar un espacio entre cada thread
		Thread.sleep(TIME_SLEEP);
		paux.setDurmiendo(true);
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
        //elimina el articulo en el panel
        mp.quitarArticulo(eliminar);
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
        cont --;
        return eliminar;
    }

}
