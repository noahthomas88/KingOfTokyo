package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

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
			JOptionPane.showMessageDialog(null, "Exceed Roll Limit", "Warning", 0);
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
				.showInputDialog("Which dice would you like to change (1 to max from left to right and top to bottom)");
		String changeTo = JOptionPane
				.showInputDialog("What would you like to change to (1, 2, 3, 4 for attack, 5 for energy, 6 for heal)");
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
			game.diceRolled(dicelist, messages);
		}

	}
}
