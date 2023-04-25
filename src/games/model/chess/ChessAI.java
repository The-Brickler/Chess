package games.model.chess;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

import java.io.IOException;

import games.controller.Controller;

public class ChessAI
{
	
	private Controller app;
	
	public ChessAI(Controller app)
	{
		this.app = app;
	}
	
	public String getNextMove()
	{
		String fen = createFEN(true);
		return accessPage("https://www.chessdb.cn/cdb.php?action=querybest&board=" + fen);
	}
	
	private String createFEN(boolean isWebReady)
	{
		String space = " ";
		if (isWebReady)
		{
			space = "%20";
		}
		String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR" + space + "b" + space + "-" + space + "-" + space + "0" + space + "0";
		return fen;
	}
	
	private String accessPage(String url)
	{
		String output = "";
		try
		{
			Document page = Jsoup.connect(url).get();
			String text = page.text();
			text = text.substring(5);
			output = text;
		}
		catch (IOException exception)
		{
			app.handleError(exception);
		}
		return output;
	}
}
