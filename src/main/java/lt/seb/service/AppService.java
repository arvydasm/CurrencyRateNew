package lt.seb.service;

import lt.seb.model.ExchangeRates;
import lt.seb.utils.DateValidator;
import lt.seb.utils.Utils;
import lt.seb.ws.ExchangeRatesSoap;
import lt.seb.ws.GetExchangeRatesByDateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class AppService {

    private static Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private ExchangeRatesSoap soapClient;

    @Autowired
    private DateValidator dateValidator;

    public List<ExchangeRates.Item> getCurrencyRatesItems(String date) throws Exception {
        try {
            //getting results from WS
            GetExchangeRatesByDateResponse.GetExchangeRatesByDateResult list = soapClient.getExchangeRatesByDate(date);

            //checking has server returned data
            if (list == null || list.getContent() == null || list.getContent().isEmpty()) {
                throw new Exception("No data for date - " + date);
            }

            ExchangeRates exchangeRates = Utils.unmarshaller((Node) list.getContent().get(0));
            List<ExchangeRates.Item> currentList = exchangeRates.getItem();

            //get date-1
            String dateBefore = dateValidator.getDateBefore(date);

            //get new currency data
            list = soapClient.getExchangeRatesByDate(dateBefore);

            //if date before list is empty returning first list
            if (list == null || list.getContent() == null || list.getContent().isEmpty()) {
                return currentList;
            }

            exchangeRates = Utils.unmarshaller((Node) list.getContent().get(0));
            List<ExchangeRates.Item> dayBeforeList = exchangeRates.getItem();

            //calculate exchange rate for each currency
            currentList.forEach(item -> {
                ExchangeRates.Item i = dayBeforeList
                        .stream()
                        .filter(item1 -> item1.getCurrency().equals(item.getCurrency()))
                        .findFirst().get();
                if (i != null) {
                    item.setChangeRateUnit(item.getRate().subtract(i.getRate()));
                    item.setChangeRatePercentage(item.getChangeRateUnit().divide(i.getRate(), 4, RoundingMode.HALF_UP));
                }
            });

            //Collections.sort(currentList, (item1, item2) -> item1.getChangeRate().compareTo(item2.getChangeRate()));
            //Collections.reverse(currentList);

            Comparator<ExchangeRates.Item> itemsComparator = Comparator.comparing(ExchangeRates.Item::getChangeRateUnit);
            Comparator<ExchangeRates.Item> itemsComparatorReversed = itemsComparator.reversed();
            Collections.sort(currentList, itemsComparatorReversed);

            return currentList;
        } catch (Exception e) {
            logger.error("getCurrencyRatesItems error", e);
            throw new Exception(e.getMessage());
        }
    }
}
