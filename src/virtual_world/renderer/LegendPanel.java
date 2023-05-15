package virtual_world.renderer;

import virtual_world.Config;
import virtual_world.Species;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import javax.swing.*;
import java.awt.*;

public class LegendPanel extends JPanel {
    public LegendPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.removeAll();
        int i = 0;
        for (Species species : Species.values()) {
            Organism organism = null;
            switch (species) {
                case SHEEP -> organism = new Sheep();
                case WOLF -> organism = new Wolf();
                case FOX -> organism = new Fox();
                case TURTLE -> organism = new Turtle();
                case ANTELOPE -> organism = new Antelope();
                case GRASS -> organism = new Grass();
                case DANDELION -> organism = new Dandelion();
                case GUARANA -> organism = new Guarana();
                case BELLADONNA -> organism = new Belladonna();
                case HERACLEUM_SOSNOWSKYI -> organism = new HeracleumSosnowskyi();
                case HUMAN -> organism = new Human();
            }
            if (organism == null) {
                continue;
            }
            Color borderColor = organism instanceof Animal ? Color.BLACK : Color.WHITE;
            Square square = new Square(0, i * Config.LINE_HEIGHT , organism.getColor(), borderColor);
            JLabel label = new JLabel(species.name());
            label.setLocation(Config.FIELD_SIZE + Config.LEGEND_TEXT_PADDING, i * Config.LINE_HEIGHT);
            label.setSize(Config.LEGEND_WIDTH, Config.LINE_HEIGHT);
            this.add(label);
            square.paintSquare(g);
            i++;
        }
        JLabel label = new JLabel("Jerzy Szyjut 193064");
        label.setLocation(Config.LEGEND_TEXT_PADDING, i * Config.LINE_HEIGHT);
        label.setSize(Config.LEGEND_WIDTH, Config.LINE_HEIGHT);
        this.add(label);
    }

    public Dimension getPreferredSize() {
        return new Dimension(Config.LEGEND_WIDTH, Config.LEGEND_HEIGHT);
    }
}
