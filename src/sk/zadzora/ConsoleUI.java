package sk.zadzora;

import java.util.Scanner;

public class ConsoleUI {
    private Field field = new Field();
    private GameState gameState = GameState.PLAYING;
    boolean breakLoop = false;
    private String firstName = "a";
    private String secondName = "b";
    private Scanner scanner;
    private int posX;
    private int posY;

    public void playGame(){
        System.out.println("Welcome to Pentago, please insert names");

        while(firstName.length() < 3) {
            System.out.print("Player One(X): ");
            scanner = new Scanner(System.in);
            firstName = scanner.nextLine();
            if(firstName.length() < 3)
                System.out.println("Please insert atleast 3 symbols");
        }

        while(secondName.length() < 3) {
            System.out.print("Player Two(O): ");
            scanner = new Scanner(System.in);
            secondName = scanner.nextLine();
            if(secondName.length() < 3)
                System.out.println("Please insert atleast 3 symbols");
            if(secondName.equals(firstName)) {
                secondName = "a";
                System.out.println("Same names not alowed");
            }
        }

        while (gameState.equals(GameState.PLAYING)) {
            //  System.out.println("FirstPlayer: " + firstName + " SecondPlayer: " + secondName);
            breakLoop = false;
            while (!breakLoop) {
                System.out.println(firstName + "´s turn...");

                field.showField();

                System.out.print("Position(X field): ");
                scanner = new Scanner(System.in);
                if(scanner.hasNextInt())
                    posY = scanner.nextInt();
                else
                    posY = -1;


                System.out.print("Position(Y field): ");
                scanner = new Scanner(System.in);
                if(scanner.hasNextInt())
                    posX = scanner.nextInt();
                else
                    posX = -1;

                if (field.setMarker(posY, posX, 'X')) {
                    breakLoop = true;
                    field.showField();
                }
            }

                if(field.checkDraw()) {
                    gameState = GameState.DRAW;
                    System.out.println("Draw");
                    return;
                }

                if(field.checkStatus('X',posY, posX)){
                    System.out.println(firstName + " win !");
                    field.showField();
                    gameState = GameState.END;
                    return;
                }

            breakLoop = false;
            while (!breakLoop) {

                System.out.println("Select quadrant (r/b/g/y)");
                scanner = new Scanner(System.in);
                char quadrant = scanner.next().charAt(0);
                System.out.println("Select direction (l/r)");
                scanner = new Scanner(System.in);
                char dir = scanner.next().charAt(0);
                if(field.rotateFields(quadrant, dir)){
                    breakLoop = true;
                }

            }

            if(field.checkDraw()) {
                gameState = GameState.DRAW;
                System.out.println("Draw");
                return;
            }

                if(field.checkStatus('X',posY, posX)){
                    System.out.println(firstName + " win !");
                    field.showField();
                    gameState = GameState.END;
                    return;
                }

            ///////// SECOND PLAYER ///////////

            breakLoop = false;
            while (!breakLoop) {
                System.out.println(secondName + "´s turn...");
                field.showField();

                System.out.print("Position(X field): ");
                scanner = new Scanner(System.in);
                if(scanner.hasNextInt())
                    posY = scanner.nextInt();
                else
                    posY = -1;

                System.out.print("Position(Y field): ");
                scanner = new Scanner(System.in);
                if(scanner.hasNextInt())
                    posX = scanner.nextInt();
                else
                    posX = -1;

                if (field.setMarker(posY, posX, 'O')) {
                    breakLoop = true;
                    field.showField();
                }
            }

                if(field.checkDraw()) {
                    gameState = GameState.DRAW;
                    System.out.println("Draw");
                    return;
                }

                if(field.checkStatus('X',posY, posX)){
                    System.out.println(firstName + " win !");
                    field.showField();
                    gameState = GameState.END;
                    return;
                }

                breakLoop = false;
                while (!breakLoop){
                    System.out.println("Select quadrant (r/b/g/y)");
                    scanner = new Scanner(System.in);
                    char quadrant = scanner.next().charAt(0);
                    System.out.println("Select direction (l/r)");
                    scanner = new Scanner(System.in);
                    char dir = scanner.next().charAt(0);
                    if(field.rotateFields(quadrant, dir)){
                        breakLoop = true;
                    }
                }

                if(field.checkDraw()) {
                    gameState = GameState.DRAW;
                    System.out.println("Draw");
                    return;
                }

                if(field.checkStatus('O',posY, posX)){
                    System.out.println(secondName + " win !");
                    field.showField();
                    gameState = GameState.END;
                    return;
                }

            }

    }

}
