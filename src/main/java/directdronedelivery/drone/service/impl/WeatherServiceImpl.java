package directdronedelivery.drone.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import directdrondelivery.entity.Weather;
import directdronedelivery.drone.service.WeatherService;

/**
*
* @author Marcin Stachowiak, Capgemini(marcin.stachowiak@capgemini.com)
*
*/

@Service
@Scope("singleton")
public class WeatherServiceImpl implements WeatherService {
	private final static double MAX_WIND_IN_PMS = 60;
	private final static int MAX_TEMPERATURE = 35;

	@Override
	public boolean areWeatherConditionsGoodForFly(Weather weather) {
		return weather.getTemperature() < MAX_TEMPERATURE && weather.getWindInPMS() < MAX_WIND_IN_PMS
				&& !weather.isLightningsPossible();
	}

}