package Game;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {
	
	public GUI () {
		
	}
	
	public Integer getNumPlayers() {
		String result = JOptionPane.showInputDialog("enter number of players");
		int numplayers = Integer.parseInt(result);
		return numplayers;
	}
	
	public void getNames(ArrayList<Player> playerlist) {
		for(int i=0;i<playerlist.size();i++) {
			Player playertochange = playerlist.get(i);
			while(playertochange.name.equals("")) {
				playertochange.name = JOptionPane.showInputDialog("Player #" + (i+1) + " Enter Your Name");
			}
		}
	}
	
	public void displayBoard(Board myboard) {
		JFrame myframe = new JFrame();
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mypanel = new JPanel();
		myframe.add(mypanel);
		JButton tokyo = new JButton("Tokyo");
		tokyo.setPreferredSize(new Dimension(500,500));
		JButton bay = new JButton("Tokyo Bay");
		bay.setPreferredSize(new Dimension(500,500));
		mypanel.add(tokyo);
		mypanel.add(bay);
		ArrayList<JTextField> playertexts = new ArrayList<JTextField>();
		for(int i=0;i<6;i++) {
			JTextField playertext = new JTextField("empty");
			playertext.setEnabled(false);
			playertext.setPreferredSize(new Dimension(200,200));
			mypanel.add(playertext);
			playertexts.add(playertext);
		}
		
		for(int i=0;i<myboard.player.size();i++) {
			JTextField playertoset = playertexts.get(i);
			playertoset.setText(myboard.player.get(i).name);
			playertoset.setEnabled(true);
		}
		JButton diebutton = new JButton("roll dice");
		diebutton.setPreferredSize(new Dimension(100,100));
		mypanel.add(diebutton);
		myframe.pack();
		myframe.setVisible(true);
	}
	

}
