package virtual_world.organisms;

import virtual_world.*;

abstract public class Organism {
    private int strength, initiative, age;
    private Coordinates coordinates;
    private boolean alive;
    private World world;
    private Species species;

    public Organism(int strength, int initiative, int age, Coordinates coordinates, boolean alive, World world, Species species) {
        this.strength = strength;
        this.initiative = initiative;
        this.age = age;
        this.coordinates = coordinates;
        this.alive = alive;
        this.world = world;
        this.species = species;
    }

    abstract public void action(Direction direction);

    abstract public void draw();

    abstract public Organism clone();

    public CollisionResult collision(Organism secondOrganism) {
        return this.collision(secondOrganism, false);
    }

    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {

        if(this.isStrongerThan(secondOrganism)) {
            if (!isAttacked && secondOrganism.collision(this, true) == CollisionResult.TIE) {
                return CollisionResult.TIE;
            }
            else
            {
                return CollisionResult.VICTORY;
            }
        }
        else {
            return CollisionResult.DEFEAT;
        }
    }

    public Coordinates findClosestFreeSpace(int distance) {
        Coordinates coordinates = this.getCoordinates();
        Coordinates newCoordinates = new Coordinates(coordinates.getX(), coordinates.getY());
        if(distance == 1)
        {
            for(int i=-1; i<=1; i++)
            {
                for(int j=-1; j<=1; j++)
                {
                    if((i * j) != 0)
                    {
                        continue;
                    }
                    newCoordinates.setX(coordinates.getX() + i);
                    newCoordinates.setY(coordinates.getY() + j);
                    if(world.isInWorld(newCoordinates) && world.getOrganism(newCoordinates) == null)
                    {
                        return newCoordinates;
                    }
                }
            }
        }
        else
        {
            for(int i=-distance; i<=distance; i++)
            {
                for(int j=-distance; j<=distance; j++)
                {
                    newCoordinates.setX(coordinates.getX() + i);
                    newCoordinates.setY(coordinates.getY() + j);
                    if(world.isInWorld(newCoordinates) && world.getOrganism(newCoordinates) == null)
                    {
                        return newCoordinates;
                    }
                }
            }
        }
        return null;
    }

    public boolean isStrongerThan(Organism secondOrganism) {
        if(strength == secondOrganism.getStrength())
        {
            return age > secondOrganism.getAge();
        }
        else
        {
            return strength > secondOrganism.getStrength();
        }
    }

    public boolean hasHigherInitiativeThan(Organism secondOrganism) {
        return initiative > secondOrganism.getInitiative();
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


}
