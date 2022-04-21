package Ventanas;


import Clases.Productor;
import Clases.Consumidor;
import Clases.Monitor;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {
	public static Monitor monitor;
    MiPanel mp;
    JButton b1;
    public Ventana(String titulo){
        super(titulo);
        this.setBounds(10,10,800,600);

        mp = new MiPanel(this);
        this.add(mp);

        monitor = new Monitor(mp);

        Consumidor c = new Consumidor(mp);
        c.setName("consumidor 1");
        iniciarThread(c);

        mp.nuevoConsumidor(c);

        Consumidor c1 = new Consumidor(mp);
        c1.setName("consumidor 2");
        iniciarThread(c1);
        
        mp.nuevoConsumidor(c1);

        // mp.nuevoConsumidor(c1);

        // Consumidor c1 = new Consumidor(mp);
        // c1.setName("consumidor 2");
        // mp.startThreat(c1);

        Productor p = new Productor(mp);
        p.setName("productor 1");
        iniciarThread(p);

        mp.nuevoProductor(p);
        
        Productor p1 = new Productor(mp);
        p1.setName("productor 2");
        iniciarThread(p1);

        mp.nuevoProductor(p1);
        
//        b1 = new JButton();
//        b1.addActionListener( new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                    mp.repaint();
//            }
//        });
//        this.add(b1);
        
        // Productor p2 = new Productor(mp);
        // p2.setName("productor 1");
        // iniciarThread(p2);

        // mp.nuevoProductor(p2);

        // Productor p1 = new Productor(mp);
        // p1.setName("productor 2");
        // mp.startThreat(p1); 

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        /*
        while(true){
            mp.repaint();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                //TODO: handle exception
            }
        }*/
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
}
