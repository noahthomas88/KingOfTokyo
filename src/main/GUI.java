package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import cards.Card;
import cards.DeckConstructor;
import game.Board;
import game.Gameplay;
import game.Player;

public class GUI {

	GUI self = this;
	Gameplay game;
	Messages messages;
	ArrayList<JPanel> panels;
	PlayerPanel playerPanel;
	TokyoPanel tokyoPanel;
	CardsPanel cardsPanel;
	DicePanel dicePanel;
	ButtonPanel buttonPanel;
	JFrame myframe;
	public String locale;
	JButton tentacleButton;

	public GUI() {
		inputLanguage();
	}
	
	public void inputLanguage() {
		HashMap<String, String> languages = getLanguages();
		JPanel panel = new JPanel();
		for (String language : languages.keySet()) {
			JButton languagebutton = new JButton(language);
			languagebutton.addActionListener(new LanguageListener(languages.get(language)));
			panel.add(languagebutton);
		}

		int result = JOptionPane.showConfirmDialog(null, panel, "Please select a language", JOptionPane.DEFAULT_OPTION);
		if (result == 0) {
			messages = new Messages("en");
		}
	}
	
	class LanguageListener implements ActionListener {
		String code;

		LanguageListener(String code) {
			this.code = code;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			messages = new Messages(code);
			locale = code;
			JOptionPane.getRootFrame().dispose();
		}
	}
	
	public void displayBoard(Board myBoard, int numberOfPlayers, Gameplay game) {
		this.game = game;
		this.panels = new ArrayList<>();
		myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		panel.setPreferredSize(new Dimension(1900,900));
		
		this.cardsPanel = new CardsPanel(messages, game);
		this.playerPanel = new PlayerPanel(messages, game);
		this.tokyoPanel = new TokyoPanel(messages, game);	
		this.dicePanel = new DicePanel(messages, game);
		this.buttonPanel = new ButtonPanel(messages, game);
		this.tentacleButton = new JButton(messages.getString("GUI.76"));
		tentacleButton.setEnabled(false);
		tentacleButton.addActionListener(new TentacleListener());
		playerPanel.add(tentacleButton);
		panels.add(tokyoPanel);
		panels.add(cardsPanel);
		panels.add(buttonPanel);
		panels.add(dicePanel);
		panels.add(playerPanel);
		
		for(JPanel p : panels) {
			panel.add(p);
		}

		myframe.add(panel);
		myframe.pack();
		myframe.setVisible(true);
	}
	
	public void checkEnableButton(Player currentplayer) {
		if(currentplayer.haveCard("Parasitic Tentacles")) {
			this.tentacleButton.setEnabled(true);
		} else {
			this.tentacleButton.setEnabled(false);
		}
	}
	
	public Integer inputNumPlayers() {
		String result = JOptionPane.showInputDialog(messages.getString("GUI.9"));
		int numplayers = Integer.parseInt(result);
		while (numplayers > 6 || numplayers < 2) {
			System.err.println(messages.getString("GUI.10"));
			numplayers = inputNumPlayers();
		}
		return numplayers;
	}

