package virtual_world.organisms.plants;

import virtual_world.*;
import virtual_world.organisms.Organism;
import virtual_world.organisms.animals.Animal;

import java.awt.*;

public class HeracleumSosnowskyi extends Plant {
    public HeracleumSosnowskyi() {
        super(Config.HERACLEUM_SOSNOWSKYI_STRENGTH, new Coordinates(0, 0), Species.HERACLEUM_SOSNOWSKYI, Config.HERACLEUM_SOSNOWSKYI_COLOR);
    }

    public HeracleumSosnowskyi(Coordinates coordinates) {
        super(Config.HERACLEUM_SOSNOWSKYI_STRENGTH, coordinates, Species.HERACLEUM_SOSNOWSKYI, Config.HERACLEUM_SOSNOWSKYI_COLOR);
    }

    @Override
    public Organism clone() {
        return new HeracleumSosnowskyi();
    }

    @Override
    public void action(Direction direction) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i * j) == 0) {
                    Organism organism = world.getOrganism(new Coordinates(this.getCoordinates().getX() + i, this.getCoordinates().getY() + j));
                    if (organism instanceof Animal) {
                        organism.die();
                    }
                }
            }

        }

        super.action(direction);
    }

    @Override
    public CollisionResult collision(Organism secondOrganism, boolean isAttacked) {
        secondOrganism.die();
        return super.collision(secondOrganism, isAttacked);
    }
}
