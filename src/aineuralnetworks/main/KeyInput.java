/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Adminn
 */
public class KeyInput implements KeyListener{
    public Handler handler;
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    @Override
    public void keyTyped(KeyEvent key) {
    }

    @Override
    public void keyPressed(KeyEvent key) {
        switch(key.getKeyCode()){
            case KeyEvent.VK_SPACE:
                
        }
    }

    @Override
    public void keyReleased(KeyEvent key) {
    }
    
}
