package virtual_world.organisms.plants;

import virtual_world.Config;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Dandelion extends Plant {
    public Dandelion() { super(Config.DANDELION_STRENGTH, Species.DANDELION); }
    @Override
    public void draw() {}
    @Override
    public Organism clone() { return new Dandelion(); }

    @Override
    public void action(Direction direction) {
        for(int i = 0; i < Config.DANDELION_SPREAD_TRIES; i++)
        {
            super.action(direction);
        }
    }
}
