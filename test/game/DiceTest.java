package game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Dice;

public class DiceTest {
	@Test
	public void diceConstructorTest() {
		Player p = EasyMock.niceMock(Player.class);
		
		EasyMock.expect(p.getNumberOfRolls()).andReturn(3);
		
		EasyMock.replay(p);
		
		Dice testDice = new Dice(p);
		
		assertTrue(testDice != null);
	}

	@Test
	public void rollAppropriateValueTest() {
		ArrayList<Integer> listOfRolls = get30RollResults();
		for (int roll : listOfRolls) {
			assertTrue(roll < 7);
			assertTrue(roll > 0);
		}
	}

	@Test
	public void rollDifferentValuesTest() {
		boolean nonDuplicateFlag = false;
		ArrayList<Integer> listOfRolls = get30RollResults();
		for (int roll : listOfRolls) {
			if (roll != listOfRolls.get(0)) {
				nonDuplicateFlag = true;
			}
		}
		
		assertTrue(nonDuplicateFlag);
	}

	public ArrayList<Integer> get30RollResults() {
		ArrayList<Integer> rollResults = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			Player p = EasyMock.niceMock(Player.class);
			
			EasyMock.expect(p.getNumberOfRolls()).andReturn(3);
			EasyMock.replay(p);
			
			Dice testDice = new Dice(p);
			for (int j = 0; j < 3; j++) {
				rollResults.add(testDice.roll());
			}
		}
		return rollResults;
	}

	@Test(expected = UnsupportedOperationException.class)
	public void isResolvedTest() {
		Player p = EasyMock.niceMock(Player.class);
		
		EasyMock.expect(p.getNumberOfRolls()).andReturn(3);
		EasyMock.replay(p);
		
		Dice testDice = new Dice(p);
		testDice.isResolved = true;
		testDice.roll();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void defaultThreeRollMaximumTest() {
		Player p = EasyMock.niceMock(Player.class);

		EasyMock.expect(p.getNumberOfRolls()).andReturn(3);
		EasyMock.replay(p);
		
		Dice testDice = new Dice(p);
		testDice.roll();
		testDice.roll();
		testDice.roll();
		testDice.roll();
	}

}
