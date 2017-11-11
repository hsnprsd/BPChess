package views;

import info.jdavid.ansi.colors.Colors;
import models.GameModel;
import models.Move;
import java.util.Scanner;

public class GameView {
    private GameModel gameModel;

    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void showTable() {
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                String s = gameModel.getBoard().get(i, j);
                if (s.charAt(0) == '0') {
                    System.out.print(Colors.BLUE.foreground(Character.toString(s.charAt(1))));
                } else if (s.charAt(0) == '1') {
                    System.out.print(Colors.YELLOW.foreground(Character.toString(s.charAt(1))));
                } else {
                    System.out.print(s.charAt(1));
                }
            }
            System.out.println();
        }
    }

    public String[] askNames() {
        Scanner in = new Scanner(System.in);
        String[] names = new String[2];
        System.out.print(Colors.BLUE.foreground("Please enter your name: "));
        names[0] = in.next();
        System.out.print(Colors.YELLOW.foreground("Please enter your name: "));
        names[1] = in.next();
        return names;
    }

    public void helloWorld() {
        System.out.println("Hi :). How are you? Let's play chess!");
    }

    public Move askForMove(int id) {
        Scanner in = new Scanner(System.in);
        if (id == 0) {
            System.out.print(Colors.BLUE.foreground("Enter your move: "));
        } else {
            System.out.print(Colors.YELLOW.foreground("Enter your move: "));
        }
        return new Move(id, in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1, in.nextInt() - 1);
    }
}
