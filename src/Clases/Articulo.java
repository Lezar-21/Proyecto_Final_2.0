package Clases;

import Ventanas.MiPanel;
import Ventanas.Ventana;

import java.awt.Graphics2D;

public class Articulo {
    int index;
    int x = 0, y = 0,  velX, velY, tam;    
    MiPanel mp;

    //prueba
    public Articulo(int index){
    	this.index = index;
    }
    public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Articulo(){
    }

    public void dibujarArticulo(Graphics2D g2){

        switch(index){
            case 0 : g2.fillOval(242, 160, 54, 54);
            break;
            case 1 : g2.fillOval(305, 214, 54, 54);
            break;
            case 2 : g2.fillOval(368, 160, 54, 54);
            break;
            case 3 : g2.fillOval(431, 214, 54, 54);
            break;
            case 4 : g2.fillOval(494, 160, 54, 54);
            break;
            case 5 : g2.fillOval(242, 267, 54, 54);
            break;
            case 6 : g2.fillOval(305, 321, 54, 54);
            break;
            case 7 : g2.fillOval(368, 267, 54, 54);
            break;
            case 8 : g2.fillOval(431, 321, 54, 54);
            break;
            case 9 : g2.fillOval(494, 267, 54, 54);
            break;
            default : System.out.println("ERROR!!!!!!!!!!");
        }

    }
    
    public void dibujarArticuloAux(Graphics2D g2){
    		
    }
	@Override
	public String toString() {
		return "Articulo [index=" + index+ "]";
	}
  
}