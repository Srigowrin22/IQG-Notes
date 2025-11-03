
package tranining.iqgateway.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tranining.iqgateway.types package. 
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
    private final static QName _DollarsToRsResponse_QNAME = new QName("http://iqgateway.training/", "dollarsToRsResponse");
    private final static QName _YensToRsResponse_QNAME = new QName("http://iqgateway.training/", "yensToRsResponse");
    private final static QName _RiyalsToRsResponse_QNAME = new QName("http://iqgateway.training/", "riyalsToRsResponse");
    private final static QName _PoundsToRs_QNAME = new QName("http://iqgateway.training/", "poundsToRs");
    private final static QName _DollarsToRs_QNAME = new QName("http://iqgateway.training/", "dollarsToRs");
    private final static QName _RiyalsToRs_QNAME = new QName("http://iqgateway.training/", "riyalsToRs");
    private final static QName _PoundsToRsResponse_QNAME = new QName("http://iqgateway.training/", "poundsToRsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tranining.iqgateway.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DollarsToRsResponse }
     * 
     */
    public DollarsToRsResponse createDollarsToRsResponse() {
        return new DollarsToRsResponse();
    }

    /**
     * Create an instance of {@link YensToRs }
     * 
     */
    public YensToRs createYensToRs() {
        return new YensToRs();
    }

    /**
     * Create an instance of {@link YensToRsResponse }
     * 
     */
    public YensToRsResponse createYensToRsResponse() {
        return new YensToRsResponse();
    }

    /**
     * Create an instance of {@link RiyalsToRs }
     * 
     */
    public RiyalsToRs createRiyalsToRs() {
        return new RiyalsToRs();
    }

    /**
     * Create an instance of {@link DollarsToRs }
     * 
     */
    public DollarsToRs createDollarsToRs() {
        return new DollarsToRs();
    }

    /**
     * Create an instance of {@link RiyalsToRsResponse }
     * 
     */
    public RiyalsToRsResponse createRiyalsToRsResponse() {
        return new RiyalsToRsResponse();
    }

    /**
     * Create an instance of {@link PoundsToRs }
     * 
     */
    public PoundsToRs createPoundsToRs() {
        return new PoundsToRs();
    }

    /**
     * Create an instance of {@link PoundsToRsResponse }
     * 
     */
    public PoundsToRsResponse createPoundsToRsResponse() {
        return new PoundsToRsResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link DollarsToRsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "dollarsToRsResponse")
    public JAXBElement<DollarsToRsResponse> createDollarsToRsResponse(DollarsToRsResponse value) {
        return new JAXBElement<DollarsToRsResponse>(_DollarsToRsResponse_QNAME, DollarsToRsResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link RiyalsToRsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "riyalsToRsResponse")
    public JAXBElement<RiyalsToRsResponse> createRiyalsToRsResponse(RiyalsToRsResponse value) {
        return new JAXBElement<RiyalsToRsResponse>(_RiyalsToRsResponse_QNAME, RiyalsToRsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoundsToRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "poundsToRs")
    public JAXBElement<PoundsToRs> createPoundsToRs(PoundsToRs value) {
        return new JAXBElement<PoundsToRs>(_PoundsToRs_QNAME, PoundsToRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DollarsToRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "dollarsToRs")
    public JAXBElement<DollarsToRs> createDollarsToRs(DollarsToRs value) {
        return new JAXBElement<DollarsToRs>(_DollarsToRs_QNAME, DollarsToRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RiyalsToRs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "riyalsToRs")
    public JAXBElement<RiyalsToRs> createRiyalsToRs(RiyalsToRs value) {
        return new JAXBElement<RiyalsToRs>(_RiyalsToRs_QNAME, RiyalsToRs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PoundsToRsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://iqgateway.training/", name = "poundsToRsResponse")
    public JAXBElement<PoundsToRsResponse> createPoundsToRsResponse(PoundsToRsResponse value) {
        return new JAXBElement<PoundsToRsResponse>(_PoundsToRsResponse_QNAME, PoundsToRsResponse.class, null, value);
    }

}
