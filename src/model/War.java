package model;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Objects;

import static model.Battlefield.*;
import static util.BattlefieldUtil.generateCoordinates;
import static util.WarUtil.validateShootCoordinates;

public class War {

    public static void shootShip(BufferedReader reader) {
        boolean isShotTaken = false;
        System.out.println("The game starts!");
        printBattlefield();
        System.out.println("Take a shot!");
        while (!isShotTaken) {
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
                    isShotTaken = true;
                    printBattlefield();
                    System.out.println("You hit a ship!");
                } else if (Objects.equals(battlefield[indexes.get(0)][indexes.get(1)],"~")) {
                    battlefield[indexes.get(0)][indexes.get(1)] = "M";
                    isShotTaken = true;
                    printBattlefield();
                    System.out.println("You missed!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
