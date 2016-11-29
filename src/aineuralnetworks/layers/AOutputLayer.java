/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.layers;

/**
 *
 * @author Adminn
 */
public abstract class AOutputLayer {
    public double[] inputs;
    public double[] weights;
    
    public AOutputLayer(double[] inputs, double[] weights){
        this.inputs = inputs;
        this.weights = weights;
    }
    
    public abstract double calculateWeightedSum();
    
    public abstract double activationFunction(double weightedSum);
    
    public abstract double[] calculateNewWeights(double error,double learningRate);
}
