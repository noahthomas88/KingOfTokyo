package cards;

import java.util.ArrayList;
import java.util.List;

public class DeckConstructor {
	
	List<Card> deck;

	public DeckConstructor() {
		this.deck = new ArrayList<>();
	}
	
	public void createDeck(){
		deck.add(new ApartmentBuilding());
		deck.add(new ExtraHead());
		deck.add(new GasRefinery());
		deck.add(new GiantBrain());
		deck.add(new HighAltitudeBombing());
		deck.add(new JetFighter());
		deck.add(new Tank());
		deck.add(new Ultravore());
	}
}
