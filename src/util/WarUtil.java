package util;

import java.util.Arrays;

import static model.Battlefield.battlefield;
import static model.Battlefield.battlefieldIndex;

public class WarUtil {

    public static boolean validateShootCoordinates(char[] chars) {
        if (chars.length < 3) {
            return battlefieldIndex.containsKey(String.valueOf(chars[0]))
                    && Integer.parseInt(String.valueOf(chars[1])) <= 9;
        } else if (chars.length == 3) {
            return battlefieldIndex.containsKey(String.valueOf(chars[0]))
                    && Integer.parseInt(String.valueOf(new char[]{chars[1], chars[2]})) == 10;
        }
        return false;
    }
}
