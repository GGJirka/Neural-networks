/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.frame;

import aineuralnetworks.main.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author Adminn
 */
public class Console {
    
    public Main main;
    public Point location = new Point(1070,0);
    public Point size = new Point(300,800);
    public Font font = new Font("Arial",Font.BOLD,30);
    public String[] names = {"Subject: ","Speed: ","Distance: ","Length: ","Output: "};
    public Console(Main main){
        this.main = main;        
    }
    
    public void render(Graphics g){
        g.setColor(new Color(198,194,193));
        g.fillRect(location.x, location.y, size.x, size.y);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("Console", location.x+size.x/2-50, 50);
        g.drawRect(location.x,location.y,size.x,70);
        g.drawRect(location.x,70,size.x,size.y-70);
        
        g.setFont(new Font("Arial",Font.PLAIN,20));
        for(int i=0;i<names.length;i++){
            if(i>=1 && i<=3){
                g.drawString(names[i]+Math.round(main.network.inputs[i-1]*100.0)/100.0,location.x,90+i*30);
            }else{
                if(i==0)
                    g.drawString(names[i]+main.subject,location.x,90+i*30);
                else if(i==4)
                    g.drawString(names[i]+main.network.output,location.x,90+i*30);
            }
        }
    }   
    
}
