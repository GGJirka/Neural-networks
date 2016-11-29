/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithm;

import aineuralnetworks.layers.AHiddenLayer;
import aineuralnetworks.main.Handler;

/**
 *
 * @author Adminn
 * 
 */
public class GeneticAlgorithm implements IGeneticAlgorithm{
    
    public Handler handler;
    
    public GeneticAlgorithm(Handler handler){
        this.handler = handler;
    }
    /*
        createMatingPool() - list which contains neural network
        the more fitness they have the more place they will take.
    */
    
    @Override
    public void createMatingPool() {
        
    }
    /*
        calculateFitness() - return fitness, calculate each neural netowork
        how performed.
    */
    @Override
    public double calculateFitness(){
        return 0;
    }
    /*
        crosssover() - take a part from first subject and another
        part from second subject. 
    */
    @Override
    public AHiddenLayer crossover(AHiddenLayer subjectB) {
        return null;
    }
    /*
        mutate() - for each weights in neural network
        create a random new weight
    */
    @Override
    public AHiddenLayer mutate() {
        return null;
    }
    
}
