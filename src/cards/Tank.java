package cards;

public class Tank extends Card {

	public Tank() {
		this.name = "Tank";
		this.cost = 4;
		this.description = "Gain 4 Victory Points and lose 3 Health";
		this.type = "Discard";
		this.logic = new TankLogic();
	}

}
