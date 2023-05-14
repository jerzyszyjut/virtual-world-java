package virtual_world.organisms.plants;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

abstract public class Plant extends Organism {
    public Plant(int strength, Species species) {
        super(strength, Config.PLANT_INITIATIVE, new Coordinates(0, 0), true, null, species);
    }

    @Override
    public void action(Direction direction) {
        if(Math.random() * 100 < Config.PLANT_SPREAD_CHANCE_PERCENT)
        {
            Coordinates newCoordinates = this.findClosestFreeSpace();
            if(newCoordinates != null)
            {
                world.addOrganism(this.clone(newCoordinates));
            }
        }
    }
}
