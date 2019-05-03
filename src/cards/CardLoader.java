package cards;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class CardLoader {
	
	public CardLoader() {
		
	}
	
	public HashMap<String, ArrayList<String>> loadCards(String filename, BufferedReader reader, PrintStream stream) {
		if(stream!=null) {
			System.setErr(stream);
		}
		if(reader==null) {
			try {
				reader = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException e) {
				System.err.println("Unable to load cards file!");
				return null;
			}
		}
		HashMap<String, ArrayList<String>> cardmap = new HashMap<String, ArrayList<String>>();
		String line = null;
		int index = 0;
		try {
			ArrayList<String> card = new ArrayList<String>();
			String cardname = null;
			while((line = reader.readLine()) != null) {
				if(index==0) {
					cardname = line;
					index++;
				} else if(index>=5) {
					cardmap.put(cardname, card);
					card = new ArrayList<String>();
					index = 0;
					if(!line.equals("-")) {
						System.err.println("error in card file syntax");
					}
				} else {
					card.add(line);
					index++;
				}
			}
			cardmap.put(cardname, card);
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
}
