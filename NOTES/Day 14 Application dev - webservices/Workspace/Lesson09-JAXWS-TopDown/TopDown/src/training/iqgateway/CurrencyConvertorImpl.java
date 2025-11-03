package training.iqgateway;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import training.iqgateway.types.ObjectFactory;

@WebService(name = "CurrencyConvertor", targetNamespace = "http://iqgateway.training/", serviceName = "CurrencyConvertorService", portName = "CurrencyConvertorPort", wsdlLocation = "/WEB-INF/wsdl/CurrencyConvertorService.wsdl")
@XmlSeeAlso( { ObjectFactory.class })
public class CurrencyConvertorImpl {
    public CurrencyConvertorImpl() {
    }

    @ResponseWrapper(localName = "dollarToRsResponse", targetNamespace = "http://iqgateway.training/", className = "training.iqgateway.types.DollarToRsResponse")
    @RequestWrapper(localName = "dollarToRs", targetNamespace = "http://iqgateway.training/", className = "training.iqgateway.types.DollarToRs")
    @WebMethod
    public double dollarToRs(@WebParam(name = "arg0")
        double arg0) {
        return 0;
    }

    @ResponseWrapper(localName = "eurosToRsResponse", targetNamespace = "http://iqgateway.training/", className = "training.iqgateway.types.EurosToRsResponse")
    @RequestWrapper(localName = "eurosToRs", targetNamespace = "http://iqgateway.training/", className = "training.iqgateway.types.EurosToRs")
    @WebMethod
    public double eurosToRs(@WebParam(name = "arg0")
        double arg0) {
        return 0;
    }

    @ResponseWrapper(localName = "yensToRsResponse", targetNamespace = "http://iqgateway.training/", className = "training.iqgateway.types.YensToRsResponse")
    @RequestWrapper(localName = "yensToRs", targetNamespace = "http://iqgateway.training/", className = "training.iqgateway.types.YensToRs")
    @WebMethod
    public double yensToRs(@WebParam(name = "arg0")
        double arg0) {
        return 0;
    }
}
