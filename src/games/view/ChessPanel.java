package games.view;

import games.controller.Controller;

import javax.swing.JPanel;

public class ChessPanel extends JPanel
{
	
	private Controller app;
	
	private GamePanel game;
	
	public ChessPanel(Controller app)
	{
		super();
		this.app = app;
		
		game = new GamePanel(this.app);
		
		setupPanel();
		setupListeners();
		setupLayout();
	}
	
	private void setupPanel()
	{
		this.add(game);
	}
	
	private void setupListeners()
	{

	}
	
	private void setupLayout()
	{
		
	}
}
