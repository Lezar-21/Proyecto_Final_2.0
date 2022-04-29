package Ventanas;


import Clases.Productor;
import Clases.Consumidor;
import Clases.Monitor;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {
	public static Monitor monitor;
    private MiPanel mp;
    private final int MAX_PRODUC = 3;
    private int produCont = 0;
    private final int MAX_CONSUM = 3;
    private int consumCont = 0;
    
    JButton b1;
    public Ventana(String titulo){
        super(titulo);
        this.setBounds(10,10,800,600);
        
        mp = new MiPanel(this);
        this.add(mp);

        monitor = new Monitor(mp);

        System.out.println(this.iniciarProductor());
        System.out.println(this.iniciarProductor());
        System.out.println(this.iniciarProductor());
        System.out.println(this.iniciarProductor());
        
        System.out.println(this.iniciarConsumidor());
        System.out.println(this.iniciarConsumidor());
        System.out.println(this.iniciarConsumidor());
//        b1 = new JButton();
//        b1.addActionListener( new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                    mp.repaint();
//            }
//        });
//        this.add(b1);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        while(true){
        	mp.moverPelota();
            mp.repaint();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }
    }

    //este metodo usa generics para poder funcionar tanto con productores como con consumidores
    public <T extends Thread> void iniciarThread (T t){
        t.start();
    }


    public <T extends Thread> void finalizarThread (T t){
        try {
	    	while(true){
	            t.wait();
	    	}
        }catch(InterruptedException e) {
        
        }
    }
    
    public int iniciarProductor(){
        if(produCont<MAX_PRODUC){
        	Productor p = new Productor(mp);
        	p.setName("productor "+produCont);
        	iniciarThread(p);
        	mp.nuevoProductor(p);
        	produCont++;
        	return 1;
        }
        return 0;
    }
    
    public int iniciarConsumidor(){
        if(consumCont<MAX_CONSUM){
        	Consumidor c = new Consumidor(mp);
        	c.setName("Consumidor "+consumCont);
        	iniciarThread(c);
        	mp.nuevoConsumidor(c);
        	consumCont++;
        	return 1;
        }
        return 0;
    }
}
