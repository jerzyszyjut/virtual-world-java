package virtual_world;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static public Coordinates fromString(String coordinatesString) {
        String[] coordinatesStringParts = coordinatesString.split(",");
        return new Coordinates(Integer.parseInt(coordinatesStringParts[0]), Integer.parseInt(coordinatesStringParts[1]));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return x + "," + y;
    }
}
