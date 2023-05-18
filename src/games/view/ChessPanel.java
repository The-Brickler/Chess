package games.view;

import games.controller.Controller;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class ChessPanel extends JPanel
{
	
	private boolean canMove;
	
	private Controller app;
	
	private GamePanel game;
	
	private JPanel promotePanel;
	private JButton rookButton;
	private JButton bishopButton;
	private JButton queenButton;
	private JButton knightButton;
	
	private JButton newGameButton;

	private SpringLayout layout;
	
	private JLabel status;
	
	private int [] nextPromotion;
	
	//private JLabel positionLabel;
	
	public ChessPanel(Controller app)
	{
		super();
		this.app = app;
		
		layout = new SpringLayout();
		
		setCanMove(true);
		
		game = new GamePanel(this.app);
		nextPromotion = new int [] {-1, -1};
		
		promotePanel = new JPanel(new GridLayout(1, 0));
		rookButton = new JButton("Rook");
		bishopButton = new JButton("Bishop");
		queenButton = new JButton("Queen");
		knightButton = new JButton("Knight");
		
		newGameButton = new JButton ("New Game");
		
		status = new JLabel("Click a white piece to move!");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		//this.add(positionLabel);
		this.add(game);
		this.setLayout(layout);
		
		promotePanel.add(rookButton);
		promotePanel.add(bishopButton);
		promotePanel.add(knightButton);
		promotePanel.add(queenButton);
		this.add(promotePanel);
		rookButton.setEnabled(false);
		bishopButton.setEnabled(false);
		knightButton.setEnabled(false);
		queenButton.setEnabled(false);
		
		status.setHorizontalAlignment(JLabel.CENTER);
		this.add(status);
		
		this.add(newGameButton);
	}
	
	private void setupListeners()
	{
			game.addMouseListener(new MouseListener()
			{
				public void mouseClicked(MouseEvent click)
				{
					
				}

				public void mousePressed(MouseEvent press)
				{
					
				}

				@Override
				public void mouseReleased(MouseEvent release)
				{
					int x = release.getX();
					int y = release.getY();
					
					x = x * 8 / game.getWidth();
					y = y * 8 / game.getHeight();
					
					//positionLabel.setText("x: " + x + "\ty: " + y);
					if (canMove)
					{
						game.select(y, x);
					}
				}

				@Override
				public void mouseEntered(MouseEvent enter)
				{
					
				}

				@Override
				public void mouseExited(MouseEvent exit)
				{
					
				}
			});
	
			rookButton.addActionListener(click -> promote("rook"));
			bishopButton.addActionListener(click -> promote("bishop"));
			knightButton.addActionListener(click -> promote("knight"));
			queenButton.addActionListener(click -> promote("queen"));
	}
	
	private void promote(String newPiece)
	{
		madeMove();
		if (nextPromotion[0] != -1)
		{
			app.promote(nextPromotion, newPiece);
		}
		rookButton.setEnabled(false);
		bishopButton.setEnabled(false);
		knightButton.setEnabled(false);
		queenButton.setEnabled(false);
		
		nextPromotion[0] = -1;
		nextPromotion[1] = -1;
		game.updateDisplay();
	}

	private void setupLayout()
	{
		layout.putConstraint(SpringLayout.NORTH, game, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, game, 10, SpringLayout.WEST, this);
		
		layout.putConstraint(SpringLayout.NORTH, promotePanel, 10, SpringLayout.SOUTH, game);
		layout.putConstraint(SpringLayout.WEST, promotePanel, 0, SpringLayout.WEST, game);
		layout.putConstraint(SpringLayout.SOUTH, promotePanel, 50, SpringLayout.NORTH, promotePanel);
		layout.putConstraint(SpringLayout.EAST, promotePanel, 0, SpringLayout.EAST, game);
		
		layout.putConstraint(SpringLayout.NORTH, status, 10, SpringLayout.SOUTH, promotePanel);
		layout.putConstraint(SpringLayout.EAST, status, 0, SpringLayout.EAST, promotePanel);
		layout.putConstraint(SpringLayout.WEST, status, 0, SpringLayout.WEST, promotePanel);
		
		layout.putConstraint(SpringLayout.WEST, newGameButton, 30, SpringLayout.EAST, game);
		layout.putConstraint(SpringLayout.EAST, newGameButton, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, newGameButton, 0, SpringLayout.NORTH, game);
		layout.putConstraint(SpringLayout.SOUTH, newGameButton, 100, SpringLayout.NORTH, newGameButton);
	}

	public void showPromoteDialog(int row, int col)
	{
		rookButton.setEnabled(true);
		bishopButton.setEnabled(true);
		knightButton.setEnabled(true);
		queenButton.setEnabled(true);
		this.nextPromotion[0] = row;
		this.nextPromotion[1] = col;
	}
	
	public void madeMove()
	{
		setStatus("Calculating Move.....");
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					app.AIMove();
					game.updateDisplay();
					setCanMove(true);
					setStatus("Click a white piece to move!");
				}
				catch (InterruptedException error)
				{
					app.handleError(error);
				}
			}
		}).start();
	}

	public void gameEnd()
	{
		setCanMove(false);
		setStatus("GAME OVER");
		
	}

	public void setCanMove(boolean canMove)
	{
		this.canMove = canMove;
	}

	public void updateDisplay()
	{
		game.updateDisplay();
		
	}

	public void setStatus(String status)
	{
		this.status.setText(status);
	}
}
