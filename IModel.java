package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 *
 *         Implementation of this interface should manage all details that
 *         impact game state (i.e. snake position, dot position, points,
 *         messages) and check conditions on which game ends.
 */

public interface IModel {

	/**
	 * This method triggers snake movement in one of four directions;
	 * 
	 * @param code
	 */
	public void moveSnake(int code);

	/**
	 * This method is called by IView implementation to determine where to draw snake's tail.
	 * 
	 * @param x
	 *            is snake's tail coordinate x
	 * @param y
	 *            is snake's tail coordinate y
	 * @return true is snake's tail should be drawn in certain field;
	 */
	public boolean getWhereIsSnake(int x, int y);

	/**
	 * This method is called by IView implementation to determine where to draw snake's head.
	 * 
	 * @param x
	 *            is snake's head coordinate x
	 * @param y
	 *            is snake's head coordinate y
	 * @return true is snake's head should be drawn in certain field;
	 */
	public boolean getWhereIsSnakeHead(int y, int x);

	/**
	 * This method is called by IView implementation to determine where to draw dot.
	 * 
	 * @param x
	 *            is field coordinate x
	 * @param y
	 *            is field coordinate y
	 * @return true is dot should be drawn in certain field;
	 */
	public boolean getDotPosition(int x, int y);

	/**
	 * This getter method is called by IView implementation to display value in
	 * total points field.
	 * 
	 * @return total points
	 */
	public int getTotalPoints();

	/**
	 * This getter method checks the value of speed field and passes it to
	 * IController implementation.
	 * 
	 * @return snake speed
	 */
	public int getSnakeDelay();

	/**
	 * This getter method is called by IView implementation to display value in
	 * speed level field.
	 * 
	 * @return snake speed level
	 */
	public int getSpeedLevel();

	/**
	 * This method checks if game should be ceased.
	 * 
	 * @return true if game can go on and false if it should end
	 */
	public boolean isGameRunning();

	/**
	 * This getter method is called by IView implementation to display message in
	 * message field
	 * 
	 * @return message to display
	 */
	public String getMessage();
}
