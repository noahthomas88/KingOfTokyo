package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Dice;

public class DiceTest {

	@Test
	public void diceConstructorTest() {
		Dice testDice = new Dice();
		assertTrue(testDice.isEmpty());
	}

}
