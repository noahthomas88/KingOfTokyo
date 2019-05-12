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
	ArrayList<JLabel> playerStatus;
	ArrayList<HandPanel> playerHands;
	Messages messages;
	Board board;
	Gameplay game;

	public PlayerPanel(Messages messages, Gameplay game) {
		this.messages = messages;
		this.board = game.gameboard;
		this.game = game;
		this.players = new ArrayList<>();
		this.playerStatus = new ArrayList<>();
		this.playerHands = new ArrayList<>();
		
		for (int i = 0; i < board.playerList.size(); i++) {
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
			p.setPreferredSize(new Dimension(300, 600));
			
			JLabel playertext = new JLabel(board.playerList.get(i).buildPlayerStatusString(messages.getString("GUI.60"),
					messages.getString("GUI.61"), messages.getString("GUI.62"), messages.getString("GUI.63")));
			playertext.setPreferredSize(new Dimension(300, 300));
			playertext.setOpaque(true);
			playerStatus.add(playertext);
			
			HandPanel handPanel = new HandPanel(messages, game, board.playerList.get(i));
			playerHands.add(handPanel);
			
			p.add(playertext);
			p.add(handPanel);

			players.add(p);
			this.add(players.get(i));
		}
	}

	public void setActivePlayer(Integer playerNumber) {
		for (int i = 0; i < playerStatus.size(); i++) {
			playerStatus.get(i).setBackground(null);
		}
		playerStatus.get(playerNumber).setBackground(Color.GREEN);
		System.out.println(messages.getString("GUI.15") + playerNumber);
	}

	public void updatePlayerText() {
		for (int i = 0; i < board.playerList.size(); i++) {
			JLabel status = playerStatus.get(i);
			status.setText(board.playerList.get(i).buildPlayerStatusString(messages.getString("GUI.60"),
					messages.getString("GUI.61"), messages.getString("GUI.62"), messages.getString("GUI.63")));
			
			HandPanel hand = playerHands.get(i);
			hand.viewHand();
		}
	}
}
