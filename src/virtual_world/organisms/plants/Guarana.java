package virtual_world.organisms.plants;

import virtual_world.CollisionResult;
import virtual_world.Config;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Guarana extends Plant {
    public Guarana() { super(Config.GUARANA_STRENGTH, Species.GUARANA); }
    @Override
    public void draw() {};
    @Override
    public Organism clone() { return new Guarana(); }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        CollisionResult collisionResult = super.collision(secondOrganism, isAttacked);
        if(collisionResult == CollisionResult.DEFEAT)
        {
            secondOrganism.setStrength(secondOrganism.getStrength() + Config.GUARANA_STRENGTH_BOOST);
        }
        return collisionResult;
    }
}
