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

    private int xPos = 50;
    private int yPos = 50;
    private final int width = Config.FIELD_SIZE;
    private final int height = Config.FIELD_SIZE;
    private Color color = Color.RED;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
        this.xPos = xPos;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
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
