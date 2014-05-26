package hazi;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Kiirja a lekérdezett adatokat a kimenetre.
 * @author Rácz Dénes
 *
 */
public class kiir {

	/**
	 * Loggoláshoz szükséges változó.
	 */
	protected static Logger logger = LoggerFactory.getLogger(Main.class
			.getName());

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
}
