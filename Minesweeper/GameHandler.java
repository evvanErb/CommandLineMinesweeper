import java.util.Scanner;

/*runs and handles the game*/

class GameHandler{
    Board gameBoard;
    //game loop
    public GameHandler(){
        boolean running;
		do{
			DisplayIntro();
			GameManager();
			running = KeepPlaying();
        }while(running);
    }

    //print intro message
    private void DisplayIntro(){
        System.out.println("\nWelcome to Minesweeper!\n");
	}

    //manages each individual game
	private void GameManager(){
        this.gameBoard = new Board();
        this.gameBoard.Display();
		boolean victory = false;
		boolean defeat = false;
		do{
			//get guess changes defeat if mine chosen if not reveals chosen cell
			defeat = this.gameBoard.GetGuess();
            this.gameBoard.Display();
			if(defeat){
				System.out.println("\nYOU LOST\n");
			}
			//if not display adjusted board
			else{
				//check if only mines left covered
				victory = this.gameBoard.CheckVictory();
				if(victory){
					System.out.println("\nYOU WON\n");
				}
			}
		}while(!victory && !defeat);
	}

    //asks user if they want to keep playing
	private boolean KeepPlaying(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Would you like to play again?\n>>> ");
        String choice = scan.nextLine();
        if((choice.toLowerCase().equals("y")) || (choice.toLowerCase().equals("yes"))){
            return(true);
        }
		return(false);
	}
}