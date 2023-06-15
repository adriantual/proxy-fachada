package dominio;

public class Main1 {

	public static void main(String[] args) {

		Clima temperatura = new CacheClima(new ClimaWeb("e3a815eaff208aeecbc836319f9643cf"));

		long start = System.currentTimeMillis();
		String temp = temperatura.clima();
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000f);

		long start1 = System.currentTimeMillis();
		String temp1 = temperatura.clima();
		long end1 = System.currentTimeMillis();
		System.out.println((end1 - start1) / 1000f);

		long start2 = System.currentTimeMillis();
		String temp2 = temperatura.clima();
		long end2 = System.currentTimeMillis();
		System.out.println((end2 - start2) / 1000f);
	}

}
