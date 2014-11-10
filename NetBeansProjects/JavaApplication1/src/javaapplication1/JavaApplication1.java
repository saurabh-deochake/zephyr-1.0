package javaapplication1;

import ws.Candidate;
import ws.MessageType;
import ws.SimpleMessage;

public class JavaApplication1 {
    public static void main(String[] args) {
        Candidate from = new Candidate(), to = new Candidate();
        from.setAgent("a");
        from.setProduct("a");
        to.setAgent("b");
        to.setProduct("b");
        
        SimpleMessage msg = new SimpleMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setType(MessageType.HELLO);
        
        send(msg);
        
        SimpleMessage msg2 = getMessageFrom("a", "a");
        System.out.println(msg2);
    }

    private static void send(ws.SimpleMessage message) {
        ws.ServerService_Service service = new ws.ServerService_Service();
        ws.ServerService port = service.getServerServicePort();
        port.send(message);
    }

    private static SimpleMessage getMessageFrom(java.lang.String agent, java.lang.String product) {
        ws.ServerService_Service service = new ws.ServerService_Service();
        ws.ServerService port = service.getServerServicePort();
        return port.getMessageFrom(agent, product);
    }
}