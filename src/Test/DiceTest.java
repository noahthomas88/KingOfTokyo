package Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

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
		 ArrayList<Integer> listOfRolls = get30RollResults();
		 for(int roll: listOfRolls){
				assertTrue(roll < 7 );
				assertTrue(roll > 0 ); 
		 }
	}
	
	@Test
	public void rollDifferentValuesTest() {
		boolean nonDuplicateFlag = false;
		 ArrayList<Integer> listOfRolls = get30RollResults();
		 for(int roll: listOfRolls){
				if(roll != listOfRolls.get(0)){
					nonDuplicateFlag = true;
				}
		 }
		
		assertTrue(nonDuplicateFlag);
	}
	
	@Test
	public void getTimesRolledTest() {
		Dice testDice = new Dice();
		assertTrue(testDice.getTimesRolled() == 0);
		testDice.roll();
		assertTrue(testDice.getTimesRolled() == 1);
		testDice.roll();
		assertTrue(testDice.getTimesRolled() == 2);
		testDice.roll();
		assertTrue(testDice.getTimesRolled() == 3);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void isResolvedTest() {
		//TODO will not allow us to roll the die once we have resolved it currently, It will only be resolved after 3 dice rolls
		Dice testDice = new Dice();
		testDice.isResolved = true;
		testDice.roll();
	}

	public ArrayList<Integer> get30RollResults(){
		ArrayList<Integer> rollResults = new ArrayList();
		for(int i = 0; i< 10; i++){
			Dice testDice = new Dice();
			for(int j = 0; i < 3; i++){
				rollResults.add(testDice.roll());
			}
		}
		return rollResults;
	}

}
