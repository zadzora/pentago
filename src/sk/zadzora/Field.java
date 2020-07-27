package sk.zadzora;

public class Field {
    private char[][] ticTacField = {
            {'#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#'},
            {'#', '#', '#', '#', '#', '#'}
    };

    private char[][] testField = {
            {'a', 'b', 'c', 'd', 'e', 'f'},
            {'a', 'b', 'c', 'd', 'e', 'f'},
            {'a', 'b', 'c', 'd', 'e', 'f'},
            {'a', 'b', 'c', 'd', 'e', 'f'},
            {'a', 'b', 'c', 'd', 'e', 'f'},
            {'a', 'b', 'c', 'd', 'e', 'f'}
    };

    private char[][] backUpField = new char[3][3];

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public Field() {
     //   fillField();
    }

    public void fillField() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                ticTacField[i][j] = '#';
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                backUpField[i][j] = '#';
            }
        }

    }

    public void changeTo3x3(char color, char[][] matrix){
        if(color == 'r'){
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 3 ;j++){
                    backUpField[i][j] = matrix[i][j];
                }
            }
        }else if(color == 'b'){
            for(int i = 0;i < 3;i++){
                for(int j = 3;j < 6 ;j++){
                    backUpField[i][j-3] = matrix[i][j];
                }
            }
        }else if(color == 'g'){
            for(int i = 3;i < 6;i++){
                for(int j = 0;j < 3 ;j++){
                    backUpField[i-3][j] = matrix[i][j];
                }
            }
        }else if(color == 'y'){
            for(int i = 3;i < 6;i++){
                for(int j = 3;j < 6 ;j++){
                    backUpField[i-3][j-3] = matrix[i][j];
                }
            }
        }
    }

    public void changeTo6x6(char color, char[][] matrix){
        if(color == 'r'){
            for(int i = 0;i < 3;i++){
                for(int j = 0;j < 3 ;j++){
                    matrix[i][j] = backUpField[i][j];
                }
            }
        }else if(color == 'b'){
            for(int i = 0;i < 3;i++){
                for(int j = 3;j < 6 ;j++){
                    matrix[i][j] = backUpField[i][j-3];
                }
            }
        }else if(color == 'g'){
            for(int i = 3;i < 6;i++){
                for(int j = 0;j < 3 ;j++){
                    matrix[i][j] =  backUpField[i-3][j];
                }
            }
        }else if(color == 'y'){
            for(int i = 3;i < 6;i++){
                for(int j = 3;j < 6 ;j++){
                    matrix[i][j] = backUpField[i-3][j-3];
                }
            }
        }

    }

    static void rotateMatrix(int N, char mat[][], char side) {

        if(side == 'l') {
            // Consider all squares one by one
            for (int x = 0; x < N / 2; x++) {
                // Consider elements in group
                // of 4 in current square
                for (int y = x; y < N - x - 1; y++) {
                    // Store current cell in
                    // temp variable
                    char temp = mat[x][y];

                    // Move values from right to top
                    mat[x][y] = mat[y][N - 1 - x];

                    // Move values from bottom to right
                    mat[y][N - 1 - x]
                            = mat[N - 1 - x][N - 1 - y];

                    // Move values from left to bottom
                    mat[N - 1 - x][N - 1 - y] = mat[N - 1 - y][x];

                    // Assign temp to left
                    mat[N - 1 - y][x] = temp;
                }
            }
        }else{
            for (int i = 0; i < N / 2; i++)
            {
                for (int j = i; j < N - i - 1; j++)
                {

                    // Swap elements of each cycle
                    // in clockwise direction
                    char temp = mat[i][j];
                    mat[i][j] = mat[N - 1 - j][i];
                    mat[N - 1 - j][i] = mat[N - 1 - i][N - 1 - j];
                    mat[N - 1 - i][N - 1 - j] = mat[j][N - 1 - i];
                    mat[j][N - 1 - i] = temp;
                }
            }
        }
    }


    public void showField() {
        System.out.print("- ");
        for (int i = 0; i < 6; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int i = 0; i < 6; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 6; j++) {
                if (i < 3 && j < 3)
                    System.out.print(ANSI_RED + ticTacField[i][j] + " " + ANSI_RESET);
                else if (i < 3 && j >= 3)
                    System.out.print(ANSI_BLUE + ticTacField[i][j] + " " + ANSI_RESET);
                else if (i >= 3 && j < 3)
                    System.out.print(ANSI_GREEN + ticTacField[i][j] + " " + ANSI_RESET);
                else
                    System.out.print(ANSI_YELLOW + ticTacField[i][j] + " " + ANSI_RESET);
            }
            System.out.println();
        }
    }

    public boolean setMarker(int xField, int yField, char playerMarker) {

        if(xField < 0 || xField > 6 || yField < 0 || yField > 6){
            System.out.println("You cannot put your marker here!");
            return false;
        }

        if (this.ticTacField[yField][xField] == '#') {
            this.ticTacField[yField][xField] = playerMarker;
            return true;
        } else {
            System.out.println("You cannot put your marker here!");
            return false;
        }
    }


    private boolean rowCrossed(char playerMarker) {
        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (ticTacField[i][j] == playerMarker)
                    count++;
            }
            if (count >= 5 && fullRowCheck(i, playerMarker)) {
                //System.out.println("You win!");
                return true;
            }
            count = 0;
        }

        return false;
    }

    private boolean fullRowCheck(int row, char playerMarker) {
        if (ticTacField[row][1] == playerMarker && ticTacField[row][2] == playerMarker && ticTacField[row][3] == playerMarker && ticTacField[row][4] == playerMarker)
            return true;
        return false;
    }

    private boolean colCrossed(char playerMarker) {
        int count = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (ticTacField[j][i] == playerMarker)
                    count++;
            }
            if (count >= 5 && fullColCheck(i, playerMarker)) {
                //System.out.println("You win!");
                return true;
            }
            count = 0;
        }

        return false;
    }

    private boolean fullColCheck(int col, char playerMarker) {
        if (ticTacField[1][col] == playerMarker && ticTacField[2][col] == playerMarker && ticTacField[3][col] == playerMarker && ticTacField[4][col] == playerMarker)
            return true;
        return false;
    }

    private boolean diagonalRightCrossed(char playerMarker, int xField, int yField) {
        int count = 0;
        int j = xField;

        // System.out.println(xField + " " + yField);
        // System.out.println(ticTacField[yField][xField]);

        if (ticTacField[yField][xField] == playerMarker) {
            count++;
            for (int i = yField + 1; i < 6; i++) {
                if (j < 5) {
                    if (ticTacField[i][j + 1] == playerMarker) {
                        count++;
                        j = j + 1;
                    }
                }
            }
            j = xField;

            for (int i = yField - 1; i >= 0; i--) {
                if (j > 0) {
                    if (ticTacField[i][j - 1] == playerMarker) {
                        count++;
                        j = j - 1;
                    }
                }
            }

        }

        if (count >= 5)
            return true;

        return false;
    }

    private boolean diagonalLeftCrossed(char playerMarker, int xField, int yField) {
        int count = 0;
        int j = xField;

        // System.out.println(xField + " " + yField);
        // System.out.println(ticTacField[yField][xField]);

        if (ticTacField[yField][xField] == playerMarker) {
            count++;
            for (int i = yField + 1; i < 6; i++) {
                if (j > 0) {
                    if (ticTacField[i][j - 1] == playerMarker) {
                        count++;
                        j = j - 1;
                    }
                }
            }
            j = xField;

            for (int i = yField - 1; i >= 0; i--) {
                if (j < 5) {
                    if (ticTacField[i][j + 1] == playerMarker) {
                        count++;
                        j = j + 1;
                    }
                }
            }

        }

        if (count >= 5)
            return true;

        return false;
    }


    public boolean checkStatus(char playerMarker, int xField, int yField) {
        if (rowCrossed(playerMarker) || colCrossed(playerMarker) || diagonalRightCrossed(playerMarker, xField, yField) || diagonalLeftCrossed(playerMarker, xField, yField))
            return true;

        return false;
    }

    public boolean checkDraw() {
        int count = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (ticTacField[i][j] == '#')
                    count++;
            }
        }
        if (count == 0) {
            System.out.println("No empty fields - DRAW");
            return true;
        }
        return false;
    }

    public boolean rotateFields(char color, char side) {
        //backUpField = ticTacField;

       /* if(color != 'r' || color != 'b' || color != 'g' || color != 'y') {
            System.out.println("Wrong input");
            return false;
        }
        if(side != 'l' || side != 'r') {
            System.out.println("Wrong input");
            return false;
        }
        */

        changeTo3x3(color,ticTacField);
        rotateMatrix(3,backUpField, side);
        changeTo6x6(color,ticTacField);

        return true;
    }


}
