package cards;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;
import main.GUI;

public class PlotTwistLogicTest {

	@Test
	public void testNotResolve() {
		PlotTwistLogic logic = new PlotTwistLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(null, null, gui);	
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testNotCurrentPlayer() {
		PlotTwistLogic logic = new PlotTwistLogic();
		Player player = EasyMock.strictMock(Player.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);	
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testCanUse() {
		PlotTwistLogic logic = new PlotTwistLogic();
		Player player = EasyMock.strictMock(Player.class);
		GUI gui = EasyMock.strictMock(GUI.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		gui.usePlotTwist();
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
	}

}
