import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class Main {
    static String[][] battlefield =  {
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
    public static void main(String[] args) {
        placeAircraft();
    }

    public static void printBattlefield() {
        for (int i = 0; i < battlefield.length - 1; i++) {
            for (int j = 0; j < battlefield[i].length - 1; j++) {
                System.out.print(battlefield[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void placeAircraft() {
        boolean isShipPlaced = false;
        while (!isShipPlaced) {
            try (InputStreamReader reader1 = new InputStreamReader(System.in);
                 BufferedReader reader2 = new BufferedReader(reader1)) {
                printBattlefield();
                System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
                char[] coordinates = reader2.readLine().toCharArray();
                int[] arrayIndexes = new int[4];
                for (int i = 0; i < coordinates.length - 1; i++) {
                    if (battlefieldIndex.containsKey(String.valueOf(coordinates[i]))) {
                        arrayIndexes[i] = battlefieldIndex.get(String.valueOf(coordinates[i]));
                    } else {
                        arrayIndexes[i] = coordinates[i];
                    }

                    if (arrayIndexes[0] == arrayIndexes[2] || arrayIndexes[1] == arrayIndexes[3]) {
                        if (arrayIndexes[0] - arrayIndexes[2] + 1 == 5) {

                        } else if (arrayIndexes[1] arrayIndexes[3]) {

                        }
                    } else {
                        System.out.println("Error! Wrong ship location! Try again:");
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}