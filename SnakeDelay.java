package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 */
public enum SnakeDelay {

	DELAY;

	int snakeDelay = 500;
	int speedLevel = 1;

	public void setSnakeDelay() {

		if (snakeDelay > 50) {
			snakeDelay = snakeDelay - 25;
			speedLevel++;
		}

	}

}
