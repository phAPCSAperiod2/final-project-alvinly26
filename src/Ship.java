import java.util.ArrayList;

/**
 * Represents a single ship in the Battleship game.
 * A ship has a name, a size (number of cells it occupies),
 * and a list of coordinates where it has been hit.
 */
public class Ship {
    private String name;
    private int size;
    private ArrayList<int[]> coordinates;
    private ArrayList<int[]> hits; // NEW in Sprint 2

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.coordinates = new ArrayList<>();
        this.hits = new ArrayList<>();
    }

    public void addCoordinate(int row, int col) {
        coordinates.add(new int[] { row, col });
    }

    /** Check if this ship occupies the given cell. */
    public boolean occupies(int row, int col) {
        for (int[] c : coordinates) {
            if (c[0] == row && c[1] == col)
                return true;
        }
        return false;
    }

    /**
     * Register a hit on this ship. Returns true if the cell was part of the ship.
     */
    public boolean registerHit(int row, int col) {
        if (occupies(row, col) && !alreadyHit(row, col)) {
            hits.add(new int[] { row, col });
            return true;
        }
        return false;
    }

    private boolean alreadyHit(int row, int col) {
        for (int[] h : hits) {
            if (h[0] == row && h[1] == col)
                return true;
        }
        return false;
    }

    /** A ship is sunk when every cell it occupies has been hit. */
    public boolean isSunk() {
        return hits.size() >= size;
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
