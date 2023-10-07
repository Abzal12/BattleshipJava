package battleship;

public enum CellNumsPerShip {
    AIRCRAFT_CARRIER (5),
    BATTLESHIP(4),
    SUBMARINE(3),
    CRUISER(3),
    DESTROYER(2);
    final int cellNums;
    CellNumsPerShip (int cellNums) {

        this.cellNums = cellNums;
    }

}
