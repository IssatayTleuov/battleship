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
                //TODO Make print text 1 time
                System.out.println();
                printBattlefield(player.getBattlefield());
                promptEnterPress();
            }

            for (Player player: Player.values()) {
                promptEnterPress();
                shootShip(player.getBattlefield(), player.getFogBattlefield(), reader, player);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
