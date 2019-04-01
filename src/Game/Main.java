package Game;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		
		GUI mygui = new GUI();
		
		int numOfPlayers;
		Board myBoard = null;

		try{
			numOfPlayers = mygui.getNumPlayers();
			myBoard = new Board(numOfPlayers);
		}catch(IllegalArgumentException e){
			JOptionPane.showMessageDialog(null, "Number of players have to be between 2 to 6");
			return;
		}		
		
		myBoard.constructPlayers(mygui.getNames(numOfPlayers));
		Gameplay game = new Gameplay(myBoard);
		mygui.displayBoard(myBoard, numOfPlayers);
	}

}
