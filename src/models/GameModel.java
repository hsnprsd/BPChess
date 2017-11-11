package models;

public class GameModel {
    private Board board;
    private Player[] players;
    private String winner;

    public GameModel() {
        board = new Board();
        board.initialize();
        players = new Player[2];
        players[0] = new Player();
        players[1] = new Player();
        winner = "";
    }

    public void setNames(String[] names) {
        for (int i = 0; i < 2; ++i) {
            players[i].setName(names[i]);
        }
    }

    public Board getBoard() {
        return board;
    }

    public String getWinner() {
        return winner;
    }

    public void movePiece(Move move) {
        if (board.get(move.x2, move.y2).charAt(1) == 'K') {
            int id = '1' - board.get(move.x2, move.y2).charAt(0);
            winner = players[id].getName();
        }
        board.move(move);
    }
}
