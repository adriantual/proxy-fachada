package dominio;

public class Main {

	public static void main(String args[]) {

		PersonaDao dao = new PersonaDao("jdbc:mysql://localhost:3306/proxy");
		Persona p = dao.personaPorId(1);
		System.out.println(p.nombre() + "\n");

		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono);
		}
	}
}