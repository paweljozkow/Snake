package pl.jozkow.Snake;

/**
 * @author Pawe³ JóŸków pawel.jozkow@gmail.com
 *
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GameView implements IView {

	int keyCode;

	JFrame f = new JFrame("S-N-A-K-E");
	JButton[][] fieldsOnBoard = new JButton[GameBoardConstants.GAME_BOARD_LENGTH][GameBoardConstants.GAME_BOARD_HEIGHT];
	JTextField messagesField = new JTextField();
	JTextField totalPointsField = new JTextField();
	JTextField speedLevelField = new JTextField();

	LineBorder borderSnakeHead = new LineBorder(Color.BLACK, 3);
	LineBorder borderSnakeTail = new LineBorder(Color.GREEN, 3);
	LineBorder borderNoSnake = new LineBorder(Color.GRAY, 0);

	GameView() {

		JPanel masterPanel = new JPanel(new BorderLayout());
		masterPanel.setBackground(Color.DARK_GRAY);

		JPanel messagesPanel = new JPanel();

		messagesField.setPreferredSize(new Dimension(370, 45));
		messagesField.setHorizontalAlignment(SwingConstants.CENTER);
		messagesField.setFocusable(false);
		messagesPanel.setBackground(Color.DARK_GRAY);
		messagesPanel.add(messagesField);

		JPanel snakePanel = new JPanel(
				new GridLayout(GameBoardConstants.GAME_BOARD_LENGTH, GameBoardConstants.GAME_BOARD_HEIGHT));
		snakePanel.setBackground(Color.DARK_GRAY);
		for (int i = 0; i < GameBoardConstants.GAME_BOARD_LENGTH; i++) {
			for (int k = 0; k < GameBoardConstants.GAME_BOARD_HEIGHT; k++) {
				fieldsOnBoard[i][k] = new JButton();
				fieldsOnBoard[i][k].setFocusable(false);
				snakePanel.add(fieldsOnBoard[i][k]);
			}
		}

		JPanel pointsAndSpeedPanel = new JPanel();
		pointsAndSpeedPanel.setBackground(Color.DARK_GRAY);
		JLabel labelPoints = new JLabel("Points: ");
		labelPoints.setForeground(Color.YELLOW);

		totalPointsField.setText("0");
		totalPointsField.setPreferredSize(new Dimension(60, 30));
		totalPointsField.setHorizontalAlignment(SwingConstants.RIGHT);
		totalPointsField.setFocusable(false);

		JLabel labelSpeed = new JLabel("Speed: ");
		labelSpeed.setForeground(Color.YELLOW);

		speedLevelField.setText("0");
		speedLevelField.setPreferredSize(new Dimension(60, 30));
		speedLevelField.setHorizontalAlignment(SwingConstants.RIGHT);
		speedLevelField.setFocusable(false);
		pointsAndSpeedPanel.add(labelPoints);
		pointsAndSpeedPanel.add(totalPointsField);
		pointsAndSpeedPanel.add(labelSpeed);
		pointsAndSpeedPanel.add(speedLevelField);

		masterPanel.add(messagesPanel, BorderLayout.PAGE_START);
		masterPanel.add(snakePanel, BorderLayout.CENTER);
		masterPanel.add(pointsAndSpeedPanel, BorderLayout.PAGE_END);

		f.setFocusable(true);
		f.setResizable(false);
		f.setSize(400, 500);
		f.getContentPane().add(masterPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		f.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				keyCode = e.getKeyCode();
			}
		});
	}

	@Override
	public void refreshGameBoardView(IModel model) {

		for (int i = 0; i < GameBoardConstants.GAME_BOARD_LENGTH; i++) {
			for (int k = 0; k < GameBoardConstants.GAME_BOARD_HEIGHT; k++) {
				setGameBoardFieldColor(i, k, model);
			}
		}
		showMessage(model);
		showTotalPoints(model);
		showSpeedLevel(model);
		f.repaint();
	}

	private void showMessage(IModel model) {
		messagesField.setText(model.getMessage());
	}

	private void showSpeedLevel(IModel model) {
		speedLevelField.setText(Integer.toString(model.getSpeedLevel()));
	}

	private void showTotalPoints(IModel model) {
		totalPointsField.setText(Integer.toString(model.getTotalPoints()));
	}

	/**
	 * This method is responsible for setting colors of fields on game board. To do
	 * so, it has to ask model getter methods about whereabouts of snake's head,
	 * snake's tail and dot and colors fields in game board.
	 * 
	 * @param i
	 *            represents field coordinate x
	 * @param k
	 *            represents field coordinate y
	 * @param model
	 *            is model instantiation
	 */
	private void setGameBoardFieldColor(int i, int k, IModel model) {

		if (model.getWhereIsSnakeHead(i, k)) {
			fieldsOnBoard[i][k].setBackground(Color.GREEN);
			fieldsOnBoard[i][k].setBorder(borderSnakeHead);
		} else {
			if (model.getWhereIsSnake(i, k)) {
				fieldsOnBoard[i][k].setBackground(Color.GREEN);
				fieldsOnBoard[i][k].setBorder(borderSnakeTail);
			} else {
				if (model.getDotPosition(i, k)) {
					fieldsOnBoard[i][k].setBackground(Color.RED);
					fieldsOnBoard[i][k].setBorder(borderNoSnake);
				} else {
					fieldsOnBoard[i][k].setBackground(Color.GRAY);
					fieldsOnBoard[i][k].setBorder(borderNoSnake);
				}
			}
		}

	}

	@Override
	public int getKeyCode() {
		return keyCode;
	}

}
