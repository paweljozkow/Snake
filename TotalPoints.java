package pl.jozkow.Snake;

/**
 * @author Pawel Jozkow pawel.jozkow@gmail.com
 */
public enum TotalPoints {

	POINTS;
	
	private int pointsForMove = 0;
	private int pointsForDot = 0;
	protected int totalPoints = 0;
	
	public void addPointsForMove() {
		pointsForMove++;
	}
	
	public void addPointsForDot() {
		pointsForDot =+ 10;
	}
	
	public void countTotalPoints() {
		totalPoints = pointsForMove + pointsForDot;
		
		if(totalPoints%100==0) {
			SnakeDelay.DELAY.setSnakeDelay();
		}
	}
	
}
