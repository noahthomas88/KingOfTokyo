package cards;

public class VastStorm extends Card {
	
	public VastStorm(){
		this.name = "VastStorm";
		this.cost = 6;
		this.description = "Gain 2 victory points and all other monsters "
				+ "lose 1 energy for every 2 energy they have";
		this.type = "discard";
		this.logic = new VastStormLogic();
	}
}
