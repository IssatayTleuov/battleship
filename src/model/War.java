package model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;

import static model.Battlefield.*;
import static model.Ship.checkShip;
import static model.Ship.checkShips;
import static util.BattlefieldUtil.generateCoordinates;
import static util.WarUtil.validateShootCoordinates;

public class War {

    public static boolean shootShip(String[][] mainBattlefield, String[][] minorBattlefield, String[][] fogBattlefield, BufferedReader reader, Player player) {
        boolean isShipsSank = false;
        boolean isShootTaken = false;
        printBattlefield(fogBattlefield);
        System.out.println("---------------------");
        printBattlefield(mainBattlefield);
        System.out.printf("%s, it's your turn:\n", player.getName());
        while (!isShootTaken) {
            try {
                char[] chars = reader.readLine().replace(" ", "").toCharArray();
                ArrayList<Integer> indexes;
                if (validateShootCoordinates(chars)) {
                    indexes = generateCoordinates(chars);
                } else {
                    System.out.println("Error! You entered the wrong coordinates! Try again:");
                    continue;
                }

                if (Objects.equals(minorBattlefield[indexes.get(0)][indexes.get(1)],"O")) {
                    minorBattlefield[indexes.get(0)][indexes.get(1)] = "X";
                    fogBattlefield[indexes.get(0)][indexes.get(1)] = "X";
                    printBattlefield(fogBattlefield);
                    if (!checkShips(minorBattlefield)) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        isShipsSank = true;
                        break;
                    } else if (checkShip(minorBattlefield, indexes)) {
                        System.out.println("You sank a ship!");
                        isShootTaken = true;
                        continue;
                    }
                    System.out.println("You hit a ship.");
                    isShootTaken = true;
                } else if (Objects.equals(minorBattlefield[indexes.get(0)][indexes.get(1)],"~")) {
                    minorBattlefield[indexes.get(0)][indexes.get(1)] = "M";
                    fogBattlefield[indexes.get(0)][indexes.get(1)] = "M";
                    System.out.println("You missed!");
                    isShootTaken = true;
                } else if (Objects.equals(minorBattlefield[indexes.get(0)][indexes.get(1)],"X")) {
                    isShootTaken = true;
                    System.out.println("You hit a ship!");
                } else if (Objects.equals(minorBattlefield[indexes.get(0)][indexes.get(1)],"M")) {
                    System.out.println("You missed!");
                    isShootTaken = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return isShipsSank;
    }
}
