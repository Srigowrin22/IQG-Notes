
package training.iqgateway.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the training.iqgateway.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _YensToRs_QNAME = new QName("http://iqgateway.training/", "yensToRs");
    private final static QName _EurosToRs_QNAME = new QName("http://iqgateway.training/", "eurosToRs");
    private final static QName _YensToRsResponse_QNAME = new QName("http://iqgateway.training/", "yensToRsResponse");
    private final static QName _EurosToRsResponse_QNAME = new QName("http://iqgateway.training/", "eurosToRsResponse");
    private final static QName _DollarToRs_QNAME = new QName("http://iqgateway.training/", "dollarToRs");
    private final static QName _DollarToRsResponse_QNAME = new QName("http://iqgateway.training/", "dollarToRsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: training.iqgateway.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link YensToRs }
     * 
     */
    public YensToRs createYensToRs() {
        return new YensToRs();
    }

    /**
     * Create an instance of {@link EurosToRs }
     * 
     */
    public EurosToRs createEurosToRs() {
        return new EurosToRs();
    }

    /**
     * Create an instance of {@link YensToRsResponse }
     * 
     */
    public YensToRsResponse createYensToRsResponse() {
        return new YensToRsResponse();
    }

    /**
     * Create an instance of {@link EurosToRsResponse }
     * 
     */
    public EurosToRsResponse createEurosToRsResponse() {
        return new EurosToRsResponse();
    }

    /**
     * Create an instance of {@link DollarToRs }
     * 
     */
    public DollarToRs createDollarToRs() {
        return new DollarToRs();
    }

    /**
     * Create an instance of {@link DollarToRsResponse }
     * 
     */
    public DollarToRsResponse createDollarToRsResponse() {
        return new DollarToRsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YensToRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "yensToRs")
    public JAXBElement<YensToRs> createYensToRs(YensToRs value) {
        return new JAXBElement<YensToRs>(_YensToRs_QNAME, YensToRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EurosToRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "eurosToRs")
    public JAXBElement<EurosToRs> createEurosToRs(EurosToRs value) {
        return new JAXBElement<EurosToRs>(_EurosToRs_QNAME, EurosToRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YensToRsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "yensToRsResponse")
    public JAXBElement<YensToRsResponse> createYensToRsResponse(YensToRsResponse value) {
        return new JAXBElement<YensToRsResponse>(_YensToRsResponse_QNAME, YensToRsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EurosToRsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "eurosToRsResponse")
    public JAXBElement<EurosToRsResponse> createEurosToRsResponse(EurosToRsResponse value) {
        return new JAXBElement<EurosToRsResponse>(_EurosToRsResponse_QNAME, EurosToRsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DollarToRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "dollarToRs")
    public JAXBElement<DollarToRs> createDollarToRs(DollarToRs value) {
        return new JAXBElement<DollarToRs>(_DollarToRs_QNAME, DollarToRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DollarToRsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "dollarToRsResponse")
    public JAXBElement<DollarToRsResponse> createDollarToRsResponse(DollarToRsResponse value) {
        return new JAXBElement<DollarToRsResponse>(_DollarToRsResponse_QNAME, DollarToRsResponse.class, null, value);
    }

}
