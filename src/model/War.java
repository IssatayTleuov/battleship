package model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;

import static model.Battlefield.*;
import static util.BattlefieldUtil.generateCoordinates;
import static util.WarUtil.checkShips;
import static util.WarUtil.validateShootCoordinates;

public class War {

    public static void shootShip(BufferedReader reader) {
        boolean isShipsSank = false;
        System.out.println("\nThe game starts!");
        printBattlefield(fogBattlefield);
        System.out.println("Take a shot!");
        while (!isShipsSank) {
            try {
                char[] chars = reader.readLine().replace(" ", "").toCharArray();
                ArrayList<Integer> indexes;
                if (validateShootCoordinates(chars)) {
                    indexes = generateCoordinates(chars);
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    continue;
                }

                if (Objects.equals(battlefield[indexes.get(0)][indexes.get(1)],"O")) {
                    battlefield[indexes.get(0)][indexes.get(1)] = "X";
                    fogBattlefield[indexes.get(0)][indexes.get(1)] = "X";
                    printBattlefield(fogBattlefield);
                    if (!checkShips()) {
                        isShipsSank = true;
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        break;
                    }
                    System.out.println("You hit a ship. Try again:");
                } else if (Objects.equals(battlefield[indexes.get(0)][indexes.get(1)],"~")) {
                    battlefield[indexes.get(0)][indexes.get(1)] = "M";
                    fogBattlefield[indexes.get(0)][indexes.get(1)] = "M";
                    printBattlefield(fogBattlefield);
                    System.out.println("You missed. Try again:");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
