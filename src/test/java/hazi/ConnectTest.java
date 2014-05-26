package hazi;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectTest {

	@Test
	 public void ConnectionTest() {
		  
		  Connect o = new Connect();
		  if (o.connect()!=null)
		   assertTrue("Jo", true);
		  else
		   assertTrue("Nem Jo", false);
		 }

}
