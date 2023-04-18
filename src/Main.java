import model.Player;
import model.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static model.Battlefield.*;
import static model.Ship.placeShips;
import static model.War.shootShip;

public class Main {
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try (reader) {
            for (Player player: Player.values()) {
                System.out.printf("%s, place your ships to the game field", player.getName());
                for (Ship ship : Ship.values()) {
                    placeShips(player.getBattlefield(), ship.getShipName(), ship.getShipLength(), reader);
                }
                printBattlefield(player.getBattlefield());
            }
            shootShip(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
