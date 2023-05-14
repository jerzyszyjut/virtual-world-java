package virtual_world.renderer;
import virtual_world.World;
import virtual_world.organisms.;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Renderer {
    private final JFrame frame;
    protected World world;
    ArrayList<JPanel> squares = new ArrayList<>();

    public Renderer(World world) {
        this.world = world;
        this.frame = new JFrame("Virtual World");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public void addSquare(Square square) {
        squares.add(square);
    }

    public void clear() {
        squares.clear();
    }

    public void render() {
        for (Organism[] row : world.getOrganisms()) {
            for (Organism organism : row) {
                if (organism != null) {
                    addSquare(organism.draw());
                }
            }
        }
        for(JPanel square : squares) {
            frame.add(square);
        }
    }
}
