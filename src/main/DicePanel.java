package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cards.Card;
import game.Dice;
import game.Gameplay;
import game.Player;

public class DicePanel extends JPanel {

	Messages messages;
	Gameplay game;
	JButton dieButton;
	JButton resolve;
	JTextArea information;
	JPanel dice;
	ArrayList<Dice> dicelist;
	ArrayList<JButton> diebuttons;
	int numberOfDiceRolls;
	int numberOfDice;
	int count;

	public DicePanel(Messages messages, Gameplay game) {
		this.messages = messages;
		this.game = game;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(400, 300));
		dice = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		dice.setPreferredSize(new Dimension(400, 200));

		setUp();
	}

	public void setUp() {
		dicelist = new ArrayList<Dice>();
		diebuttons = new ArrayList<JButton>();
		count = 0;
		this.removeAll();
		this.dice.removeAll();

		dieButton = new JButton(messages.getString("GUI.39"));
		dieButton.addActionListener(new RollListener());
		dieButton.setPreferredSize(new Dimension(100, 100));
		this.add(dieButton);

		this.repaint();
		this.revalidate();
	}

	public void firstRow() {
		numberOfDiceRolls = game.currentplayer.getNumberOfRolls();
		numberOfDice = game.currentplayer.getNumberOfDie();
		for (int i = 0; i < numberOfDice; i++) {
			Dice die = new Dice(game.currentplayer);
			die.roll();
			dicelist.add(die);
			JButton diebutton = new JButton(numberToString(dicelist.get(i).numberRolled));
			diebuttons.add(diebutton);
			diebutton.addActionListener(new DieListener(diebutton));
			diebutton.setPreferredSize(new Dimension(100, 100));
			dice.add(diebutton);
		}

		information = new JTextArea("1/" + numberOfDiceRolls);
		information.setEditable(false);
		information.setLineWrap(true);
		information.setPreferredSize(new Dimension(100, 100));

		resolve = new JButton("resolve");
		resolve.addActionListener(new ResolveListener());
		resolve.setPreferredSize(new Dimension(100, 100));

		this.add(information);
		this.add(resolve);
		this.add(dice);

		for (int i = 0; i < numberOfDice; i++) {
			JButton button = diebuttons.get(i);
			Dice die = dicelist.get(i);
			if (button.getBackground() == Color.RED) {
				die.roll();
				button.setText(numberToString(die.numberRolled));
			}
		}
		this.revalidate();
		count++;
	}

	public void nextRow() {
		if (numberOfDiceRolls > count) {
			count++;
			information.setText(count + "/" + numberOfDiceRolls);
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				Dice die = dicelist.get(i);
				if (button.getBackground() == Color.RED) {
					die.roll();
					button.setText(numberToString(die.numberRolled));
				}
			}
		} else {
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				button.setBackground(null);
			}
			JOptionPane.showMessageDialog(null, messages.getString("GUI.75"), "Warning", 0);
		}
		this.revalidate();
	}

	public String numberToString(int number) {
		if (number < 4) {
			return number + "";
		} else if (number == 4) {
			return messages.getString("GUI.64");
		} else if (number == 5) {
			return messages.getString("GUI.62");
		} else {
			return messages.getString("GUI.65");
		}
	}

	public boolean checkIsResolve() {
		return (numberOfDiceRolls == count) && (count != 0) && resolve.isEnabled();
	}

	public void usePlotTwist() {
		String toChange = JOptionPane
				.showInputDialog(messages.getString("GUI.73"));
		String changeTo = JOptionPane
				.showInputDialog(messages.getString("GUI.74"));
		if (Integer.parseInt(toChange) >= 1 && Integer.parseInt(toChange) <= numberOfDice) {
			if (Integer.parseInt(changeTo) >= 1 && Integer.parseInt(changeTo) <= 6) {
				dicelist.get(Integer.parseInt(toChange) - 1).numberRolled = Integer.parseInt(changeTo);
				diebuttons.get(Integer.parseInt(toChange) - 1).setText(numberToString(Integer.parseInt(changeTo)));
				Player player = game.currentplayer;
				for (int j = 0; j < player.cardsInHand.size(); j++) {
					if (player.cardsInHand.get(j).name.equals("Plot Twist")) {
						player.cardsInHand.remove(j);
						return;
					}
				}
				game.gameUI.update();
			}
		}
	}

	class DieListener implements ActionListener {
		JButton button;

		DieListener(JButton button) {
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (this.button.getBackground() == new JButton().getBackground()) {
				this.button.setBackground(Color.RED);
			} else {
				this.button.setBackground(null);
			}
		}
	}

	class RollListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (count != 0) {
				nextRow();
			} else {
				firstRow();
			}
		}
	}

	class ResolveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for (JButton d : diebuttons) {
				d.setEnabled(false);
			}
			resolve.setEnabled(false);
			dieButton.setEnabled(false);
			for(Player player : game.gameboard.playerList) {
				if(player.haveCard("Psychic Probe") && player != game.currentplayer) {
					JPanel panel = new JPanel();
					int index = 0;
					for(Dice die : dicelist) {
						JButton reroll = new JButton(numberToString(die.numberRolled));
						reroll.addActionListener(new RerollListener(index, player));
						index++;
						panel.add(reroll);
					}
					JOptionPane.showConfirmDialog(null, panel, "The owner of psychic probe may select a die to reroll or ok to skip", JOptionPane.DEFAULT_OPTION);
					
				}
			}
			game.diceRolled(dicelist, messages);
		}

	}
	
	class RerollListener implements ActionListener {
		
		int index;
		Player player;
		
		public RerollListener(int index, Player player) {
			this.index = index;
			this.player = player;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Random random = new Random();
			int result = random.nextInt(6);
			dicelist.get(index).numberRolled = result;
			if(result==6) {
				JOptionPane.showConfirmDialog(null, null, "Psychic Probe lost due to rolling heart", JOptionPane.DEFAULT_OPTION);
				for(Card card : this.player.cardsInHand) {
					if(card.name.equals("Psychic Probe")) {
						this.player.cardsInHand.remove(card);
					}
				}
			}
			JOptionPane.getRootFrame().dispose();
		}
	}

	public void rerow3s() {
		if(numberOfDiceRolls == count && game.currentplayer.haveCard("Background Dweller")) {
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				if (button.getText().equals("3") && button.getBackground() == Color.RED) {
					Dice die = dicelist.get(i);
					die.roll();
					button.setText(numberToString(die.numberRolled));
				}
				button.setBackground(null);
			}
		}
		this.repaint();
	}
}
