package virtual_world.organisms;

import java.util.Comparator;

public class OrganismComparator implements Comparator<Organism> {
    @Override
    public int compare(Organism firstOrganism, Organism secondOrganism) {
        if (firstOrganism.hasHigherInitiativeThan(secondOrganism)) {
            return -1;
        } else if (secondOrganism.hasHigherInitiativeThan(firstOrganism)) {
            return 1;
        } else {
            return 0;
        }
    }
}
