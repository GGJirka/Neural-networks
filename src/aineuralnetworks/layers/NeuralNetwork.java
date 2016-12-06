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
      backprogpagation - maybe working  
    
      TODO: add list for genetic algortithm, which
            handles all neurons
     */
    
    public LinkedList<AHiddenLayer> inputLayer;
    public LinkedList<AHiddenLayer> hiddenLayer;
    public LinkedList<AHiddenLayer> hiddenLayer2;
    public LinkedList<AHiddenLayer> outputLayer;
    public double[] inputs = new double[3];
    public double[][] hiddenWeights = new double[4][3];
    public double[][] hiddenWeights2 = new double[4][4];
    public double[] outputWeights = new double[4];
    public double[] surfaces = new double[4];
    public double JUMPING_TARGET = 0.5;
    public final double DOWN_TARGET = 0.5;
    public final double LEARNING_RATE = 0.00005;
    public boolean isLearning = false;
    public double deltaSecond = 0;
    public double deltaFirst = 0;
    public double output = 0;

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
            for (int i = 0; i < hiddenWeights[0].length; i++) {
                hiddenWeights[l][i] = Math.random();
            }
            this.hiddenLayer.add(new HiddenLayer(inputs, hiddenWeights[l]));
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < hiddenWeights2.length; j++) {
                hiddenWeights2[i][j] = Math.random();
            }
            this.hiddenLayer2.add(new HiddenLayer(inputs, hiddenWeights2[i]));
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
        double[] layer2inputs = new double[4];
        double[] outputInputs = new double[this.hiddenWeights2.length];
        
        /*
        *   calculating variables for first hidden layer
        */
        
        for (int i = 0; i < this.hiddenLayer.size(); i++){
            AHiddenLayer layer = this.hiddenLayer.get(i);
            layer.inputs = this.inputs;
            layer.activationFunction(layer.calculateWeightedSum());
            layer2inputs[i] = layer.activationFunction(layer.calculateWeightedSum());

            /*for(int j=0;j<layer.weights.length;j++){
                System.out.println("FIRST HIDDEN LAYER "+i+": "+layer.weights[j]);
            }
            System.out.println("FIRST HIDDEN LAYER WEIGHTED SUM "+i+": "+layer.calculateWeightedSum());
            System.out.println("FIRST HIDDEN LAYER ACT FUNC "+i+": "+layer.activationFunction(layer.calculateWeightedSum()));
            */
        }

        /*
        *   calculating variables for second hidden layer
         */
        for (int i = 0; i < this.hiddenLayer2.size(); i++) {
            AHiddenLayer layer = this.hiddenLayer2.get(i);
            layer.inputs = layer2inputs;
            layer.activationFunction(layer.calculateWeightedSum());
            outputInputs[i] = layer.activationFunction(layer.calculateWeightedSum());
            //System.out.println("SECOND HIDDEN LAYER "+i+": "+layer.activationFunction(layer.calculateWeightedSum()));
        }

        /*
        *   calculating variables for output layer
         */
        for (int i = 0; i < this.outputLayer.size(); i++) {
            AHiddenLayer outputLayer = this.outputLayer.get(i);
            outputLayer.inputs = outputInputs;
            output = outputLayer.activationFunction(outputLayer.calculateWeightedSum());
            //System.out.println(output);
//            System.out.println("OUTPUT LAYER "+i+": "
//                    +outputLayer.activationFunction(outputLayer.calculateWeightedSum()));
        }
        if(inputs[1]<=200){
            backPropagation();
        }
    }

    /*
        calculating new weights for each layer
     */
    public void backPropagation() {
        backPropagationOutput(outputLayer);
        backPropagationSecondHidden(hiddenLayer2);
        backPropagationFirstHidden(hiddenLayer);
    }

    /*
    *   @backPropagation - the algorithm for deep learning
    *   calculating errors (expected output - actual output)
    *   adjusting new weights so in next time we get better results
     */
    //COMPLETED
    public void backPropagationOutput(LinkedList<AHiddenLayer> layer) {
        for (int i = 0; i < layer.size(); i++) {
            AHiddenLayer actualLayer = layer.get(i);

            for (int j = 0; j < actualLayer.weights.length; j++) {
                deltaSecond = actualLayer.weights[j] * getErrorNet() * getErrorCalc();
                actualLayer.oldWeights[j] = actualLayer.weights[j];
                actualLayer.weights[j] = actualLayer.weights[j] - LEARNING_RATE * deltaSecond;
            }
        }
    }

    //COMPLETED
    public void backPropagationSecondHidden(LinkedList<AHiddenLayer> layer) {

        for (int i = 0; i < this.hiddenLayer2.size(); i++) {
            AHiddenLayer hiddenLayer = this.hiddenLayer2.get(i);
            surfaces[i] = hiddenLayer.activationFunction(hiddenLayer.calculateWeightedSum());
        }

        // IF AINT WORK TRY TO DELETE + FROM DELTA FIRST 
        for (int i = 0; i < layer.size(); i++) {
            AHiddenLayer hiddenLayer = layer.get(i);
            for (int j = 0; j < hiddenLayer.weights.length; j++) {
                deltaFirst += surfaces[j] * (1 - surfaces[j]) * hiddenLayer.weights[j] * calculateSecondDelta(j);
            }
            for (int j = 0; j < hiddenLayer.weights.length; j++) {
                hiddenLayer.oldWeights[j] = hiddenLayer.weights[j];
                hiddenLayer.weights[j] = hiddenLayer.weights[j] - LEARNING_RATE * deltaFirst;
            }
            //CARE THIS MIGHT BE PROBLEM WHO KNOWS
            deltaFirst = 0;
        }
    }

    public void backPropagationFirstHidden(LinkedList<AHiddenLayer> layer) {
        double[] surfaces2 = new double[4];
        
        for(int i=0;i<this.hiddenLayer.size();i++){
            AHiddenLayer hiddenLayer = this.hiddenLayer.get(i);           
            surfaces2[i] = hiddenLayer.activationFunction(hiddenLayer.calculateWeightedSum());
        }
        for (int i = 0; i < layer.size(); i++) {
            AHiddenLayer hiddenLayer = layer.get(i);          
            for(int j=0;j<hiddenLayer.weights.length;j++){
                double delta = surfaces2[j]*(1-surfaces2[j])*hiddenLayer.inputs[j]*calculateFirstDelta(i);
                hiddenLayer.oldWeights[j] = hiddenLayer.weights[j];
                hiddenLayer.weights[j] = hiddenLayer.weights[j]-LEARNING_RATE*delta;
            }
        }
    }

    public double calculateFirstDelta(int index) {
        AHiddenLayer hiddenLayer = this.hiddenLayer.get(index);
        double delta = 0;
        
        for (int j = 0; j < hiddenLayer.weights.length; j++) {
            delta += surfaces[j] * (1 - surfaces[j]) * hiddenLayer.weights[j] * calculateSecondDelta(j);
        }
        return delta;
    }

    public double calculateSecondDelta(int index) {
        AHiddenLayer layer = this.hiddenLayer2.get(index);
        return layer.oldWeights[index] * getErrorNet() * getErrorCalc();
    }

    public double getErrorNet() {
        return output * (1 - output);
    }

    public double getErrorCalc() {
        return (output - JUMPING_TARGET);
    }

    public double getError() {
        double error = 0.5 * Math.pow((0.7 - output), 2);
        return error;
    }
}
