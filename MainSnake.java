package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 */
public class MainSnake {

	public static void main(String[] args) {
	
		IModel model = new GameModel();
		IView view = new GameView();
	
		GameController controller = new GameController(model, view);
				controller.run();
	}

}


