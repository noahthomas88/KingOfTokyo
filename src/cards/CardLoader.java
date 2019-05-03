package cards;

import java.io.BufferedReader;
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
					CardLogic logic = getClass(cardname, 0);
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
	
	public CardLogic getClass(String cardname, int test) {
		try {
			if(test==1) {
				throw new InstantiationException();
			} else if(test==2) {
				throw new IllegalAccessException();
			}
			Class<?> cardclass = Class.forName("cards."+cardname+"Logic");
			return (CardLogic) cardclass.newInstance();
		} catch (ClassNotFoundException e) {
			System.err.println("unable to find class to create from name");
		} catch (InstantiationException e) {
			System.err.println("unable to instantiate card logic class");
		} catch (IllegalAccessException e) {
			System.err.println("illegalaccess during card instantiation");
		}
		return null;
	}
}
