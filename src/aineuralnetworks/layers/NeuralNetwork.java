/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.layers;

import aineuralnetworks.main.Handler;
import java.util.LinkedList;

/**
 *
 * @author Adminn
 */
public class NeuralNetwork {

    /*
      This class manage the connection between Layers
      Weighted sum of all layers - working
      activation function of all layers - working
      generating weights of all layers - working
      backprogpagation - not working  
    
      TODO: add list for genetic algortithm, which
            handles all neurons
      TODO: all actiavtion function on each layer are same, fix it 
     */
    
    public LinkedList<AHiddenLayer> inputLayer;
    public LinkedList<AHiddenLayer> hiddenLayer;
    public LinkedList<AHiddenLayer> hiddenLayer2;
    public LinkedList<AHiddenLayer> outputLayer;
    public double[] inputs = new double[3];
    public double[] hiddenWeights = new double[3];
    public double[] hiddenWeights2 = new double[4];
    public double[] outputWeights = new double[4];
    public final double JUMPING_TARGET = 0.7;
    public final double LEARNING_RATE = 0.5;
    public double output = 0;
    
    public NeuralNetwork() {
        hiddenLayer = new LinkedList<>();
        outputLayer = new LinkedList<>();
        hiddenLayer2 = new LinkedList<>();
    }
    
    /*
        Initializing weights in each layer
    */
      
    public void initializeHiddenLayer()  {
        for (int l = 0; l < 4; l++) {
            for (int i = 0; i < hiddenWeights.length; i++) {
                hiddenWeights[i] = Math.random();
            }
            this.hiddenLayer.add(new HiddenLayer(inputs, hiddenWeights));
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < hiddenWeights2.length; j++) {
                hiddenWeights2[j] = Math.random();
            }
            this.hiddenLayer2.add(new HiddenLayer(inputs, hiddenWeights2));
        }
        for (int i = 0; i < outputWeights.length; i++) {
            outputWeights[i] = Math.random();
        }
        this.outputLayer.add(new HiddenLayer(inputs, outputWeights));
    }
    
    public void setInputs(double speed, double distance, double length) {
        inputs[0] = speed;
        inputs[1] = distance;
        inputs[2] = length;
    }
        
    /*
        calculate all variables in each layer
        @weights - weights of each neuron
        @layer2inputs and @outputInputs are there for
        calculating in backpropagation new weights
    */
    public void calculateHiddenLayer() {
        double[] layer2inputs = new double[this.hiddenWeights2.length];
        double[] outputInputs = new double[this.hiddenWeights2.length];
        /*
        *   calculating variables for first hidden layer
        */
        for (int i = 0; i < this.hiddenLayer.size(); i++) {
            AHiddenLayer layer = this.hiddenLayer.get(i);
            layer.inputs = this.inputs;
            layer.activationFunction(layer.calculateWeightedSum());
            layer2inputs[i] = layer.activationFunction(layer.calculateWeightedSum());                  
        }
        
        /*
        *   calculating variables for second hidden layer
        */
        
        for (int i = 0; i < this.hiddenLayer2.size(); i++) {
            AHiddenLayer layer = this.hiddenLayer2.get(i);
            layer.inputs = layer2inputs;
            layer.activationFunction(layer.calculateWeightedSum());
            outputInputs[i] = layer.activationFunction(layer.calculateWeightedSum());
        }
        
        /*
        *   calculating variables for output layer
        */
        
        for (int i = 0; i < this.outputLayer.size(); i++) {
            AHiddenLayer outputLayer = this.outputLayer.get(i);
            outputLayer.inputs = outputInputs;
            output = outputLayer.activationFunction(outputLayer.calculateWeightedSum());
        }
    }
    /*
        calculating new weights for each layer
    */
    
    public void adjustWeight(){
          
    }
    /*
    *   @backPropagation - the algorithm for deep learning
    *   calculating errors (expected output - actual output)
    *   adjusting new weights so in next time we get better results
    */
    public void backPropagation(LinkedList<AHiddenLayer> layer){
        for(int i=0;i<layer.size();i++){
            AHiddenLayer actualLayer = layer.get(i);
            double delta = actualLayer.inputs[i]*getErrorNet()*getErrorCalc();
            //ADD OLD WEIGHTS TO HIDDENLAYER FOR CONTINUE IN OTHE HIDDENLAYER IMPORTANT!!
            actualLayer.weights[i] = actualLayer.weights[i]-LEARNING_RATE*delta;        
        } 
    }
    /*
        
    */
    public double getErrorNet(){
        return output*(1-output);
    }
    public double getErrorCalc(){
        return -(JUMPING_TARGET-output);
    }
    public double getError(){
        double error = 0.5*Math.pow((0.7-output),2);
        return error;
    }
}
