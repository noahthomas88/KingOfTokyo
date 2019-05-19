package cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Board;
import game.Dice;
import game.Player;
import main.GUI;

public class NovaBreathLogicTest {

	@Test
	public void testCityAttack() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1, p2, p3, p4;
		playerList.add(p1 = new Player("test1"));
		playerList.add(p2 = new Player("test2"));
		playerList.add(p3 = new Player("test3"));
		playerList.add(p4 = new Player("test4"));
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board b = new Board(4, null, mockedUI);
		b.playerList = playerList;
		b.cityPlayer = p1;
		b.bayPlayer = p2;
		
		Card card = new Card();
		card.name = "Nova Breath";
		p1.addToHand(card);
		
		p1.health = 10;
		p2.health = 10;
		p3.health = 10;
		p4.health = 10;
		
		b.doAttack(p1, -3);
		assertEquals(p2.health, 7);
		assertEquals(p3.health, 7);
		assertEquals(p4.health, 7);
	}
	
	@Test
	public void testBayAttack() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1, p2, p3, p4;
		playerList.add(p1 = new Player("test1"));
		playerList.add(p2 = new Player("test2"));
		playerList.add(p3 = new Player("test3"));
		playerList.add(p4 = new Player("test4"));
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board b = new Board(4, null, mockedUI);
		b.playerList = playerList;
		b.cityPlayer = p1;
		b.bayPlayer = p2;
		
		Card card = new Card();
		card.name = "Nova Breath";
		p2.addToHand(card);
		
		p1.health = 10;
		p2.health = 10;
		p3.health = 10;
		p4.health = 10;
		
		b.doAttack(p2, -3);
		assertEquals(p1.health, 7);
		assertEquals(p3.health, 7);
		assertEquals(p4.health, 7);
	}
	
	@Test
	public void testOutAttack() {
		ArrayList<Player> playerList = new ArrayList<Player>();
		Player p1, p2, p3, p4;
		playerList.add(p1 = new Player("test1"));
		playerList.add(p2 = new Player("test2"));
		playerList.add(p3 = new Player("test3"));
		playerList.add(p4 = new Player("test4"));
		GUI mockedUI = EasyMock.createNiceMock(GUI.class);
		Board b = new Board(4, null, mockedUI);
		b.playerList = playerList;
		b.cityPlayer = p1;
		b.bayPlayer = p2;
		
		Card card = new Card();
		card.name = "Nova Breath";
		p3.addToHand(card);
		
		p1.health = 10;
		p2.health = 10;
		p3.health = 10;
		p4.health = 10;
		
		b.doAttack(p3, -3);
		assertEquals(p2.health, 7);
		assertEquals(p1.health, 7);
		assertEquals(p4.health, 7);
	}

}
