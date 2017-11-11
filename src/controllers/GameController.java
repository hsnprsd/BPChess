package controllers;

import models.GameModel;
import models.Move;
import views.GameView;

public class GameController {
    private GameView gameView;
    private GameModel gameModel;

    public GameController(GameModel gameModel, GameView gameView) {
        this.gameView = gameView;
        this.gameModel = gameModel;
    }

    public boolean isMoveValid(Move move) {
        int x1 = move.x1;
        int y1 = move.y1;
        int x2 = move.x2;
        int y2 = move.y2;
        if (x1 < 0 || x1 >= 8 || y1 < 0 || y1 >= 8 || x2 < 0 || x2 >= 8 || y2 < 0 || y2 >= 8) {
            return false;
        }
        if (x1 == x2 && y1 == y2) {
            return false;
        }
        if (Character.toString(gameModel.getBoard().get(x1, y1).charAt(0)).compareTo(Integer.toString(move.id)) != 0) {
            return false;
        }
        if (Character.toString(gameModel.getBoard().get(x2, y2).charAt(0)).compareTo(Integer.toString(move.id)) == 0) {
            return false;
        }
        char charAtDest = gameModel.getBoard().get(x2, y2).charAt(1);
        int ownerAtDest = gameModel.getBoard().get(x2, y2).charAt(0) - '0';
        switch (gameModel.getBoard().get(x1, y1).charAt(1)) {
            case 'P':
                int d;
                if (move.id == 0) {
                    d = -1;
                } else {
                    d = 1;
                }
                if (x1 + d != x2) {
                    return false;
                }
                if (y1 == y2) {
                    if (charAtDest == '.') {
                        return true;
                    }
                    return false;
                }
                if ((y1 + 1 == y2 || y1 - 1 == y2) && ownerAtDest == 1 - move.id) {
                    return true;
                }
            case 'R':
                if (x1 - x2 != 0 && y1 - y2 != 0) {
                    return false;
                }
                while (1 == 1) {
                    if (x1 < x2) ++x1;
                    if (x1 > x2) --x1;
                    if (y1 < y2) ++y1;
                    if (y1 > y2) --y1;
                    if (x1 == x2 && y1 == y2) {
                        if (charAtDest == '.') {
                            return true;
                        } else if (ownerAtDest == 1 - move.id) {
                            return true;
                        }
                        return false;
                    } else if (gameModel.getBoard().get(x1, y1).charAt(1) != '.') {
                        return false;
                    }
                }
            case 'B':
                if (Math.abs(x1 - x2) != Math.abs(y1 - y2)) {
                    return false;
                }
                while (1 == 1) {
                    if (x1 < x2) ++x1;
                    if (x1 > x2) --x1;
                    if (y1 < y2) ++y1;
                    if (y1 > y2) --y1;
                    if (x1 == x2 && y1 == y2) {
                        if (charAtDest == '.') {
                            return true;
                        } else if (ownerAtDest == 1 - move.id) {
                            return true;
                        }
                        return false;
                    } else if (gameModel.getBoard().get(x1, y1).charAt(1) != '.') {
                        return false;
                    }
                }
            case 'N':
                if ((Math.abs(x1 - x2) == 2 && Math.abs(y1 - y2) == 1) || (Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 2)) {
                    if (ownerAtDest == move.id) {
                        return false;
                    }
                    return true;
                }
                return false;
            case 'Q':
                if (x1 - x2 != 0 && y1 - y2 != 0 && Math.abs(x1 - x2) != Math.abs(y1 - y2)) {
                    return false;
                }
                while (1 == 1) {
                    if (x1 < x2) ++x1;
                    if (x1 > x2) --x1;
                    if (y1 < y2) ++y1;
                    if (y1 > y2) --y1;
                    if (x1 == x2 && y1 == y2) {
                        if (charAtDest == '.') {
                            return true;
                        } else if (ownerAtDest == 1 - move.id) {
                            return true;
                        }
                        return false;
                    } else if (gameModel.getBoard().get(x1, y1).charAt(1) != '.') {
                        return false;
                    }
                }
            case 'K':
                if (Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1) {
                    if (ownerAtDest != move.id) {
                        return true;
                    }
                    return false;
                }
                return false;
            default:
        }
        return false;
    }

    public void startGame() {
        gameView.helloWorld();
        String[] names = gameView.askNames();
        gameModel.setNames(names);
        int id = 0;
        while (gameModel.getWinner().equals("")) {
            gameView.showTable();
            Move move;
            do {
                move = gameView.askForMove(id);
            } while (!isMoveValid(move));
            gameModel.movePiece(move);
            id = 1 - id;
        }
        System.out.println("CONGRATS " + gameModel.getWinner().toUpperCase());
    }
}
