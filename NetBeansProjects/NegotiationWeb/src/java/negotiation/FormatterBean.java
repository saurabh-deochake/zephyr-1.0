package negotiation;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class FormatterBean {
    List<String> images;
    
    @PostConstruct
    public void initialize() {
        images = new ArrayList<String>();
        images.add("GlobalAgents.jpg");
        images.add("hierarchy.jpg");
        images.add("Concurrency.jpg");
    }
    
    public List<String> getImages() {
        return images;
    }
}