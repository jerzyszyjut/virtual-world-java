package virtual_world.organisms.animals;

import virtual_world.*;
import virtual_world.organisms.Organism;

abstract public class Animal extends Organism {
    public Animal(int strength, int initiative, Species species) {
        super(strength, initiative, new Coordinates(0, 0), true, null, species);
    }

    @Override
    public void action(Direction direction)
    {
        if(direction == Direction.NONE)
        {
            direction = Direction.randomDirection();
        }

        Coordinates newPosition = coordinates;

        switch (direction)
        {
            case UP -> newPosition.setY(newPosition.getY() - 1);
            case DOWN -> newPosition.setY(newPosition.getY() + 1);
            case LEFT -> newPosition.setX(newPosition.getX() - 1);
            case RIGHT -> newPosition.setX(newPosition.getX() + 1);
        }

        if(world.isInWorld(newPosition))
        {
            Organism otherOrganism = world.getOrganism(newPosition);
            if(otherOrganism == null)
            {
                world.moveOrganism(this, newPosition);
            }
            else
            {
                if(otherOrganism.getSpecies() == this.getSpecies())
                {
                    this.reproduce(otherOrganism);
                }
                else {
                    CollisionResult collisionResult = this.collision(otherOrganism);
                    if(collisionResult == CollisionResult.VICTORY)
                    {
                        otherOrganism.die();
                        world.moveOrganism(this, newPosition);
                    }
                    else if(collisionResult == CollisionResult.DEFEAT)
                    {
                        this.die();
                    }
                }
            }
        }
    }

    protected void reproduce(Organism otherOrganism)
    {
        Coordinates newCoordinates = findClosestFreeSpace();
        if(newCoordinates != null)
        {
            Organism newOrganism = otherOrganism.clone();
            newOrganism.setCoordinates(newCoordinates);
            world.addOrganism(newOrganism);
        }
    }
}
