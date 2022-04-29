package Ventanas;


import javax.swing.JPanel;

import Clases.Articulo;
import Clases.Consumidor;
import Clases.Productor;
import Clases.Monitor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class MiPanel extends JPanel{
    private Ventana v;
    private int contProduct = 0;
    private int indexPA;
    private ArrayList<Productor> producList = new ArrayList<>();
    private int contConsum = 0;
    private int indexCA;
    private ArrayList<Consumidor> consumList = new ArrayList<>();
    private int contArtic = 0;
    public ArrayList<Articulo> ArticList = new ArrayList<>();
    int eliminados=0;
    public MiPanel(Ventana vx){
        this.v=vx;
        this.setFocusable(true);    
    }


    public void nuevoProductor(Productor t){
        producList.add(t);
        contProduct ++;
    }
    public void nuevoConsumidor(Consumidor t){
        consumList.add(t);
        contConsum ++;
    }

    public void nuevoArticulo(Articulo t){
        ArticList.add(t);
        System.out.println("index: "+t.getIndex());
        eliminados --;
        contArtic ++;
    }
    
    public void quitarArticulo(Articulo eliminar) {
    	int indexEliminar = ArticList.indexOf(eliminar);
    	ArticList.get(indexEliminar).setEliminado(true);
    	ArticList.get(indexEliminar).setFinalCoordenades(indexEliminar,indexCA);
    }
    
    public void eliminarArticulo(Articulo eliminar) {
    	ArticList.remove(eliminar);
    	eliminados ++;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //int derecha = 530;
        //int abajo = 165;
        
        Ventana.monitor.dibujarMonitor(g2);
        
        //dibuja a los productores
        int abajo = 0;
        for(int i =0; contProduct>i;i++) {
        	if(producList.get(i).isDurmiendo()) {
        		producList.get(i).dibujarProductor(g2, abajo);
        		producList.get(i).dibujarDormido(g2, abajo);
        	}else if (producList.get(i).isActivo()) {
        		producList.get(i).dibujarActivo(g2, abajo);
        		indexPA = i;
        	}
        	abajo += 165;
        }
        
        //dibuja a los consumidores
        abajo = 0;
        for(int i =0; contConsum>i;i++) {
        	if(consumList.get(i).isDurmiendo()) {
        		consumList.get(i).dibujarConsumidor(g2,abajo);
        		consumList.get(i).dibujarDormido(g2, abajo);
        	}else if(consumList.get(i).isActivo()) {
        		consumList.get(i).dibujarActivo(g2, abajo);
        		indexCA = i;
        	}
        	abajo += 165;
        }

        //System.out.println("Tamano de arcticlist: "+ArticList.size());
        
        
        //dibuja los productos actualizados

        for(Articulo a : ArticList){
        	if(a.isNuevo()) {
        		a.dibujarArticuloAux(g2,indexPA);
        	}else {
        		a.dibujarArticulo(g2);
        	}
        }

        /*productos de arriba
        g2.fillOval(241, 160, 54, 54);
        g2.fillOval(305, 214, 54, 54);
        g2.fillOval(390, 160, 54, 54);
        g2.fillOval(454, 214, 54, 54);
        g2.fillOval(538, 160, 54, 54);
        
        //productos de abajo
        g2.fillOval(241, 256, 54, 54);
        g2.fillOval(305, 310, 54, 54);
        g2.fillOval(390, 256, 54, 54);
        g2.fillOval(454, 310, 54, 54);
        g2.fillOval(538, 256, 54, 54);
        */
    }
    
    public void moverPelota() {
    	for(Articulo a : ArticList){
    		if(a.isNuevo() || a.isEliminado()) {
    			a.moverArticulo();
        	}
    		
        }
    }
    
}
