package lt.seb.controller;

import lt.seb.model.ExchangeRates;
import lt.seb.utils.DateValidator;
import lt.seb.utils.Utils;
import lt.seb.ws.ExchangeRatesSoap;
import lt.seb.ws.GetExchangeRatesByDateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Node;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
public class AppRestController {

    private static Logger logger = LoggerFactory.getLogger(AppRestController.class);

    @Autowired
    private ExchangeRatesSoap soapClient;

    @Autowired
    private DateValidator dateValidator;


    @RequestMapping(value = "/getRatesByDate", method = RequestMethod.POST)
    public List<ExchangeRates.Item> getRatesByDate(@RequestParam("date") String date) throws Exception {
        try {
            // validating date
            String error = dateValidator.validate(date);
            if (error != null){
                throw new Exception(error);
            }

            //getting results from WS
            GetExchangeRatesByDateResponse.GetExchangeRatesByDateResult list = soapClient.getExchangeRatesByDate(date);

            //checking has server returned data
            if (list == null || list.getContent() == null || list.getContent().isEmpty()) {
                throw new Exception("No data for date - " + date);
            }

            ExchangeRates exchangeRates = Utils.unmarshaller((Node) list.getContent().get(0));
            List<ExchangeRates.Item> currentList = exchangeRates.getItem();

            String dateBefore = dateValidator.getDateBefore(date);

            list = soapClient.getExchangeRatesByDate(dateBefore);

            //if date before list is empty returning first list
            if (list == null || list.getContent() == null || list.getContent().isEmpty()) {
                return currentList;
            }

            exchangeRates = Utils.unmarshaller((Node) list.getContent().get(0));
            List<ExchangeRates.Item> dayBeforeList = exchangeRates.getItem();

            currentList.forEach(item -> {
                ExchangeRates.Item i = dayBeforeList
                        .stream()
                        .filter(item1 -> item1.getCurrency().equals(item.getCurrency()))
                        .findFirst().get();
                if (i != null) {
                    item.setChangeRate(item.getRate().subtract(i.getRate()));
                }
            });

            Comparator<ExchangeRates.Item> itemsComparator = Comparator.comparing(ExchangeRates.Item::getChangeRate);
            Comparator<ExchangeRates.Item> itemsComparatorReversed = itemsComparator.reversed();
            Collections.sort(currentList, itemsComparatorReversed);

            //Collections
            //        .sort(currentList, (item1, item2) -> item1.getChangeRate().compareTo(item2.getChangeRate()));

            return currentList;

        } catch (Exception e) {
            logger.error("getRatesByDate error", e);
            throw new Exception(e.getMessage());
        }
    }

}
