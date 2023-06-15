package dominio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class ClimaWeb implements Clima {

	private String key;

	public ClimaWeb(String key) {
		this.key = key;
	}

	@Override
	public String clima() {

		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=viedma,032&lang=sp&APPID=" + key
				+ "&units=metric";
		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(apiUrl)).build();

		HttpResponse<String> response = null;
		try {
			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {

			throw new RuntimeException("Error al consultar el clima", e);
		}

		String jsonString = response.body();

		Gson gson = new GsonBuilder().create();
		var jsonObject = gson.fromJson(jsonString, JsonObject.class);

		var main = jsonObject.getAsJsonObject("main");

		return main.get("temp").toString();
	}

}
