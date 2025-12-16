package com.yaren;
import java.util.*;

public class Game2048 {

    public static final int SIZE = 4;
    private final int[][] board = new int[SIZE][SIZE];
    private int score = 0;
    private final Random rand = new Random();

    public  Game2048() {
        addRandomTile();
        addRandomTile();
    }
    public int getScore(){
        return score;
    }

    public int[][] getBoardCopy(){
        int[][] copy = new int[SIZE][SIZE];
        for( int r =0;r<SIZE;r++){
            System.arraycopy(board[r], 0, copy[r], 0, SIZE);

        }return copy;
    }

    public void reset(){
        for(int i=0;i<SIZE;i++){
            Arrays.fill(board[i],0);
        }
    }

    public boolean has2048(){

        for(int[] row :board)  for( int v :row) if(v ==2048) return true;
        return false;
    }
    public boolean move() {
        for(int[] row : board) for (int v: row) if(v ==0) return true;
        for(int r =0; r<SIZE; r++){
            for(int i =0;i<SIZE;i++){
                int v = board[r][i];
                if(r+1<SIZE && board[r+1][i] == v) return true;
                if(i+1<SIZE && board[r][i+1] == v) return true;

            }

        }return false;
    }

    private void addRandomTile() {
        List<int[]> empty = new ArrayList<>();
        for (int r = 0; r < SIZE; r++)
            for (int c = 0; c < SIZE; c++)
                if (board[r][c] == 0) empty.add(new int[]{r, c});
        if (empty.isEmpty()) return;
        int[] pos = empty.get(rand.nextInt(empty.size()));
        board[pos[0]][pos[1]] = rand.nextDouble() < 0.9 ? 2 : 4;
    }
    private int[] compactRow(int[] row) {
        int[] out = new int[SIZE];
        int idx = 0;
        for (int v : row) if (v != 0) out[idx++] = v;
        return out;
    }
    private int[] mergeRow(int[] row) {
        int[] out = Arrays.copyOf(row, SIZE);
        for (int i = 0; i < SIZE - 1; i++) {
            if (out[i] != 0 && out[i] == out[i + 1]) {
                out[i] *= 2;
                score += out[i];
                out[i + 1] = 0;
                i++;
            }
        }
        return out;
    }

    private void reverseRows() {
        for (int r = 0; r < SIZE; r++)
            for (int c = 0, k = SIZE - 1; c < k; c++, k--) {
                int tmp = board[r][c]; board[r][c] = board[r][k]; board[r][k] = tmp;
            }
    }

    private void transpose() {
        for (int r = 0; r < SIZE; r++)
            for (int c = r + 1; c < SIZE; c++) {
                int tmp = board[r][c]; board[r][c] = board[c][r]; board[c][r] = tmp;
            }
    }

    public boolean moveLeft() {
        boolean changed = false;
        for (int r = 0; r < SIZE; r++) {
            int[] row = board[r];
            int[] compact = compactRow(row);
            int[] merged = mergeRow(compact);
            int[] finalRow = compactRow(merged);
            if (!Arrays.equals(row, finalRow)) changed = true;
            board[r] = finalRow;
        }
        if (changed) addRandomTile();
        return changed;
    }

    public boolean moveRight() {
        reverseRows(); boolean changed = moveLeft(); reverseRows(); return changed;
    }

    public boolean moveUp() {
        transpose(); boolean changed = moveLeft(); transpose(); return changed;
    }

    public boolean moveDown() {
        transpose(); reverseRows(); boolean changed = moveLeft(); reverseRows(); transpose(); return changed;
    }


}
