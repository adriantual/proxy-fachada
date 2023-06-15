package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonaDao {

	private Conn conn;

	public PersonaDao(String conn) {
		this.conn = new Conn(conn);
	}

	private Connection obtenerConexion() {
		return this.conn.open();
	}

	public Persona personaPorId(int id) {

		String sql = "select p.nombre " + "from personas p " + "where p.id_persona = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			String nombrePersona = null;

			while (result.next()) {
				nombrePersona = result.getString(1);

			}

			return new Persona(id, nombrePersona, new TelefonoProxy(id, this.conn));

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}
	}
}