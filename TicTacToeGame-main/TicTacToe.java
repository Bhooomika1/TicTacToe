package TicTacToe;
import java.util.Scanner;

public class TicTacToe {
    private player player1, player2;
    private Board board;
    static Scanner sc = new Scanner(System.in);

    private int player1Score = 0;
    private int player2Score = 0;

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        t.run();
    }

    public void run() {
        System.out.println("Welcome to TicTacToe!");
        System.out.println("Players enter coordinates between 0 and 2 for rows and columns.");
        System.out.println("Let’s begin!");

        player1 = takePlayerInput(1);
        player2 = takePlayerInput(2);

        while (player1.getSymbol() == player2.getSymbol()) {
            System.out.println("Symbol already Taken !! Pick another symbol !!");
            char symbol = sc.next().charAt(0);
            player2.setSymbol(symbol);
        }

        boolean playAgain = true;
        while (playAgain) {
            board = new Board(player1.getSymbol(), player2.getSymbol());
            int player1Moves = 0;
            int player2Moves = 0;

            boolean player1Turn = true;
            int status = Board.INCOMPLETE;

            while (status == Board.INCOMPLETE || status == Board.INVALID) {
                if (player1Turn) {
                    System.out.println("Player 1 - " + player1.getName() + "'s turn");
                    int x = getValidCoordinate("Enter row (0-2): ");
                    int y = getValidCoordinate("Enter column (0-2): ");
                    status = board.move(player1.getSymbol(), x, y);
                    if (status != Board.INVALID) {
                        player1Moves++;
                        player1Turn = false;
                        board.print();
                    } else {
                        System.out.println("Invalid Move !! Try Again !! ");
                    }
                } else {
                    System.out.println("Player 2 - " + player2.getName() + "'s turn");
                    int x = getValidCoordinate("Enter row (0-2): ");
                    int y = getValidCoordinate("Enter column (0-2): ");
                    status = board.move(player2.getSymbol(), x, y);
                    if (status != Board.INVALID) {
                        player2Moves++;
                        player1Turn = true;
                        board.print();
                    } else {
                        System.out.println("Invalid Move !! Try Again !! ");
                    }
                }
            }

            if (status == Board.PLAYER1_WINS) {
                System.out.println("Player 1 - " + player1.getName() + " wins !!");
                player1Score++;
            } else if (status == Board.PLAYER2_WINS) {
                System.out.println("Player 2 - " + player2.getName() + " wins !!");
                player2Score++;
            } else {
                System.out.println(" !! DRAW !!");
            }

            System.out.println("Moves made:");
            System.out.println(player1.getName() + ": " + player1Moves);
            System.out.println(player2.getName() + ": " + player2Moves);

            System.out.println("Scoreboard:");
            System.out.println(player1.getName() + ": " + player1Score);
            System.out.println(player2.getName() + ": " + player2Score);

            System.out.print("Do you want to play again? (y/n): ");
            String ans = sc.next();
            playAgain = ans.equalsIgnoreCase("y");
        }

        System.out.println("Thanks for playing! Final Scores:");
        System.out.println(player1.getName() + ": " + player1Score);
        System.out.println(player2.getName() + ": " + player2Score);
    }

    private int getValidCoordinate(String prompt) {
        int coord = -1;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                coord = sc.nextInt();
                if (coord >= 0 && coord <= 2) {
                    break;
                } else {
                    System.out.println("Invalid input: please enter a number between 0 and 2.");
                }
            } else {
                System.out.println("Invalid input: please enter a valid integer.");
                sc.next(); // clear invalid input
            }
        }
        return coord;
    }

    private player takePlayerInput(int num) {
        System.out.println("Enter Player " + num + " name: ");
        String name = sc.next();
        System.out.println("Enter Player " + num + " symbol: ");
        char symbol = sc.next().charAt(0);
        return new player(name, symbol);
    }
}
