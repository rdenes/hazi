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
	 * Kiépíti a kapcsolatot az sql szerverrel.
	 * 
	 * @return az sql kapcsolat.
	 */
	public final Connection connect() {
		logger.info("Metódus:Létrehozzuk a kapcsolatot az sql szerverrel.");
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g",
					"H_F7Q9VM", "kassai");
			return con;
		} catch (SQLException e) {
			System.out.println("Nem sikerült a kapcsolat.");
			logger.error("Nem sikerült létrehozni a kapcsolatot.");
			return null;
		}
	}

	/**
	 * Lekérdezi a megadott busz menetrendjét.
	 * 
	 * @param busz
	 *            A busz aminek a menetrendjét le akarjuk kérdezni.
	 * 
	 * @param con
	 *            A kapcsolat az sql szerverrel.
	 * 
	 * @return A busz menetrendje
	 */
	public ResultSet lekerdez(String busz, Connection con) {
		logger.info("Metódus:Lekérdezzük a táblát az sql szervertől.");
		Statement stmt;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT *  FROM " + busz);
			if (rs != null)
				logger.debug("Talált ilyen táblát");
			return rs;
		} catch (SQLException e) {
			System.out.println("Nincs ilyen tábla az adatbázisban.");
			logger.warn("Nincs ilyen tábla az adatbázisban.");
			return null;
		}
	}

	/**
	 * Kiírja a kimenetre a busz menetrendjét.
	 * 
	 * @param rs
	 *            A busz menetrendje.
	 * 
	 */
	public void kiir(ResultSet rs) {
		logger.info("Metódus:Kiírjuk a lekérdezett táblát.");
		String ora, tan_i, tan_sz, szombat, vasarnap;
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			if (rsmd == null)
				logger.debug("Nem sikerült átrakni az rsmd-be.");
			System.out.println(rsmd.getColumnName(1) + "    "
					+ rsmd.getColumnName(2) + "       " + rsmd.getColumnName(3)
					+ "            " + rsmd.getColumnName(4) + "              "
					+ rsmd.getColumnName(5));
			while (rs.next()) {
				ora = rs.getString(1);
				tan_i = rs.getString(2);
				tan_sz = rs.getString(3);
				szombat = rs.getString(4);
				vasarnap = rs.getString(5);
				if (ora.length() != 2)
					ora = ora + " ";
				while (tan_i.length() != 20) {
					tan_i = tan_i + " ";
				}
				while (tan_sz.length() != 20) {
					tan_sz = tan_sz + " ";
				}
				while (szombat.length() != 20) {
					szombat = szombat + " ";
				}
				while (vasarnap.length() != 20) {
					vasarnap = vasarnap + " ";
				}
				System.out.println(ora + "      " + tan_i + "   " + tan_sz
						+ " " + szombat + " " + vasarnap);
			}
		} catch (SQLException e) {
			System.out.println("Nem sikerült a kiírás.");
			logger.debug("Nem sikerült kiírni.");
		}

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
		con = m.connect();
		while (true) {
			busz = sc.next();
			if (busz.equals("vége")) {
				logger.info("Program vége.");
				break;
			}

			if (con != null) {
				rs = m.lekerdez(busz, con);
				if (rs != null)
					m.kiir(rs);
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
