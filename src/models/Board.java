package models;

public class Board {
    private char[][] grid;
    private int[][] ids;

    public String get(int i, int j) {
        return Integer.toString(ids[i][j]) + Character.toString(grid[i][j]);
    }

    public void initialize() {
        grid = new char[8][8];
        ids = new int[8][8];
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                grid[i][j] = '.';
                ids[i][j] = 2;
            }
        }
        for (int i = 0; i < 8; ++i) {
            grid[1][i] = grid[6][i] = 'P';
        }
        for (int i = 0; i < 8; i += 7) {
            grid[i][0] = grid[i][7] = 'R';
            grid[i][1] = grid[i][6] = 'N';
            grid[i][2] = grid[i][5] = 'B';
            grid[i][3] = 'K';
            grid[i][4] = 'Q';
        }
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 8; ++j) {
                ids[i][j] = 1;
                ids[7 - i][j] = 0;
            }
        }
    }

    public void move(Move move) {
        grid[move.x2][move.y2] = grid[move.x1][move.y1];
        grid[move.x1][move.y1] = '.';
        ids[move.x2][move.y2] = ids[move.x1][move.y1];
        ids[move.x1][move.y1] = 2;
    }
}
