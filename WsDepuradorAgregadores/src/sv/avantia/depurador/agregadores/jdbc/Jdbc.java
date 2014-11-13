package sv.avantia.depurador.agregadores.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Jdbc {

	private static Connection connection = null;
	private static ResourceBundle properties = ResourceBundle
			.getBundle("sv.avantia.depurador.agregadores.propiedades.parametrosSistema");

	/**
	 * Method that loads the specified driver
	 * 
	 * @return void
	 **/

	private static void loadDriver() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (Exception e) {
			errorHandler("Failed to load the driver ", e);
		}
	}

	/**
	 * Method that loads the connection into the right property
	 * 
	 * @return void
	 **/

	private static void loadConnection() {
		try {
			connection = DriverManager.getConnection(getFormatedUrl(),
					properties.getString("jdbc.usuario"),
					properties.getString("jdbc.contrasenia"));
		} catch (SQLException e) {
			errorHandler("Failed to connect to the database "
					+ getFormatedUrl(), e);
		}
	}

	/**
	 * Method that shows the errors thrown by the singleton
	 * 
	 * @param {String} Message
	 * @option {Exception} e
	 * @return void
	 **/

	private static void errorHandler(String message, Exception e) {
		System.out.println(message);
		if (e != null)
			System.out.println(e.getMessage());
	}

	/**
	 * Method that returns the formated URL to connect to the database
	 * 
	 * @return {String}
	 **/

	private static String getFormatedUrl() {
		return properties.getString("jdbc.url")
				+ properties.getString("jdbc.ip") + ":"
				+ properties.getString("jdbc.puerto") + ":"
				+ properties.getString("jdbc.db");
	}

	/**
	 * Static method that returns the instance for the singleton
	 * 
	 * @return {Connection} connection
	 **/

	public static Connection getConnection() {
		if (connection == null) {
			loadDriver();
			loadConnection();
		}
		return connection;
	}

	/**
	 * Static method that close the connection to the database
	 * 
	 * @return void
	 **/

	public static void closeConnection() {
		if (connection == null) {
			errorHandler("No connection found", null);
		} else {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				errorHandler("Failed to close the connection", e);
			}
		}
	}
}