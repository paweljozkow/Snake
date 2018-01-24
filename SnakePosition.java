package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 */
import java.util.LinkedList;

public class SnakePosition {

	protected LinkedList<Integer> snakeCoordX = new LinkedList<>();
	protected LinkedList<Integer> snakeCoordY = new LinkedList<>();
	
	//when snake's head enters new position
	public void setSnakeCoordinates (int x, int y) {
		snakeCoordX.addFirst(x);
		snakeCoordY.addFirst(y);
	}

	//when snake's tail is not there anymore
	public void removeSnakeCoordinates() {	
		snakeCoordX.removeLast();
		snakeCoordY.removeLast();
	}
	
}
