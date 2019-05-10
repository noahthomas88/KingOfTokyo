package cards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

public class CardLoader {
	
	public CardLoader() {
		
	}
	
	public HashMap<String, Card> loadCards(String filename, BufferedReader reader) {
		if(reader==null) {
			try {
				reader = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				System.err.println("Unable to load cards file!");
				return null;
			}
		}
		HashMap<String, Card> cardmap = new HashMap<String, Card>();
		String line = null;
		int index = 0;
		try {
			Card card = new Card();
			String cardname = null;
			while((line = reader.readLine()) != null) {
				if(index==0) {
					cardname = line;
					index++;
				} else if (index==1) {
					card.name = line;
					index++;
				} else if (index==2) {
					card.cost = Integer.parseInt(line);
					index++;
				} else if (index==3) {
					card.description = line;
					index++;
				} else if (index==4) {
					card.type = line;
					index++;
				} else {
					cardmap.put(cardname, card);
					CardLogic logic = getClass(cardname);
					card.logic = logic;
					card = new Card();
					index = 0;
					if(!line.equals("-")) {
						System.err.println("error in card file syntax");
					}
				}
			}
		} catch (IOException e) {
			System.err.println("An IOException occured while loading cards");
		}
		try {
			reader.close();
		} catch (IOException e) {
			System.err.println("failed to close reader");
		}
		return cardmap;
	}
	
	public CardLogic getClass(String cardname) {
		try {
			Class<?> cardclass = Class.forName("cards."+cardname+"Logic");
			return (CardLogic) cardclass.newInstance();
		} catch (Exception e) {
			if (new File("src/cards/" + cardname + "Logic_aspect.aj").exists()) {
				return new EmptyLogic();
			}
			throw new RuntimeException("unable to create card: " + cardname);
		}
	}
}
