package util;

import static model.Battlefield.battlefieldIndex;

public class WarUtil {

    public static boolean validateShootCoordinates(char c) {
        return battlefieldIndex.containsKey(String.valueOf(c));
    }
}
