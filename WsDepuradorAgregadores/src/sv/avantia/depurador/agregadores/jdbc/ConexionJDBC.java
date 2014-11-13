package sv.avantia.depurador.agregadores.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConexionJDBC {
	
	public static Connection obtenerConexion() throws SQLException{
		ResourceBundle properties = ResourceBundle.getBundle("sv.avantia.depurador.agregadores.propiedades.parametrosSistema");
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
	    return DriverManager.getConnection
	          (properties.getString("jdbc.url") + properties.getString("jdbc.ip") +":" + properties.getString("jdbc.puerto")+":" + properties.getString("jdbc.db"), properties.getString("jdbc.usuario"), properties.getString("jdbc.contrasenia"));
	}
}
