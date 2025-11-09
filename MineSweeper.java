import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowNumber, colNumber;
    int[][] map;
    int[][] board;
    boolean game = true;
    Random rand = new Random();
    Scanner scan = new Scanner(System.in);

    public MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.map = new int[rowNumber][colNumber];
        this.board = new int[rowNumber][colNumber];
    }

    public void run() {
        int row, col;
        int success = 0;
        prepareGame();
        printMap();

        System.out.println("Oyun Başladı!");

        while (game) {
            printBoard();
            System.out.print("Satır Giriniz : ");
            row = scan.nextInt();
            System.out.print("Sütun Giriniz : ");
            col = scan.nextInt();

            if (row < 0 || row >= rowNumber || col < 0 || col >= colNumber) {
                System.out.println("Geçersiz koordinat! Tekrar deneyin.");
                continue;
            }

            if (map[row][col] == -1) {
                System.out.println("Boom! Mayına bastınız! Oyun bitti ☠️");
                game = false;
            } else {
                check(row, col);
                success++;
                if (success == (rowNumber * colNumber) - (rowNumber * colNumber / 4)) {
                    System.out.println("Tebrikler! Tüm güvenli bölgeleri açtınız! 🎉");
                    break;
                }
            }
        }
    }

    public void prepareGame() {
        int count = 0;
        int mineNumber = (rowNumber * colNumber) / 4;

        while (count < mineNumber) {
            int randRow = rand.nextInt(rowNumber);
            int randCol = rand.nextInt(colNumber);

            if (map[randRow][randCol] != -1) {
                map[randRow][randCol] = -1;
                count++;
            }
        }
    }

    public void check(int r, int c) {
        int mineCount = 0;

        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < rowNumber && j >= 0 && j < colNumber) {
                    if (map[i][j] == -1) {
                        mineCount++;
                    }
                }
            }
        }

        board[r][c] = mineCount;
    }

    public void printMap() {
        System.out.println("\nMayın Haritası (Test amaçlı):");
        for (int[] row : map) {
            for (int value : row) {
                if (value == -1)
                    System.out.print("* ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printBoard() {
        System.out.println("\nOyun Durumu:");
        for (int[] row : board) {
            for (int value : row) {
                if (value == 0)
                    System.out.print("- ");
                else
                    System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Satır sayısı giriniz: ");
        int row = input.nextInt();
        System.out.print("Sütun sayısı giriniz: ");
        int col = input.nextInt();

        MineSweeper game = new MineSweeper(row, col);
        game.run();
    }
}
