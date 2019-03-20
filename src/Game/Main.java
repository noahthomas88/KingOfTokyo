package Game;

public class Main {

	public static void main(String[] args) {
		GUI mygui = new GUI();
		Board myboard = new Board(mygui.getNumPlayers());
		mygui.getNames(myboard.player);
		mygui.displayBoard(myboard);
	}

}
