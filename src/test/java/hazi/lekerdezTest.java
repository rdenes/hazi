package hazi;

import static org.junit.Assert.*;

import org.junit.Test;

public class lekerdezTest {

	@Test
	public void lekerdez() {
		lekerdez o = new lekerdez();
		Connect c =new Connect();
		if (o.lekerdez("teszt", c.connect())!=null)
			assertTrue("Jo",true);
		else
			assertTrue("Nem Jo",false);
	}

}
