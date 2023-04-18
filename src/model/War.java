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

    public static void shootShip(BufferedReader reader) {
        boolean isShipsSank = false;
        System.out.println("\nThe game starts!");
        printBattlefield(player1FogBattlefield);
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

                if (Objects.equals(player1Battlefield[indexes.get(0)][indexes.get(1)],"O")) {
                    player1Battlefield[indexes.get(0)][indexes.get(1)] = "X";
                    player1FogBattlefield[indexes.get(0)][indexes.get(1)] = "X";
                    printBattlefield(player1FogBattlefield);
                    if (!checkShips()) {
                        isShipsSank = true;
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        break;
                    } else if (checkShip(indexes)) {
                        System.out.println("You sank a ship! Specify a new target:");
                        continue;
                    }
                    System.out.println("You hit a ship. Try again:");
                } else if (Objects.equals(player1Battlefield[indexes.get(0)][indexes.get(1)],"~")) {
                    player1Battlefield[indexes.get(0)][indexes.get(1)] = "M";
                    player1FogBattlefield[indexes.get(0)][indexes.get(1)] = "M";
                    printBattlefield(player1FogBattlefield);
                    System.out.println("You missed. Try again:");
                } else if (Objects.equals(player1Battlefield[indexes.get(0)][indexes.get(1)],"X")) {
                    printBattlefield(player1FogBattlefield);
                    System.out.println("You hit a ship!");
                } else if (Objects.equals(player1Battlefield[indexes.get(0)][indexes.get(1)],"M")) {
                    printBattlefield(player1FogBattlefield);
                    System.out.println("You missed!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
