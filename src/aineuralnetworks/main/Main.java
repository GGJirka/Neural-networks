/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.main;

import aineuralnetworks.dinosaur.Dinosaur;
import aineuralnetworks.frame.Frame;
import aineuralnetworks.layers.NeuralNetwork;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adminn
 */
public class Main extends Canvas implements Runnable{

    public Thread thread;
    public Handler handler;
    public NeuralNetwork network;
    public int subject = 0;
    
    /**
    * @param args the command line arguments
    */
    
    // starting frame
    public static void main(String[] args){
        Frame frame = new Frame(new Main());
    }   
    public Main(){
        network = new NeuralNetwork();
        handler = new Handler(this, network);
        gameStarted();
    }
    //initialize thread, starting thread
    public void start(){
        try{
            thread = new Thread(this,"main thread");
            update();
            thread.start();
        }catch(ConcurrentModificationException ex){
            ex.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /*
    *   gameStarted() - this method is called when
    *   the game starts or when dinosaurus dies;
    */
    public boolean gameStarted(){
        network = new NeuralNetwork();
        handler.network = network;
        handler.dino.add(new Dinosaur(50,500));
        network.initializeHiddenLayer();
        network.calculateHiddenLayer();
        this.subject++;
        return true;
    }
    @Override
    public void run(){
        while(thread.isAlive()){
            render();
        }
    }
    public void update() throws Exception{
        Thread updateThread = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    handler.update();
                    handler.generateCactus();
                }
            }
        });
        updateThread.start();
    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs==null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, 10000, 1200);
        handler.render(g);
        g.setColor(Color.BLACK);
        g.drawString("subject: " + subject,150,150);
        g.dispose();
        bs.show();
    }
}
