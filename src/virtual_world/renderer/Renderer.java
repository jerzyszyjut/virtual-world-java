package virtual_world.renderer;

import virtual_world.World;

import javax.swing.*;

public class Renderer {
    protected World world;

    public Renderer(World world) {
        this.world = world;
        JFrame f = new JFrame("Virtual World");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        BoardPanel panel = new BoardPanel(world);
        panel.setFocusable(true);
        LegendPanel legendPanel = new LegendPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.add(panel);
        mainPanel.add(legendPanel);
        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
    }
}


