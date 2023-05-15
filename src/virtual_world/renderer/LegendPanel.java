package virtual_world.renderer;

import virtual_world.Species;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.*;
import virtual_world.organisms.plants.*;

import javax.swing.*;
import java.awt.*;
import java.security.KeyPair;

public class LegendPanel extends JPanel {
    public LegendPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        this.removeAll();
        int i = 0;
        for(Species species : Species.values())
        {
            Organism organism = null;
            switch (species)
            {
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
            if(organism == null) {
                continue;
            }
            Color borderColor = organism instanceof Animal ? Color.BLACK : Color.WHITE;
            Square square = new Square(0, i * 20, organism.getColor(), borderColor);
            JLabel label = new JLabel(species.name());
            label.setLocation(25, i * 20);
            label.setSize(100, 20);
            this.add(label);
            square.paintSquare(g);
            i++;
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(120, 300);
    }
}
