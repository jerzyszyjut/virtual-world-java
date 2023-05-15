package virtual_world.organisms.animals;

import virtual_world.Config;
import virtual_world.Coordinates;
import virtual_world.Direction;
import virtual_world.Species;
import virtual_world.organisms.Organism;

public class Human extends Animal {
    private boolean specialAbilityUsed = false;
    private int specialAbilityCooldown = 0;

    public Human() {
        super(Config.HUMAN_STRENGTH, Config.HUMAN_INITIATIVE, new Coordinates(0, 0), Species.HUMAN, Config.HUMAN_COLOR);
    }

    public Human(Coordinates coordinates) {
        super(Config.HUMAN_STRENGTH, Config.HUMAN_INITIATIVE, coordinates, Species.HUMAN, Config.HUMAN_COLOR);
    }

    @Override
    public void action(Direction direction) {
        performSpecialAbility();
        super.action(direction);
    }

    public void performSpecialAbility() {
        if (specialAbilityUsed) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Coordinates newCoordinates = new Coordinates(coordinates.getX() + i, coordinates.getY() + j);
                    if (world.isInWorld(newCoordinates)) {
                        Organism organism = world.getOrganism(newCoordinates);
                        if (organism != null && organism != this) {
                            organism.die();
                        }
                    }
                }
            }
        }
    }

    @Override
    public Organism clone() {
        return null;
    }

    public boolean isSpecialAbilityUsed() {
        return specialAbilityUsed;
    }

    public int getSpecialAbilityCooldown() {
        return specialAbilityCooldown;
    }

    public void useSpecialAbility() {
        if (specialAbilityCooldown == 0) {
            specialAbilityUsed = true;
            specialAbilityCooldown = Config.HUMAN_SPECIAL_ABILITY_COOLDOWN;
        }
    }

    public void decrementSpecialAbilityCooldown() {
        if (specialAbilityCooldown > 0) {
            specialAbilityCooldown--;
        }
        if (specialAbilityUsed && specialAbilityCooldown == 0) {
            specialAbilityUsed = false;
            specialAbilityCooldown = Config.HUMAN_SPECIAL_ABILITY_COOLDOWN;
        }
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
                coordinates +
                "\t" +
                specialAbilityUsed +
                "\t" +
                specialAbilityCooldown;
    }

    public Organism fromString(String organismString) {
        String[] organismStringParts = organismString.split("\t");
        this.strength = Integer.parseInt(organismStringParts[1]);
        this.initiative = Integer.parseInt(organismStringParts[2]);
        this.age = Integer.parseInt(organismStringParts[3]);
        this.coordinates = Coordinates.fromString(organismStringParts[4]);
        this.specialAbilityUsed = Boolean.parseBoolean(organismStringParts[5]);
        this.specialAbilityCooldown = Integer.parseInt(organismStringParts[6]);
        return this;
    }
}
