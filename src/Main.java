import controllers.GameController;
import models.GameModel;
import views.GameView;

public class Main {
    public static void main(String[] args) {
        GameModel model = new GameModel();
        GameView view = new GameView(model);
        GameController controller = new GameController(model, view);
        controller.startGame();
    }
}
