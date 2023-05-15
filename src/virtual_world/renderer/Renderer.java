package virtual_world.renderer;

import virtual_world.Config;
import virtual_world.World;

import javax.swing.*;
import java.awt.*;

public class Renderer {
    protected World world;

    public Renderer(World world) {
        this.world = world;
        JFrame f = new JFrame("Virtual World");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel(world);
        panel.setFocusable(true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(panel);
        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
    }
}

class Square {

    private int x, y, width = Config.FIELD_SIZE, height = Config.FIELD_SIZE;
    private Color color, borderColor;

    public Square(int x, int y, Color color, Color borderColor) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.borderColor = borderColor;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void setX(int xPos) {
        this.x = xPos;
    }


    public void setY(int yPos) {
        this.y = yPos;
    }


    public void paintSquare(Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(this.borderColor);
        graphics.drawRect(x, y, width, height);
    }
}
