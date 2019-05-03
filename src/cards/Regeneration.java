package cards;

public class Regeneration extends Card {

	public Regeneration() {
		this.name = "Regeneration";
		this.cost = 4;
		this.description = "Whenever you gain health gain 1 extra health";
		this.type = "Keep";
		this.logic = new RegenerationLogic();
	}
	
}
