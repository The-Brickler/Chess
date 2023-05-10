package games.view;

import games.controller.Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	private Controller app;
	
	private BufferedImage[][] imageArray;
	private JLabel[][] labelArray;
	
	public GamePanel(Controller app)
	{
		super();
		this.app = app;
		
		imageArray = new BufferedImage[8][8];
		labelArray = new JLabel[8][8];
		
		setupPanel();
		setupListeners();
	}

	private void setupPanel()
	{
		final Color whiteTile = new Color(219, 188, 147);
		final Color blackTile = new Color(59, 48, 33);
		
		this.setLayout(new GridLayout(8, 8));
		
		for (int row = 0; row < imageArray.length; row++)
		{
			for (int col = 0; col < imageArray[row].length; col++)
			{
				imageArray[row][col] = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
				
				Color background = blackTile;
				
				if (row % 2 == 0)
				{
					if (col % 2 == 0)
					{
						background = whiteTile;
					}
				}
				else
				{
					if (col % 2 == 1)
					{
						background = whiteTile;
					}
				}
				
				Graphics2D graphics = imageArray[row][col].createGraphics();
				graphics.setColor(background);
				graphics.fill(new Rectangle(0, 0, 50, 50));
				repaint();
				
				labelArray[row][col] = new JLabel();
				labelArray[row][col].setIcon(new ImageIcon(imageArray[row][col]));
				this.add(labelArray[row][col]);
			}
		}
	}
	
	private void setupListeners()
	{
		
	}
}
