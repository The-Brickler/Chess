package games.view;

import games.controller.Controller;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessPanel extends JPanel
{
	
	private Controller app;
	
	private GamePanel game;
	
	private JPanel promotePanel;
	private JButton rookButton;
	private JButton bishopButton;
	private JButton queenButton;
	private JButton knightButton;

	private int [] nextPromotion;
	
	//private JLabel positionLabel;
	
	public ChessPanel(Controller app)
	{
		super();
		this.app = app;
		
		game = new GamePanel(this.app);
		nextPromotion = new int [] {-1, -1};
		
		promotePanel = new JPanel(new GridLayout(1, 0));
		rookButton = new JButton("Rook");
		bishopButton = new JButton("Bishop");
		queenButton = new JButton("Queen");
		knightButton = new JButton("Knight");
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		//this.add(positionLabel);
		this.add(game);
		
		promotePanel.add(rookButton);
		promotePanel.add(bishopButton);
		promotePanel.add(knightButton);
		promotePanel.add(queenButton);
		this.add(promotePanel);
		promotePanel.setVisible(false);
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
					
					game.select(y, x);
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
		if (nextPromotion[0] != -1)
		{
			app.promote(nextPromotion, newPiece);
		}
		promotePanel.setVisible(false);
		nextPromotion[0] = -1;
		nextPromotion[1] = -1;
		game.updateDisplay();
	}

	private void setupLayout()
	{
		
	}

	public void showPromoteDialog(int row, int col)
	{
		promotePanel.setVisible(true);
		this.nextPromotion[0] = row;
		this.nextPromotion[1] = col;
	}
}
