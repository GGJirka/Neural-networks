/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.StateManager;

import aineuralnetworks.main.GameState;
import java.io.Serializable;



/**
 *
 * @author Adminn
 */
public class StateManager implements Serializable{
    
    /*
    *   @serialVersionUID - universal key for serializable class,
    *    used when you want to save something to file (writeObject())
    */
    
    private static final long serialVersionUID = 7526472295622776147L;
    
    /*
    @StateMananger - managing the enum class for game states
    */    
    public GameState state;
 
    public StateManager(GameState state){
        this.state = state;
    }
    
    public void setState(GameState state){
        this.state = state;
    }
    
    public GameState actualState(){
        return this.state;
    }   
}
