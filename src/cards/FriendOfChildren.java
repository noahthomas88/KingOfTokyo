package cards;

public class FriendOfChildren extends Card {
	
	public FriendOfChildren() {
		this.name = "Friend of Children";
		this.cost = 3;
		this.description = "Whenever you gain energy gain 1 extra energy";
		this.logic = new FriendOfChildrenLogic();
	}

}
