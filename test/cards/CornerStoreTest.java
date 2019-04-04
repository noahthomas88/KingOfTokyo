package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class CornerStoreTest {

	@Test
	public void constructorTest() {
		CornerStore store = new CornerStore();
		assertTrue(store.name.equals("Corner Store"));
		assertTrue(store.description.equals("Gain 1 Victory Point"));
		assertTrue(store.type.equals("Discard"));
		assertTrue(store.cost == 3);
	}

}
