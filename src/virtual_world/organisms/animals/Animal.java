package virtual_world.organisms.animals;

import virtual_world.CollisionResult;
import virtual_world.Coordinates;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

import java.awt.*;

abstract public class Animal extends Organism {
    public Animal(int strength, int initiative, Coordinates coordinates, Species species, Color color) {
        super(strength, initiative, coordinates, true, null, species, color);
    }

    @Override
    public void action(Direction direction) {
        if (direction == Direction.NONE) {
            direction = Direction.randomDirection();
        }

        Coordinates newPosition = new Coordinates(this.getCoordinates().getX(), this.getCoordinates().getY());

        switch (direction) {
            case UP -> newPosition.setY(newPosition.getY() - 1);
            case DOWN -> newPosition.setY(newPosition.getY() + 1);
            case LEFT -> newPosition.setX(newPosition.getX() - 1);
            case RIGHT -> newPosition.setX(newPosition.getX() + 1);
        }

        if (world.isInWorld(newPosition)) {
            Organism otherOrganism = world.getOrganism(newPosition);
            if (otherOrganism == null) {
                world.moveOrganism(this, newPosition);
            } else {
                if (otherOrganism.getSpecies() == this.getSpecies()) {
                    this.reproduce(otherOrganism);
                } else {
                    CollisionResult collisionResult = this.collision(otherOrganism);
                    if (collisionResult == CollisionResult.VICTORY) {
                        otherOrganism.die();
                        world.moveOrganism(this, newPosition);
                    } else if (collisionResult == CollisionResult.DEFEAT) {
                        this.die();
                    } else if (collisionResult == CollisionResult.ESCAPED) {
                        world.moveOrganism(this, newPosition);
                    }
                }
            }
        }
    }

    protected void reproduce(Organism otherOrganism) {
        Coordinates newCoordinates = findClosestFreeSpace();
        if (newCoordinates != null) {
            world.addOrganism(otherOrganism.clone(newCoordinates));
        }
    }
}
