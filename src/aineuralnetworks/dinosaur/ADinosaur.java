/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.dinosaur;

/**
 *
 * @author Adminn
 */

public abstract class ADinosaur {
    
    public double x, y; // x position y position
        
    public double vx,vy=0;
    
    public boolean jumping;

    public ADinosaur(double x, double y){
        this.x = x;
        this.y = y;
    }
    public abstract void render(java.awt.Graphics g);
    
    public abstract void update();
    
    public abstract void jump();
    
    public abstract java.awt.Rectangle getRectangle();
}
