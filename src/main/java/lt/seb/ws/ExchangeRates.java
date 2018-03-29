
package lt.seb.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * WebService provides official (established by Bank of Lithuania) exchange rates of the Litas against Foreign Currencies.
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ExchangeRates", targetNamespace = "http://webservices.lb.lt/ExchangeRates", wsdlLocation = "http://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx?wsdl")
public class ExchangeRates
    extends Service
{

    private final static URL EXCHANGERATES_WSDL_LOCATION;
    private final static WebServiceException EXCHANGERATES_EXCEPTION;
    private final static QName EXCHANGERATES_QNAME = new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRates");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://www.lb.lt/webservices/ExchangeRates/ExchangeRates.asmx?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EXCHANGERATES_WSDL_LOCATION = url;
        EXCHANGERATES_EXCEPTION = e;
    }

    public ExchangeRates() {
        super(__getWsdlLocation(), EXCHANGERATES_QNAME);
    }

    public ExchangeRates(WebServiceFeature... features) {
        super(__getWsdlLocation(), EXCHANGERATES_QNAME, features);
    }

    public ExchangeRates(URL wsdlLocation) {
        super(wsdlLocation, EXCHANGERATES_QNAME);
    }

    public ExchangeRates(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EXCHANGERATES_QNAME, features);
    }

    public ExchangeRates(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ExchangeRates(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ExchangeRatesSoap
     */
    @WebEndpoint(name = "ExchangeRatesSoap")
    public ExchangeRatesSoap getExchangeRatesSoap() {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesSoap"), ExchangeRatesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExchangeRatesSoap
     */
    @WebEndpoint(name = "ExchangeRatesSoap")
    public ExchangeRatesSoap getExchangeRatesSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesSoap"), ExchangeRatesSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns ExchangeRatesSoap
     */
    @WebEndpoint(name = "ExchangeRatesSoap12")
    public ExchangeRatesSoap getExchangeRatesSoap12() {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesSoap12"), ExchangeRatesSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExchangeRatesSoap
     */
    @WebEndpoint(name = "ExchangeRatesSoap12")
    public ExchangeRatesSoap getExchangeRatesSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesSoap12"), ExchangeRatesSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns ExchangeRatesHttpGet
     */
    @WebEndpoint(name = "ExchangeRatesHttpGet")
    public ExchangeRatesHttpGet getExchangeRatesHttpGet() {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesHttpGet"), ExchangeRatesHttpGet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExchangeRatesHttpGet
     */
    @WebEndpoint(name = "ExchangeRatesHttpGet")
    public ExchangeRatesHttpGet getExchangeRatesHttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesHttpGet"), ExchangeRatesHttpGet.class, features);
    }

    /**
     * 
     * @return
     *     returns ExchangeRatesHttpPost
     */
    @WebEndpoint(name = "ExchangeRatesHttpPost")
    public ExchangeRatesHttpPost getExchangeRatesHttpPost() {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesHttpPost"), ExchangeRatesHttpPost.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ExchangeRatesHttpPost
     */
    @WebEndpoint(name = "ExchangeRatesHttpPost")
    public ExchangeRatesHttpPost getExchangeRatesHttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.lb.lt/ExchangeRates", "ExchangeRatesHttpPost"), ExchangeRatesHttpPost.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EXCHANGERATES_EXCEPTION!= null) {
            throw EXCHANGERATES_EXCEPTION;
        }
        return EXCHANGERATES_WSDL_LOCATION;
    }

}
