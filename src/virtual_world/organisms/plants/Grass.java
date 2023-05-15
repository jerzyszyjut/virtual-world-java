package virtual_world.organisms.plants;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Grass extends Plant {
    public Grass() {
        super(Config.GRASS_STRENGTH, new Coordinates(0, 0), Species.GRASS, Config.GRASS_COLOR);
    }

    public Grass(Coordinates coordinates) {
        super(Config.GRASS_STRENGTH, coordinates, Species.GRASS, Config.GRASS_COLOR);
    }

    @Override
    public Organism clone() {
        return new Grass();
    }
}
