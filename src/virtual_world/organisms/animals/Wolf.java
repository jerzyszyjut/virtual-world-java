package virtual_world.organisms.animals;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Wolf extends Animal {
    public Wolf() {
        super(Config.WOLF_STRENGTH, Config.WOLF_INITIATIVE, new Coordinates(0, 0), Species.WOLF, Config.WOLF_COLOR);
    }

    public Wolf(Coordinates coordinates) {
        super(Config.WOLF_STRENGTH, Config.WOLF_INITIATIVE, coordinates, Species.WOLF, Config.WOLF_COLOR);
    }

    public Organism clone() {
        return new Wolf();
    }
}
