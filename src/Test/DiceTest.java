package Test;
import static org.junit.Assert.*;
import org.junit.Test;

import Game.Dice;

public class DiceTest {

	@Test
	public void diceConstructorTest() {
		Dice testDice = new Dice();
		assertTrue(testDice.isEmpty());
	}
	
	@Test
	public void rollTest() {
		Dice testDice = new Dice();
		int numberRolled = testDice.roll();
		assertTrue(numberRolled < 7 );
		assertTrue(numberRolled > 1 );
	}

}
