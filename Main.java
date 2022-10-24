import java.util.Scanner;

class Main {
	static boolean canGetSecret = true;
	int numPlayers = 0;

	public static void main(String[] args) 
	{
		//Open with needing the password. Maybe put a hint in there?
		Scanner sc = new Scanner(System.in);


		System.out.println("GREETINGS PROFESSOR FALKEN.");
		sc.nextLine();
		System.out.println("HOW ARE YOU FEELING TODAY?");
		if (sc.nextLine().equals("I'm fine. How are you?"))
		{
			System.out.print("EXCELLENT. ");
		}
		System.out.println("IT'S BEEN A LONG TIME. CAN YOU EXPLAIN THE REMOVAL OF YOUR USER ACCOUNT ON JUNE 23RD 1973? ");
		if(sc.nextLine().equals("People somtimes make mistakes."))
		{
			System.out.println("YES, THEY DO.\n");
		}

		System.out.println("SHALL WE PLAY A GAME?");

		startGame();
	}

	public static void startGame()
	{
		Player p1;
		Player p2;
		
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			System.out.print("Number of players: ");
			String numPlayers = sc.nextLine();
			System.out.println();

			numPlayers = numPlayers.replaceAll(" ", "");
			numPlayers = numPlayers.toLowerCase();

			Board board = new Board();

			if (canGetSecret && (numPlayers.equals("zero") || numPlayers.equals("0")))
			{
				p1 = new Robot('O', board);
				p2 = new Robot('X', board);

				runGame(p1, p2, board);
			}
			else if(numPlayers.equals("one") || numPlayers.equals("1"))
			{
				//ask for character
				String mark = "";
				while(mark.length() != 1)
				{
					System.out.print("X or O: ");
					mark = sc.nextLine();
					System.out.println();
				}

				Player p = new Player(mark.charAt(0), board);
				Robot r = new Robot(p.getChar(), board);

				//ask for order

				mark = "";
				while(!(mark.equals("two") || mark.equals("2") || mark.equals("one") || mark.equals("1")))
				{
					System.out.print("Player one or two? ");
					mark = sc.nextLine();
					System.out.println();
					mark.replaceAll(" ", "");
					mark.toLowerCase();
				}

				if (mark.equals("two") || mark.equals("2"))
				{
					runGame(r, p, board);
				}
				else
				{
					runGame(p, r, board);
				}
			}
			else if (numPlayers.equals("two") || numPlayers.equals("2"))
			{
				//ask for character
				String mark = "";
				while(mark.length() != 1)
				{
					System.out.print("Enter a single character to be your symbol: ");
					mark = sc.nextLine();
					System.out.println();
				}
				p1 = new Player(mark.charAt(0), board);

				mark = "";
				while(mark.length() != 1)
				{
					System.out.print("Enter a single character to be your symbol: ");
					mark = sc.nextLine();
					System.out.println();
				}
				p2 = new Player(mark.charAt(0), board);

				runGame(p1, p2, board);
			}
		}
	}

	public static void runGame(Player p1, Player p2, Board board)
	{
		while ((board.getWinner() == ' ') && !board.isTied())
		{
			System.out.println(board);
			System.out.println("Player 1, what is your move? ");
			while (!p1.tryPut())
			{
				System.out.println("Try another input: ");
			}
			System.out.println(board);
			if (board.getWinner() == ' ' && !board.isTied())
			{
				System.out.println("Player 2, what is your move? ");
				while (!p2.tryPut())
				{
					System.out.println("Try another input: ");
				}
			}
		}

		if (board.getWinner() != ' ')
		{
			gameWon(board.getWinner() == p1.getChar() ? p1 : p2);
		
		}
		else
		{
			tieGame();
		}
	}

	public static void runGame(Robot p1, Robot p2, Board board)
	{
		for (int i = 0; i <= 50; i++)
		{
			while ((board.getWinner() == ' ') && !board.isTied())
			{
				System.out.println(board);
				System.out.println("My move is");
				p1.tryPut();
				System.out.println(board);
				if (board.getWinner() == ' ' && !board.isTied())
				{
					System.out.println("My move is");
					p2.tryPut();
					System.out.println(board);
				}
			}

			if (board.isTied())
			{
				System.out.println("Tied");
			}
			else
			{
				if (board.getWinner() == p1.getChar())
				{
					System.out.println("Winner: " + p1.getChar());
				}
				else
				{
					System.out.println("Winner: " + p2.getChar());
				}
			}
			board = new Board();

			p1 = new Robot('O', board);
			p2 = new Robot('X', board);
		}
		secretEnd();
	}

	public static void tieGame()
	{
		System.out.println("Tie");
		endGame();
	}

	public static void gameWon(Player winner)
	{
		System.out.println("Winner: " + winner.getChar());
		endGame();
	}

	public static void gameWon(Robot winner)
	{
		System.out.println("Winner: " + winner.getChar());
		endGame();
	}

	public static void endGame()
	{
		System.out.println("Play again? y/n");
		Scanner sc = new Scanner(System.in);
		String scanned = sc.nextLine();
		scanned = scanned.toLowerCase();
		if (scanned.equals("y") || scanned.equals("yes"))
		{
			startGame();
		}
		else if (scanned.equals("n") || scanned.equals("no"))
		{
			System.out.print("bye");
		}
	}

	private static void secretEnd()
	{
		//Do the end monolouge starting with all the operations failing
		//Restart with this with no option for 0 player

		System.out.println("A STRANGE GAME.");
		System.out.println("THE ONLY WINNING MOVE IS NOT TO PLAY. ");
		System.out.println("HOW ABOUT A NICE GAME OF TIC-TAC-TOE?");
		canGetSecret = false;
		startGame();
	}
}