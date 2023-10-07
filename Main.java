package battleship;

import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Write your code here
        int arrSize = 11;
        String[][] gameField = new String[arrSize][arrSize];
        createField(gameField, arrSize);
        printField(gameField);

        Scanner scanner = new Scanner(System.in);
        int counter = 5;
        while (counter >= 1) {
            //System.out.println("\nEnter the coordinates of the Aircraft Carrier (5 cells):\n");
            System.out.println();
            String ship = enterShipType(counter);
            printShipType(ship);
            processField(gameField, scanner, ship);
            printField(gameField);
            counter--;
        }

    }
    public static void createField(String[][] gameField, int arrSize) {

        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {

                if (i == 0 && j != 0) {
                    gameField[0][j] = Integer.toString(j);
                } else if (i != 0 && j == 0) {
                    gameField[i][j] = Character.toString((char) (64 + i));
                } else {
                    gameField[i][j] = "~";
                }
            }
        }

        gameField[0][0] = " ";
    }
    public static void printField(String[][] gameField) {

        System.out.println(Arrays.deepToString(gameField).replace("], ", "\n")
                .replace("[", "").replace("[[", "").replace("]]", "")
                .replace(",", ""));
    }
    public static String enterShipType(int counter) {

        System.out.println();
        switch (counter) {

            case 5 -> {
                return "Aircraft Carrier (5 cells):";
            }
            case 4 -> {
                return "Battleship (4 cells):";
            }
            case 3 -> {
                return "Submarine (3 cells):";
            }
            case 2 -> {
                return "Cruiser (3 cells):";
            }
            case 1 -> {
                return "Destroyer (2 cells):";
            }
            default -> {
            }
        }
        return null;
    }
    public static void printShipType(String ship) {
        System.out.printf("Enter the coordinates of the %s\n\n", ship);
    }
    public static void processField(String[][] gameField, Scanner scanner, String shipType) {

        boolean flag = true;
        while (flag) {

            String[] coords = scanner.nextLine().split(" "); //11 110
            System.out.println();
            String[] coordsXYInitial = coords[0].split("", 2); // 1 1
            String[] coordsXYLast = coords[1].split("", 2); // 1 1 0
            coordsXYInitial[0] = Integer.toString((coordsXYInitial[0].charAt(0)) - 64);
            coordsXYLast[0] = Integer.toString((coordsXYLast[0].charAt(0)) - 64);

            //focus here
            if (isCloseMethod(gameField, coordsXYInitial, coordsXYLast)) {

                System.out.println("Error! You placed it too close to another one. Try again:\n");
            } else if (isDiagonalMethod(coordsXYInitial, coordsXYLast)) {

                System.out.println("Error! Wrong ship location! Try again:\n");
            } else if (!isLengthRightMethod(shipType, coordsXYInitial, coordsXYLast)) {

                System.out.println("Error! Wrong length of the Submarine! Try again:\n");
            } else if (coordsXYInitial[0].equals(coordsXYLast[0])) {

                if (Integer.parseInt(coordsXYInitial[1]) < Integer.parseInt(coordsXYLast[1])) {

                    for (int i = Integer.parseInt(coordsXYInitial[1]); i <= Integer.parseInt(coordsXYLast[1]); i++) {

                        gameField[Integer.parseInt(coordsXYInitial[0])][i] = "O";
                    }
                } else {

                    for (int i = Integer.parseInt(coordsXYLast[1]); i <= Integer.parseInt(coordsXYInitial[1]); i++) {

                        gameField[Integer.parseInt(coordsXYLast[0])][i] = "O";
                    }
                }

                flag = false;
            } else if (coordsXYInitial[1].equals(coordsXYLast[1])) {

                if (Integer.parseInt(coordsXYInitial[0]) < Integer.parseInt(coordsXYLast[0])) {

                    for (int i = Integer.parseInt(coordsXYInitial[0]); i <= Integer.parseInt(coordsXYLast[0]); i++) {

                        gameField[i][Integer.parseInt(coordsXYInitial[1])] = "O";
                    }
                } else {

                    for (int i = Integer.parseInt(coordsXYLast[0]); i <= Integer.parseInt(coordsXYInitial[0]); i++) {

                        gameField[i][Integer.parseInt(coordsXYLast[1])] = "O";
                    }
                }

                flag = false;
            } else {
                //for diagonal
                System.out.println("Error! Wrong ship location! Try again:\n");
            }
        }
    }
    public static boolean isCloseMethod(String[][] gameField, String[] coordsXYInitial, String[] coordsXYLast) {
        boolean isCloseBoolean = false;

        for (int i = Integer.parseInt(coordsXYInitial[1]); i <= Integer.parseInt(coordsXYLast[1]); i++) {

            if (Integer.parseInt(coordsXYInitial[0]) == 10 && i == 10) {

                if (gameField[Integer.parseInt(coordsXYInitial[0])][i - 1].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i - 1].equals("O")) {

                    isCloseBoolean = true;
                }
            } else if (Integer.parseInt(coordsXYInitial[0]) == 10) {

                if (gameField[Integer.parseInt(coordsXYInitial[0])][i - 1].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0])][i + 1].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i - 1].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i + 1].equals("O")) {

                    isCloseBoolean = true;
                }
            } else if (i == 10) {

                if (gameField[Integer.parseInt(coordsXYInitial[0])][i - 1].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) + 1][i].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i - 1].equals("O")
                        || gameField[Integer.parseInt(coordsXYInitial[0]) + 1][i - 1].equals("O")) {

                    isCloseBoolean = true;
                }
            } else if (gameField[Integer.parseInt(coordsXYInitial[0])][i - 1].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0])][i + 1].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0]) + 1][i].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i - 1].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0]) - 1][i + 1].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0]) + 1][i - 1].equals("O")
                    || gameField[Integer.parseInt(coordsXYInitial[0]) + 1][i + 1].equals("O")) {

                isCloseBoolean = true;
            }
        }
        return isCloseBoolean;
    }
    public static boolean isDiagonalMethod(String[] coordsXYInitial, String[] coordsXYLast) {

        return !coordsXYInitial[0].equals(coordsXYLast[0]) && !coordsXYInitial[1].equals(coordsXYLast[1]);
    }
    public static boolean isLengthRightMethod(String shipType, String[] coordsXYInitial, String[] coordsXYLast) {

        int cellNum = 0;
        boolean isLengthRightBoolean = true;
        switch (shipType) {

            case "Aircraft Carrier (5 cells):" -> cellNum = CellNumsPerShip.AIRCRAFT_CARRIER.cellNums;
            case "Battleship (4 cells):" -> cellNum = CellNumsPerShip.BATTLESHIP.cellNums;
            case "Submarine (3 cells):" -> cellNum = CellNumsPerShip.SUBMARINE.cellNums;
            case "Cruiser (3 cells):" -> cellNum = CellNumsPerShip.CRUISER.cellNums;
            case "Destroyer (2 cells):" -> cellNum = CellNumsPerShip.DESTROYER.cellNums;
        }
        if (coordsXYInitial[0].equals(coordsXYLast[0])) {

            if (Math.abs(Integer.parseInt(coordsXYLast[1]) - Integer.parseInt(coordsXYInitial[1])) != cellNum - 1) {

                isLengthRightBoolean = false;
            }
        } else if (coordsXYInitial[1].equals(coordsXYLast[1])) {

            if (Math.abs(Integer.parseInt(coordsXYLast[0]) - Integer.parseInt(coordsXYInitial[0])) != cellNum - 1) {

                isLengthRightBoolean = false;
            }
        }
        return isLengthRightBoolean;
    }
}
