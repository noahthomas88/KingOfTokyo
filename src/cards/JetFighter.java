package cards;

public class JetFighter extends Card{

	public JetFighter() {
		this.name = "Jet Fighter";
		this.cost = 5;
		this.description = "Gain 5 Victory Points and lose 4 health";
		this.type = "Discard";
		this.logic = new JetFighterLogic();
	}
}