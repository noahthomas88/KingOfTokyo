package cards;

import java.util.ArrayList;

import game.Player;

public class VastStormLogic implements CardLogic {

	@Override
	public void use(Player player) {

	}

	@Override
	public void use(Player player, ArrayList<Player> players) {
		
		for(int i = 0; i < players.size(); i++) {
			int energyHalved = players.get(i).energy;
<<<<<<< HEAD
			if(energyHalved % 2 != 0) {
				energyHalved = (energyHalved/2) + 1;
			} else {
=======
			if(energyHalved % 2 == 0) {
>>>>>>> 10f1d3593ffe36214a5d2ba37627b591ac6e208b
				energyHalved = energyHalved/2;
			} else {
				energyHalved = (energyHalved/2) + 1;
			}
			if(!players.get(i).equals(player)) {
				players.get(i).energy = energyHalved;
			} else {
				players.get(i).addEnergy(2);
			}
			
		}

	}

}