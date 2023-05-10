package games.view;

import games.controller.Controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
	private static final Color whiteTile = new Color(219, 188, 147);
	private static final Color blackTile = new Color(135, 107, 73);
	private static final Color whiteSelected = new Color(178, 199, 145);
	private static final Color blackSelected = new Color(73, 87, 51);
	
	private Controller app;
	
	private BufferedImage[][] imageArray;
	private JLabel[][] labelArray;
	
	private int [] selected;
	
	public GamePanel(Controller app)
	{
		super();
		this.app = app;
		
		imageArray = new BufferedImage[8][8];
		labelArray = new JLabel[8][8];
		
		selected = new int [] {-1, -1};
		
		setupPanel();
		setupListeners();
	}

	private void setupPanel()
	{
		
		this.setLayout(new GridLayout(8, 8));
		
		for (int row = 0; row < imageArray.length; row++)
		{
			for (int col = 0; col < imageArray[row].length; col++)
			{
				imageArray[row][col] = new BufferedImage(45, 45, BufferedImage.TYPE_INT_ARGB);
				
				Color white = whiteTile;
				Color black = blackTile;
				
				if (selected[0] == row && selected[1] == col)
				{
					white = whiteSelected;
					black = blackSelected;
				}
				
				Color background = black;
				
				if (row % 2 == 0)
				{
					if (col % 2 == 0)
					{
						background = white;
					}
				}
				else
				{
					if (col % 2 == 1)
					{
						background = white;
					}
				}
				
				Graphics2D graphics = imageArray[row][col].createGraphics();
				graphics.setColor(background);
				graphics.fill(new Rectangle(0, 0, 45, 45));
				repaint();
				
				labelArray[row][col] = new JLabel();
				labelArray[row][col].setIcon(new ImageIcon(imageArray[row][col]));
				this.add(labelArray[row][col]);
			}
		}
		
		updateDisplay();
	}
	
	private void setupListeners()
	{
		
	}
	
	private void updateDisplay()
	{
		for (int row = 0; row < imageArray.length; row++)
		{
			for (int col = 0; col < imageArray[row].length; col++)
			{
				Color white = whiteTile;
				Color black = blackTile;
				
				if (selected[0] == row && selected[1] == col)
				{
					white = whiteSelected;
					black = blackSelected;
				}
				
				Color background = black;
				
				if (row % 2 == 0)
				{
					if (col % 2 == 0)
					{
						background = white;
					}
				}
				else
				{
					if (col % 2 == 1)
					{
						background = white;
					}
				}
				
				Graphics2D graphics = imageArray[row][col].createGraphics();
				graphics.setColor(background);
				graphics.fill(new Rectangle(0, 0, 45, 45));
				
				String fileName = app.getImageFor(row, col);
				if (!fileName.equals(""))
				{
					String path = "./src/games/view/images/" + fileName + ".png";
					try
					{
						BufferedImage image = ImageIO.read(new File(path));
						graphics.drawImage(image, 0, 0, null);
						repaint();
					}
					catch (IOException error)
					{
						app.handleError(error);
						break;
					}
				}
				
				repaint();
					
				
			}
		}
	}
	
	public void select(int row, int col)
	{
		if (selected[0] == -1 || selected[1] == -1)
		{
			selected[0] = row;
			selected[1] = col;
		}
		else if (selected[0] == row && selected[1] == col)
		{
			selected[0] = -1;
			selected[1] = -1;
		}
		else
		{
			app.playerMove(selected[0], selected[1], row, col);
			
			selected[0] = -1;
			selected[1] = -1;
		}
		
		updateDisplay();
	}
}
