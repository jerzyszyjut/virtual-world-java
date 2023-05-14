package virtual_world.organisms.animals;

import virtual_world.CollisionResult;
import virtual_world.Config;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Turtle extends Animal{
    public Turtle() { super(Config.TURTLE_STRENGTH, Config.TURTLE_INITIATIVE, Species.TURTLE); }

    @Override
    public void action(Direction direction) {
        if(Math.random() * 100 < Config.TURTLE_MOVE_CHANCE_PERCENT)
        {
            return;
        }
        super.action(direction);
    }
    @Override
    public CollisionResult collision(Organism otherOrganism) {
        if(otherOrganism.getStrength() < Config.TURTLE_REFLECT_STRENGTH)
        {
            return CollisionResult.TIE;
        }
        return super.collision(otherOrganism);
    }

    @Override
    public void draw() {}
    @Override
    public Organism clone() {
        return new Turtle();
    }
}
