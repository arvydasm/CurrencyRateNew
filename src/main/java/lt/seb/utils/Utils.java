package lt.seb.utils;

import lt.seb.exception.AppException;
import lt.seb.ws.ExchangeRates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    /*public static ExchangeRates unmarshaller(Node node) throws AppException {
        try {
        JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRates.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return  (ExchangeRates) unmarshaller.unmarshal(node);
        } catch (JAXBException e) {
            logger.error("Error unmarshalling data from WS", e);
            throw new AppException("Error unmarshalling data from WS", e);
        }
    }*/

}
