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

import game.Dice;
import game.Gameplay;

public class DicePanel extends JPanel {

	Messages messages;
	Gameplay game;
	JButton dieButton;
	
	public DicePanel(Messages messages, Gameplay game) {
		this.messages = messages;
		this.game = game;
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		this.setPreferredSize(new Dimension(300,300));
		dieButton = new JButton(messages.getString("GUI.39")); //$NON-NLS-1$
		dieButton.addActionListener(new RollListener());
		this.add(dieButton);
	}
	
	class RollListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			displayDice();
		}
	}
	
	public void displayDice() {
		JPanel panel = new JPanel();
		ArrayList<Dice> dicelist = new ArrayList<Dice>();
		ArrayList<JButton> diebuttons = new ArrayList<JButton>();
		int numberOfDiceRolls = game.currentplayer.getNumberOfRolls();
		int numberOfDice = game.currentplayer.getNumberOfDie();
		for (int i = 0; i < numberOfDice; i++) {
			Dice dice = new Dice(game.currentplayer);
			dice.roll();
			dicelist.add(dice);
			JButton diebutton = new JButton(numberToString(dicelist.get(i).numberRolled));
			diebuttons.add(diebutton);
			diebutton.addActionListener(new DieListener(diebutton));
			panel.add(diebutton);
		}
		JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.22"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
		for (int i = 0; i < numberOfDice; i++) {
			JButton button = diebuttons.get(i);
			Dice die = dicelist.get(i);
			if (button.getBackground() == Color.RED) {
				die.roll();
				button.setText(numberToString(die.numberRolled));
			}
		}
		JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.23"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
		for (int i = 0; i < numberOfDice; i++) {
			JButton button = diebuttons.get(i);
			Dice die = dicelist.get(i);
			if (button.getBackground() == Color.RED) {
				die.roll();
				button.setText(numberToString(die.numberRolled));
			}
		}
		if (numberOfDiceRolls == 3) {
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				button.setEnabled(false);
				button.setBackground(Color.WHITE);
			}
			JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.24"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
		} else {
			JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.25"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				Dice die = dicelist.get(i);
				if (button.getBackground() == Color.RED) {
					die.roll();
					button.setText(numberToString(die.numberRolled));
				}
				button.setEnabled(false);
				button.setBackground(Color.WHITE);
			}
			JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.26"), JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
		}
		game.diceRolled(dicelist, messages);
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
	
	public class DieListener implements ActionListener {

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

}
