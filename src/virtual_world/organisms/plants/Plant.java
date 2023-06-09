package virtual_world.organisms.plants;

import virtual_world.*;
import virtual_world.organisms.Organism;

import java.awt.*;

abstract public class Plant extends Organism {
    public Plant(int strength, Coordinates coordinates, Species species, Color color) {
        super(strength, Config.PLANT_INITIATIVE, coordinates, true, null, species, color);
    }

    @Override
    public void action(Direction direction) {
        if (Math.random() * 100 < Config.PLANT_SPREAD_CHANCE_PERCENT) {
            Coordinates newCoordinates = this.findClosestFreeSpace();
            if (newCoordinates != null) {
                world.addOrganism(this.clone(newCoordinates));
            }
        }
    }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        return CollisionResult.DEFEAT;
    }
}
