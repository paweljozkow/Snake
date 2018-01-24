package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 * 
 *         Implementation of this interface manages the view part of the game.
 */
public interface IView {

	/**
	 * This method enforces repainting game board and updating information shown on
	 * 3 textfields (points, speed level, and messages);
	 * 
	 * @param model
	 *            is model instantiation
	 */
	void refreshGameBoardView(IModel model);

	/**
	 * This getter method is responsible for passing key pressed value to IModel
	 * implementation.
	 * 
	 * @return code of key taken by key listener
	 */
	public int getKeyCode();

}
