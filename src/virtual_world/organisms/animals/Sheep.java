package virtual_world.organisms.animals;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

import java.awt.*;

public class Sheep extends Animal {
    public Sheep() {
        super(Config.SHEEP_STRENGTH, Config.SHEEP_INITIATIVE, new Coordinates(0, 0), Species.SHEEP, Config.SHEEP_COLOR);
    }

    public Sheep(Coordinates coordinates) {
        super(Config.SHEEP_STRENGTH, Config.SHEEP_INITIATIVE, coordinates, Species.SHEEP, Config.SHEEP_COLOR);
    }

    @Override
    public Organism clone() {
        return new Sheep();
    }
}
