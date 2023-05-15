package virtual_world.organisms.animals;

import virtual_world.*;
import virtual_world.organisms.Organism;

public class Turtle extends Animal {
    public Turtle() {
        super(Config.TURTLE_STRENGTH, Config.TURTLE_INITIATIVE, new Coordinates(0, 0), Species.TURTLE, Config.TURTLE_COLOR);
    }

    public Turtle(Coordinates coordinates) {
        super(Config.TURTLE_STRENGTH, Config.TURTLE_INITIATIVE, coordinates, Species.TURTLE, Config.TURTLE_COLOR);
    }

    @Override
    public void action(Direction direction) {
        if (Math.random() * 100 > Config.TURTLE_MOVE_CHANCE_PERCENT) {
            return;
        }
        this.world.addLog("Turtle moves at " + coordinates);
        super.action(direction);
    }

    @Override
    public CollisionResult collision(Organism otherOrganism) {
        if (otherOrganism.getStrength() < Config.TURTLE_REFLECT_STRENGTH) {
            this.world.addLog("Turtle reflects attack from " + otherOrganism.getSpecies() + " at " + coordinates);
            return CollisionResult.TIE;
        }
        return super.collision(otherOrganism);
    }

    @Override
    public Organism clone() {
        return new Turtle();
    }
}
