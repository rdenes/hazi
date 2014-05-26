package hazi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A programot tartalmazó osztály.Megadjuk az indításhoz szükséges kódot, majd
 * megadjuk egy busz számát betűkkel. Ezek után a program felcsatlakozik egy sql
 * szerverhez és innen lekérdezi a busz menetrendjét, majd megformázva kiírja a
 * képernyőre. Ezek után több busz menetrendjét is lekérhetjük, vagy be is
 * fejezhetjük a programot a "vége" szóval.
 * 
 * @author Rácz Dénes A program készítője.
 * 
 */
public class Main {
	/**
	 * Loggoláshoz szükséges változó.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Main.class
			.getName());

	/**
	 * Ellenőrzi, hogy jó indítási kódot adtunk-e meg.
	 * 
	 * @param kod
	 *            Az indítási kód
	 * @return Visszaadja az indításhoz szükséges paramétert.
	 */
	public static int ellenoriz(String kod) {
		if (kod.equals("indit"))
			return 1;
		return 0;
	}

	/**
	 * A programot futtató metódus, ami meghívja a többi metódust.
	 * 
	 * @throws SQLException
	 *             Elkapja az sql hibákat.
	 * 
	 */
	public void futtat() throws SQLException {
		logger.info("A programot futtató metódus meghívása.");
		Scanner sc = new Scanner(System.in);
		String busz;
		ResultSet rs;
		Connection con = null;
		Main m = new Main();
		Connect c=new Connect();
		lekerdez l=new lekerdez();
		kiir k=new kiir();
		con = c.connect();
		while (true) {
			busz = sc.next();
			if (busz.equals("vége")) {
				logger.info("Program vége.");
				break;
			}

			if (con != null) {
				rs = l.lekerdez(busz, con);
				if (rs != null)
					k.kiir(rs);
			}
		}
		if (con != null)
			con.close();
	}

	/**
	 * A főprogram.
	 * 
	 * @param args
	 *            A főprogram argumentuma.
	 * 
	 * @throws SQLException
	 *             Elkapja az sql hibákat.
	 * 
	 */
	public static void main(String[] args) throws SQLException {
		Main m = new Main();
		Scanner sc = new Scanner(System.in);
		if (ellenoriz(sc.next()) == 1)
			m.futtat();
		else
			logger.error("Rossz kod.");
	}

}
