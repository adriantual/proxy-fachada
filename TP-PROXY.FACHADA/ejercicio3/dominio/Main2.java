package dominio;

public class Main2 {

	public static void main(String[] args) {

		DBFacade prueba = new ImplementacionDBFacade("jdbc:mysql://localhost:3306/proxy");

		System.out.println("primer metodo");
		prueba.open();

		System.out.println("segundo metodo");
		System.out.println(prueba.queryResultAsAsociation("select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id_persona = t.id_persona and p.id_persona = 1"));

		System.out.println("tercero metodo");

		System.out.println(prueba.queryResultAsArray("select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id_persona = t.id_persona and p.id_persona = 1"));
		System.out.println("cuarto metodo");

		prueba.close();

	}

}
