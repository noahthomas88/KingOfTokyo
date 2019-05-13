package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import game.Gameplay;
import game.Player;

public class TokyoPanel extends JPanel {
	
	Messages messages;
	Gameplay game;
	JTextArea tokyo;
	JTextArea bay;
	JButton cedeTokyoCity;
	JButton cedeTokyoBay;
	
	public TokyoPanel(Messages messages, Gameplay game) {
		this.messages = messages;
		this.game = game;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(300, 300));
		
		build();
	}
	
	private void build() {
		Font tokyofont = new Font("TimesRoman", Font.BOLD, 20);
		
		tokyo = new JTextArea(messages.getString("GUI.37")); 
		tokyo.setFont(tokyofont);
		tokyo.setEditable(false);
		tokyo.setPreferredSize(new Dimension(200, 150));
		
		bay = new JTextArea(messages.getString("GUI.38"));
		bay.setFont(tokyofont);
		bay.setEditable(false);
		bay.setPreferredSize(new Dimension(200, 150));
		
		cedeTokyoCity = new JButton(messages.getString("GUI.41"));
		cedeTokyoCity.setEnabled(false);
		cedeTokyoCity.addActionListener(new CedeListener());
		cedeTokyoCity.setPreferredSize(new Dimension(100, 150));
		
		cedeTokyoBay = new JButton(messages.getString("GUI.70"));
		cedeTokyoBay.setEnabled(false);
		cedeTokyoBay.addActionListener(new CedeListener());
		cedeTokyoBay.setPreferredSize(new Dimension(100, 150));
		
		this.add(tokyo);
		this.add(cedeTokyoCity);
		this.add(bay);
		this.add(cedeTokyoBay);		
		
		if (game.gameboard.numOfPlayers <= 4) {
			bay.setVisible(false);
			cedeTokyoBay.setVisible(false);
		}
	}

	class CedeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.cedeTokyo();
		}
	}

	public void moveToTokyo(Player player) {
		tokyo.setText(messages.getString("GUI.12") + player.name);
	}

	public void movetoBay(Player player) {
		bay.setText(messages.getString("GUI.14") + player.name);
	}
}
