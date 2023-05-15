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

    public static Species[] allWithoutHuman() {
        Species[] species = new Species[values.length - 1];
        System.arraycopy(values, 0, species, 0, values.length - 1);
        return species;
    }
}
