package virtual_world.organisms.animals;

import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Sheep extends Animal {
    public Sheep() { super(4, 4,  Species.SHEEP); }
    @Override
    public void draw() {}
    @Override
    public Organism clone() { return new Sheep(); }
}
