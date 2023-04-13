package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static util.BattlefieldUtil.*;

public class Battlefield {
    public static String[][] battlefield = {
            {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };

    public static String[][] fogBattlefield = {
            {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
            {"A", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"B", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"C", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"D", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"E", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"F", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"G", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"H", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"I", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"},
            {"J", "~", "~", "~", "~", "~", "~", "~", "~", "~", "~"}
    };

    public static Map<String, Integer> battlefieldIndex = Map.of(
            "A", 1,
            "B", 2,
            "C", 3,
            "D", 4,
            "E", 5,
            "F", 6,
            "G", 7,
            "H", 8,
            "I", 9,
            "J", 10
    );

    public static void printBattlefield(String[][] battlefield) {
        System.out.println();
        for (int i = 0; i <= battlefield.length - 1; i++) {
            for (int j = 0; j <= battlefield[i].length - 1; j++) {
                System.out.print(battlefield[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean changeBattlefield(int mainArray1, int mainArray2, int nestedArray1, int nestedArray2) {
        for (int i = mainArray1; i <= mainArray2; i++) {
            for (int j = nestedArray1; j <= nestedArray2; j++) {
                battlefield[i][j] = "O";
            }
        }
        return true;
    }

    public static void placeShips(String shipName, int  shipSize, BufferedReader reader) throws IOException {
        boolean isShipPlaced = false;
        boolean isValid;
        boolean isChecked;
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
                isChecked = checkNeighbors(nestedArray1, nestedArray2, mainArray, mainArray);
                if (isChecked) {
                    isShipPlaced = changeBattlefield(nestedArray1, nestedArray2, mainArray, mainArray);
                }
            } else if (Math.abs(coordinates.get(1) - coordinates.get(3)) + 1 == shipSize && isValid) {
                int mainArray = coordinates.get(0);
                int nestedArray1 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(1) : coordinates.get(3);
                int nestedArray2 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(3) : coordinates.get(1);
                isChecked = checkNeighbors(mainArray, mainArray, nestedArray1, nestedArray2);
                if (isChecked) {
                    isShipPlaced = changeBattlefield(mainArray, mainArray, nestedArray1, nestedArray2);
                }
            }
        }
    }
}
