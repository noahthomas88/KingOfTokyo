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
	
	Board myboard;
	
	public GUI () {
		myboard = new Board(getNumPlayers());
	}
	
	public Integer getNumPlayers() {
		String result = JOptionPane.showInputDialog("enter number of players");
		int numplayers = Integer.parseInt(result);
		return numplayers;
	}
	
	public static String getName(int i) {
		return JOptionPane.showInputDialog("Player #" + (i+1) + " Enter Your Name");		
	}
	
	public void displayBoard() {
		JFrame myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel tokyoPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JButton diebutton = new JButton("roll dice");
		
		myframe.add(tokyoPanel,BorderLayout.NORTH);
		myframe.add(playerPanel,BorderLayout.CENTER);
		myframe.add(diebutton, BorderLayout.EAST);
		
		JButton tokyo = new JButton("Tokyo City");
		JButton bay = new JButton("Tokyo Bay");
		tokyoPanel.add(tokyo);
		tokyoPanel.add(bay);
		
		tokyo.setPreferredSize(new Dimension(500,500));
		bay.setPreferredSize(new Dimension(500,500));
		diebutton.setPreferredSize(new Dimension(100,100));
		
		ArrayList<JLabel> playertexts = new ArrayList<JLabel>();
		for(int i=0;i<6;i++) {
			JLabel playertext = new JLabel("empty");
			playertext.setEnabled(false);
			playertext.setPreferredSize(new Dimension(200,200));
			playerPanel.add(playertext);
			playertexts.add(playertext);
		}
		
		for(int i=0;i<myboard.player.size();i++) {
			JLabel playertoset = playertexts.get(i);
			playertoset.setText(myboard.player.get(i).name);
			playertoset.setEnabled(true);
		}
		
		playerPanel.add(diebutton);
		myframe.pack();
		myframe.setVisible(true);
	}
	

}
