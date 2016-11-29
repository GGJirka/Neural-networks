/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.layers;

import java.awt.Graphics;

/**
 *
 * @author Adminn
 */
public class HiddenLayer extends AHiddenLayer {
    
    public HiddenLayer(double[] inputs, double[] weights){
        super(inputs, weights);
    }    
    
    public HiddenLayer(double[] inputs){
        super(inputs);
    }
    
    @Override
    public double calculateWeightedSum(){
        double weightedSum = 0;
        for(int i=0;i<weights.length;i++){
            weightedSum+=weights[i]*inputs[i];
            
        }
        return weightedSum;
    }
    @Override
    public double activationFunction(double sum){
        return (1/(1+Math.exp(-sum)));
        //return Math.exp(-Math.pow(sum,2));
        //return Math.sin(sum)/2+0.5;
    }

    @Override
    public double[] calculateNewWeights(double error, double learningRate) {
        double[] adjustedWeights = new double[this.weights.length];
        for(int i=0;i<this.weights.length;i++){
            adjustedWeights[i] = error*learningRate*weights[i]+inputs[i];
        }
        return adjustedWeights;
    }
}
