package negotiation.db.bean.matcher;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import negotiation.db.Advertisement;
import negotiation.db.AdvertisementId;
import negotiation.db.AgentType;

public class NegotiationSpace {

    private Set<Advertisement> ads = new TreeSet<Advertisement>();
    private Advertisement adInitial;

    public Set<Advertisement> getAds() {
        return ads;
    }

    public ArrayList<Candidate> getInterlocutors(String agent, String product) {
        ArrayList<Candidate> candidates = new ArrayList<Candidate>();
        AdvertisementId id = new AdvertisementId(agent, product);
        AgentType type = null;
        boolean thisSpace = false;

        for (Advertisement ad : ads)
            if (ad.getId().equals(id)) {
                thisSpace = true;
                type = ad.getType();
            }
        
        if(thisSpace)
            for(Advertisement ad : ads)
                if(!ad.getId().equals(id) & !ad.getType().equals(type))
                    //agents.add(ad.getId().getAgent());
                    candidates.add(new Candidate(ad.getId().getAgent(),ad.getId().getProduct()));

        return candidates;
    }

    public NegotiationSpace(Advertisement ad) {
        adInitial = ad;
        ads.add(ad);
    }

    public boolean add(Advertisement advertisement) {
        for (Advertisement ad : ads) {
            if (advertisement.getAttributes().containsAll(ad.getAttributes())) {
                ads.add(advertisement);
                return true;
            }
        }

        return false;
    }

    Advertisement getInitialAdvertisement() {
        return adInitial;
    }

    public String toString() {
        return ads.toString();
    }
}
