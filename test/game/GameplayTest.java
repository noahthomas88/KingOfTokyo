package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game.Board;
import game.Gameplay;

public class GameplayTest {

	@Test
	public void PlayerSelectionTest() {
		Board myboard = new Board(2);
		ArrayList<String> fakenames = new ArrayList<String>();
		fakenames.add("player1");
		fakenames.add("player2");
		myboard.constructPlayers(fakenames);
		Gameplay gameplay = new Gameplay(myboard);
		assertTrue(gameplay.currentplayer == null);
		gameplay.selectFirstPlayer();
		assertTrue(gameplay.currentplayer!=null);
	}

}
