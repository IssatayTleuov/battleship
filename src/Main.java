import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[][] battlefield =  {
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
        Map<String, Integer> battlefieldIndex = Map.of(
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

        for (int i = 0; i < battlefield.length - 1; i++) {
            for (int j = 0; j < battlefield[i].length - 1; j++) {
                System.out.print(battlefield[i][j] + " ");
            }
            System.out.println();
        }
    }
}