import model.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static model.Battlefield.*;

public class Main {
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try (reader) {
            for (Ship ship : Ship.values()) {
                placeShips(ship.getShipName(), ship.getShipLength(), reader);
            }
//            placeShips("Battleship", 4);
            printBattlefield();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
