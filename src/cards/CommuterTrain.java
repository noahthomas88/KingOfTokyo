package cards;

public class CommuterTrain extends Card {

	public CommuterTrain() {
		this.name = "Commuter Train";
		this.cost = 4;
		this.description = "Gain 2 Victory Points";
		this.type = "Discard";
		this.logic = new CommuterTrainLogic();
	}

}
