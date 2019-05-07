package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DeckConstructor {
	
	ArrayList<Card> deck;
	public Card[] visibleCard;
	ArrayList<Card> discard;

	public DeckConstructor() {
		this.deck = new ArrayList<>();
		this.visibleCard = new Card[3];
		for (int index = 0; index < 3; index++) {
			this.visibleCard[index] = new Card();
		}
		this.discard = new ArrayList<>();
	}
	
	public void createDeck(){
		CardLoader loader = new CardLoader();
		HashMap<String, Card> cardmap = loader.loadCards("cards.txt", null);
		for(String name : cardmap.keySet()) {
			deck.add(cardmap.get(name));
		}
		this.shuffle();
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
		while(index != 3){
			if(this.visibleCard[index].name.equals("default")){
				if(this.deck.isEmpty()) {
					addDiscardtoDeck();
					shuffle();
				}
				this.visibleCard[index] = this.deck.remove(0);
			}
			index++;
		}
		return;
	}
	
	public void addToDiscard(Card card){
		this.discard.add(card);
	}
	
	public void swipe() {
		for(int index = 0; index < 3; index++){
			this.discard.add(this.visibleCard[index]);
			this.visibleCard[index] = new Card();
		}
		reveal();
	}
	
	public void buy(int index){
		this.discard.add(this.visibleCard[index]);
		this.visibleCard[index] = new Card();
		reveal();
	}
	
	public Card[] getVisibleCard() {
		return this.visibleCard;
	}

}
