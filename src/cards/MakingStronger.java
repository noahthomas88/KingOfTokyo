package cards;

public class MakingStronger extends Card {
	
	public MakingStronger() {
		this.name = "Were only making it stronger";
		this.cost = 3;
		this.description = "When you lose 2 health, you gain 1 energy";
		this.type = "Keep";
		this.logic = new MakingStrongerLogic();
	}

}
