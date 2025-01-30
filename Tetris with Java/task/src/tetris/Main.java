package tetris;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int col = sc.nextInt();
        int row = sc.nextInt();
        String input = sc.nextLine();

        Board board = new Board(row, col);

        boolean exit = false;
        do{
            board.print();
            System.out.println();
            input = sc.nextLine();
            switch (input) {
                case "piece":
                    String p = sc.nextLine();
                    board.addNewPiece(p);
                    break;
                case "down":
                    board.movePieceDown();
                    break;
                case "right":
                    board.movePieceRight();
                    break;
                case "left":
                    board.movePieceLeft();
                    break;
                case "rotate":
                    board.rotatePiece();
                    break;
                case "break":
                    board.removeFullLine();
                    break;
                case "exit":
                    exit = true;
                    break;
            }

        }while(!exit && !board.gameOver);
        if(board.gameOver){
            board.print();
            System.out.println();
            System.out.println("Game Over!");
        }
        sc.close();
    }
}