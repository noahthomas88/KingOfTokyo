package cards;

import org.easymock.EasyMock;
import org.junit.Test;

import game.Player;
import main.GUI;

public class StretchyLogicTest {

	@Test
	public void testNotResolve() {
		StretchyLogic logic = new StretchyLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(null, null, gui);
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testNotCurrentPlayer() {
		StretchyLogic logic = new StretchyLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testWrongDie() {
		StretchyLogic logic = new StretchyLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		EasyMock.expect(gui.chooseDice()).andReturn("0");
		EasyMock.expect(gui.checkDieNumber("0")).andReturn(false);
		
		EasyMock.replay(gui);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui);
	}
	
	@Test
	public void testWrongNumber() {
		StretchyLogic logic = new StretchyLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		EasyMock.expect(gui.chooseDice()).andReturn("1");
		EasyMock.expect(gui.checkDieNumber("1")).andReturn(true);
		EasyMock.expect(gui.inputChange()).andReturn("0");
		EasyMock.expect(gui.checkNumber("0")).andReturn(false);
		
		EasyMock.replay(gui, player);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui, player);
	}
	
	@Test
	public void testNotEnoughEnergy() {
		StretchyLogic logic = new StretchyLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		player.energy = 1;
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		EasyMock.expect(gui.chooseDice()).andReturn("1");
		EasyMock.expect(gui.checkDieNumber("1")).andReturn(true);
		EasyMock.expect(gui.inputChange()).andReturn("2");
		EasyMock.expect(gui.checkNumber("2")).andReturn(true);
		
		EasyMock.replay(gui, player);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui, player);
	}
	
	@Test
	public void testEnoughEnergy() {
		StretchyLogic logic = new StretchyLogic();
		GUI gui = EasyMock.strictMock(GUI.class);
		Player player = EasyMock.strictMock(Player.class);
		player.energy = 2;
		
		EasyMock.expect(gui.checkBeforeResolve()).andReturn(true);
		EasyMock.expect(gui.isCurrentPlayer(player)).andReturn(true);
		EasyMock.expect(gui.chooseDice()).andReturn("1");
		EasyMock.expect(gui.checkDieNumber("1")).andReturn(true);
		EasyMock.expect(gui.inputChange()).andReturn("2");
		EasyMock.expect(gui.checkNumber("2")).andReturn(true);
		player.addEnergy(-2);
		gui.updateDie("1", "2");
		
		EasyMock.replay(gui, player);
		
		logic.use(player, null, gui);
		
		EasyMock.verify(gui, player);
	}

}
