package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import cards.Card;
import game.Gameplay;

public class CardsPanel extends JPanel {

	Messages messages;
	Gameplay game;
	ArrayList<CardPanel> cards = new ArrayList<>();

	public CardsPanel(Messages messages, Gameplay game) {
		this.game = game;
		this.messages = messages;
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		this.setPreferredSize(new Dimension(900,300));
		setUp();
	}

	private void setUp() {
		CardPanel card1 = new CardPanel(messages, game, 1);
		CardPanel card2 = new CardPanel(messages, game, 2);
		CardPanel card3 = new CardPanel(messages, game, 3);
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);

		this.add(card1);
		this.add(card2);
		this.add(card3);
	}

	public void update() {
		for(int i = 0; i < 3; i++) {
			cards.get(i).update();
		}
		this.repaint();
		this.revalidate();
	}
}
