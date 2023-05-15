package virtual_world;

public enum Species {
    WOLF,
    SHEEP,
    FOX,
    TURTLE,
    ANTELOPE,
    GRASS,
    DANDELION,
    GUARANA,
    BELLADONNA,
    HERACLEUM_SOSNOWSKYI,
    HUMAN;

    private static final Species[] values = values();

    public static Species[] allWithoutHuman () {
        Species[] species = new Species[values.length - 1];
        for(int i = 0; i < values.length - 1; i++) {
            species[i] = values[i];
        }
        return species;
    }
}
