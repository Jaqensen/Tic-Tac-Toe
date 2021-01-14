package tictactoe;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] arrayMain = new String[3][3];

        // filling the main array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arrayMain[i][j] = " ";
            }
        }

        // print the grid
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arrayMain[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        int move = 0;
        String xOrOwins = "";

        do {
            boolean correctInput = false;
            boolean xoRowCol = false;
            boolean xoDiagonal = false;
            boolean impossible = false;
            boolean xWins = false;
            boolean oWins = false;
            boolean emptyCells = false;

            int numberOfX = 0;
            int numberOfO = 0;
            int userInputFirst = 1;
            int userInputSecond = 1;

            System.out.print("Enter the coordinates: ");

            do {
                String input = scanner.nextLine();
                String trimInput = input.trim().replaceAll(" ", "");

                char[] arrayInput = trimInput.toCharArray();

                userInputFirst = Character.getNumericValue(trimInput.charAt(0));
                userInputSecond = Character.getNumericValue(trimInput.charAt(1));

                // numbers checker
                for (int i = 0; i < arrayInput.length; i++) {
                    if (!Character.isDigit(arrayInput[i])) {
                        System.out.println("You should enter numbers!");
                        correctInput = false;
                        break;
                    } else if (userInputFirst < 1 || userInputFirst > 3 ||
                            userInputSecond < 1 || userInputSecond > 3) { // correct index checker
                        System.out.println("Coordinates should be from 1 to 3!");
                        break;
                    } else if (!" ".equals(arrayMain[userInputFirst - 1][userInputSecond - 1])) {
                        System.out.println("This cell is occupied! Choose another one!"); // checking if the cell is busy or not
                        break;
                    } else {
                        correctInput = true;
                    }
                }

                if (correctInput) {
                    userInputFirst--;
                    userInputSecond--;
                    switch (move) {
                        case 0:
                            move += 1;
                            arrayMain[userInputFirst][userInputSecond] = "X";
                            break;
                        case 1:
                            move -= 1;
                            arrayMain[userInputFirst][userInputSecond] = "O";
                            break;
                    }
                }
            } while (!correctInput);

            // counter of X and O; xoRowCol winner checker
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ("X".equals(arrayMain[i][j])) {
                        numberOfX += 1;
                    } else if ("O".equals(arrayMain[i][j])) {
                        numberOfO += 1;
                    } else {
                        emptyCells = true;
                    }

                    if ((arrayMain[i][j].equals(arrayMain[(i - 1 + 3) % 3][j])) &&
                            (arrayMain[i][j].equals(arrayMain[(i + 1) % 3][j])) ||
                            (arrayMain[i][j].equals(arrayMain[i][(j - 1 + 3) % 3]) &&
                                    (arrayMain[i][j].equals(arrayMain[i][(j + 1) % 3])))) {
                        xoRowCol = true;
                        xOrOwins = arrayMain[i][j];

                        if ("X".equals(xOrOwins)) {
                            xWins = true;
                        } else if ("O".equals(xOrOwins)) {
                            oWins = true;
                        }
                    }
                }
            }

            // xoDiagonal winner checker
            if (arrayMain[0][0].equals(arrayMain[1][1]) &&
                    arrayMain[0][0].equals(arrayMain[2][2]) ||
                    arrayMain[0][2].equals(arrayMain[1][1]) &&
                            arrayMain[0][2].equals(arrayMain[2][0])) {
                xoDiagonal = true;
                xOrOwins = arrayMain[1][1];

                if ("X".equals(xOrOwins)) {
                    xWins = true;
                } else {
                    oWins = true;
                }
            }

            // impossible checker
            if (Math.abs(numberOfO - numberOfX) > 1 || xWins && oWins) {
                impossible = true;
            }

            // printing grid after a users move
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");
                for (int j = 0; j < 3; j++) {
                    System.out.print(arrayMain[i][j] + " ");
                }
                System.out.println("|");
            }
            System.out.println("---------");

            // print result
            if (impossible) {
                System.out.println("Impossible");
                break;
            } else if (!xoRowCol && !xoDiagonal && !emptyCells) {
                System.out.println("Draw");
                break;
            } else if ("X".equals(xOrOwins)) {
                System.out.println(("X wins"));
                break;
            } else if ("O".equals(xOrOwins)) {
                System.out.println("O wins");
                break;
            }
        } while (true);
    }
}