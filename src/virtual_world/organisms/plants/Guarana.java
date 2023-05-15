package virtual_world.organisms.plants;

import virtual_world.CollisionResult;
import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Guarana extends Plant {
    public Guarana() {
        super(Config.GUARANA_STRENGTH, new Coordinates(0, 0), Species.GUARANA, Config.GUARANA_COLOR);
    }

    public Guarana(Coordinates coordinates) {
        super(Config.GUARANA_STRENGTH, coordinates, Species.GUARANA, Config.GUARANA_COLOR);
    }

    @Override
    public Organism clone() {
        return new Guarana();
    }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        CollisionResult collisionResult = super.collision(secondOrganism, isAttacked);
        if (collisionResult == CollisionResult.DEFEAT) {
            this.world.addLog(this.species + " boosted " + secondOrganism.getSpecies() + " at " + this.coordinates.toString() + "!");
            secondOrganism.setStrength(secondOrganism.getStrength() + Config.GUARANA_STRENGTH_BOOST);
        }
        return collisionResult;
    }
}
