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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import cards.Card;
import game.Board;
import game.Gameplay;
import game.Player;

public class GUI {

	GUI self = this;
	Gameplay game;
	Messages messages;
	PlayerPanel playerPanel;
	TokyoPanel tokyoPanel;
	CardsPanel cardsPanel;
	DicePanel dicePanel;
	ButtonPanel buttonPanel;
	JFrame myframe;

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
			JOptionPane.getRootFrame().dispose();
		}
	}
	
	public void displayBoard(Board myBoard, int numberOfPlayers, Gameplay game) {
		this.game = game;
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
		
		panel.add(tokyoPanel);
		panel.add(cardsPanel);
		panel.add(buttonPanel);
		panel.add(dicePanel);
		panel.add(playerPanel);

		myframe.add(panel);
		myframe.pack();
		myframe.setVisible(true);
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
		this.tokyoPanel.moveToTokyo(player);
	}

	public void moveToBay(Player player) {
		this.tokyoPanel.movetoBay(player);
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
		this.playerPanel.setActivePlayer(playerNumber);
	}
	
	public void setCards(Card[] cards) {
		this.cardsPanel.update();
	}

	public void updatePlayerText(Board myBoard) {
		this.playerPanel.updatePlayerText();
	}

	public void EnableEndTurnButton() {
		this.buttonPanel.endTurn.setEnabled(true); 
	}

	public void DisableEndTurnButton() {
		this.buttonPanel.endTurn.setEnabled(false);
	}

	public void EnableRollButton() {
		this.dicePanel.dieButton.setEnabled(true); //$NON-NLS-1$
	}

	public void DisableRollButton() {
		this.dicePanel.dieButton.setEnabled(false); //$NON-NLS-1$
	}

	public void EnableCedeButton() {
		this.tokyoPanel.cedeTokyoCity.setEnabled(true); //$NON-NLS-1$
	}
	
	public void DisableCedeButton() {
		this.tokyoPanel.cedeTokyoCity.setEnabled(false);
	}

	public void endGame(Player currentplayer, int i) {
		if (i == 1) {
			JOptionPane.showMessageDialog(null, currentplayer.name + messages.getString("GUI.57")); //$NON-NLS-1$
		} else {
			JOptionPane.showMessageDialog(null, currentplayer.name + messages.getString("GUI.58")); //$NON-NLS-1$
		}
		this.buttonPanel.swipeCards.setEnabled(false);
		this.buttonPanel.endTurn.setEnabled(false);
		for(i=0;i<3;i++) {
			this.cardsPanel.cards.get(i).buy.setEnabled(false);
		}
		this.dicePanel.dieButton.setEnabled(false);
		for(HandPanel p : this.playerPanel.playerHands) {
			if(p!=null && p.cardbutton!=null)	p.cardbutton.setEnabled(false);
		}
		this.tokyoPanel.cedeTokyoCity.setEnabled(false);
		this.tokyoPanel.cedeTokyoBay.setEnabled(false);
		
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
		JOptionPane.showMessageDialog(null, "This card cannot be used");
	}

	public String numberToString(int numberRolled) {
		return this.dicePanel.numberToString(numberRolled);
	}
	
	public void replaceDice() {
		this.dicePanel.setUp();
	}
}
