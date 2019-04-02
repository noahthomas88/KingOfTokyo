package cards;

public class Card {

	public String name;
	public String type;
	public String description;
	public int cost; 
	
	public Card() {
		this.name = "default";
		this.type = "base";
		this.cost = 0;
		this.description = "This is the base card, override me";
	}

}
