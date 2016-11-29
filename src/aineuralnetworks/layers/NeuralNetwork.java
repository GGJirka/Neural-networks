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
     */
    public LinkedList<AHiddenLayer> inputLayer;
    public LinkedList<AHiddenLayer> hiddenLayer;
    public LinkedList<AHiddenLayer> hiddenLayer2;
    public LinkedList<AHiddenLayer> outputLayer;
    public double[] inputs = new double[3];
    public double[] hiddenWeights = new double[3];
    public double[] hiddenWeights2 = new double[4];
    public double[] outputWeights = new double[4];

    public NeuralNetwork() {
        hiddenLayer = new LinkedList<>();
        outputLayer = new LinkedList<>();
        hiddenLayer2 = new LinkedList<>();
    }
    /*
        Initializing weights in each layer
    */
    public void initializeHiddenLayer() {
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
    */
    public void calculateHiddenLayer() {
        double[] layer2inputs = new double[this.hiddenWeights2.length];
        double[] outputInputs = new double[this.hiddenWeights2.length];
        double error;
//        for(int i = 0;i<this.inputLayer.size();i++){
//            
//        }
        for (int i = 0; i < this.hiddenLayer.size(); i++) {
            AHiddenLayer layer = this.hiddenLayer.get(i);
            layer.inputs = this.inputs;
            layer.activationFunction(layer.calculateWeightedSum());
            layer2inputs[i] = layer.activationFunction(layer.calculateWeightedSum());
            //System.out.println(layer.activationFunction(layer.calculateWeightedSum())+" "+i);
            //error = layer2inputs[i] - inputs[i];
            //layer.weights = layer.calculateNewWeights(error, 0.05);                   
        }
        for (int i = 0; i < this.hiddenLayer2.size(); i++) {
            AHiddenLayer layer = this.hiddenLayer2.get(i);
            layer.inputs = layer2inputs;
            layer.activationFunction(layer.calculateWeightedSum());
            outputInputs[i] = layer.activationFunction(layer.calculateWeightedSum());
            error = outputInputs[i] - layer2inputs[i];
            layer.weights = layer.calculateNewWeights(error, 0.05);
        }
        for (int i = 0; i < this.outputLayer.size(); i++) {
            AHiddenLayer outputLayer = this.outputLayer.get(i);
            outputLayer.inputs = outputInputs;
            outputLayer.activationFunction(outputLayer.calculateWeightedSum());
            // System.out.println(outputLayer.activationFunction(outputLayer.calculateWeightedSum()));
        }
    }
    /*
        calculating new weights for each layer
    */
    public void adjustWeight() {

    }
}
