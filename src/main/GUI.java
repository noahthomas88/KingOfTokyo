package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	Messages messages;

	public GUI() {
	}

	public void viewHand() {
		JPanel bigPanel = new JPanel();
		bigPanel.setLayout(new FlowLayout());
		bigPanel.setPreferredSize(new Dimension(1800, 800));
		if(game.currentplayer.cardsInHand.isEmpty()) {
			JLabel label = new JLabel(messages.getString("GUI.0")); //$NON-NLS-1$
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
		JOptionPane.showConfirmDialog(null, bigPanel, messages.getString("GUI.1"),JOptionPane.DEFAULT_OPTION); //$NON-NLS-1$
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
		label.setText(messages.getString("GUI.2") + card.cost + messages.getString("GUI.3") + ", " + card.type); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		label2.setText(card.name);
		description.setPreferredSize(new Dimension(200,200));
		panel.add(description, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
		panel.add(label2, BorderLayout.NORTH);
		int option = JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.5"),JOptionPane.OK_CANCEL_OPTION); //$NON-NLS-1$
		if(option == JOptionPane.OK_OPTION) {
			game.buyCard(index);
		}
	}

	
	public void setCards(Card[] cards) {
		Card c1 = cards[0];
		Card c2 = cards[1];
		Card c3 = cards[2];
		JButton b1 = buttonmap.get("card1"); //$NON-NLS-1$
		JButton b2 = buttonmap.get("card2"); //$NON-NLS-1$
		JButton b3 = buttonmap.get("card3"); //$NON-NLS-1$
		b1.setText(c1.name);
		b2.setText(c2.name);
		b3.setText(c3.name);
	}

	public Integer inputNumPlayers() {
		String result = JOptionPane.showInputDialog(messages.getString("GUI.9")); //$NON-NLS-1$
		int numplayers = Integer.parseInt(result);
		while (numplayers > 6 || numplayers < 2) {
			System.err.println(messages.getString("GUI.10")); //$NON-NLS-1$
			numplayers = inputNumPlayers();
		}
		return numplayers;
	}
	
	public void inputLanguage() {
		HashMap<String, String> languages = getLanguages();
		JPanel panel = new JPanel();
		for(String language : languages.keySet()) {
			JButton languagebutton = new JButton(language);
			languagebutton.addActionListener(new LanguageListener(languages.get(language)));
			panel.add(languagebutton);
		}
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Please select a language",JOptionPane.DEFAULT_OPTION);
		if(result==0) {
			messages = new Messages("en");
		}
	}
	
	public HashMap<String,String> getLanguages() {
		HashMap<String,String> languages = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("languages.txt"));
			String buffer;
			languages = new HashMap<String, String>();
			try {
				while((buffer = reader.readLine())!=null) {
					languages.put(buffer,reader.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return languages;
	}

	public void moveToTokyo(Player player) {
		JTextArea tokyo = textmap.get("tokyo"); //$NON-NLS-1$
		tokyo.setText(messages.getString("GUI.12") + player.name); //$NON-NLS-1$
	}

	public void moveToBay(Player player) {
		JTextArea bay = textmap.get("bay"); //$NON-NLS-1$
		bay.setText(messages.getString("GUI.14") + player.name); //$NON-NLS-1$
	}

	public void setActivePlayer(Integer playerNumber) {
		for (int i = 0; i < playertexts.size(); i++) {
			playertexts.get(i).setBackground(null);
		}
		playertexts.get(playerNumber).setBackground(Color.GREEN);
		System.out.println(messages.getString("GUI.15") + playerNumber); //$NON-NLS-1$
	}

	public ArrayList<String> inputNames(int numOfPlayers) {
		ArrayList<String> names = new ArrayList<>();
		for (int index = 0; index < numOfPlayers; index++) {
			String name = ""; //$NON-NLS-1$
			while (name.equals("")) { //$NON-NLS-1$
				name = JOptionPane.showInputDialog(messages.getString("GUI.18") + (index + 1) + messages.getString("GUI.19")); //$NON-NLS-1$ //$NON-NLS-2$
				if (name.equals("")) { //$NON-NLS-1$
					JOptionPane.showMessageDialog(null, messages.getString("GUI.21")); //$NON-NLS-1$
				}
			}
			names.add(name);
		}
		return names;
	}
	
	public String numberToString(int number) {
		if(number < 4) {
			return number + "";
		} else if (number == 4){
			return  messages.getString("GUI.64");
		} else if (number == 5) {
			return messages.getString("GUI.62");
		} else {
			return messages.getString("GUI.65");
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
		if(numberOfDiceRolls == 3) {
			for (int i = 0; i < numberOfDice; i++) {
				JButton button = diebuttons.get(i);
				button.setEnabled(false);
				button.setBackground(Color.WHITE);
			}
			JOptionPane.showConfirmDialog(null, panel, messages.getString("GUI.24"), JOptionPane.DEFAULT_OPTION);	 //$NON-NLS-1$
		}else {
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

	public void displayStartingPlayer(String name) {
		JOptionPane.showMessageDialog(null, name + messages.getString("GUI.27")); //$NON-NLS-1$
	}

	public void updatePlayerText(Board myBoard) {
		for (int i = 0; i < myBoard.playerList.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(myBoard.playerList.get(i).buildPlayerStatusString(messages.getString("GUI.60"),messages.getString("GUI.61"),messages.getString("GUI.62"),messages.getString("GUI.63")));
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

		JButton card1 = new JButton(messages.getString("GUI.7")); //$NON-NLS-1$
		JButton card2 = new JButton(messages.getString("GUI.6")); //$NON-NLS-1$
		JButton card3 = new JButton(messages.getString("GUI.4")); //$NON-NLS-1$
		buttonmap.put("card1", card1); //$NON-NLS-1$
		buttonmap.put("card2", card2); //$NON-NLS-1$
		buttonmap.put("card3", card3); //$NON-NLS-1$
		card3.addActionListener(new Card3Listener());
		card2.addActionListener(new Card2Listener());
		card1.addActionListener(new Card1Listener());
		JButton swipeCards = new JButton(messages.getString("GUI.34")); //$NON-NLS-1$
		swipeCards.addActionListener(new SwipeListener());
		buttonmap.put("swipeCards", swipeCards); //$NON-NLS-1$
		Font tokyofont = new Font("TimesRoman", Font.BOLD, 40); //$NON-NLS-1$
		JTextArea tokyo = new JTextArea(messages.getString("GUI.37")); //$NON-NLS-1$
		tokyo.setFont(tokyofont);
		tokyo.setEditable(false);
		JTextArea bay = new JTextArea(messages.getString("GUI.38")); //$NON-NLS-1$
		bay.setFont(tokyofont);
		bay.setEditable(false);
		JButton dieButton = new JButton(messages.getString("GUI.39")); //$NON-NLS-1$
		JButton endTurn = new JButton(messages.getString("GUI.40")); //$NON-NLS-1$
		JButton cedeTokyo = new JButton(messages.getString("GUI.41")); //$NON-NLS-1$
		cedeTokyo.setEnabled(false);
		endTurn.setEnabled(false);
		dieButton.addActionListener(new RollListener());
		endTurn.addActionListener(new EndListener());
		cedeTokyo.addActionListener(new CedeListener());
		JButton viewHand = new JButton(messages.getString("GUI.42")); //$NON-NLS-1$
		viewHand.addActionListener(new HandListener());
		textmap.put("tokyo", tokyo); //$NON-NLS-1$
		textmap.put("bay", bay); //$NON-NLS-1$
		buttonmap.put("die", dieButton); //$NON-NLS-1$
		buttonmap.put("end", endTurn); //$NON-NLS-1$
		buttonmap.put("cede", cedeTokyo); //$NON-NLS-1$
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
			JLabel playertext = new JLabel(messages.getString("GUI.48")); //$NON-NLS-1$
			playertext.setPreferredSize(new Dimension(250, 250));
			playerPanel.add(playertext);
			playertext.setOpaque(true);
			playertexts.add(playertext);
		}
		playerPanel.add(viewHand);
		for (int i = 0; i < myBoard.playerList.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(myBoard.playerList.get(i).buildPlayerStatusString(messages.getString("GUI.60"),messages.getString("GUI.61"),messages.getString("GUI.62"),messages.getString("GUI.63")));
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
		this.buttonmap.get("end").setEnabled(true); //$NON-NLS-1$
	}

	public void DisableEndTurnButton() {
		this.buttonmap.get("end").setEnabled(false); //$NON-NLS-1$
	}

	public void EnableCedeButton() {
		this.buttonmap.get("cede").setEnabled(true); //$NON-NLS-1$
	}

	public void EnableRollButton() {
		this.buttonmap.get("die").setEnabled(true); //$NON-NLS-1$
	}

	public void DisableRollButton() {
		this.buttonmap.get("die").setEnabled(false); //$NON-NLS-1$
	}

	public void DisableCedeButton() {
		this.buttonmap.get("cede").setEnabled(false); //$NON-NLS-1$
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
	
	public class LanguageListener implements ActionListener {
		String code;
		

		LanguageListener(String code) {
			this.code = code;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			messages = new Messages(code);
			JOptionPane.getRootFrame().dispose();
		}
	}

	public void energyWarning() {
		JOptionPane.showMessageDialog(null, messages.getString("GUI.55")); //$NON-NLS-1$
	}

	public void playerCountWarning() {
		JOptionPane.showMessageDialog(null, messages.getString("GUI.56")); //$NON-NLS-1$
	}

	public void endGame(Player currentplayer, int i) {
		if(i==1) {
			JOptionPane.showMessageDialog(null, currentplayer.name + messages.getString("GUI.57")); //$NON-NLS-1$
		} else {
			JOptionPane.showMessageDialog(null, currentplayer.name + messages.getString("GUI.58")); //$NON-NLS-1$
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
