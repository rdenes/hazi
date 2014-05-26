package hazi;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Lekérdezi a kért táblát az adatbázisból.
 * @author Rácz Dénes
 *
 */
public class lekerdez {


	/**
	 * Loggoláshoz szükséges változó.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Main.class
			.getName());
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
}
