package cards;

import game.Board;
import game.Player;

public aspect SpikedTailLogic_aspect {

pointcut spikeTail(Player attacker, int amount, Board board) : execution(void Board.doAttack(Player, int)) && args(attacker, amount) && target(board);
	
	void around(Player attacker, int amount, Board board) : spikeTail(attacker, amount, board) {
		if (attacker.haveCard("Spiked Tail") && amount < 0) {
			proceed(attacker, amount-1 ,board);
		}else {
			proceed(attacker, amount, board);
		}
		
	}
}
