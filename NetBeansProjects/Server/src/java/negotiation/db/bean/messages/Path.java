package negotiation.db.bean.messages;

import javax.xml.bind.annotation.XmlRootElement;
import negotiation.db.bean.matcher.Candidate;

@XmlRootElement
public class Path {
    Candidate from, to;
    
    public Path(Candidate from, Candidate to) {
        this.from = from;
        this.to = to;
    }
}