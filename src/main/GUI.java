package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
		JPanel bigPanel = new JPanel();
		bigPanel.setLayout(new FlowLayout());
		bigPanel.setPreferredSize(new Dimension(1800, 800));
		if(game.currentplayer.cardsInHand.isEmpty()) {
			JLabel label = new JLabel("Your hand is empty");
			bigPanel.add(label);
		}
		for(Card card : game.currentplayer.cardsInHand) {
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			JButton cardbutton = new JButton();
			JTextArea description = new JTextArea();
			description.setLineWrap(true);
			description.setText(card.description);
			description.setEditable(false);
			cardbutton.setText(card.name);
			cardbutton.setPreferredSize(new Dimension(300,100));
			description.setPreferredSize(new Dimension(300,100));
			cardbutton.addActionListener(new UseCardListener(card.name));
			panel.add(cardbutton, BorderLayout.CENTER);
			panel.add(description,BorderLayout.SOUTH);
			bigPanel.add(panel);
		}
		JOptionPane.showConfirmDialog(null, bigPanel, "Here is your hand",JOptionPane.DEFAULT_OPTION);
	}
	
	public void viewCard(int index) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		JLabel label2 = new JLabel();
		JTextArea description = new JTextArea();
		description.setLineWrap(true);
		panel.setLayout(new BorderLayout());
		Card card = game.deck.visibleCard[index-1];
		description.setText(card.description);
		description.setEditable(false);
		label.setText("Cost: " + card.cost + " energy" + ", " + card.type);
		label2.setText(card.name);
		description.setPreferredSize(new Dimension(200,200));
		panel.add(description, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
		panel.add(label2, BorderLayout.NORTH);
		int option = JOptionPane.showConfirmDialog(null, panel, "Here is your hand",JOptionPane.OK_CANCEL_OPTION);
		if(option == JOptionPane.OK_OPTION) {
			game.buyCard(index);
		}
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

	public Integer inputNumPlayers() {
		String result = JOptionPane.showInputDialog("enter number of players");
		int numplayers = Integer.parseInt(result);
		while (numplayers > 6 || numplayers < 2) {
			System.err.println("invalid player number trying again");
			numplayers = inputNumPlayers();
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
		for (int i = 0; i < playertexts.size(); i++) {
			playertexts.get(i).setBackground(null);
		}
		playertexts.get(playerNumber).setBackground(Color.GREEN);
		System.out.println("setting" + playerNumber);
	}

	public ArrayList<String> inputNames(int numOfPlayers) {
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
		int numberOfDiceRolls = game.currentplayer.numberOfDieRolls;
		int numberOfDice = game.currentplayer.getNumberOfDie();
		for (int i = 0; i < numberOfDice; i++) {
			Dice dice = new Dice(game.currentplayer);
			dice.roll();
			dicelist.add(dice);
			JButton diebutton = new JButton(dice.numberToString(dicelist.get(i).numberRolled));
			diebuttons.add(diebutton);
			diebutton.addActionListener(new DieListener(diebutton));
			panel.add(diebutton);
		}
		JOptionPane.showConfirmDialog(null, panel, "First roll, select dice to re-roll", JOptionPane.DEFAULT_OPTION);
		for (int i = 0; i < numberOfDice; i++) {
			JButton button = diebuttons.get(i);
			Dice die = dicelist.get(i);
			if (button.getBackground() == Color.RED) {
				die.roll();
				button.setText(die.numberToString(die.numberRolled));
			}
		}
		JOptionPane.showConfirmDialog(null, panel, "Second roll, select dice to re-roll", JOptionPane.DEFAULT_OPTION);
		for (int i = 0; i < numberOfDice; i++) {
			JButton button = diebuttons.get(i);
			Dice die = dicelist.get(i);
			if (button.getBackground() == Color.RED) {
				die.roll();
				button.setText(die.numberToString(die.numberRolled));
			}
		}
		if(numberOfDiceRolls == 3) {
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				button.setEnabled(false);
				button.setBackground(Color.WHITE);
			}
			JOptionPane.showConfirmDialog(null, panel, "Third roll, this is your final row", JOptionPane.DEFAULT_OPTION);	
		}else {
			JOptionPane.showConfirmDialog(null, panel, "Third roll, you have a fourth row", JOptionPane.DEFAULT_OPTION);
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				Dice die = dicelist.get(i);
				if (button.getBackground() == Color.RED) {
					die.roll();
					button.setText(die.numberToString(die.numberRolled));
				}
				button.setEnabled(false);
				button.setBackground(Color.WHITE);
			}
			JOptionPane.showConfirmDialog(null, panel, "Fourth roll, this is your final row", JOptionPane.DEFAULT_OPTION);
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
		buttonmap.put("die", dieButton);
		buttonmap.put("end", endTurn);
		buttonmap.put("cede", cedeTokyo);
		cardPanel.add(card1);
		cardPanel.add(card2);
		cardPanel.add(card3);
		cardPanel.add(swipeCards);
		cardPanel.add(cedeTokyo);
		tokyoPanel.add(tokyo);
		if (numberOfPlayers > 4) {
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

	public void EnableCedeButton() {
		this.buttonmap.get("cede").setEnabled(true);
	}

	public void EnableRollButton() {
		this.buttonmap.get("die").setEnabled(true);
	}

	public void DisableRollButton() {
		this.buttonmap.get("die").setEnabled(false);
	}

	public void DisableCedeButton() {
		this.buttonmap.get("cede").setEnabled(false);
	}

	public class RollListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			self.displayDice();
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
			viewCard(1);
		}
	}

	public class Card2Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewCard(2);
		}
	}

	public class Card3Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewCard(3);
		}
	}

	public class SwipeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.swipeCard();
		}
	}

	public class UseCardListener implements ActionListener {
		String cardname;

		UseCardListener(String name) {
			this.cardname = name;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.useCard(cardname);
			JOptionPane.getRootFrame().dispose();
		}
	}

	public void energyWarning() {
		JOptionPane.showMessageDialog(null, "Insufficient Energy");
	}

	public void playerCountWarning() {
		JOptionPane.showMessageDialog(null, "Number of players have to be between 2 to 6");
	}

	public void endGame(Player currentplayer, int i) {
		if(i==1) {
			JOptionPane.showMessageDialog(null, currentplayer.name + " has won the game with victory points!");
		} else {
			JOptionPane.showMessageDialog(null, currentplayer.name + " has won the game by killing the other players!");
		}
		for (String string : this.buttonmap.keySet()) {
			this.buttonmap.get(string).setEnabled(false);
		}
		this.DisableCedeButton();
		this.DisableEndTurnButton();
	}

	public void cardCannotUseWarning() {
		JOptionPane.showMessageDialog(null, "This card cannot be used");
	}

}
