package hazi;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class kiirTest {

	
	private final ByteArrayOutputStream kiirt = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(kiirt));
	}
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	@Test
	public void kiirTest() {
	     kiir k = new kiir();
	     Connect c=new Connect();
	     lekerdez l=new lekerdez();
	     Connection cn=c.connect();
	     ResultSet eredmeny=l.lekerdez("teszt",cn);
	     k.kiir(eredmeny);
	     String[] tmp;
	     tmp=kiirt.toString().split("\n");
	     tmp[0]=tmp[0].trim();
	     tmp[1]=tmp[1].trim();
	     assertEquals("ORA    TANITASI_IDOSZAK       TANSZUNET            SZOMBAR              VASARNAP", tmp[0]);
	     assertEquals("01      01234567890123456789   01234567890123456789 01234567890123456789 01234567890123456789",tmp[1]);
	 }

}
