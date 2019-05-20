package cards;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;
import main.GUI;

public class HerdCullerLogicTest {

	@Test
	public void testNotResolve() {
		HerdCullerLogic logic = new HerdCullerLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(null, null, gui);
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testNotCurrentPlayer() {
		HerdCullerLogic logic = new HerdCullerLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = new Player("test");
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testUsedBefore() {
		HerdCullerLogic logic = new HerdCullerLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = new Player("test");
		player.herdCuller = false;
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testUseWrongInput() {
		HerdCullerLogic logic = new HerdCullerLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = new Player("test");
		player.herdCuller = true;
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		EasyMock.expect(gui.chooseDice()).andReturn("1");
		EasyMock.expect(gui.checkDieNumber("1")).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
		assertTrue(player.herdCuller);
	}
	
	@Test
	public void testUse() {
		HerdCullerLogic logic = new HerdCullerLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = new Player("test");
		player.herdCuller = true;
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		EasyMock.expect(gui.chooseDice()).andReturn("1");
		EasyMock.expect(gui.checkDieNumber("1")).andReturn(true);
		gui.updateDie("1", "1");
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
		assertFalse(player.herdCuller);
	}

}
