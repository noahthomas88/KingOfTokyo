package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cards.Card;
import game.Gameplay;

public class CardPanel extends JPanel {

	Gameplay game;
	Messages messages;
	int index;
	JButton buy;

	public CardPanel(Messages messages, Gameplay game, int index) {
		this.game = game;
		this.messages = messages;
		this.index = index;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.setPreferredSize(new Dimension(300, 300));
		update();
	}

	class BuyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.buyCard(index);
		}

	}

	public void update() {
		this.removeAll();
		Card card = game.deck.visibleCard[index - 1];

		JLabel name = new JLabel();
		name.setText(card.name);
		name.setPreferredSize(new Dimension(300, 100));

		JTextArea description = new JTextArea();
		description.setLineWrap(true);
		description.setEditable(false);
		description.setText(card.description);
		description.setPreferredSize(new Dimension(300, 100));

		JLabel costType = new JLabel();
		costType.setText(messages.getString("GUI.2") + card.cost + messages.getString("GUI.3") + ", " + card.type); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		costType.setPreferredSize(new Dimension(300, 50));

		buy = new JButton(messages.getString("GUI.71"));
		buy.addActionListener(new BuyListener());
		buy.setPreferredSize(new Dimension(300, 50));

		this.add(name);
		this.add(description);
		this.add(costType);
		this.add(buy);
		
		this.repaint();
		this.revalidate();
	}
}
