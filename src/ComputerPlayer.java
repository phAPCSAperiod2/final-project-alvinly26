import java.util.Random;
import java.util.Scanner;

/**
 * Computer-controlled player. Extends Player and overrides
 * takeTurn() to pick a random unused cell instead of reading input.
 * Demonstrates inheritance — ComputerPlayer IS-A Player.
 */
public class ComputerPlayer extends Player {
    private Random rng;

    public ComputerPlayer(String name) {
        super(name);
        this.rng = new Random();
    }

    /**
     * Overrides takeTurn to fire at a random unused cell
     * instead of prompting the user for input.
     */
    @Override
    public int[] takeTurn(Scanner scanner, Board opponentBoard) {
        while (true) {
            int r = rng.nextInt(Board.SIZE);
            int c = rng.nextInt(Board.SIZE);
            if (opponentBoard.getCell(r, c) != Board.HIT &&
                opponentBoard.getCell(r, c) != Board.MISS) {
                System.out.println(getName() + " fires at " + r + " " + c);
                return new int[]{r, c};
            }
        }
    }
}
