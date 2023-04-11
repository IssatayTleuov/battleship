package model;

import java.util.Map;

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

    public static void printBattlefield() {
        System.out.println();
        for (int i = 0; i <= Battlefield.battlefield.length - 1; i++) {
            for (int j = 0; j <= Battlefield.battlefield[i].length - 1; j++) {
                System.out.print(Battlefield.battlefield[i][j] + " ");
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
}
