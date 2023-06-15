package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

public class ImplementacionDBFacade implements DBFacade {

	private static final String USER = "root";
	private static final String PWD = "";

	private String conn;
	private Connection connection;

	public ImplementacionDBFacade(String conn) {
		this.conn = conn;
	}

	public void open() throws RuntimeException {
		try {
			String url = this.conn;
			String user = USER;
			String password = PWD;

			this.connection = DriverManager.getConnection(url, user, password);

		} catch (Exception ex) {
			throw new RuntimeException("No fue posible conectarse a la base de datos");
		}
	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {
		// List<Map<String, String>> result = new ArrayList<>();

		this.open();
		List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
		Map<String, String> mapa = new HashMap<String, String>();

		try {
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();

			while (result.next()) {
				mapa = new HashMap<String, String>();
				for (int x = 1; x <= result.getMetaData().getColumnCount(); x++) {
					mapa.put(rsmd.getColumnName(x), result.getString(x));
				}
				lista.add(mapa);
			}

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {
		List<String[]> lista = new ArrayList<String[]>();
// hacer un for para ver lo que hay adentro del array
		try {
			PreparedStatement statement = (PreparedStatement) this.connection.prepareStatement(sql);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				for (int x = 1; x <= result.getMetaData().getColumnCount(); x++) {
					lista.add(new String[] { result.getString(x) });
				}
			}

			return lista;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
				throw new RuntimeException("error al cerrar la conexion!");
			}
		}

	}

}
