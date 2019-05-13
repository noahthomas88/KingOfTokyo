package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import game.Board;
import game.Player;

public class SpikedTailLogicTest {

	@Test
	public void testHaveCardDamage() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1, p2, p3, p4;
		playerList.add(p1 = new Player("test1"));
		playerList.add(p2 = new Player("test2"));
		playerList.add(p3 = new Player("test3"));

		Board b = new Board(3);
		b.playerList = playerList;
		b.cityPlayer = p1;
		
		Card card = new Card();
		card.name = "Spiked Tail";
		p1.addToHand(card);
		
		p1.health = 10;
		p2.health = 10;
		p3.health = 10;
		
		b.doAttack(p1, -3);
		assertEquals(p2.health, 6);
		assertEquals(p3.health, 6);
	}
	
	@Test
	public void testHaveCardNoDamage() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1, p2, p3, p4;
		playerList.add(p1 = new Player("test1"));
		playerList.add(p2 = new Player("test2"));
		playerList.add(p3 = new Player("test3"));

		Board b = new Board(3);
		b.playerList = playerList;
		b.cityPlayer = p1;
		
		Card card = new Card();
		card.name = "Spiked Tail";
		p1.addToHand(card);
		
		p1.health = 10;
		p2.health = 8;
		p3.health = 8;
		
		b.doAttack(p1, 0);
		assertEquals(p2.health, 8);
		assertEquals(p3.health, 8);
	}

}
