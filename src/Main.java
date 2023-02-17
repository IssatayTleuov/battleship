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

    public static boolean placeAircraft() {
        printBattlefield();
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        try (InputStreamReader reader1 = new InputStreamReader(System.in);
             BufferedReader reader2 = new BufferedReader(reader1)) {
            String input = reader2.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}