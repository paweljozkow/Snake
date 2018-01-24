package pl.jozkow.Snake;

/**
 * @author Pawe³ JóŸków pawel.jozkow@gmail.com
 *
 */

public class GameController implements IController {

	private GameModel model;
	private GameView view;

	public GameController(IModel model, IView view) {
		this.model = (GameModel) model;
		this.view = (GameView) view;
	}

	@Override
	public void run() {

		while (model.isGameRunning()) {

			model.moveSnake(view.getKeyCode());
			view.refreshGameBoardView(model);

			try {
				Thread.sleep(model.getSnakeDelay());
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

}
