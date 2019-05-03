package cards;

public class ArmorPlating extends Card {

	public ArmorPlating() {
		this.name = "Armor Plating";
		this.cost = 4;
		this.description = "Do not lose health when you lose exactly 1 health";
		this.type = "Keep";
		this.logic = new ArmorPlatingLogic();
	}
}
