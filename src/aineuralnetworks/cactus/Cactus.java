/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.cactus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Adminn
 */
public class Cactus extends ACactus{

    public Cactus(double x, int y, int width, int height){
        super(x, y, width, height);
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.green);
        g.drawRect((int)x, y, width, height);
        x-=2;
        x-=velX;
        velX+=0.0001;
    }
    
    @Override
    public Rectangle getRectangle(){
        return new Rectangle((int)x,y,width,height);
    }
}
