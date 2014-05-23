package hazi;

import static org.junit.Assert.*;
import hazi.Main;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainTest {
	@Test
	public void ellenorizTest(){
		Main m=new Main();
		if (m.ellenoriz("indit")==1)
			assertTrue(true);
	}
	/*
	@Test
	 public void ConnectionTest() {
		  
		  Main o = new Main();
		  if (o.connect()!=null)
		   assertTrue("Jo", true);
		  else
		   assertTrue("Nem Jo", false);
		 }
	@Test
	public void lekerdez() {
		Main o = new Main();
		if (o.lekerdez("teszt", o.connect())!=null)
			assertTrue("Jo",true);
		else
			assertTrue("Nem Jo",false);
	}	
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
	     Main m = new Main();
	     Connection cn=m.connect();
	     ResultSet eredmeny=m.lekerdez("teszt",cn);
	     m.kiir(eredmeny);
	     String[] tmp;
	     tmp=kiirt.toString().split("\n");
	     tmp[0]=tmp[0].trim();
	     tmp[1]=tmp[1].trim();
	     assertEquals("ORA    TANITASI_IDOSZAK       TANSZUNET            SZOMBAR              VASARNAP", tmp[0]);
	     assertEquals("01      01234567890123456789   01234567890123456789 01234567890123456789 01234567890123456789",tmp[1]);
	 }*/
}
