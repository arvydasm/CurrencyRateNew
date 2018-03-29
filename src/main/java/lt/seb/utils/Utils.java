package lt.seb.utils;

import lt.seb.model.ExchangeRates;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Utils {

    public static ExchangeRates unmarshaller(Node node) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ExchangeRates.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return  (ExchangeRates) unmarshaller.unmarshal(node);
    }

}
