package pl.jozkow.Snake;

/**
 * @author Pawe³ JóŸków, pawel.jozkow@gmail.com
 *
 */

import java.awt.event.KeyEvent;
import java.util.Random;

public class GameModel implements IModel {

	private static int currentPositionX = 0;
	private static int currentPositionY = 0;

	private boolean[][] whereIsSnake = new boolean[GameBoardConstants.GAME_BOARD_LENGTH][GameBoardConstants.GAME_BOARD_HEIGHT];
	private boolean[][] whereAreDots = new boolean[GameBoardConstants.GAME_BOARD_LENGTH][GameBoardConstants.GAME_BOARD_HEIGHT];

	private boolean dotEaten;
	private boolean gameRunning = true;

	private String message;
	private static final String BEGIN = "Shall we begin?";
	private static final String CRAWLING_AROUND = "Just crawling around";
	private static final String WANT_MORE = "I want more!";
	private static final String GAME_OVER = "Game over! You have %s points.";
	private static final String YOU_WIN = "It's impossible. You win with total of %s points.";

	SnakePosition snakePosition = new SnakePosition();
	Random random = new Random();

	public GameModel() {
		snakePosition.setSnakeCoordinates(0, 0);
		setSnakeIsHere(0, 0);
		placeNewDot();
		setMessage(BEGIN);
	}

	@Override
	public void moveSnake(int code) {
		switch (code) {
		case KeyEvent.VK_UP:
			moveUp();
			break;
		case KeyEvent.VK_DOWN:
			moveDown();
			break;
		case KeyEvent.VK_LEFT:
			moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			moveRight();
			break;

		}
	}

	/**
	 * One of four move methods. It checks if desired move is possible with regard
	 * to game board border. If so, it updates message and calls method for updating
	 * snake's position. If not, it triggers ceasing the game.
	 */
	private void moveUp() {
		if (currentPositionY > 0) {
			currentPositionY--;
			setMessage(CRAWLING_AROUND);
			updateSnakePosition();
		} else {
			setGameOver();
		}
	}

	private void moveDown() {
		if (currentPositionY < GameBoardConstants.GAME_BOARD_HEIGHT - 1) {
			currentPositionY++;
			setMessage(CRAWLING_AROUND);
			updateSnakePosition();
		} else {
			setGameOver();
		}
	}

	private void moveRight() {
		if (currentPositionX < GameBoardConstants.GAME_BOARD_LENGTH - 1) {
			currentPositionX++;
			setMessage(CRAWLING_AROUND);
			updateSnakePosition();
		} else {
			setGameOver();
		}
	}

	private void moveLeft() {
		if (currentPositionX > 0) {
			currentPositionX--;
			setMessage(CRAWLING_AROUND);
			updateSnakePosition();
		} else {
			setGameOver();
		}
	}

	/**
	 * This method is called after every move. It calls methods in order to update
	 * snake coordinates, check if dot can be eaten and add points.
	 */
	private void updateSnakePosition() {
		snakePosition.setSnakeCoordinates(currentPositionX, currentPositionY);
		setSnakeIsHere(currentPositionX, currentPositionY);
		eatDot(currentPositionX, currentPositionY);
		setSnakeNotHere(snakePosition.snakeCoordX.peekLast(), snakePosition.snakeCoordY.peekLast());

		if (!dotEaten) {
			snakePosition.removeSnakeCoordinates();
		}

		TotalPoints.POINTS.addPointsForMove();
		TotalPoints.POINTS.countTotalPoints();

	}

