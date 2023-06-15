package dominio;

import java.time.LocalDateTime;

public class CacheClima implements Clima {

	private ClimaWeb climaWeb;
	private String climaActual;
	private LocalDateTime horaCache;

	public CacheClima(ClimaWeb climaWeb) {
		this.climaWeb = climaWeb;
		this.horaCache = LocalDateTime.now();

	}

	@Override
	public String clima() {

		if ((this.climaActual != null) && (LocalDateTime.now().isBefore(horaCache.plusMinutes(30)))) {

			return climaActual;
		}

		climaActual = this.climaWeb.clima();

		return climaActual;
	}

}
