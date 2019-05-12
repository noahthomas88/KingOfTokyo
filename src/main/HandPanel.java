package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cards.Card;
import game.Gameplay;
import game.Player;

public class HandPanel extends JPanel {

	HandPanel self = this;
	Messages messages;
	Player player;
	Gameplay game;
	
	public HandPanel(Messages messages, Gameplay game, Player player) {
		this.messages = messages;
		this.game = game;
		this.player = player;
		this.setPreferredSize(new Dimension(300,320));
		this.setLayout(new FlowLayout());
		this.viewHand();
	}
	
	public void viewHand() {
		this.removeAll();
		
		if(player.cardsInHand.isEmpty()) {
			JLabel label = new JLabel(messages.getString("GUI.0"));
			label.setPreferredSize(new Dimension(300,50));
			
			this.add(label);
			
			this.repaint();
			this.revalidate();
			return;
		}
		
		ArrayList<Card> playerCard = player.cardsInHand;
		Card card = playerCard.get(0);
							
		JButton cardbutton = new JButton();
		cardbutton.setText(card.name);
		cardbutton.setPreferredSize(new Dimension(180,100));
		cardbutton.addActionListener(new UseCardListener(card.name));
			
		JTextArea description = new JTextArea();
		description.setLineWrap(true);
		description.setText(card.description);
		description.setEditable(false);
		description.setPreferredSize(new Dimension(180,150));

		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new FlowLayout());
		cardPanel.setPreferredSize(new Dimension(180,250));
		cardPanel.add(cardbutton);
		cardPanel.add(description);
		
		JTextField count = new JTextField();
		count.setEditable(false);
		count.setText("1/" + playerCard.size());
		count.setPreferredSize(new Dimension(50,250));
		
		JButton next = new JButton();
		class nextCardListener implements ActionListener {
			int index;
			
			public nextCardListener(int index) {
				this.index = index;
			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				index++;
				if (index == playerCard.size() + 1) {
					index = 1;
				}
				cardbutton.setText(playerCard.get(index-1).name);
				description.setText(playerCard.get(index-1).description);
				count.setText(index + "/" + playerCard.size());
				self.revalidate();
			}
		}
		next.addActionListener(new nextCardListener(1));
		next.setText(">");
		next.setPreferredSize(new Dimension(50,250));
		
		this.add(count);
		this.add(cardPanel);
		this.add(next);
		
		this.repaint();
		this.revalidate();
	}
	
	class HandListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			viewHand();
		}
	}
	
	class UseCardListener implements ActionListener {
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
}
