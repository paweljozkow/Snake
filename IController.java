package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 *
 */

public interface IController {

	/**
	 * This method is responsible for keep the game running as long as game is not
	 * over. I should guarantee basic interaction between IModel and IView: IView
	 * should inform IModel on key pressed value (determine snake move direction),
	 * whereas IModel should pass data to refresh IView (repaint game board). The
	 * interaction frequency must increase along with points gathered.
	 */
	public void run();

}
