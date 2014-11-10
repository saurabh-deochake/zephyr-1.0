package negotiation.ws;

import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import negotiation.db.Advertisement;
import negotiation.db.AgentType;
import negotiation.db.bean.AdvertisementBean;
import negotiation.db.bean.matcher.Candidate;
import negotiation.db.bean.matcher.ConditionChecker;
import negotiation.db.bean.messages.Buffer;
import negotiation.db.bean.messages.Path;
import negotiation.db.bean.messages.SimpleMessage;

@WebService(serviceName = "ServerService")
public class ServerService {
    @EJB
    private Buffer buffer;
    @EJB
    private ConditionChecker conditionChecker;
    @EJB
    private AdvertisementBean ejbRef;
    
    @WebMethod(operationName = "update")
    public Advertisement update(@WebParam(name = "ad") Advertisement ad) {
        return ejbRef.update(ad);
    }

    @WebMethod(operationName = "getAllData")
    public List<Advertisement> getAllData() {
        return ejbRef.getAllData();
    }

    @WebMethod(operationName = "add")
    public String add(@WebParam(name = "agent") String agent, @WebParam(name = "type") AgentType type, @WebParam(name = "product") String product, @WebParam(name = "attributeNames") Set<String> attributeNames) {
        return ejbRef.add(agent, type, product, attributeNames);
    }

    @WebMethod(operationName = "getInterlocutors")
    public List<Candidate> getInterlocutors(@WebParam(name = "agent") String agent, @WebParam(name = "product") String product) {
        return conditionChecker.getInterlocutors(agent, product);
    }

    @WebMethod(operationName = "send")
    @Oneway
    public void send(@WebParam(name = "message") SimpleMessage message) {
        buffer.addMessage(message);
    }

    @WebMethod(operationName = "getMessageFrom")
    public SimpleMessage getMessageForPath(@WebParam(name = "fromAgent") String fromAgent,
                                           @WebParam(name = "fromProduct") String fromProduct,
                                           @WebParam(name = "toAgent") String toAgent,
                                           @WebParam(name = "toProduct") String toProduct) {
        Path path = new Path(new Candidate(fromAgent, fromProduct), new Candidate(toAgent, toProduct));
        SimpleMessage message = buffer.getMessageFrom(path);
        return message;
    }
}