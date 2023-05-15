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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        LogsPanel logsPanel = new LogsPanel(world);

        BoardPanel boardPanel = new BoardPanel(world, logsPanel);
        boardPanel.setFocusable(true);

        LegendPanel legendPanel = new LegendPanel();

        mainPanel.add(boardPanel);
        mainPanel.add(logsPanel);
        mainPanel.add(legendPanel);

        f.add(mainPanel);
        f.pack();
        f.setVisible(true);
    }
}


