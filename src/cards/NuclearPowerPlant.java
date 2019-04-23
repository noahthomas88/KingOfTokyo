package cards;

public class NuclearPowerPlant extends Card {

	public NuclearPowerPlant() {
		this.name = "Nuclear Power Plant";
		this.cost = 6;
		this.description = "Gain 2 Victory Points and gain 3 Health";
		this.type = "Discard";
		this.logic = new NuclearPowerPlantLogic();
	}

}
