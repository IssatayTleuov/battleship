import model.Player;
import model.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static model.Battlefield.*;
import static model.Ship.placeShips;
import static model.War.shootShip;
import static util.GameplayUtil.promptEnterPress;

public class Main {
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try (reader) {
            for (Player player: Player.values()) {
                System.out.printf("%s, place your ships to the game field\n", player.getName());
                for (Ship ship : Ship.values()) {
                    placeShips(player.getBattlefield(), ship.getShipName(), ship.getShipLength(), reader);
                }

                if (player.equals(Player.PLAYER1)) {
                    System.out.println();
                    printBattlefield(player.getBattlefield());
                    promptEnterPress();
                }
                System.out.println();
                printBattlefield(player.getBattlefield());
            }

            boolean isShipsSank = false;
            while (!isShipsSank) {
                for (Player player: Player.values()) {
                    if (player.equals(Player.PLAYER1)) {
                        promptEnterPress();
                        isShipsSank = shootShip(player.getBattlefield(), Player.PLAYER2.getBattlefield(), Player.PLAYER2.getFogBattlefield(), reader, player);
                    } else if (player.equals(Player.PLAYER2)) {
                        promptEnterPress();
                        isShipsSank = shootShip(player.getBattlefield(), Player.PLAYER1.getBattlefield(), Player.PLAYER1.getFogBattlefield(), reader, player);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
