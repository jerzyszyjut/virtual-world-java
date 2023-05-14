package virtual_world.organisms.animals;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

import java.util.ArrayList;

public class Fox extends Animal{
    public Fox() { super(Config.FOX_STRENGTH, Config.FOX_INITIATIVE, Species.FOX); }
    @Override
    public void draw() {}
    @Override
    public Organism clone() { return new Fox(); }

    @Override
    public void action(Direction direction) {
        Coordinates newPosition = coordinates;

        ArrayList<Direction> possibleMoves = new ArrayList<Direction>();

        for(int i=-1; i<=1; i++)
        {
            for(int j=-1; j<=1; j++)
            {
                if(i * j != 0)
                {
                    continue;
                }
                Organism adjecentOrganism = world.getOrganism(
                        new Coordinates(coordinates.getX() + i, coordinates.getY() + j)
                );
                if(
                        adjecentOrganism == null
                                || (adjecentOrganism.getSpecies() != this.getSpecies()
                                && this.isStrongerThan(adjecentOrganism)))
                {
                    possibleMoves.add(Direction.fromOffset(i, j));
                }
            }
        }

        if(possibleMoves.size() > 0)
        {
            direction = possibleMoves.get((int)(Math.random() * possibleMoves.size()));
        }

        super.action(direction);
    }
}
