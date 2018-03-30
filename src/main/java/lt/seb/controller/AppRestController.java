package lt.seb.controller;

import lt.seb.model.ExchangeRates;
import lt.seb.service.AppService;
import lt.seb.utils.DateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AppRestController {

    private static Logger logger = LoggerFactory.getLogger(AppRestController.class);

    @Autowired
    private DateValidator dateValidator;

    @Autowired
    private AppService appService;

    @RequestMapping(value = "/getRatesByDate", method = RequestMethod.POST)
    public List<ExchangeRates.Item> getRatesByDate(@RequestParam("date") String date) throws Exception {
        try {
            // validating date
            String error = dateValidator.validate(date);
            if (error != null){
                throw new Exception(error);
            }

            return appService.getCurrencyRatesItems(date);

        } catch (Exception e) {
            logger.error("getRatesByDate error", e);
            throw new Exception(e.getMessage());
        }
    }

}
