/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aineuralnetworks.dinosaur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Adminn
 */
public class Dinosaur extends ADinosaur {

    public Dinosaur(double x, double y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.black);
        g.drawRect((int) x, (int) y, 30, 80);
        g.drawString((int) x + "", (int) x - 10, 50);
        g.drawLine((int) x - 300, 500 + 80, (int) x + 2000, 500 + 80);
    }

    @Override
    public void update() {

        if (jumping) {
            y += vy;
            vy += 0.2;
        }
        if (y > 500) {
            y = 500;
            vy = 0;
            jumping = false;

        }
    }

    @Override
    public void jump() {
        jumping = true;
        vy = -10;
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle((int) x, (int) y, 30, 80);
    }
}
