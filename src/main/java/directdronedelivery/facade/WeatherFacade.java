package directdronedelivery.facade;

import directdronedelivery.entity.Weather;

public interface WeatherFacade {
	public boolean areWeatherConditionsGoodForFly(Weather weather);
}
