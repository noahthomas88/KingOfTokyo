package Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {

	public GUI() {

	}

	public Integer getNumPlayers() {
		String result = JOptionPane.showInputDialog("enter number of players");
		int numplayers = Integer.parseInt(result);
		return numplayers;
	}

	public ArrayList<String> getNames(int numOfPlayers) {
		ArrayList<String> names = new ArrayList<>();
		for (int index = 0; index < numOfPlayers; index++) {
			String name = "";
			while(name.equals("")){
				name = JOptionPane.showInputDialog("Player #" + (index + 1) + " please enter your name");
				if(name.equals("")){
					JOptionPane.showMessageDialog(null, "name cannot be empty");
				}
			}
			names.add(name);
		}
		return names;
	}

	public void displayBoard(Board myBoard) {
		JFrame myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel tokyoPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JButton tokyo = new JButton("Tokyo City");
		JButton bay = new JButton("Tokyo Bay");
		JButton diebutton = new JButton("roll dice");
		tokyoPanel.add(tokyo);
		tokyoPanel.add(bay);
		buttonPanel.add(diebutton);
		
		tokyo.setPreferredSize(new Dimension(400, 400));
		bay.setPreferredSize(new Dimension(400, 400));
		diebutton.setPreferredSize(new Dimension(100, 100));

		ArrayList<JLabel> playertexts = new ArrayList<JLabel>();
		for (int i = 0; i < 6; i++) {
			JLabel playertext = new JLabel("empty");
			playertext.setPreferredSize(new Dimension(250, 250));
			playerPanel.add(playertext);
			playertexts.add(playertext);
		}

		for (int i = 0; i < myBoard.player.size(); i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(myBoard.player.get(i).name);
		}

		panel.add(tokyoPanel, BorderLayout.NORTH);
		panel.add(playerPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		myframe.add(panel);
		myframe.pack();
		myframe.setVisible(true);
	}

}
