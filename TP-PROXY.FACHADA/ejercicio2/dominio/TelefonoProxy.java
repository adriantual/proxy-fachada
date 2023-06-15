package dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TelefonoProxy implements Set<Telefono> {

	private int id;
	private Conn conn;
	private Set<Telefono> telefonos;

	public TelefonoProxy(int id, Conn conn) {
		this.conn = conn;
		this.id = id;
		this.telefonos = new HashSet<>();

	}

	private Connection obtenerConexion() {
		return this.conn.open();
	}

	@Override
	public int size() {

		return telefonos.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Telefono> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;

	}

	@Override
	public <T> T[] toArray(T[] a) {
		String sql = "select t.numero " + "from telefonos t " + "where t.id_persona= ?";

		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				telefonos.add(new Telefono(result.getString(1)));
			}

			return (T[]) telefonos.toArray(new Telefono[telefonos.size()]);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean add(Telefono e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Telefono> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