	public HashMap<String, String> getLanguages() {
		HashMap<String, String> languages = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("languages.txt"));
			String buffer;
			languages = new HashMap<String, String>();
			try {
				while ((buffer = reader.readLine()) != null) {
					languages.put(buffer, reader.readLine());
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
		tokyoPanel.moveToTokyo(player);
	}

	public void moveToBay(Player player) {
		tokyoPanel.movetoBay(player);
	}

	public ArrayList<String> inputNames(int numOfPlayers) {
		ArrayList<String> names = new ArrayList<>();
		for (int index = 0; index < numOfPlayers; index++) {
			String name = ""; //$NON-NLS-1$
			while (name.equals("")) {
				name = JOptionPane
						.showInputDialog(messages.getString("GUI.18") + (index + 1) + messages.getString("GUI.19"));
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, messages.getString("GUI.21"));
				}
			}
			names.add(name);
		}
		return names;
	}
	
	public void setActivePlayer(Integer playerNumber) {
		playerPanel.setActivePlayer(playerNumber);
	}
	
	public void setCards(Card[] cards) {
		cardsPanel.update();
	}

	public void updatePlayerText(Board myBoard) {
		playerPanel.updatePlayerText();
	}

	public void EnableEndTurnButton() {
		buttonPanel.endTurn.setEnabled(true); 
	}

	public void DisableEndTurnButton() {
		buttonPanel.endTurn.setEnabled(false);
	}

	public void EnableRollButton() {
		dicePanel.dieButton.setEnabled(true); 
	}

	public void DisableRollButton() {
		dicePanel.dieButton.setEnabled(false);
	}

	public void EnableCedeButton() {
		tokyoPanel.enableCede();
	}
	
	public void DisableCedeButton() {
		
	}

	public void endGame(Player currentplayer, int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, currentplayer.name + messages.getString("GUI.57")); //$NON-NLS-1$
		} else {
			JOptionPane.showMessageDialog(null, currentplayer.name + messages.getString("GUI.58")); //$NON-NLS-1$
		}
		buttonPanel.swipeCards.setEnabled(false);
		buttonPanel.endTurn.setEnabled(false);
		for(i=0;i<3;i++) {
			cardsPanel.cards.get(i).buy.setEnabled(false);
		}
		dicePanel.dieButton.setEnabled(false);
		for(HandPanel p : playerPanel.playerHands) {
			if(p!=null && p.cardbutton!=null)	p.cardbutton.setEnabled(false);
		}
		tokyoPanel.cedeTokyoCity.setEnabled(false);
		tokyoPanel.cedeTokyoBay.setEnabled(false);
		
	}

	public void displayStartingPlayer(String name) {
		JOptionPane.showMessageDialog(null, name + messages.getString("GUI.27"));
	}
	
	public void energyWarning() {
		JOptionPane.showMessageDialog(null, messages.getString("GUI.55"));
	}

	public void playerCountWarning() {
		JOptionPane.showMessageDialog(null, messages.getString("GUI.56"));
	}
	
	public void cardCannotUseWarning() {
		JOptionPane.showMessageDialog(null, messages.getString("GUI.72"));
	}

	public String numberToString(int numberRolled) {
		return dicePanel.numberToString(numberRolled);
	}
	
	public void replaceDice() {
		dicePanel.setUp();
	}

	public boolean checkBeforeResolve() {
		return dicePanel.checkIsResolve();	
	}

	public boolean isCurrentPlayer(Player player) {
		return game.currentplayer.equals(player);
	}

	public void usePlotTwist() {
		dicePanel.usePlotTwist();
	}

	public void update() {
		for(JPanel p : panels) {
			p.repaint();
			p.validate();
		}
	}

	public String chooseDice() {
		return JOptionPane.showInputDialog(messages.getString("GUI.73"));
	}

	public String inputChange() {
		return JOptionPane.showInputDialog(messages.getString("GUI.74"));
	}

	public boolean checkDieNumber(String die) {
		return Integer.parseInt(die) >= 1 && Integer.parseInt(die) <= dicePanel.numberOfDice;
	}

	public void updateDie(String die, String changeTo) {
		dicePanel.dicelist.get(Integer.parseInt(die) - 1).numberRolled = Integer.parseInt(changeTo);
		dicePanel.diebuttons.get(Integer.parseInt(die) - 1).setText(numberToString(Integer.parseInt(changeTo)));
		update();
	}

	public boolean checkNumber(String changeTo) {
		return Integer.parseInt(changeTo) >= 1 && Integer.parseInt(changeTo) <= 6;
	}

	public void HealingRay(Player currentplayer, Board gameboard) {
		if(currentplayer.haveCard("Healing Ray")) {
			int result = JOptionPane.showConfirmDialog(null, null, "Would you like to use Healing Ray?", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				for(Player player : gameboard.playerList) {
					if(player.health<10) {
						if(player.energy > 1) {
							player.addHealth(1);
							player.addEnergy(-2);
							currentplayer.addEnergy(2);
						} else if (player.energy == 1) {
							player.addHealth(1);
							player.addEnergy(-1);
							currentplayer.addEnergy(1);
						}
						this.updatePlayerText(gameboard);
					}
				}
			}
		}
		
	}

	public void Camouflage(Player indexedPlayer) {
		if(indexedPlayer.haveCard("Camouflage")) {
			Random random = new Random();
			int result = random.nextInt(6);
			if(result == 5) {
				JOptionPane.showConfirmDialog(null, null, "Camouflage saved a health point", JOptionPane.DEFAULT_OPTION);
				indexedPlayer.addHealth(1);
			} else {
				JOptionPane.showConfirmDialog(null, null, "Camouflage failed", JOptionPane.DEFAULT_OPTION);
			}
		}
		
	}
		
	class TentacleListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JPanel panel =  new JPanel();
			for(Player player : game.gameboard.playerList) {
				for(Card card : player.cardsInHand) {
					JButton buybutton = new JButton(card.name + ":" + card.cost);
					buybutton.addActionListener(new BuyListener(card.name));
					panel.add(buybutton);
				}
			}
			JOptionPane.showConfirmDialog(null, panel, "Please select a card to buy", JOptionPane.DEFAULT_OPTION);
		}

	}
	
	class BuyListener implements ActionListener {
		
		String name;
		
		public BuyListener(String name) {
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			for(Player player : game.gameboard.playerList) {
				for(Card card : player.cardsInHand) {
					if(card.name.equals(this.name)) {
						if(game.currentplayer.energy>=card.cost) {
							game.currentplayer.addEnergy(-card.cost);
							player.addEnergy(card.cost);
							game.currentplayer.cardsInHand.add(card);
							player.cardsInHand.remove(card);
						}
					}
				}
			}
		}

	}
	
	class WingListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(game.currentplayer.energy>1) {
				game.currentplayer.addEnergy(-2);
				game.currentplayer.wings = true;
			}
		}

	}
	
	class LabListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Card peek = game.deck.deck.get(0);
			int result = JOptionPane.showConfirmDialog(null, null, "Would you like to buy" + peek.name+ "for " + peek.cost, JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {
				if(game.currentplayer.energy >= peek.cost) {
					game.currentplayer.addEnergy(-peek.cost);
					game.currentplayer.cardsInHand.add(game.deck.deck.remove(0));
				}
			}
		}

	}
	
	class MimicListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JPanel panel =  new JPanel();
			for(Player player : game.gameboard.playerList) {
				for(Card card : player.cardsInHand) {
					JButton buybutton = new JButton(card.name);
					buybutton.addActionListener(new CopyListener(card.name));
					panel.add(buybutton);
				}
			}
			JOptionPane.showConfirmDialog(null, panel, "Please select a card to mimic", JOptionPane.DEFAULT_OPTION);
		}

	}
	
	class CopyListener implements ActionListener {
		
		String name;
		
		public CopyListener(String name) {
			this.name = name;
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			boolean used = false;
			for(Card card : game.currentplayer.cardsInHand) {
				if(card.mimic==true) {
					used = true;
				}
			}
			if(used) {
				if(game.currentplayer.energy>0) {
					game.currentplayer.addEnergy(-1);
				}
			} else {
				for(Player player : game.gameboard.playerList) {
					for(Card card : player.cardsInHand) {
						if(card.name.equals(this.name)) {
							game.currentplayer.cardsInHand.add(card);
							card.mimic = true;
							return;
						}
					}
				}
			}
		}

	}

	public void opportunist (int index, DeckConstructor deck) {
		for(Player player : game.gameboard.playerList) {
			if(player.haveCard("Opportunist")) {
				int result = JOptionPane.showConfirmDialog(null, null, "Would you like to buy" + deck.visibleCard[index].name, JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					if(player.energy >= deck.visibleCard[index].cost) {
						player.addEnergy(-deck.visibleCard[index].cost);
						player.cardsInHand.add(deck.visibleCard[index]);
						deck.visibleCard[index] = new Card();
						deck.reveal();
					}
				}
			}
		}
		
	}

	public void frenzy() {
		this.game.redoTurn = true;
	}
}
