package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Dice;
import game.Gameplay;
import game.Player;
import main.GUI;
import main.Messages;

public class AcidAttackLogicTest {

	@Test
	public void addsOneAttackTest() {
		Player player = new Player("test");
		Player attackedPlayer = new Player("test");
		attackedPlayer.health = 2;
		Card card = new Card();
		card.name = "Acid Attack";
		player.addToHand(card);
		
		ArrayList<Dice> dice = new ArrayList<Dice>();
		Messages message = EasyMock.niceMock(Messages.class);
		GUI gui = EasyMock.niceMock(GUI.class);
		Board board = new Board(2, null, gui);
		DeckConstructor dc = new DeckConstructor(null);
		Gameplay gameplay = new Gameplay(gui,player,board,null,null);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		players.add(attackedPlayer);
		board.playerList  = players;
		board.cityPlayer = player;
		
		gameplay.diceRolled(dice, message);
		
		assertEquals(1,attackedPlayer.health);
	
		
	}

}
