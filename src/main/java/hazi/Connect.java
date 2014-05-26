package hazi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author Rácz Dénes
 *
 */
public class Connect {
	
	/**
	 * Loggoláshoz szükséges változó.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Main.class
			.getName());
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
}
