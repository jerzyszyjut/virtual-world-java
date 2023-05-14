package virtual_world.organisms.plants;

import virtual_world.CollisionResult;
import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Belladonna extends Plant {
    public Belladonna() {
        super(Config.BELLADONNA_STRENGTH, new Coordinates(0, 0), Species.BELLADONNA);
    }

    public Belladonna(Coordinates coordinates) {
        super(Config.BELLADONNA_STRENGTH, coordinates, Species.BELLADONNA);
    }

    @Override
    public void draw() {
    }

    @Override
    public Organism clone() {
        return new Belladonna();
    }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        secondOrganism.die();
        return super.collision(secondOrganism, isAttacked);
    }
}
