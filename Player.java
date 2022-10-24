import java.util.Scanner;

public class Player
{
	//ask about use of protected v getChar
	protected Board board;
	protected char pChar;

	public Player(char charChosen, Board board)
	{
		this.board = board;
		pChar = charChosen;
	}

	public boolean tryPut()
	{
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		int humanY = 1;
		int humanX = 1;

		//Replace all whitespace and commas with spaces
		//try to take the first and second chars as ints
		//if they are in the valid range, then use them
		//if anything fails then return false and print what happened

		String usableInput = input.replaceAll(" ", "").replaceAll(",", "");

		try 
		{
			if (isNumeric(usableInput.substring(0, 1)) && (Integer.parseInt(usableInput.substring(0, 1)) > 0 && Integer.parseInt(usableInput.substring(0, 1)) < 4))
			{
				humanX = Integer.parseInt(usableInput.substring(0, 1));
			}
			else
			{
				System.out.println("That is not a valid number");
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("That is not long enough");
			return false;
		}

		try
		{
			if (isNumeric(usableInput.substring(1, 2)) && (Integer.parseInt(usableInput.substring(1, 2)) > 0 && Integer.parseInt(usableInput.substring(1, 2)) < 4))
			{
				humanY = Integer.parseInt(usableInput.substring(1, 2));
			}
			else
			{
				System.out.println("That is not a valid number");
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println("That is not long enough");
			return false;
		}

		return board.put((4 - humanY)-1, humanX-1, pChar);
	}

	//I found this in my Republic code but might have stolen it from somewhere before then
	public static boolean isNumeric(String string) {

		if(string == null || string.equals("")) {
			return false;
		}

		try 
		{
			Integer.parseInt(string);
			return true;
		} 
		catch (NumberFormatException e) {}
		return false;
	}

	public char getChar()
	{
		return pChar;
	}

	public void setChar(char newChar)
	{
		pChar = newChar;
	}
}