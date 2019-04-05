package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cards.Card;
import game.Board;
import game.Dice;
import game.Gameplay;
import game.Player;

public class GUI {
	
	HashMap<String, JButton> buttonmap = new HashMap<String, JButton>();
	HashMap<String, JTextArea> textmap = new HashMap<String, JTextArea>();
	GUI self = this;
	ArrayList<JLabel> playertexts;
	Gameplay game;

	public GUI() {
	}

	public void viewHand() {
		JPanel panel = new JPanel();
		for(Card card : game.currentplayer.cardsInHand) {
			JButton cardbutton = new JButton();
			cardbutton.setText(card.name);
			cardbutton.setPreferredSize(new Dimension(200,200));
			panel.add(cardbutton);
		}
		JOptionPane.showConfirmDialog(null, panel, "Here is your hand",JOptionPane.OK_OPTION);
	}
	
	public void setCards(Card[] cards) {
		Card c1 = cards[0];
		Card c2 = cards[1];
		Card c3 = cards[2];
		JButton b1 = buttonmap.get("card1");
		JButton b2 = buttonmap.get("card2");
		JButton b3 = buttonmap.get("card3");
		b1.setText(c1.name);
		b2.setText(c2.name);
		b3.setText(c3.name);
	}
	
	public Integer getNumPlayers() {
		String result = JOptionPane.showInputDialog("enter number of players");
		int numplayers = Integer.parseInt(result);
		while (numplayers > 6 || numplayers < 2) {
			System.err.println("invalid player number trying again");
			numplayers = getNumPlayers();
		}
		return numplayers;
	}
	
	public void moveToTokyo(Player player) {
		JTextArea tokyo = textmap.get("tokyo");
		tokyo.setText("    Tokyo City \n  Occupied By: \n" + player.name);
	}
	
	public void moveToBay(Player player) {
		JTextArea bay = textmap.get("bay");
		bay.setText("    Tokyo Bay \n  Occupied By: \n" + player.name);
	}
	
	public void setActivePlayer(Integer playerNumber) {
		for(int i=0;i<playertexts.size();i++) {
			playertexts.get(i).setBackground(null);
		}
		playertexts.get(playerNumber).setBackground(Color.GREEN);
		System.out.println("setting" + playerNumber);
	}

