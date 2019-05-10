package cards;

import game.Board;
import game.Player;

public aspect NovaBreathLogic_aspect {
	
	void around(int amount,Board board,  Player player) : calldoAttack(amount, Gameboard,playerThatCalledAttack) {
		proceed(amount, board ,player);
	}
	
	after(int amount, Board board,Player player) : callAddHealth(amount, player) {
		if (player.haveCard("NovaBreath")) {
			if(board.bayPlayer != player && board.cityPlayer != player){
				for(Player p : board.playerList){
					if(board.bayPlayer != player && board.cityPlayer != player){
						p.addHealth(-amount);
					}
				}
			}
		}
	}
	

}
