/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneticAlgorithm;

import aineuralnetworks.layers.AHiddenLayer;

/**
 *
 * @author Adminn
 */
public interface IGeneticAlgorithm {
    
    public void createMatingPool();
    
    public double calculateFitness();
    
    public AHiddenLayer crossover(AHiddenLayer subjectB);
    
    public AHiddenLayer mutate();
    
}
