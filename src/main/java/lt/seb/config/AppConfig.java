package lt.seb.config;

import lt.seb.utils.DateValidator;
import lt.seb.ws.ExchangeRates;
import lt.seb.ws.ExchangeRatesSoap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ExchangeRatesSoap currencyRatesClient() {
        ExchangeRates exchangeRatesService = new ExchangeRates();
        ExchangeRatesSoap client = exchangeRatesService.getExchangeRatesSoap();
        return client;
    }

    @Bean
    public DateValidator dateValidator() {
        return new DateValidator();
    }
}
