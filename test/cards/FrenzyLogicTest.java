package cards;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;
import main.GUI;

public class FrenzyLogicTest {

	@Test
	public void GetAnotherTurnTest() {
		FrenzyLogic logic = new FrenzyLogic();
		Player player = new Player("test");
		GUI gui = EasyMock.strictMock(GUI.class);
		
		gui.frenzy();
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
	}

}
