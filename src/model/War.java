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

    public static void shootShip(String[][] battlefield, String[][] fogBattlefield, BufferedReader reader, Player player) {
        boolean isShipsSank = false;
        boolean isShipSank = false;
        printBattlefield(fogBattlefield);
        System.out.println("---------------------");
        printBattlefield(battlefield);
        System.out.printf("%s, it's your turn:\n", player.getName());
        while (!isShipsSank || !isShipSank) {
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
                    if (!checkShips(battlefield)) {
                        isShipsSank = true;
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        break;
                    } else if (checkShip(battlefield, indexes)) {
//                        System.out.println("You sank a ship! Specify a new target:");
                        System.out.println("You sank a ship!");
                        continue;
                    }
                    System.out.println("You hit a ship.");
                } else if (Objects.equals(battlefield[indexes.get(0)][indexes.get(1)],"~")) {
                    battlefield[indexes.get(0)][indexes.get(1)] = "M";
                    fogBattlefield[indexes.get(0)][indexes.get(1)] = "M";
//                    printBattlefield(fogBattlefield);
                    System.out.println("You missed.");
                } else if (Objects.equals(battlefield[indexes.get(0)][indexes.get(1)],"X")) {
//                    printBattlefield(fogBattlefield);
                    isShipSank = true;
                    System.out.println("You hit a ship!");
                } else if (Objects.equals(battlefield[indexes.get(0)][indexes.get(1)],"M")) {
//                    printBattlefield(fogBattlefield);
                    System.out.println("You missed!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
