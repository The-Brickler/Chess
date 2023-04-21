package games.model.chess;

public class ChessAI
{
	public String getNextMove()
	{
		String fen = createFEN(true);
		return accessPage("https://www.chessdb.cn/cdb.php?action=querybest&board=" + fen)
	}
	
	private String createFEN(boolean isWebReady)
	{
		String space = " ";
		if (isWebReady)
		{
			space = "%20";
		}
		return space;
	}
}
