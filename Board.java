import java.util.ArrayList;
import java.util.Collections;
//Fix bug that clears board every time the bot moves
public class Board {
	private char[][] board = new char[3][3];
	private static final int[][] totalMoves = {
			{0, 0}, {1, 0}, {2, 0}, 
			{0, 1}, {1, 1}, {2, 1}, 
			{0, 2}, {1, 2}, {2, 2},
	};

	private ArrayList<int[]> movesLeft = new ArrayList<>();

	public Board()
	{
		for (int i = 0; i < 3; i ++)
		{
			for (int j = 0; j < 3; j++)
			{
				board[i][j] = ' ';
			}
		}
		Collections.addAll(movesLeft, totalMoves);
	}

	public Board(char[][] charArr)
	{
		board = charArr;
		for (int i = 0; i < 3; i ++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == ' ')
				{
					int[] hold = {i, j};
					movesLeft.add(hold);
				}
			}
		}
	}

	public boolean put(int x, int y, char player)
	{
		if (board[x][y] == ' ')
		{
			for (int i = 0; i < movesLeft.size(); i++)
			{
				if (movesLeft.get(i)[0] == x && movesLeft.get(i)[1] == y)
				{
					movesLeft.remove(i);
				}
			}

			board[x][y] = player;

			return true;
		}
		return false;
	}

	public char getWinner()
	{
		if (board[0][2] != ' ' && ((board[0][2] == board[1][1]) && (board[0][2] == board[2][0])))
		{
			return board[0][2];
		}
		else if (board[0][0] != ' ' && ((board[0][0] == board[1][1]) && (board[0][0] == board[2][2])))
		{
			return board[0][0];
		} 

		for (int i = 0; i < 3; i++)
		{
			if (board[i][0] != ' ' && ((board[i][0] == board[i][1]) && (board[i][0] == board[i][2])))
			{
				return board[i][0];
			}
		}

		for (int i = 0; i < 3; i++)
		{
			if (board[0][i] != ' ' && ((board[0][i] == board[1][i]) && (board[0][i] == board[2][i])))
			{
				return board[0][i];
			}
		}

		return ' ';
	}

	public boolean isTied()
	{
		for (int i = 0; i < 3; i ++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (board[i][j] == ' ')
				{
					return false;
				}
			}
		}
		return true;
	}

	public void remove(int[] move)
	{
		board[move[0]] [move[1]] = ' ';
		movesLeft.add(move);
	}

	public int[][] getMovesLeft()
	{
		int[][] ret = new int[movesLeft.size()][2];
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = movesLeft.get(i);
		}
		return ret; 
	}

	public char[][] getBoard()
	{
		return board;
	}

	public String toString()
	{
		String output = "";
		for (int i = 0; i < 3; i ++)
		{
			for (int j = 0; j < 3; j++)
			{
				output += "[" + board[i][j] + "]";
			}
			output += "\n";
		}

		return output;
	}
}
