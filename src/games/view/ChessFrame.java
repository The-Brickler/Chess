package games.view;

import games.controller.Controller;
import javax.swing.JFrame;

public class ChessFrame extends JFrame
{
	private Controller app;
	private ChessPanel panel;
	
	public ChessFrame(Controller app)
	{
		super();
		this.app = app;
		this.panel = new ChessPanel(this.app);
		
		setupFrame();
	}
	
	private void setupFrame()
	{
		this.setContentPane(panel);;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setTitle("Chess");
		this.setResizable(false);
		this.setVisible(true);
	}

	public void showPromoteDialog(int row, int col)
	{
		panel.showPromoteDialog(row, col);
		
	}

	public void madeMove()
	{
		panel.madeMove();
		
	}

	public void gameEnd()
	{
		panel.gameEnd();
		
	}
}
