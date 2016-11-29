/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.layers;

/**
 *
 * @author Adminn
 * 
*/

public abstract class AHiddenLayer{
    
    public double[] inputs;
    public double[] weights;
    
    public AHiddenLayer(double[] inputs, double[] weights){
        this.inputs = inputs;
        this.weights = weights;
    }
    public AHiddenLayer(double[] inputs){
        this.inputs = inputs;
    }
    
    public abstract double calculateWeightedSum();
    
    public abstract double activationFunction(double sum);
    
    public abstract double[] calculateNewWeights(double error, double learningRate);
    
   
}
