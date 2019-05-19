package cards;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.easymock.EasyMock;
import org.junit.Test;

public class CardLoaderTest {

	@Test
	public void testLoaderNames() {
		CardLoader loader = new CardLoader();
		HashMap<String, Card> cardmap = loader.loadCards("cardstest.txt", null);
		assertTrue(cardmap.containsKey("ApartmentBuilding"));
		assertTrue(cardmap.containsKey("CommuterTrain"));
	}
	
	@Test
	public void testLoaderContent1() {
		CardLoader loader = new CardLoader();
		HashMap<String, Card> cardmap = loader.loadCards("cardstest.txt", null);
		Card card = cardmap.get("ApartmentBuilding");
		assertTrue(card.name.equals("Apartment Building"));
		assertTrue(card.type.contentEquals("Discard"));
	}
	
	@Test
	public void testLoaderContent2() {
		CardLoader loader = new CardLoader();
		HashMap<String, Card> cardmap = loader.loadCards("cardstest.txt", null);
		Card card = cardmap.get("CommuterTrain");
		assertTrue(card.name.equals("Commuter Train"));
		assertTrue(card.type.contentEquals("Discard"));
	}
	
	@Test
	public void testBadFile() {
		CardLoader loader = new CardLoader();
		PrintStream mockedstream = EasyMock.createMock(PrintStream.class);
		System.setErr(mockedstream);
		mockedstream.println("Unable to load cards file!");
		EasyMock.replay(mockedstream);
		HashMap<String, Card> cardmap = loader.loadCards("bla.txt", null);
		EasyMock.verify(mockedstream);
		assertEquals(cardmap,null);
	}
	
	@Test
	public void testBadSyntax() throws IOException {
		CardLoader loader = new CardLoader();
		PrintStream mockedstream = EasyMock.createMock(PrintStream.class);
		System.setErr(mockedstream);
		BufferedReader mockedreader = EasyMock.createMock(BufferedReader.class);
		EasyMock.expect(mockedreader.readLine()).andReturn("ApartmentBuilding");
		EasyMock.expect(mockedreader.readLine()).andReturn("");
		EasyMock.expect(mockedreader.readLine()).andReturn("1");
		EasyMock.expect(mockedreader.readLine()).andReturn("");
		EasyMock.expect(mockedreader.readLine()).andReturn("");
		EasyMock.expect(mockedreader.readLine()).andReturn("");
		mockedstream.println("error in card file syntax");
		EasyMock.expect(mockedreader.readLine()).andReturn(null);
		mockedreader.close();
		EasyMock.replay(mockedstream, mockedreader);
		HashMap<String, Card> cardmap = loader.loadCards("cardstest.txt", mockedreader);
		EasyMock.verify(mockedstream, mockedreader);
		assertTrue(cardmap!=null);
	}
	
	@Test
	public void testIOException() throws IOException {
		CardLoader loader = new CardLoader();
		PrintStream mockedstream = EasyMock.createMock(PrintStream.class);
		System.setErr(mockedstream);
		BufferedReader mockedreader = EasyMock.createMock(BufferedReader.class);
		EasyMock.expect(mockedreader.readLine()).andThrow(new IOException());
		mockedstream.println("An IOException occured while loading cards");
		mockedreader.close();
		EasyMock.replay(mockedstream, mockedreader);
		HashMap<String, Card> cardmap = loader.loadCards("cardstest.txt", mockedreader);
		EasyMock.verify(mockedstream, mockedreader);
		assertTrue(cardmap!=null);
	}
	
	@Test
	public void testIOException2() throws IOException {
		CardLoader loader = new CardLoader();
		PrintStream mockedstream = EasyMock.createMock(PrintStream.class);
		System.setErr(mockedstream);
		BufferedReader mockedreader = EasyMock.createMock(BufferedReader.class);
		EasyMock.expect(mockedreader.readLine()).andReturn(null);
		mockedreader.close();
		EasyMock.expectLastCall().andThrow(new IOException());
		mockedstream.println("failed to close reader");
		EasyMock.replay(mockedstream, mockedreader);
		HashMap<String, Card> cardmap = loader.loadCards("cardstest.txt", mockedreader);
		EasyMock.verify(mockedstream, mockedreader);
		assertTrue(cardmap!=null);
	}
	
	@Test
	public void getClassTest() {
		CardLoader loader = new CardLoader();
		CardLogic logic = loader.getClass("ApartmentBuilding");
		assertTrue(logic.getClass().getName().equals("cards.ApartmentBuildingLogic"));
	}
	
	@Test
	public void getAspectTest() {
		CardLoader loader = new CardLoader();
		CardLogic logic = loader.getClass("ExtraHead");
		assertTrue(logic.getClass().getName().equals("cards.CardLogic"));
	}
	
	@Test(expected = RuntimeException.class)
	public void getBadClassTest() {
		CardLoader loader = new CardLoader();
		loader.getClass("bla");
	}

}
