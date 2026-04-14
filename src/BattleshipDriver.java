import java.util.ArrayList;

/**
 * Sprint 1 driver: build a board, place the standard fleet, print it.
 * Proves the foundation works before adding gameplay.
 */
public class BattleshipDriver {
    public static void main(String[] args) {
        Board board = new Board();

        ArrayList<Ship> fleet = new ArrayList<>();
        fleet.add(new Ship("Carrier", 5));
        fleet.add(new Ship("Battleship", 4));
        fleet.add(new Ship("Cruiser", 3));
        fleet.add(new Ship("Submarine", 3));
        fleet.add(new Ship("Destroyer", 2));

        board.placeFleetRandomly(fleet);

        System.out.println("Sprint 1 — Foundation");
        System.out.println("Fleet placed on board:");
        System.out.println(board.render());
    }
}
