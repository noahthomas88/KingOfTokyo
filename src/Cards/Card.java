package Cards;

public class Card {

	public String type;
	public int cost; 
	public String description;
	
	public Card() {
		this.type = "base";
		this.cost = 0;
		this.description = "This is the base card, override me";
	}

}
