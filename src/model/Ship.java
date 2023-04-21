package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static model.Battlefield.*;
import static util.BattlefieldUtil.*;
import static util.BattlefieldUtil.checkNeighbors;

public enum Ship {
    AIRCRAFT_CARRIER("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    private final String shipName;
    private final int shipLength;

    Ship(String shipName, int shipLength) {
        this.shipName = shipName;
        this.shipLength = shipLength;
    }

    public String getShipName() {
        return shipName;
    }


    public int getShipLength() {
        return shipLength;
    }

    public static void placeShips(String[][] battlefield, String shipName, int  shipSize, BufferedReader reader) throws IOException {
        boolean isShipPlaced = false;
        boolean isValid;
        boolean isChecked;
        System.out.println();
        printBattlefield(battlefield);
        System.out.println("Enter the coordinates of the " + shipName + " (" + shipSize + " cells):");
        while (!isShipPlaced) {
            char[] chars = reader.readLine().replace(" ", "").toCharArray();
            ArrayList<Integer> coordinates = generateCoordinates(chars);
            isValid = validateCoordinates(coordinates, shipName, shipSize);

            if (Math.abs(coordinates.get(0) - coordinates.get(2)) + 1 == shipSize && isValid) {
                int mainArray = coordinates.get(1);
                int nestedArray1 = (coordinates.get(0) < coordinates.get(2)) ? coordinates.get(0) : coordinates.get(2);
                int nestedArray2 = (coordinates.get(0) < coordinates.get(2)) ? coordinates.get(2) : coordinates.get(0);
                isChecked = checkNeighbors(battlefield, nestedArray1, nestedArray2, mainArray, mainArray);
                if (isChecked) {
                    isShipPlaced = changeBattlefield(battlefield, nestedArray1, nestedArray2, mainArray, mainArray);
                }
            } else if (Math.abs(coordinates.get(1) - coordinates.get(3)) + 1 == shipSize && isValid) {
                int mainArray = coordinates.get(0);
                int nestedArray1 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(1) : coordinates.get(3);
                int nestedArray2 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(3) : coordinates.get(1);
                isChecked = checkNeighbors(battlefield, mainArray, mainArray, nestedArray1, nestedArray2);
                if (isChecked) {
                    isShipPlaced = changeBattlefield(battlefield, mainArray, mainArray, nestedArray1, nestedArray2);
                }
            }
        }
    }

    public static boolean checkShips(String[][] battlefield) {
        return Arrays.stream(battlefield)
                .anyMatch(a -> Arrays.stream(a)
                        .anyMatch("O"::equals));
    }

    public static boolean checkShip(String[][] battlefield, ArrayList<Integer> indexes) {
        int firstIndex = indexes.get(0) == 10 ? indexes.get(0) : indexes.get(0) + 1;
        int lastIndex = indexes.get(1) == 10 ? indexes.get(1) : indexes.get(1) + 1;
        boolean isAbove = !battlefield[indexes.get(0) - 1][indexes.get(1)].equals("O");
        boolean isBottom = !battlefield[firstIndex][indexes.get(1)].equals("O");
        boolean isLeft = !battlefield[indexes.get(0)][lastIndex].equals("O");
        boolean isRight = !battlefield[indexes.get(0)][indexes.get(1) - 1].equals("O");
        return isAbove && isBottom && isLeft && isRight;
    }
}
