package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckConstructor {
	
	List<Card> deck;

	public DeckConstructor() {
		this.deck = new ArrayList<>();
	}
	
	public void createDeck(){
		deck.add(new ApartmentBuilding());
		deck.add(new Energize());
		deck.add(new ExtraHead());
		deck.add(new GasRefinery());
		deck.add(new GiantBrain());
		deck.add(new HighAltitudeBombing());
		deck.add(new JetFighter());
		deck.add(new NationalGuard());
		deck.add(new NuclearPowerPlant());
		deck.add(new Tank());
		deck.add(new Ultravore());
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
}
