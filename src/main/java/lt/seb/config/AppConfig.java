package lt.seb.config;

import lt.seb.service.ExchangeRatesSoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class AppConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("lt.seb.ws");
        return marshaller;
    }

    @Bean
    public ExchangeRatesSoapClient exchangeRatesServiceClient(Jaxb2Marshaller marshaller) {
        ExchangeRatesSoapClient client = new ExchangeRatesSoapClient();
        client.setDefaultUri("http://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
