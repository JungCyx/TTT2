
import java.util.Scanner;
import java.util.Hashtable;

public class TicTacToe{
    private final int BOARDSIZE = 3;
    private char[][] board;
    private boolean firstPLayer;
    private boolean gameOver;
    public Scanner  scanner;
    public Hashtable<Boolean, Character> mappedPlayers; // Fixed Hashtable type parameters

    //Enum game Status
    public enum Status{
    WIN, DRAW, CONTINUE
    }


    /*Constructor to start a new board
     * there is no parameter and no returing value because it is just 
     * initializing the value of the empty game
     */
    public TicTacToe(){
        board = new char[BOARDSIZE][BOARDSIZE];//3X3 board
        firstPLayer = true; //player 1 gets X and player 2 gets 0
        gameOver = false; // initializing game
        scanner = new Scanner(System.in); // scanner to take the input from the user 
        mappedPlayers = new Hashtable<>();
        emptyBoard();// method to set all the spots with no inititial value
    }


    /* Method to initialize the board
    * there is no parameters and will return anything
    *It is private because we do not want the player's or anybody to change the starting values
    */
    private void emptyBoard() {
        for (int i = 0; i < BOARDSIZE; i++) { // ROWS
            for (int j = 0; j < BOARDSIZE; j++) { // COLUMNS
                board[i][j] = ' '; // Every square will start empty0
            }
        }
    }

    public void printBoard(){
        System.out.println("--------------");
            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board[i].length; j++){
                    System.out.print(" " + board[i][j] + " ");
                    System.out.print("|");                 
            }
           System.out.println("\n--------------");
            }
    }

    private void play(){
        int playerTrun = 0;
        mappedPlayers.put(true, 'X');
        mappedPlayers.put(false, 'O');
        Status currentStatus = Status.CONTINUE; // 

        while (!gameOver) {
            if (firstPLayer)
            {playerTrun = 1;}
            else
            {playerTrun = 0;}

            int[] current_coordinate = printCurrentPLayer(playerTrun); //take the input from the player 
            printSymbol(current_coordinate[0],  current_coordinate[1], mappedPlayers.get(firstPLayer));
            printBoard();

            currentStatus = gamestatus(); // check for a win, draw after each play
            firstPLayer = !firstPLayer ;// change turns
            
            if (currentStatus == Status.WIN || currentStatus == Status.DRAW){ // check if there is a win or Draw IF so change gameOver to True 
                gameOver = !gameOver;
            }
        }
             
            
           

        

        // the switch case checks who has won the game and display massage accordingly 
        switch (currentStatus) {
            case WIN:
                char winner = mappedPlayers.get(!firstPLayer);
                System.out.println(winner + " You won the game!");
                break;
            case DRAW:
                System.out.println("No player won the game its a DRAW");
                break;
            default:
                System.out.println("Invalid game status.");
                break;

    }
}

    /*Method to print player's turn
     * private because it is used to just diplay info not change anything
     * Parameter is the player and it is an int because its either player 1 or 2
     * the function will take the input from the user and return the input as well
     */
    private int[] printCurrentPLayer(int player){

        int[] coordinates = new int[2];
        int x = -1;
        int y = -1;

        if(player == 1){
            System.out.println("Player X's turn.");

            System.out.println("Player X: Enter row (0, 1 or 2).");
            x = scanner.nextInt();

            System.out.println("Player X: Enter column (0, 1 or 2).");
            y = scanner.nextInt();
        }
        else{
            System.out.println("Player O's turn.");

            System.out.println("Player O: Enter row (0, 1 or 2).");
            x = scanner.nextInt();

            System.out.println("Player O: Enter column (0, 1 or 2).");
            y = scanner.nextInt();
        }

        coordinates[0] = x;
        coordinates[1] = y;

        return coordinates;
    }

    

//place (X or O)
// add some input validation 
private void printSymbol(int row, int column, char value){
    board[row][column] = value;
}

public Status gamestatus() {
//columns and rows
for(int i=0;i < BOARDSIZE;i++){

    if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                    return Status.WIN;
    }
    if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                    return Status.WIN;
        }
    }
 //diagonals
    if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                return Status.WIN;
        }
            if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                return Status.WIN;
        }
    
    // check for DRAW 
    for(int i = 0; i < BOARDSIZE; i++){
        for(int j = 0; j < BOARDSIZE; j++){
            if(board[i][j] == ' '){
                return Status.CONTINUE;
            }
        }
    }
    return Status.DRAW;

        }


    // Main method: entry point of the program
    public static void main(String[] args) {
        // Print a message to the console
        TicTacToe game = new TicTacToe();
        game.play();
    }
    
}


