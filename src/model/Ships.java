package model;

public enum Ships {
    AIRCRAFT("Aircraft Carrier", 5),
    BATTLESHIP("Battleship", 4),
    SUBMARINE("Submarine", 3),
    CRUISER("Cruiser", 3),
    DESTROYER("Destroyer", 2);

    public final String shipName;
    public final int shipLength;

    Ships(String shipName, int shipLength) {
        this.shipName = shipName;
        this.shipLength = shipLength;
    }
}
