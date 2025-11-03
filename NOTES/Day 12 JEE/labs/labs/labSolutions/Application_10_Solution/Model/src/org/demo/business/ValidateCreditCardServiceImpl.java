package org.demo.business;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.example.Input;
import org.example.ObjectFactory;
import org.example.Output;

@WebService(name = "ValidateCreditCardService", targetNamespace = "http://www.example.org", serviceName = "ValidateCreditCardService", portName = "ValidateCreditCardServiceSoap12HttpPort", wsdlLocation = "/WSDLDocument.wsdl")
@javax.jws.soap.SOAPBinding(style = javax.jws.soap.SOAPBinding.Style.DOCUMENT, parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.BARE)
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class ValidateCreditCardServiceImpl {
    public ValidateCreditCardServiceImpl() {
    }

    @javax.jws.soap.SOAPBinding(parameterStyle = javax.jws.soap.SOAPBinding.ParameterStyle.BARE)
    @WebMethod
    @WebResult(name = "response", targetNamespace = "http://www.example.org", partName = "parameters")
    public Output validateCC(@WebParam(name = "request", partName = "parameters", targetNamespace = "http://www.example.org")
        Input parameters) {
        Output res = new Output();
        if(parameters.getArg0().equalsIgnoreCase("AMEX") && parameters.getArg1()==123456789)
            res.setArg0("Valid credit card");
        else
            res.setArg0("Invalid credit card type or credit card number");
        return res;
    }
}
