package virtual_world.renderer;

import virtual_world.*;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class Renderer {
    protected World world;

    public Renderer(World world) {
        this.world = world;
        JFrame f = new JFrame("Virtual World");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel(world);
        panel.setFocusable(true);
        f.add(panel);
        f.pack();
        f.setVisible(true);
    }
}

class Square {

    private int xPos = 50;
    private int yPos = 50;
    private int width = Config.FIELD_SIZE;
    private int height = Config.FIELD_SIZE;
    private Color color = Color.RED;

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return xPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public int getY() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {

    }

    public int getHeight() {
        return height;
    }

    public void paintSquare(Graphics g) {
        g.setColor(this.color);
        g.fillRect(xPos, yPos, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(xPos, yPos, width, height);
    }
}
