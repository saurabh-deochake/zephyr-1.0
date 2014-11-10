package negotiation;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import negotiation.db.Agent;
import negotiation.db.AgentType;
import negotiation.db.bean.AgentBeanLocal;
import negotiation.db.exception.AgentException;

@ManagedBean(eager=true)
@ApplicationScoped
public class AgentController {
    @EJB
    private AgentBeanLocal agentBean;
    
    private Agent agent;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
    
    private String name,password;
    
    private String test = "no";

    public String getTest() {
        return test;
    }
       
    public List<Agent> getAgents() {
        return agentBean.getAgents(name);
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String test() {
        return agentBean.sayHello();
    }

    public void loadPage(ActionEvent ae) {
        Map map = ae.getComponent().getAttributes();
        test = (String)map.get("value");
    }
    
    public void createNewAccount() {
        try {
            agentBean.addNew(new Agent(name, password,AgentType.Buyer));
        } catch(AgentException exception) {
            FacesContext context = FacesContext.getCurrentInstance();            
            context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR,"agent already exists",""));
        }
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        try {
            agent = agentBean.login(name);
            if(agent.verify(password)) {
                //context.addMessage("password", new FacesMessage("login successful"));
                return "products.xhtml";
                //return "Loading.xhtml";
            } else {
                context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR,"invalid password", ""));
            }
        } catch(AgentException exception) {
            context.addMessage("password", new FacesMessage(FacesMessage.SEVERITY_ERROR,"invalid agent name",""));
        }
        
        return "";
    }
public String logout(){
return "index.xhtml";
}
}
