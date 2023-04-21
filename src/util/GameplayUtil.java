package util;

import java.util.Scanner;

public class GameplayUtil {
    static final Scanner scanner = new Scanner(System.in);
    public static void promptEnterPress() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }
}
