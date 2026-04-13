import java.util.ArrayList;

/**
 * Sprint 1: a ship has a name, a size, and the cells it occupies.
 * No hit tracking yet — that comes in Sprint 2.
 */
public class Ship {
    private String name;
    private int size;
    private ArrayList<int[]> coordinates;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.coordinates = new ArrayList<>();
    }

    public void addCoordinate(int row, int col) {
        coordinates.add(new int[] { row, col });
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<int[]> getCoordinates() {
        return coordinates;
    }
}
