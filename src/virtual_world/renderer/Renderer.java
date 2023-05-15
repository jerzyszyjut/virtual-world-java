package virtual_world.renderer;

import virtual_world.*;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import javax.swing.*;

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
    protected LogsPanel logsPanel;

    public Renderer(World world) {
        this.world = world;
        JFrame f = new JFrame("Virtual World");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel(world, this);
        panel.setFocusable(true);
        logsPanel = new LogsPanel();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(panel);
        mainPanel.add(logsPanel);
        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
    }

    public void renderLogs() {
        String[] logs = world.getLogs().toArray(new String[0]);
        for (String log : logs) {
            logsPanel.addLog(log);
        }
        logsPanel.repaint();
        logsPanel.renderLogs();
        world.clearLogs();
        logsPanel.clearLogs();
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
