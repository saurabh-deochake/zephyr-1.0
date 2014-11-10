package negotiation.db.bean.messages;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import negotiation.db.bean.matcher.Candidate;

@XmlRootElement
public class SimpleMessage {
    private Candidate from, to;
    private MessageType type;
    private int Id, replyId;
    private List<Issue> issues;
    
    public SimpleMessage() {}

    public SimpleMessage(Candidate from, Candidate to, MessageType type, int Id, int replyId, ArrayList<Issue> issues) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.Id = Id;
        this.replyId = replyId;
        this.issues = issues;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
    
    public Candidate getFrom() {
        return from;
    }

    public void setFrom(Candidate from) {
        this.from = from;
    }

    public Candidate getTo() {
        return to;
    }

    public void setTo(Candidate to) {
        this.to = to;
    }

    public void setType(MessageType type) {
        this.type = type;
    }
    
    public MessageType getType() {
        return type;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }
    
    public String toString() {
        return "[ " + from + ", " + to + ", " + type + ", " + Id + ", "+ replyId + ", [" + issues + "] ]";
    }
}