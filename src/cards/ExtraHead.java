package cards;

public class ExtraHead extends Card {

	public ExtraHead() {
		this.name = "Extra Head";
		this.cost = 7;
		this.description = "Gain 1 extra die";
		this.type = "Keep";
		this.logic = new ExtraHeadLogic();
	}

}
