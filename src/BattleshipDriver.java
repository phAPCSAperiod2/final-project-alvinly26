/**
 * Driver program. Entry point for the Battleship game.
 */
public class BattleshipDriver {
    public static void main(String[] args) {
        Game game = new Game();
        game.setup();
        game.play();
    }
}
