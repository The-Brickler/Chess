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
		this.setSize(1200, 900);
		this.setTitle("Chess");
		this.setResizable(false);
		this.setVisible(true);
	}
}
