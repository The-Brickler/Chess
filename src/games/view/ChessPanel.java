package games.view;

import games.controller.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessPanel extends JPanel
{
	
	private Controller app;
	
	private GamePanel game;
	
	private JLabel positionLabel;
	
	public ChessPanel(Controller app)
	{
		super();
		this.app = app;
		
		game = new GamePanel(this.app);
		positionLabel = new JLabel();
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.add(positionLabel);
		this.add(game);
	}
	
	private void setupListeners()
	{
			game.addMouseListener(new MouseListener()
			{
				public void mouseClicked(MouseEvent click)
				{
					int x = click.getX();
					int y = click.getY();
					
					x = x * 8 / game.getWidth();
					y = y * 8 / game.getHeight();
					
					positionLabel.setText("x: " + x + "\ty: " + y);
					
					game.select(y, x);
				}

				public void mousePressed(MouseEvent press)
				{
					
				}

				@Override
				public void mouseReleased(MouseEvent release)
				{
					
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
	}
	
	private void setupLayout()
	{
		
	}
}
