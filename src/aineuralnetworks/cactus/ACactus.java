/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aineuralnetworks.cactus;

/**
 *
 * @author Adminn
 */
public abstract class ACactus {
    public int y,width,height;
    public double x;
    public double velX;
    
    public ACactus(double x, int y,int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public abstract void render(java.awt.Graphics g);
    
    public abstract java.awt.Rectangle getRectangle();
}
