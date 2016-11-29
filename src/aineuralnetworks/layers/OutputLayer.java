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
public class OutputLayer extends AOutputLayer{

    public OutputLayer(double[] inputs, double[] weights) {
        super(inputs, weights);
    }

    @Override
    public double calculateWeightedSum() {
        double weightedSum = 0;
        for(int i=0;i<this.weights.length;i++){
            weightedSum+=this.inputs[i]*this.weights[i];
        }
        return weightedSum;
    }

    @Override
    public double activationFunction(double weightedSum) {
        return (1/1+Math.exp(-weightedSum));
    }

    @Override
    public double[] calculateNewWeights(double error, double learningRate) {
        double[] adjustedWeights = new double[this.weights.length]; 
        for(int i=0;i<this.weights.length;i++){
            adjustedWeights[i] = error*learningRate*this.weights[i]+this.inputs[i];
        }
        return adjustedWeights;
    }
    
}
