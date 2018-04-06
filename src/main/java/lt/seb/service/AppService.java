package lt.seb.service;

import lt.seb.exception.AppException;
import lt.seb.ws.ExchangeRates.Item;
import lt.seb.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class AppService {

    private static Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private ExchangeRatesSoapClient serviceClient;

    @Autowired
    private DateUtils dateUtils;

    public List<Item> getCurrencyRatesItems(String date) throws AppException {
        try {

            List<Item> currentList = serviceClient.getExchangeRates(date);
            if (currentList == null) {
                throw new AppException("No currency data, for date - " + date);
            }
            //get date - 1
            String dateBefore = dateUtils.getDateBefore(date);
            List<Item> dayBeforeList = serviceClient.getExchangeRates(dateBefore);

            // day before may be empty
            if (dayBeforeList == null) {
                return currentList;
            }

            //calculate exchange rate for each currency
            currentList.forEach(item -> {
                Item i = dayBeforeList
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

            Comparator<Item> itemsComparator = Comparator.comparing(Item::getChangeRateUnit);
            Comparator<Item> itemsComparatorReversed = itemsComparator.reversed();
            Collections.sort(currentList, itemsComparatorReversed);

            return currentList;
        } catch (Exception e) {
            logger.error("getCurrencyRatesItems error", e);
            throw new AppException(e.getMessage());
        }
    }


}
