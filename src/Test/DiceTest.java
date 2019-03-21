package Test;

import static org.junit.Assert.*;
import org.junit.Test;

import Game.Dice;

public class DiceTest {

	@Test
	public void diceConstructorTest() {
		Dice testDice = new Dice();
		assertTrue(testDice != null);
	}
	
	@Test
	public void rollAppropriateValueTest() {
		Dice testDice = new Dice();
		for(int i = 0; i< 20; i++){
			int numberRolled = testDice.roll();
			assertTrue(numberRolled < 7 );
			assertTrue(numberRolled > 0 );
		}
	}
	
	@Test
	public void rollDifferentValuesTest() {
		Dice testDice = new Dice();
		boolean nonDuplicateFlag = false;
		int firstRoll = testDice.roll();
		for(int i = 0; i< 20; i++){
			if(firstRoll != testDice.roll()){
				nonDuplicateFlag = true;
			}
		}
		assertTrue(nonDuplicateFlag);
	}


}