	/**
	 * This method checks if there's a place for a new dot. As long as the total
	 * number of game board fields is greater than the number of fields occupied by
	 * snake, a new dot can be placed. Coordinates for a new dot are chosen
	 * randomly. When given coordinates point at empty field, dot is placed. Note
	 * that this method is prepared to run with game model with many dots in the
	 * same time.
	 */
	private void placeNewDot() {

		if ((GameBoardConstants.GAME_BOARD_LENGTH * GameBoardConstants.GAME_BOARD_HEIGHT) > snakePosition.snakeCoordX
				.size()) {

			int dotCoordX;
			int dotCoordY;

			do {
				dotCoordX = random.nextInt(GameBoardConstants.GAME_BOARD_LENGTH);
				dotCoordY = random.nextInt(GameBoardConstants.GAME_BOARD_HEIGHT);
			} while (whereIsSnake[dotCoordX][dotCoordY] == true || whereAreDots[dotCoordX][dotCoordY] == true);

			whereAreDots[dotCoordX][dotCoordY] = true;

		} else {
			setMessage(String.format(YOU_WIN, TotalPoints.POINTS.totalPoints));
			gameRunning = false;
		}

	}

	/**
	 * This method checks if snake entered field with dot. If so, it triggers
	 * placement of new dot, adding points for dot and displaying proper message.
	 * 
	 * @param y
	 *            field coordinate x (note the swap of x and y due to
	 *            multidimensional array characteristics)
	 * @param x
	 *            field coordinate y (note the swap of x and y due to
	 *            multidimensional array characteristics)
	 */
	private void eatDot(int y, int x) {
		if (whereIsSnake[x][y] == whereAreDots[x][y]) {
			whereAreDots[x][y] = false;
			TotalPoints.POINTS.addPointsForDot();
			setMessage(WANT_MORE);
			placeNewDot();
			setDotEaten(true);
		} else {
			setDotEaten(false);
		}

	}

	private void setDotEaten(boolean eaten) {
		dotEaten = eaten;
	}

	/**
	 * This method sets gameRunning as false and triggers message field update. This
	 * method is called whenever snake tries to go out of the game board or when
	 * snake attempts to move onto oneself.
	 */
	private void setGameOver() {
		setMessage(String.format(GAME_OVER, TotalPoints.POINTS.totalPoints));
		gameRunning = false;
	}

	/**
	 * This method is called upon every snake's move to take snake's tail form the
	 * field determined by arguments.
	 * 
	 * @param y
	 *            field coordinate x (note the swap of x and y due to
	 *            multidimensional array characteristics)
	 * @param x
	 *            field coordinate y (note the swap of x and y due to
	 *            multidimensional array characteristics)
	 */
	private void setSnakeNotHere(int y, int x) {
		whereIsSnake[x][y] = false;
	}

	/**
	 * This method is called in constructor and then upon every attempt to change
	 * snake's position. It checks if current destination is already occupied by
	 * snake - if so, game should be ceased, else snake can move there.
	 * 
	 * @param y
	 *            is destination field coordinate x (note the swap of x and y due to
	 *            multidimensional array characteristics)
	 * @param x
	 *            is destination field coordinate y (note the swap of x and y due to
	 *            multidimensional array characteristics)
	 */
	private void setSnakeIsHere(int y, int x) {
		if (this.whereIsSnake[x][y] == true) {
			setGameOver();
		} else {
			this.whereIsSnake[x][y] = true;
		}
	}

	@Override
	public boolean getWhereIsSnake(int x, int y) {
		return whereIsSnake[x][y];
	}

	@Override
	public boolean getWhereIsSnakeHead(int y, int x) {
		boolean b = false;

		if (snakePosition.snakeCoordX.peekFirst() == x && snakePosition.snakeCoordY.peekFirst() == y) {
			b = true;
		}

		return b;
	}

	@Override
	public boolean getDotPosition(int x, int y) {
		return whereAreDots[x][y];
	}

	@Override
	public int getTotalPoints() {
		return TotalPoints.POINTS.totalPoints;
	}

	@Override
	public int getSnakeDelay() {
		return SnakeDelay.DELAY.snakeDelay;
	}

	public int getSpeedLevel() {
		return SnakeDelay.DELAY.speedLevel;
	}

	@Override
	public boolean isGameRunning() {
		return gameRunning;
	}

	/**
	 * This setter method is called whenever there is a need to set value of message
	 * field
	 * 
	 * @param newMessage
	 *            points which of predefined messages assign to message field
	 */
	private void setMessage(String newMessage) {
		message = newMessage;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
