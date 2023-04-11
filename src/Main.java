import model.Ship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static model.Battlefield.*;
import static util.BattleshipUtil.*;

public class Main {
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try (reader) {
            for (Ship ship : Ship.values()) {
                placeShips(ship.getShipName(), ship.getShipLength());
            }
//            placeShips("Battleship", 4);
            printBattlefield();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void placeShips(String shipName, int  shipSize) throws IOException {
        boolean isShipPlaced = false;
        int mainArray;
        int nestedArray1;
        int nestedArray2;
        printBattlefield();
        System.out.println("Enter the coordinates of the " + shipName + " (" + shipSize + " cells):");
        while (!isShipPlaced) {
            char[] chars = reader.readLine().replace(" ", "").toCharArray();
            ArrayList<Integer> coordinates = generateCoordinates(chars);

            if (Math.abs(coordinates.get(0) - coordinates.get(2)) + 1 == shipSize) {
                mainArray = coordinates.get(1);
                nestedArray1 = (coordinates.get(0) < coordinates.get(2)) ? coordinates.get(0) : coordinates.get(2);
                nestedArray2 = (coordinates.get(0) < coordinates.get(2)) ? coordinates.get(2) : coordinates.get(0);
                if (validateCoordinates(coordinates, shipName, shipSize)
                        && checkNeighbors(nestedArray1, nestedArray2, mainArray, mainArray)) {
                    isShipPlaced = changeBattlefield(nestedArray1, nestedArray2, mainArray, mainArray);
                }
            } else if (Math.abs(coordinates.get(1) - coordinates.get(3)) + 1 == shipSize) {
                mainArray = coordinates.get(0);
                nestedArray1 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(1) : coordinates.get(3);
                nestedArray2 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(3) : coordinates.get(1);
                if (validateCoordinates(coordinates, shipName, shipSize)
                        && checkNeighbors(mainArray, mainArray, nestedArray1, nestedArray2)) {
                    isShipPlaced = changeBattlefield(mainArray, mainArray, nestedArray1, nestedArray2);
                }
            }
        }
    }
}
