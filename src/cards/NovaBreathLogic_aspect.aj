package cards;

import game.Board;
import game.Player;

public aspect NovaBreathLogic_aspect {
	
	pointcut nova(Player attacker, int amount, Board board) : execution(void Board.doAttack(Player, int)) && args(attacker, amount) && target(board);
	
	void around(Player attacker, int amount, Board board) : nova(attacker, amount, board) {
		proceed(attacker, amount ,board);
	}
	
	after(Player attacker, int amount, Board board) : nova(attacker, amount, board) {
		if (!attacker.haveCard("Nova Breath")) {
			return;
		}
		if(board.cityPlayer.equals(attacker)) {
			board.bayPlayer.addHealth(amount);
		}else if (attacker.equals(board.bayPlayer)) {
			board.cityPlayer.addHealth(amount);
		}else {
			for(Player p : board.playerList){
				if(!p.equals(board.cityPlayer) && !p.equals(board.bayPlayer)){
					p.addHealth(amount);
				}
			}
		}
	}
	

}
