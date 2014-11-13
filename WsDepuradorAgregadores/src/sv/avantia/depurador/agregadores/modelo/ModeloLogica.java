package sv.avantia.depurador.agregadores.modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sv.avantia.depurador.agregadores.hilo.ConsultaAgregadorPorHilo;
import sv.avantia.depurador.agregadores.jdbc.ConexionJDBC;
import sv.avantia.depurador.agregadores.object.Numeros;

public class ModeloLogica {

	public int acciones(Numeros numeros){
		ResultSet rset = null;
		try {
			rset = consultarParametrizacion();
			while (rset.next()){
				System.out.println("Realizaremos las acciones" + rset.getInt(1));
				//System.out.println (rset.getString(1));
				ConsultaAgregadorPorHilo hilo = new ConsultaAgregadorPorHilo();
				hilo.setNumeros(numeros);
				hilo.setAgregador(rset.getInt(1));
				hilo.start();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rset.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	     return 0;    
	}
	
	private ResultSet consultarParametrizacion() throws SQLException {
		Statement stmt = null;
		try {
			stmt = ConexionJDBC.obtenerConexion().createStatement();
			return stmt
					.executeQuery("	SELECT ag.ID, nombre, url_servicios, url_bajas, url_lista_negra, passw			"
							+ "	  FROM agregadores ag INNER JOIN paises ps    "
							+ "		   ON ag.id_pais = ps.ID                                                    "
							+ "	WHERE ps.codigo = '503' AND ag.status = 1                                       ");

		} catch (SQLException e) {
			throw e;
		} 
	}
}
