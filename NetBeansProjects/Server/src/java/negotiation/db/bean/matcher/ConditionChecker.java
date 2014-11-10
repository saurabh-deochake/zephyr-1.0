package negotiation.db.bean.matcher;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import negotiation.db.Advertisement;
import negotiation.db.Status;
import negotiation.db.bean.AdvertisementBean;

@Singleton
@LocalBean
public class ConditionChecker {

    @EJB
    private AdvertisementBean advertisementBean;
    List<NegotiationSpace> spaces = new ArrayList<NegotiationSpace>();
    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;

    public List<NegotiationSpace> getUnits() {
        return spaces;
    }
    
    public List<Advertisement> get(Status status) {
        TypedQuery<Advertisement> advertisementsQuery = em.createNamedQuery("getWithStatus", Advertisement.class);
        advertisementsQuery.setParameter("status", status);
        return advertisementsQuery.getResultList();
    }

    public ArrayList<Candidate> getInterlocutors(String agent,String product) {
        ArrayList<Candidate> candidates;
        
        for(NegotiationSpace space : spaces) {
            candidates = space.getInterlocutors(agent, product);
            if(candidates.size() > 0)
                return candidates;
        }
        return null;
    }
    
    public String match() {
        List<Advertisement> newAds = get(Status.New);
        String matches = "//search :";

        if (newAds.size() > 0) {
            for (Advertisement ad : newAds) {

                matches = matches + " " + ad;
                boolean newUnitNeeded = true;

                for (NegotiationSpace unit : spaces) {
                    if (unit.add(ad)) {
                        ad.setStatus(Status.Matched);
                        advertisementBean.update(ad);
                        newUnitNeeded = false;
                    }
                }

                if (newUnitNeeded) {
                    NegotiationSpace newUnit = new NegotiationSpace(ad);
                    ad.setStatus(Status.Matched);
                    advertisementBean.update(ad);
                    spaces.add(newUnit);
                }

                matches = matches + " newUnitNeeded" + newUnitNeeded;
            }
        }

        return matches;
    }
}