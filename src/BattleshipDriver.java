import java.util.Scanner;

/**
 * Sprint 2 driver: create a Player with a fleet, let the user fire
 * a few shots at it and see hit/miss feedback. No win loop yet.
 */
public class BattleshipDriver {
    public static void main(String[] args) {
        Player target = new Player("Target");
        target.getBoard().placeFleetRandomly(target.buildFleet());

        Scanner scanner = new Scanner(System.in);
        Player shooter = new Player("You");

        System.out.println("Sprint 2 — Players and Shooting");
        System.out.println("Fire 5 shots at the target board.\n");

        for (int i = 0; i < 5; i++) {
            System.out.println(target.getBoard().render());
            int[] shot = shooter.takeTurn(scanner, target.getBoard());
            String result = target.getBoard().receiveShot(shot[0], shot[1]);
            System.out.println("-> " + result.toUpperCase() + "\n");
        }
        System.out.println("Final board:");
        System.out.println(target.getBoard().render());
    }
}
