package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import static model.Battlefield.*;

public class BattlefieldUtil {

    public static ArrayList<Integer> generateCoordinates(char[] chars) {
        int[] ints = new int[chars.length];
        for (int i = 0; i <= chars.length - 1; i++) {
            if (battlefieldIndex.containsKey(String.valueOf(chars[i]))) {
                ints[i] = battlefieldIndex.get(String.valueOf(chars[i]));
            } else if (chars.length == 5 || chars.length == 3 || chars.length == 6) {
                if (chars[i] == '1' && chars[i + 1] == '0') {
                    ints[i] = Integer.parseInt(String
                            .valueOf(new char[]{chars[i], chars[i + 1]}));
                    ++i;
                } else {
                    ints[i] = Character.getNumericValue(chars[i]);
                }
            } else {
                ints[i] = Character.getNumericValue(chars[i]);
            }
        }

        ArrayList<Integer> coordinates = Arrays.stream(ints)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        coordinates.removeIf(v -> (v == 0));
        return coordinates;
    }

    public static boolean validateCoordinates(ArrayList<Integer> coordinates, String shipName, int shipSize) {
        boolean isValid = true;
        if (Objects.equals(coordinates.get(0), coordinates.get(2)) == Objects.equals(coordinates.get(1), coordinates.get(3))) {
            System.out.println("Error! Wrong ship location! Try again:");
            isValid = false;
        } else if (Math.abs((coordinates.get(0) - coordinates.get(2)) - (coordinates.get(1) - coordinates.get(3))) + 1 != shipSize) {
            System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
            isValid = false;
        }
        return isValid;
    }

    public static boolean checkNeighbors(String[][] battlefield, int mainArray1, int mainArray2, int nestedArray1, int nestedArray2) {
        boolean isChecked = true;
        int minMainIndex = mainArray1 - 1;
        int maxMainIndex = ((mainArray2 == 10) ? mainArray2 : mainArray2 + 1);
        int minNestedIndex = nestedArray1 - 1;
        int maxNestedIndex = ((nestedArray2 == 10) ? nestedArray2 : nestedArray2 + 1);
        for (int i = minMainIndex; i <= maxMainIndex; i++) {
            for (int j = minNestedIndex; j <= maxNestedIndex; j++) {
                if (battlefield[i][j].equals("O")) {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    isChecked = false;
                    break;
                }
            }
        }
        return isChecked;
    }
}
