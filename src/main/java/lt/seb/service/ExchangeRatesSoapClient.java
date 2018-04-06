package lt.seb.service;

import lt.seb.exception.AppException;
import lt.seb.model.ExchangeRates;
import lt.seb.utils.Utils;
import lt.seb.ws.GetExchangeRatesByDate;
import lt.seb.ws.GetExchangeRatesByDateResponse;
import lt.seb.ws.GetExchangeRatesByDateResponse.GetExchangeRatesByDateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBException;
import java.util.List;

public class ExchangeRatesSoapClient extends WebServiceGatewaySupport {

    private static Logger logger = LoggerFactory.getLogger(ExchangeRatesSoapClient.class);

    public List<ExchangeRates.Item> getExchangeRates(String date) throws AppException {
        logger.info("Requesting rates for " + date);

        GetExchangeRatesByDate getExchangeRatesByDate = new GetExchangeRatesByDate();
        getExchangeRatesByDate.setDate(date);

        GetExchangeRatesByDateResponse response = (GetExchangeRatesByDateResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx",
                        getExchangeRatesByDate,
                        new SoapActionCallback("http://webservices.lb.lt/ExchangeRates/getExchangeRatesByDate"));

        //checking has server returned data
        if (response != null && response.getGetExchangeRatesByDateResult() != null) {
            GetExchangeRatesByDateResult result = response.getGetExchangeRatesByDateResult();
            if (result != null && result.getContent() != null && !result.getContent().isEmpty()) {
                ExchangeRates exchangeRates = Utils.unmarshaller((Node) result.getContent().get(0));
                return exchangeRates.getItem();
            }
        }

        return null;
    }

}
