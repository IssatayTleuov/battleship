package model;

import static model.Battlefield.*;

public enum Player {
    PLAYER1("Player 1", player1Battlefield, player1FogBattlefield),
    PLAYER2("Player 2", player2Battlefield, player2FogBattlefield);
    private final String name;
    private String[][] battlefield;
    private String[][] fogBattlefield;

    Player(String name, String[][] battlefield, String[][] fogBattlefield) {
        this.name = name;
        this.battlefield = battlefield;
        this.fogBattlefield = fogBattlefield;
    }
    public String getName() {
        return name;
    }

    public String[][] getBattlefield() {
        return battlefield;
    }

    public String[][] getFogBattlefield() {
        return fogBattlefield;
    }
}
