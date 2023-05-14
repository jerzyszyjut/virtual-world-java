package virtual_world.organisms.plants;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

import java.awt.*;

public class Dandelion extends Plant {
    public Dandelion() {
        super(Config.DANDELION_STRENGTH, new Coordinates(0, 0), Species.DANDELION, Config.DANDELION_COLOR);
    }

    public Dandelion(Coordinates coordinates) {
        super(Config.DANDELION_STRENGTH, coordinates, Species.DANDELION, Config.DANDELION_COLOR);
    }

    @Override
    public Organism clone() {
        return new Dandelion();
    }

    @Override
    public void action(Direction direction) {
        for (int i = 0; i < Config.DANDELION_SPREAD_TRIES; i++) {
            super.action(direction);
        }
    }
}
