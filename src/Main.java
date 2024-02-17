package src;

import java.util.Scanner;

public class Main {
    public static void printField(char[][] field){
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                System.out.print(field[i][j]);
                if (j != 2){
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != 2){
                System.out.print("-----");
                System.out.println();
            }
        }
    }
    public static void fillTheField(char[][] field, int position, char letter){
        int findPosition = 1;
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                if (position == findPosition){
                    field[i][j] = letter;
                }
                findPosition++;
            }
        }
    }
    public static boolean isFieldFull(char[][] field){
        for (char[] chars : field) {
            for (int j = 0; j < field.length; j++) {
                if (chars[j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    public static int letterPosition(char letter, char[][] field){
        Scanner scanner = new Scanner(System.in);
        int position;
        System.out.println("Player " + letter + " enter (1-9):");

        try {
            position = scanner.nextInt();
            int findPosition = 1;
            if (position < 1 || position > 9 ){
                System.out.println("Invalid move!");
                position = letterPosition(letter, field);
            }
            //check if the position is not taken
            for (char[] chars : field) {
                for (int j = 0; j < field.length; j++) {
                    if (position == findPosition) {
                        if (chars[j] != ' ') {
                            System.out.println("Position taken!");
                            position = letterPosition(letter, field);
                        }
                    }
                    findPosition++;
                }
            }
        }catch (Exception e){
            System.out.println("Invalid input");
            position = letterPosition(letter, field);
        }
        return position;
    }

    public static boolean isWin(char[][] field){
        // Check rows and columns
        for (int i = 0; i < field.length; i++) {
            if ((field[i][0] == field[i][1] && field[i][1] == field[i][2] && field[i][0] != ' ')) {
                System.out.println();
                System.out.println("Player " + field[i][0] + " wins!");
                return false;
            }
            if ((field[0][i] == field[1][i] && field[1][i] == field[2][i] && field[0][i] != ' ')){
                System.out.println();
                System.out.println("Player " + field[0][i] + " wins!");
                return false;
            }
        }

        // Check diagonals
        if ((field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] != ' ')){
            System.out.println();
            System.out.println("Player " + field[0][0] + " wins!");
            return false;
        }
        if ((field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] != ' ')){
            System.out.println();
            System.out.println("Player " + field[1][1] + " wins!");
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        char[][] field =  new char[3][3];
        int position;

        boolean order = true;
        for (int i = 0; i < field.length; i++){
            for (int j = 0; j < field.length; j++){
                field[i][j] = ' ';
            }
        }

        printField(field);

        while (isWin(field)){
            if ( order) {
                position = letterPosition('X', field);
                fillTheField(field, position, 'X');
            }
            else {
                position = letterPosition('O', field);
                fillTheField(field, position, 'O');
            }

            if (isFieldFull(field) && isWin(field)){
                printField(field);
                System.out.println("It's draw!");
                break;
            }

            printField(field);
            order = !order;
        }
    }
}
