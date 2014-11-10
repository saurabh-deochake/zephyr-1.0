package negotiation.db.bean.messages;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

class PathComparator implements Comparator<Path> {
    @Override
    public int compare(Path p1, Path p2) {
        String path1 = p1.from + ":" + p1.to;
        String path2 = p2.from + ":" + p2.to;
        
        return path1.compareTo(path2);
    }
}

@Singleton
@LocalBean
public class Buffer {  
    private Map<Path,SimpleMessage> messages = new TreeMap<Path, SimpleMessage>(new PathComparator());
    
    public Set<Entry<Path, SimpleMessage>> getAllMessages() {
        return messages.entrySet();
    }
    
    public void addMessage(SimpleMessage message) {
        messages.put(new Path(message.getFrom(),message.getTo()), message);
    }
    
    public SimpleMessage getMessageFrom(Path path) {
        return messages.get(path);
    }
    
    public void clear() {
        messages.clear();
    }
}