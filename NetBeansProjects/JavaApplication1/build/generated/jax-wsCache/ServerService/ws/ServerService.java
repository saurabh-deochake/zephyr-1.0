
package ws;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2-hudson-752-
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ServerService", targetNamespace = "http://ws.negotiation/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ServerService {


    /**
     * 
     * @param product
     * @param attributeNames
     * @param type
     * @param agent
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://ws.negotiation/", className = "ws.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://ws.negotiation/", className = "ws.AddResponse")
    @Action(input = "http://ws.negotiation/ServerService/addRequest", output = "http://ws.negotiation/ServerService/addResponse")
    public String add(
        @WebParam(name = "agent", targetNamespace = "")
        String agent,
        @WebParam(name = "type", targetNamespace = "")
        AgentType type,
        @WebParam(name = "product", targetNamespace = "")
        String product,
        @WebParam(name = "attributeNames", targetNamespace = "")
        List<String> attributeNames);

    /**
     * 
     * @param ad
     * @return
     *     returns ws.Advertisement
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "update", targetNamespace = "http://ws.negotiation/", className = "ws.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://ws.negotiation/", className = "ws.UpdateResponse")
    @Action(input = "http://ws.negotiation/ServerService/updateRequest", output = "http://ws.negotiation/ServerService/updateResponse")
    public Advertisement update(
        @WebParam(name = "ad", targetNamespace = "")
        Advertisement ad);

    /**
     * 
     * @param message
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "send", targetNamespace = "http://ws.negotiation/", className = "ws.Send")
    @Action(input = "http://ws.negotiation/ServerService/send")
    public void send(
        @WebParam(name = "message", targetNamespace = "")
        SimpleMessage message);

    /**
     * 
     * @param product
     * @param agent
     * @return
     *     returns ws.SimpleMessage
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMessageFrom", targetNamespace = "http://ws.negotiation/", className = "ws.GetMessageFrom")
    @ResponseWrapper(localName = "getMessageFromResponse", targetNamespace = "http://ws.negotiation/", className = "ws.GetMessageFromResponse")
    @Action(input = "http://ws.negotiation/ServerService/getMessageFromRequest", output = "http://ws.negotiation/ServerService/getMessageFromResponse")
    public SimpleMessage getMessageFrom(
        @WebParam(name = "agent", targetNamespace = "")
        String agent,
        @WebParam(name = "product", targetNamespace = "")
        String product);

    /**
     * 
     * @param product
     * @param agent
     * @return
     *     returns java.util.List<ws.Candidate>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getInterlocutors", targetNamespace = "http://ws.negotiation/", className = "ws.GetInterlocutors")
    @ResponseWrapper(localName = "getInterlocutorsResponse", targetNamespace = "http://ws.negotiation/", className = "ws.GetInterlocutorsResponse")
    @Action(input = "http://ws.negotiation/ServerService/getInterlocutorsRequest", output = "http://ws.negotiation/ServerService/getInterlocutorsResponse")
    public List<Candidate> getInterlocutors(
        @WebParam(name = "agent", targetNamespace = "")
        String agent,
        @WebParam(name = "product", targetNamespace = "")
        String product);

    /**
     * 
     * @return
     *     returns java.util.List<ws.Advertisement>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllData", targetNamespace = "http://ws.negotiation/", className = "ws.GetAllData")
    @ResponseWrapper(localName = "getAllDataResponse", targetNamespace = "http://ws.negotiation/", className = "ws.GetAllDataResponse")
    @Action(input = "http://ws.negotiation/ServerService/getAllDataRequest", output = "http://ws.negotiation/ServerService/getAllDataResponse")
    public List<Advertisement> getAllData();

}
