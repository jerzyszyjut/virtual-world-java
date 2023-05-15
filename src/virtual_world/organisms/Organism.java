package virtual_world.organisms;

import virtual_world.*;

import java.awt.*;

abstract public class Organism {
    protected int strength, initiative, age;
    protected Coordinates coordinates;
    protected boolean alive;
    protected World world;
    protected Species species;
    protected Color color;

    public Organism(int strength, int initiative, Coordinates coordinates, boolean alive, World world, Species species, Color color) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = 0;
        this.coordinates = coordinates;
        this.alive = alive;
        this.world = world;
        this.species = species;
        this.color = color;
    }

    public void action() {
        this.action(Direction.NONE);
    }

    abstract public void action(Direction direction);

    public abstract Organism clone();

    public Organism clone(Coordinates coordinates) {
        Organism newOrganism = this.clone();
        newOrganism.setCoordinates(coordinates);
        world.addLog("New " + newOrganism.getSpecies() + " was born at " + coordinates.toString());
        return newOrganism;
    }

    public CollisionResult collision(Organism secondOrganism) {
        return this.collision(secondOrganism, false);
    }

    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        if (!isAttacked) {
            CollisionResult collisionResult = secondOrganism.collision(this, true);
            if (isStrongerThan(secondOrganism) && (collisionResult == CollisionResult.TIE || collisionResult == collisionResult.ESCAPED)) {
                this.world.addLog(this.getSpecies() + " tied a fight with " + secondOrganism.getSpecies() + " at " + this.getCoordinates().toString());
                return collisionResult;
            }
        }
        if (this.isStrongerThan(secondOrganism, isAttacked)) {
            if(!isAttacked)
            {
                this.world.addLog(this.getSpecies() + " defeated " + secondOrganism.getSpecies() + " at " + this.getCoordinates().toString());
            }
            return CollisionResult.VICTORY;
        } else {
            if(!isAttacked)
            {
                this.world.addLog(this.getSpecies() + " was defeated by " + secondOrganism.getSpecies() + " at " + this.getCoordinates().toString());
            }
            return CollisionResult.DEFEAT;
        }
    }

    protected Coordinates findClosestFreeSpace() {
        return this.findClosestFreeSpace(1);
    }

    protected Coordinates findClosestFreeSpace(int distance) {
        Coordinates coordinates = this.getCoordinates();

        if (distance == 1) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if ((i * j) != 0) {
                        continue;
                    }
                    if (world.isInWorld(coordinates.getX() + i, coordinates.getY() + j)) {
                        if (world.getOrganism(coordinates.getX() + i, coordinates.getY() + j) == null) {
                            return new Coordinates(coordinates.getX() + i, coordinates.getY() + j);
                        }
                    }
                }
            }
        } else {
            for (int i = -distance; i <= distance; i++) {
                for (int j = -distance; j <= distance; j++) {
                    if (world.isInWorld(coordinates.getX() + i, coordinates.getY() + j)) {
                        if (world.getOrganism(coordinates.getX() + i, coordinates.getY() + j) == null) {
                            return new Coordinates(coordinates.getX() + i, coordinates.getY() + j);
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean isStrongerThan(Organism secondOrganism, boolean isAttacked) {
        if (isAttacked) {
            return isStrongerThan(secondOrganism);
        } else {
            return strength >= secondOrganism.getStrength();
        }
    }

    public boolean isStrongerThan(Organism secondOrganism) {
        return strength > secondOrganism.getStrength();
    }

    public boolean hasHigherInitiativeThan(Organism secondOrganism) {
        if (initiative == secondOrganism.getInitiative()) {
            return age > secondOrganism.getAge();
        }
        return initiative > secondOrganism.getInitiative();
    }

    public String toString() {
        return species +
                "\t" +
                strength +
                "\t" +
                initiative +
                "\t" +
                age +
                "\t" +
                coordinates;
    }

    public Organism fromString(String organismString) {
        String[] organismStringParts = organismString.split("\t");
        this.strength = Integer.parseInt(organismStringParts[1]);
        this.initiative = Integer.parseInt(organismStringParts[2]);
        this.age = Integer.parseInt(organismStringParts[3]);
        this.coordinates = Coordinates.fromString(organismStringParts[4]);
        return this;
    }

    public void die() {
        alive = false;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int incrementAge() {
        return ++age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isAlive() {
        return alive;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

