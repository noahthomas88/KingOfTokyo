package cards;

public class RootingForTheUnderdog extends Card{
	
	public RootingForTheUnderdog(){
		this.name = "Rooting for the Underdog";
		this.cost = 3;
		this.description = "At the end of turn, if you have the fewest victory points, gain a victory point";
		this.type = "Keep";
		this.logic = new RootingForTheUnderdogLogic();
	}

}
