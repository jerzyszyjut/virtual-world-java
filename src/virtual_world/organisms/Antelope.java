package virtual_world.organisms;

import virtual_world.*;
import virtual_world.organisms.animals.Animal;

public class Antelope extends Animal {
    public Antelope() { super(Config.ANTELOPE_STRENGTH, Config.ANTELOPE_INITIATIVE, Species.ANTELOPE); }

    @Override
    public void draw() {}

    @Override
    public Organism clone() { return new Antelope(); }

    @Override
    public void action(Direction direction) {
        for(int i=0; i<Config.ANTELOPE_MOVE_RANGE; i++)
        {
            super.action(direction);
        }
    }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        Coordinates escapeCoordinates = findClosestFreeSpace();
        if(escapeCoordinates == null)
        {
            return super.collision(secondOrganism, isAttacked);
        }
        if(Math.random() * 100 < Config.ANTELOPE_ESCAPE_CHANCE_PERCENT)
        {
            world.moveOrganism(this, escapeCoordinates);
            return CollisionResult.ESCAPED;
        }
        return super.collision(secondOrganism, isAttacked);
    }
}
