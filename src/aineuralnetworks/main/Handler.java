/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.main;

import aineuralnetworks.cactus.ACactus;
import aineuralnetworks.cactus.Cactus;
import aineuralnetworks.dinosaur.ADinosaur;
import aineuralnetworks.layers.NeuralNetwork;
import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Adminn
 */
public class Handler {

    public LinkedList<ADinosaur> dino;
    public LinkedList<ACactus> cactuses;
    public double speed, distance, length;
    public NeuralNetwork network;
    public Main main;
    public int counter = 0;

    public Handler(Main main, NeuralNetwork network) {
        this.main = main;
        this.network = network;
        dino = new LinkedList<>();
        cactuses = new LinkedList<>();
    }

    public void render(Graphics g) throws ConcurrentModificationException {
        for (ADinosaur dinosaur : dino) {
            dinosaur.render(g);
            dinosaur.update();
        }

        for (ACactus cactus : cactuses) {
            cactus.render(g);
        }

        for (ADinosaur dinosaur : dino) {
            for (ACactus cactus : cactuses) {
                if (dinosaur.getRectangle().intersects(cactus.getRectangle())) {
                    dinosaur.x = cactus.x - cactus.width - 15;
                    this.dino.clear();
                    this.cactuses.clear();
                    counter = 0;
                    main.gameStarted();
                }
            }
        }
    }

    public void generateCactus() throws ConcurrentModificationException {
        /*
        TODO: this code sucks alot, should rewrite it in next version
         */
        for (int i = 0; i < this.dino.size(); i++) {
            ADinosaur dinosaur = this.dino.get(i);
            if (counter == 0) {
                this.cactuses.add(new Cactus(dinosaur.x + 1300, 500 + 30, 20, 50));
                counter++;
            }
        }

        if (dino.size() > 0 && cactuses.size() > 0) {
            for (ADinosaur dinosaur : dino) {
                for (int i = 0; i < this.cactuses.size(); i++) {
                    ACactus cactus = this.cactuses.get(i);
                    if (cactus.x < dinosaur.x + 300) {
                        counter = 0;
                    } else {
                        counter = 1;
                    }
                }
            }
        }
    }

    public void update() throws ConcurrentModificationException {
        if (cactuses.size() > 0) {
            for (int i = 0; i < this.cactuses.size(); i++) {
                ACactus cactus = this.cactuses.get(i);
                if (cactus.x > 50) {
                    length = cactus.width;
                    speed = cactus.velX + 2;
                    distance = cactus.x - 50;
                    this.network.setInputs(speed, distance, length);
                    network.calculateHiddenLayer();
                }
            }
        }

        Random random = new Random();
        Random rndCactus = new Random();

        if (rndCactus.nextInt(100) == 47) {
            this.generateCactus();
        }
        if (random.nextInt(200000) == 1455) {
            for (int i = 0; i < this.dino.size(); i++) {
                ADinosaur dinosaur = this.dino.get(i);
                if (dinosaur.jumping == false && dinosaur.y >= 500) {
                    dinosaur.jump();
                }
            }
        }
    }
}
