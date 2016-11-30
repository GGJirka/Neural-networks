/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.frame;

import aineuralnetworks.main.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Adminn
 */
public class Frame {
    
    public JFrame frame;
    public JPanel stats;
    public Main main;
    
    public Frame(Main main){
        this.main = main;
        frame = new JFrame("Google dinousar 1");
        stats = new JPanel();
        stats.setLocation(new Point(1100,0));
        stats.setSize(new Dimension(300,800));
        stats.setBackground(new Color(198,194,193));
        frame.setVisible(true);
        frame.setSize(1800,840);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //frame.add(stats);
        frame.add(main);
        main.start();
    }
    public JPanel Panel(){
        return this.stats;
    }
}
