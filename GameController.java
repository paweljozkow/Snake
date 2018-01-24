package pl.jozkow.Snake;

/**
 * @author Pawe³ JóŸków pawel.jozkow@gmail.com
 *
 */

public class GameController implements IController {

	private IModel model;
	private IView view;

	public GameController(IModel model, IView view) {
		this.model = model;
		this.view = view;
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
