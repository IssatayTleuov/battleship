import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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

    public static void main(String[] args) {
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

    public static void changeBattlefield(int[] coordinates) {
        if (coordinates[0] == coordinates[2]) {
            int mainArray = coordinates[0];
            int nestedArray1 = coordinates[1];
            int nestedArray2 = coordinates[3];
            for (int i = mainArray; i <= mainArray; i++) {
                for (int j = nestedArray1; j <= nestedArray2; j++) {
                    battlefield[i][j] = "O";
                }
            }
        } else if (coordinates[1] == coordinates[3]) {
            int mainArray1 = coordinates[0];
            int mainArray2 = coordinates[2];
            int nestedArray = coordinates[1];
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
            char[] coordinates = reader.readLine().replace(" ", "").toCharArray();
            int[] arrayIndexes = new int[4];
            for (int i = 0; i <= arrayIndexes.length - 1; i++) {
                if (battlefieldIndex.containsKey(String.valueOf(coordinates[i]))) {
                    arrayIndexes[i] = battlefieldIndex.get(String.valueOf(coordinates[i]));
                } else if (i == 3 && coordinates.length == 5) {
                    String srtIndex = new String(new char[]{coordinates[3], coordinates[4]});
                    arrayIndexes[i] = Integer.parseInt(srtIndex);
                } else {
                    arrayIndexes[i] = Character.getNumericValue(coordinates[i]);
                }
            }

            if (arrayIndexes[0] == arrayIndexes[2] || arrayIndexes[1] == arrayIndexes[3]) {
                if (Math.abs(arrayIndexes[0] - arrayIndexes[2]) + 1 == shipSize) {
                    changeBattlefield(arrayIndexes);
                    isShipPlaced = true;
                } else if (Math.abs(arrayIndexes[1] - arrayIndexes[3]) + 1 == shipSize) {
                    changeBattlefield(arrayIndexes);
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
