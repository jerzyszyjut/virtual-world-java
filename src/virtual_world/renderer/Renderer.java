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

    public void init() {

        JFrame f = new JFrame("Proj2");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyPanel panel = new MyPanel(world);
        panel.setFocusable(true);
        f.add(panel);
        f.pack();
        f.setVisible(true);

    }

    public void setWorld(World world) {
        this.world = world;
    }

}

class MyPanel extends JPanel implements ActionListener {

    World world;
    Dimension size;
    Graphics g;
    Square squareTest = new Square();
    protected Species chosenSpecies = null;

    JButton switchSpecies = new JButton("EMPTY");

    private void initMenu() {
        JButton next = new JButton("Next turn");
        next.setBounds(50, 100, 95, 30);
        next.setActionCommand("next");
        next.addActionListener(this);
        next.setFocusable(false);
        this.add(next);

        JButton save = new JButton("Save");
        save.setBounds(50, 100, 95, 30);
        save.setActionCommand("save");
        save.addActionListener(this);
        save.setFocusable(false);
        this.add(save);

        JButton load = new JButton("Load");
        load.setBounds(50, 100, 95, 30);
        load.setActionCommand("load");
        load.addActionListener(this);
        load.setFocusable(false);
        this.add(load);

        switchSpecies.setBounds(50, 100, 95, 30);
        switchSpecies.setActionCommand("switchSpecies");
        switchSpecies.addActionListener(this);
        switchSpecies.setFocusable(false);
        this.add(switchSpecies);

    }

    public void actionPerformed(ActionEvent e) {
        if ("save".equals(e.getActionCommand())) {
            world.saveWorldStateToFile("save.txt");
        } else if ("load".equals(e.getActionCommand())) {
            world.loadWorldStateFromFile("save.txt");
            performRedraw();
        } else if ("next".equals(e.getActionCommand())) {
            world.nextTurn();
            performRedraw();
        } else if ("switchSpecies".equals(e.getActionCommand())) {
            chosenSpecies = chosenSpecies.next();
            switchSpecies.setText(chosenSpecies.name());
        }
    }

    public MyPanel(World world) {
        this.world = world;
        setBorder(BorderFactory.createLineBorder(Color.black));
        initMenu();

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                moveSquare(e.getX(), e.getY());
            }
        });

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                world.nextTurn();

                Organism player = world.getPlayer();
                if(player != null)
                {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            player.action(Direction.UP);
                            break;
                        case KeyEvent.VK_DOWN:
                            player.action(Direction.DOWN);
                            break;
                        case KeyEvent.VK_LEFT:
                            player.action(Direction.LEFT);
                            break;
                        case KeyEvent.VK_RIGHT:
                            player.action(Direction.RIGHT);
                            break;
                    }
                }

                performRedraw();

            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    private void performRedraw() {
        for(Organism organism: world.getOrganismsList())
        {
            Square square = new Square();
            square.setColor(organism.getColor());
            square.setX(organism.getCoordinates().getX() * Config.FIELD_SIZE);
            square.setY(organism.getCoordinates().getY() * Config.FIELD_SIZE);
            repaint();
            renderLog();
        }
    }

    public void renderLog() {
        for (String log : world.getLogs()) {
            System.out.println(log);
        }
        world.clearLogs();
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 200);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g = g;

        for(Organism organism: world.getOrganismsList())
        {
            Square square = new Square();
            square.setColor(organism.getColor());
            square.setX(organism.getCoordinates().getX() * Config.FIELD_SIZE);
            square.setY(organism.getCoordinates().getY() * Config.FIELD_SIZE);
            square.paintSquare(g);
        }
    }

    private void moveSquare(int x, int y) {

        Coordinates pos = new Coordinates(x / Config.FIELD_SIZE, y / Config.FIELD_SIZE);
        if (!world.isInWorld(pos)) {
            return;
        }
        Organism org = null;
        if(chosenSpecies == null)
        {
            return;
        }
        switch (chosenSpecies) {
            case FOX -> org = new Fox(pos);
            case SHEEP -> org = new Sheep(pos);
            case WOLF -> org = new Wolf(pos);
            case TURTLE -> org = new Turtle(pos);
            case ANTELOPE -> org = new Antelope(pos);
            case GRASS -> org = new Grass(pos);
            case DANDELION -> org = new Dandelion(pos);
            case GUARANA -> org = new Guarana(pos);
            case BELLADONNA -> org = new Belladonna(pos);
            case HERACLEUM_SOSNOWSKYI -> org = new HeracleumSosnowskyi(pos);
        }
        if(org == null)
        {
            return;
        }
        world.addOrganism(org);
        performRedraw();
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
