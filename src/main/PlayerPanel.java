package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Board;
import game.Gameplay;

public class PlayerPanel extends JPanel {

	ArrayList<JPanel> players;
	ArrayList<JLabel> playertexts;
	ArrayList<HandPanel> playerHands;
	Messages messages;
	Board board;
	Gameplay game;

	public PlayerPanel(Messages messages, Board board, Gameplay game) {
		this.players = new ArrayList<>();
		this.playertexts = new ArrayList<>();
		
		for (int i = 0; i < 6; i++) {
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout());
			p.setPreferredSize(new Dimension(300, 650));

			JLabel playertext = new JLabel(messages.getString("GUI.48"));
			playertext.setPreferredSize(new Dimension(300, 300));
			playertext.setOpaque(true);
			playertexts.add(playertext);

			p.add(playertext);
			players.add(p);
		}
		
		this.messages = messages;
		this.board = board;
		this.game = game;
	}

	public void setActivePlayer(Integer playerNumber) {
		for (int i = 0; i < playertexts.size(); i++) {
			playertexts.get(i).setBackground(null);
		}
		playertexts.get(playerNumber).setBackground(Color.GREEN);
		System.out.println(messages.getString("GUI.15") + playerNumber);
	}

	public void setUp() {	
		

		for (int i = 0; i < board.playerList.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(board.playerList.get(i).buildPlayerStatusString(messages.getString("GUI.60"),
					messages.getString("GUI.61"), messages.getString("GUI.62"), messages.getString("GUI.63")));

			HandPanel handPanel = new HandPanel(messages, game, board.playerList.get(i));
			players.get(i).add(handPanel);
			playerHands.add(handPanel);
			this.add(players.get(i));
		}
	}

	public void updatePlayerText() {
		for (int i = 0; i < board.playerList.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(board.playerList.get(i).buildPlayerStatusString(messages.getString("GUI.60"),
					messages.getString("GUI.61"), messages.getString("GUI.62"), messages.getString("GUI.63")));
			HandPanel hand = playerHands.get(i);
			hand.repaint();
			hand.revalidate();
		}
	}
}
