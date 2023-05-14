package virtual_world.organisms.animals;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

import java.awt.*;

public class Human extends Animal {
    public Human() {
        super(Config.HUMAN_STRENGTH, Config.HUMAN_INITIATIVE, new Coordinates(0, 0), Species.HUMAN, Config.HUMAN_COLOR);
    }

    public Human(Coordinates coordinates) {
        super(Config.HUMAN_STRENGTH, Config.HUMAN_INITIATIVE, coordinates, Species.HUMAN, Config.HUMAN_COLOR);
    }

    @Override
    public Organism clone() {
        return null;
    }
}