	public ArrayList<String> getNames(int numOfPlayers) {
		ArrayList<String> names = new ArrayList<>();
		for (int index = 0; index < numOfPlayers; index++) {
			String name = "";
			while (name.equals("")) {
				name = JOptionPane.showInputDialog("Player #" + (index + 1) + " please enter your name");
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "name cannot be empty");
				}
			}
			names.add(name);
		}
		return names;
	}
	
	public void displayDice() {
		JPanel panel = new JPanel();
		ArrayList<Dice> dicelist = new ArrayList<Dice>();
		ArrayList<JButton> diebuttons = new ArrayList<JButton>();
		for(int i=0;i<6;i++) {
			Dice dice = new Dice(game.currentplayer);
			dice.roll();
			dicelist.add(dice);
			JButton diebutton = new JButton(dice.numberToString(dicelist.get(i).numberRolled));
			diebuttons.add(diebutton);
			diebutton.addActionListener(new DieListener(diebutton));
			panel.add(diebutton);
		}
		JOptionPane.showConfirmDialog(null, panel, "Select dice to re-roll",JOptionPane.OK_CANCEL_OPTION);
		int toresolve = 6;
		while(toresolve>1) {
			for(int i=0;i<6;i++) {
				JButton button = diebuttons.get(i);
				Dice die = dicelist.get(i);
				if(button.getBackground()!=Color.RED) {
					button.setEnabled(false);
					die.isResolved=true;
					toresolve--;
				} else {
					if(die.isResolved) {
						toresolve--;
						button.setEnabled(false);
					} else {
						die.roll();
						button.setText(die.numberToString(die.numberRolled));
					}
				}
			}
			JOptionPane.showConfirmDialog(null, panel, "Select dice to re-roll",JOptionPane.OK_CANCEL_OPTION);
		}
		game.diceRolled(dicelist);
	}
	
	public void displayStartingPlayer(String name) {
		JOptionPane.showMessageDialog(null, name + " has been selected as the starting player");
	}
	
	public void updatePlayerText(Board myBoard) {
		for (int i = 0; i < myBoard.playerList.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(myBoard.playerList.get(i).buildPlayerStatusString());
		}
	}

	public void displayBoard(Board myBoard, int numberOfPlayers, Gameplay game) {
		this.game = game;
		JFrame myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel cardPanel = new JPanel();
		JPanel tokyoPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		JButton card1 = new JButton("Card1");
		JButton card2 = new JButton("Card2");
		JButton card3 = new JButton("Card3");
		buttonmap.put("card1", card1);
		buttonmap.put("card2", card2);
		buttonmap.put("card3", card3);
		card3.addActionListener(new Card3Listener());
		card2.addActionListener(new Card2Listener());
		card1.addActionListener(new Card1Listener());
		JButton swipeCards = new JButton("Swipe Cards");
		swipeCards.addActionListener(new SwipeListener());
		buttonmap.put("swipeCards", swipeCards);
		Font tokyofont = new Font("TimesRoman", Font.BOLD, 40);
		JTextArea tokyo = new JTextArea("    Tokyo City \n   Unoccupied!");
		tokyo.setFont(tokyofont);
		tokyo.setEditable(false);
		JTextArea bay = new JTextArea("    Tokyo Bay \n   Unoccupied!");
		bay.setFont(tokyofont);
		bay.setEditable(false);
		JButton dieButton = new JButton("roll dice");
		JButton endTurn = new JButton("End Turn");
		JButton cedeTokyo = new JButton("Cede Tokyo");
		cedeTokyo.setEnabled(false);
		endTurn.setEnabled(false);
		dieButton.addActionListener(new RollListener());
		endTurn.addActionListener(new EndListener());
		cedeTokyo.addActionListener(new CedeListener());
		JButton viewHand = new JButton("View Hand");
		viewHand.addActionListener(new HandListener());
		textmap.put("tokyo", tokyo);
		textmap.put("bay", bay);
		buttonmap.put("die",dieButton);
		buttonmap.put("end", endTurn);
		buttonmap.put("cede", cedeTokyo);
		cardPanel.add(card1);
		cardPanel.add(card2);
		cardPanel.add(card3);
		cardPanel.add(swipeCards);
		cardPanel.add(cedeTokyo);
		tokyoPanel.add(tokyo);
		if(numberOfPlayers>4) {
			tokyoPanel.add(bay);
		}
		buttonPanel.add(dieButton);
		buttonPanel.add(endTurn);

		card1.setPreferredSize(new Dimension(200, 200));
		card2.setPreferredSize(new Dimension(200, 200));
		card3.setPreferredSize(new Dimension(200, 200));
		swipeCards.setPreferredSize(new Dimension(200, 50));
		tokyo.setPreferredSize(new Dimension(400, 400));
		bay.setPreferredSize(new Dimension(400, 400));
		dieButton.setPreferredSize(new Dimension(100, 100));

		playertexts = new ArrayList<JLabel>();
		for (int i = 0; i < 6; i++) {
			JLabel playertext = new JLabel("empty");
			playertext.setPreferredSize(new Dimension(250, 250));
			playerPanel.add(playertext);
			playertext.setOpaque(true);
			playertexts.add(playertext);
		}
		playerPanel.add(viewHand);
		for (int i = 0; i < myBoard.playerList.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(myBoard.playerList.get(i).buildPlayerStatusString());
		}

		panel.add(cardPanel, BorderLayout.NORTH);
		panel.add(tokyoPanel, BorderLayout.CENTER);
		panel.add(playerPanel, BorderLayout.SOUTH);
		panel.add(buttonPanel, BorderLayout.EAST);

		myframe.add(panel);
		myframe.pack();
		myframe.setVisible(true);
	}
	
	public void EnableEndTurnButton() {
		this.buttonmap.get("end").setEnabled(true);
	}
	
	public void DisableEndTurnButton() {
		this.buttonmap.get("end").setEnabled(false);
	}
	
	public class RollListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				self.displayDice();
		}
	}
	
	public class DieListener implements ActionListener {
		
		JButton button;
		
		DieListener(JButton button){
			this.button = button;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(this.button.getBackground() == new JButton().getBackground()) {
				this.button.setBackground(Color.RED);
			} else {
				this.button.setBackground(null);
			}
		}
	}
	
	public class EndListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				game.endTurn();
		}
	}
	
	public class CedeListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				game.cedeTokyo();
		}
	}
	
	public class HandListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
				self.viewHand();
		}
	}
	
	public class Card1Listener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.buyCard(1);
		}
	}
	
	public class Card2Listener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.buyCard(2);
		}
	}
	
	public class Card3Listener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.buyCard(3);
		}
	}
	
	public class SwipeListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.swipeCard();
		}
	}

}
