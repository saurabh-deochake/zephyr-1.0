
package negotiation.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the negotiation.ws package. 
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

    private final static QName _Issue_QNAME = new QName("http://ws.negotiation/", "issue");
    private final static QName _GetMessageFromResponse_QNAME = new QName("http://ws.negotiation/", "getMessageFromResponse");
    private final static QName _AddResponse_QNAME = new QName("http://ws.negotiation/", "addResponse");
    private final static QName _GetMessageFrom_QNAME = new QName("http://ws.negotiation/", "getMessageFrom");
    private final static QName _Send_QNAME = new QName("http://ws.negotiation/", "send");
    private final static QName _Update_QNAME = new QName("http://ws.negotiation/", "update");
    private final static QName _GetAllDataResponse_QNAME = new QName("http://ws.negotiation/", "getAllDataResponse");
    private final static QName _SimpleMessage_QNAME = new QName("http://ws.negotiation/", "simpleMessage");
    private final static QName _Add_QNAME = new QName("http://ws.negotiation/", "add");
    private final static QName _Candidate_QNAME = new QName("http://ws.negotiation/", "candidate");
    private final static QName _UpdateResponse_QNAME = new QName("http://ws.negotiation/", "updateResponse");
    private final static QName _GetAllData_QNAME = new QName("http://ws.negotiation/", "getAllData");
    private final static QName _GetInterlocutorsResponse_QNAME = new QName("http://ws.negotiation/", "getInterlocutorsResponse");
    private final static QName _GetInterlocutors_QNAME = new QName("http://ws.negotiation/", "getInterlocutors");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: negotiation.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link GetMessageFrom }
     * 
     */
    public GetMessageFrom createGetMessageFrom() {
        return new GetMessageFrom();
    }

    /**
     * Create an instance of {@link Send }
     * 
     */
    public Send createSend() {
        return new Send();
    }

    /**
     * Create an instance of {@link GetMessageFromResponse }
     * 
     */
    public GetMessageFromResponse createGetMessageFromResponse() {
        return new GetMessageFromResponse();
    }

    /**
     * Create an instance of {@link Issue }
     * 
     */
    public Issue createIssue() {
        return new Issue();
    }

    /**
     * Create an instance of {@link Candidate }
     * 
     */
    public Candidate createCandidate() {
        return new Candidate();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link GetAllDataResponse }
     * 
     */
    public GetAllDataResponse createGetAllDataResponse() {
        return new GetAllDataResponse();
    }

    /**
     * Create an instance of {@link SimpleMessage }
     * 
     */
    public SimpleMessage createSimpleMessage() {
        return new SimpleMessage();
    }

    /**
     * Create an instance of {@link GetAllData }
     * 
     */
    public GetAllData createGetAllData() {
        return new GetAllData();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link GetInterlocutors }
     * 
     */
    public GetInterlocutors createGetInterlocutors() {
        return new GetInterlocutors();
    }

    /**
     * Create an instance of {@link GetInterlocutorsResponse }
     * 
     */
    public GetInterlocutorsResponse createGetInterlocutorsResponse() {
        return new GetInterlocutorsResponse();
    }

    /**
     * Create an instance of {@link Advertisement }
     * 
     */
    public Advertisement createAdvertisement() {
        return new Advertisement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Issue }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "issue")
    public JAXBElement<Issue> createIssue(Issue value) {
        return new JAXBElement<Issue>(_Issue_QNAME, Issue.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessageFromResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "getMessageFromResponse")
    public JAXBElement<GetMessageFromResponse> createGetMessageFromResponse(GetMessageFromResponse value) {
        return new JAXBElement<GetMessageFromResponse>(_GetMessageFromResponse_QNAME, GetMessageFromResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMessageFrom }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "getMessageFrom")
    public JAXBElement<GetMessageFrom> createGetMessageFrom(GetMessageFrom value) {
        return new JAXBElement<GetMessageFrom>(_GetMessageFrom_QNAME, GetMessageFrom.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Send }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "send")
    public JAXBElement<Send> createSend(Send value) {
        return new JAXBElement<Send>(_Send_QNAME, Send.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "getAllDataResponse")
    public JAXBElement<GetAllDataResponse> createGetAllDataResponse(GetAllDataResponse value) {
        return new JAXBElement<GetAllDataResponse>(_GetAllDataResponse_QNAME, GetAllDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SimpleMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "simpleMessage")
    public JAXBElement<SimpleMessage> createSimpleMessage(SimpleMessage value) {
        return new JAXBElement<SimpleMessage>(_SimpleMessage_QNAME, SimpleMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Candidate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "candidate")
    public JAXBElement<Candidate> createCandidate(Candidate value) {
        return new JAXBElement<Candidate>(_Candidate_QNAME, Candidate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "updateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "getAllData")
    public JAXBElement<GetAllData> createGetAllData(GetAllData value) {
        return new JAXBElement<GetAllData>(_GetAllData_QNAME, GetAllData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInterlocutorsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "getInterlocutorsResponse")
    public JAXBElement<GetInterlocutorsResponse> createGetInterlocutorsResponse(GetInterlocutorsResponse value) {
        return new JAXBElement<GetInterlocutorsResponse>(_GetInterlocutorsResponse_QNAME, GetInterlocutorsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInterlocutors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.negotiation/", name = "getInterlocutors")
    public JAXBElement<GetInterlocutors> createGetInterlocutors(GetInterlocutors value) {
        return new JAXBElement<GetInterlocutors>(_GetInterlocutors_QNAME, GetInterlocutors.class, null, value);
    }

}
