package Clases;

import Ventanas.MiPanel;
import Ventanas.Ventana;

import java.awt.Graphics2D;

public class Articulo {
    int index;
    int x=158, y=0,xfinal=242, yfinal=160,  velX = 10, velY=10, tam = 54;    
    MiPanel mp;
    boolean nuevo = true;
    boolean eliminado = false;
    
    //prueba
    public Articulo(int index, MiPanel mp){
    	this.index = index;
    	this.mp = mp;
    	this.setFinalCoordenades(index,0);
    }
    
    public int getIndex() {
		return index;
	}
    
	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setFinalCoordenades(int index,int indexC) {
//		switch(index){
//	        case 0 :this.xfinal=242;this.yfinal=160;
//	        break;
//	        case 1 :this.xfinal=305;this.yfinal=214;
//	        break;
//	        case 2 :this.xfinal=368;this.yfinal=160;
//	        break;
//	        case 3 :this.xfinal=431;this.yfinal=214;
//	        break;
//	        case 4 :this.xfinal=494;this.yfinal=160;
//	        break;
//	        case 5 :this.xfinal=242;this.yfinal=267;
//	        break;
//	        case 6 :this.xfinal=305;this.yfinal=321;
//	        break;
//	        case 7 :this.xfinal=368;this.yfinal=267;
//	        break;
//	        case 8 :this.xfinal=431;this.yfinal=321;
//	        break;
//	        case 9 :this.xfinal=494;this.yfinal=267;
//	        break;
//	        default : System.out.println("ERROR!!!!!!!!!!");
//	    }
		int sumx=0, sumy=0, i=0;
		if(eliminado==false) {
			
			boolean abajo = false;
	    	while(i<index && index!=0) {
	    		if(i==4) {
	    			sumy += 160;
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
	    		i++;
	    	}
	    	xfinal+=sumx;
			yfinal+=sumy;
		}
		if(eliminado==true) {
			x = xfinal;
			y = yfinal;
			yfinal = 100;
			System.out.println("indexC: "+indexC);
			while(i<indexC) {
				sumy += 160;
				i++;
			}
			xfinal = 640;
			yfinal += sumy;
			System.out.println("yfinal: "+yfinal);
		}
	}
	
    public void dibujarArticulo(Graphics2D g2){
    	if(eliminado==false)g2.fillOval(xfinal, yfinal, tam, tam);
    	if(eliminado==true)g2.fillOval(x, y, tam, tam);
    }
    
    public void dibujarArticuloAux(Graphics2D g2,int indexProductor){
    	if(y<50) {
	    	int i = 0, sumy =50;
	    	while(indexProductor>i) {
	    		sumy += 165;
	    		i++;
	    	}
	    	y=sumy;
    	}
    	g2.fillOval(x,y,tam,tam);
    }
    public void moverArticulo() {
    	
    	if(x + velX<=xfinal) {
    		x = x + velX;
     	}else if(x+velX>xfinal || x<=xfinal){
     		x = x - velX;
     	}
    	
    	if(y + velY<=yfinal&&x<xfinal) {
    		y = y + velY;
     	}else if(y+ velY > yfinal || y<=yfinal) {
    		y = y-velY;
    	}
    	
    	//if (x + velX < 0) velX = velX * -1;
        //if (x + velX >= xfinal) velX = 0;
        //if (y + velY < 0) velY = velY * -1;
//        if (y + velY >= yfinal) velY = 0;
//        if(x + velX<xfinal) x = x + velX;
//        if(y + velY<yfinal)y = y + velY;
//        
    }
    
	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Articulo [index=" + index+ "]";
	}
  
}