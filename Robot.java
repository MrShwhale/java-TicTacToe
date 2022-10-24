import java.util.ArrayList;
import java.util.Random;
//Fix bug that clears board every time the bot moves
public class Robot extends Player
{
	Random rand = new Random();
	char oChar;

	public Robot(char oChar, Board board)
	{
		super(oChar == 'X' || oChar == 'x' ? 'O' : 'X', board);
		this.oChar = oChar;
	}

	private int minimax(Board eBoard, int alpha, int beta, boolean max)
	{
		if (eBoard.isTied() || eBoard.getWinner() != ' ')
		{
			if (eBoard.getWinner() == pChar)
			{
				return 10;
			}
			else if (eBoard.getWinner() == oChar)
			{
				return -10;
			}
			
			if (eBoard.isTied())
			{
				return 0;
			}
		}

		if (max)
		{
			int maxEval = Integer.MIN_VALUE;
			for (int[] i : eBoard.getMovesLeft())
			{
				Board holder = new Board(eBoard.getBoard().clone());
				holder.put(i[0], i[1], pChar);

				int eval = minimax(holder, alpha, beta, false);
				maxEval = Math.max(maxEval, eval);
				holder.remove(i);
				alpha = Math.max(alpha, eval);
				if (beta <= alpha)
				{
					break;
				}
			} 
			return maxEval;
		}
		else
		{
			int minEval = Integer.MAX_VALUE;
			for (int[] i : eBoard.getMovesLeft())
			{
				Board holder = new Board(eBoard.getBoard().clone());
				holder.put(i[0], i[1], oChar);
				
				int eval = minimax(holder, alpha, beta, true);
				minEval = Math.min(minEval, eval);
				holder.remove(i);
				beta = Math.min(beta, eval);
				if (beta <= alpha)
				{
					break;
				}
			} 
			return minEval;
		}
	}

	private int[] getBestMove()
	{
		//For every move
		//Get the minimax of it
		//take the highest one of these and do that move
		//If there is a tie, flip a coin
		ArrayList<Integer> bestMoves = new ArrayList<Integer>();
		int bestMoveVal = Integer.MIN_VALUE;
		for (int i = 0; i < board.getMovesLeft().length; i++)
		{
			Board holder = new Board (board.getBoard().clone());

			holder.put(board.getMovesLeft()[i][0], board.getMovesLeft()[i][1], pChar);
			int eval = minimax(holder, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
			holder.remove(board.getMovesLeft()[i]);

			if (eval == bestMoveVal)
			{
				//Add it to an array
				bestMoves.add(i);
			}
			else if (eval > bestMoveVal)
			{
				bestMoveVal = eval;
				bestMoves = new ArrayList<Integer>();
				bestMoves.add(i);
				System.out.println (i);
			}
		} 
		Random rand = new Random();
		return board.getMovesLeft()[bestMoves.get(rand.nextInt(bestMoves.size()))];
	}

	
	@Override
	public boolean tryPut()
	{
		int[] move = getBestMove();
		board.put(move[0], move[1], pChar);
		return true;
	}
}	