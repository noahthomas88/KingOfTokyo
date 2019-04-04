package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckConstructor {
	
	List<Card> deck;
	Card[] visibleCard;
	List<Card> discard;

	public DeckConstructor() {
		this.deck = new ArrayList<>();
		this.visibleCard = new Card[3];
		for (int index = 0; index < 3; index++) {
			this.visibleCard[index] = new Card();
		}
		this.discard = new ArrayList<>();
	}
	
	public void createDeck(){
		deck.add(new ApartmentBuilding());
		deck.add(new CommuterTrain());
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
	
	public void addDiscardtoDeck() {
		this.deck.addAll(this.discard);
		this.discard.removeAll(this.discard);
	}
	
	public void reveal() {
		int index = 0;
		while(this.visibleCard[index].name.equals("default")) {
			if(this.deck.isEmpty()) {
				addDiscardtoDeck();
				shuffle();
			}
			this.visibleCard[index] = this.deck.remove(0);
			index = (index + 1) % 3;
		}
		return;
	}

}
