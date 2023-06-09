package virtual_world.organisms.plants;

import virtual_world.CollisionResult;
import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Belladonna extends Plant {
    public Belladonna() {
        super(Config.BELLADONNA_STRENGTH, new Coordinates(0, 0), Species.BELLADONNA, Config.BELLADONNA_COLOR);
    }

    public Belladonna(Coordinates coordinates) {
        super(Config.BELLADONNA_STRENGTH, coordinates, Species.BELLADONNA, Config.BELLADONNA_COLOR);
    }

    public Organism clone() {
        return new Belladonna();
    }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        secondOrganism.die();
        this.die();
        this.world.addLog(this.species + " killed " + secondOrganism.getSpecies() + " at " + this.coordinates.toString() + "!");
        return super.collision(secondOrganism, isAttacked);
    }
}
