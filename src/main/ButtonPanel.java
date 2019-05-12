package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Gameplay;

public class ButtonPanel extends JPanel {
	
	Messages messages;
	Gameplay game;
	JButton swipeCards;
	JButton endTurn;
	
	public ButtonPanel(Messages messages, Gameplay game) {
		this.messages = messages;
		this.game = game;
		this.setPreferredSize(new Dimension(300,300));
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
		
		swipeCards = new JButton(messages.getString("GUI.34"));
		swipeCards.addActionListener(new SwipeListener());
		swipeCards.setPreferredSize(new Dimension(300,150));
		
		endTurn = new JButton(messages.getString("GUI.40"));
		endTurn.setEnabled(false);
		endTurn.addActionListener(new EndListener());
		endTurn.setPreferredSize(new Dimension(300,150));
		
		this.add(swipeCards);
		this.add(endTurn);
	}
	
	public class SwipeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.swipeCard();
		}
	}
	
	public class EndListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.endTurn();
		}
	}
}
