/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.frame;

import aineuralnetworks.main.Main;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Adminn
 */
public class Frame {
    
    public JFrame frame;
    public Main main;
    
    public Frame(Main main){
        this.main = main;
        frame = new JFrame("Google dinousar 1");
        frame.setVisible(true);
        frame.setSize(1800,840);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(main);
        main.start();
    }
}
