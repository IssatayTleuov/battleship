import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static String[][] battlefield = {
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
    static Map<String, Integer> battlefieldIndex = Map.of(
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
    static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, Integer> shipList = new LinkedHashMap<>();
        shipList.put("Aircraft Carrier", 5);
        shipList.put("Battleship", 4);
        shipList.put("Submarine", 3);
        shipList.put("Cruiser", 3);
        shipList.put("Destroyer", 2);

        try (reader) {
            for (Map.Entry<String, Integer> entry : shipList.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                placeShips(key, value);
            }
//            placeShips("Submarine", 3);
            printBattlefield();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printBattlefield() {
        System.out.println();
        for (int i = 0; i <= battlefield.length - 1; i++) {
            for (int j = 0; j <= battlefield[i].length - 1; j++) {
                System.out.print(battlefield[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void changeBattlefield(ArrayList<Integer> coordinates) {
        if (Objects.equals(coordinates.get(0), coordinates.get(2))) {
            int mainArray = coordinates.get(0);
            int nestedArray1 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(1) : coordinates.get(3);
            int nestedArray2 = (coordinates.get(1) < coordinates.get(3)) ? coordinates.get(3) : coordinates.get(1);
            for (int i = mainArray; i <= mainArray; i++) {
                for (int j = nestedArray1; j <= nestedArray2; j++) {
                    battlefield[i][j] = "O";
                }
            }
        } else if (Objects.equals(coordinates.get(1), coordinates.get(3))) {
            int mainArray1 = (coordinates.get(0) < coordinates.get(2)) ? coordinates.get(0) : coordinates.get(2);
            int mainArray2 = (coordinates.get(0) < coordinates.get(2)) ? coordinates.get(2) : coordinates.get(0);
            int nestedArray = coordinates.get(1);
            for (int i = mainArray1; i <= mainArray2; i++) {
                for (int j = nestedArray; j <= nestedArray; j++) {
                    battlefield[i][j] = "O";
                }
            }
        }
    }

    public static void placeShips(String shipName, int shipSize) throws IOException {
        boolean isShipPlaced = false;
        printBattlefield();
        System.out.println("Enter the coordinates of the " + shipName + " (" + shipSize + " cells):");
        while (!isShipPlaced) {
            char[] chars = reader.readLine().replace(" ", "").toCharArray();
            int[] ints = new int[5];
            for (int i = 0; i <= chars.length - 1; i++) {
                if (battlefieldIndex.containsKey(String.valueOf(chars[i]))) {
                    ints[i] = battlefieldIndex.get(String.valueOf(chars[i]));
                } else if (chars.length == 5) {
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

            if (Objects.equals(coordinates.get(0), coordinates.get(2)) || Objects.equals(coordinates.get(1), coordinates.get(3))) {
                if (Math.abs(coordinates.get(0) - coordinates.get(2)) + 1 == shipSize) {
                    changeBattlefield(coordinates);
                    isShipPlaced = true;
                } else if (Math.abs(coordinates.get(1) - coordinates.get(3)) + 1 == shipSize) {
                    changeBattlefield(coordinates);
                    isShipPlaced = true;
                } else {
                    System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
                }
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
            }
        }
    }
}
