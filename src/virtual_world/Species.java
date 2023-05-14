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

    public Species next() {
        Species nextValue = values[(this.ordinal() + 1) % values.length];
        if(nextValue == HUMAN) {
            return nextValue.next();
        }
        return nextValue;
    }
}
