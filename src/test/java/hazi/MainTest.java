package hazi;

import static org.junit.Assert.*;
import hazi.Main;


import org.junit.Test;

public class MainTest {
	@Test
	public void ellenorizTest(){
		Main m=new Main();
		if (m.ellenoriz("indit")==1)
			assertTrue(true);
		else assertTrue(false);
	}

	


}
