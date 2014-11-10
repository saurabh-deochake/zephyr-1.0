package negotiation.db.bean;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import negotiation.db.Advertisement;
import negotiation.db.AgentType;
import negotiation.db.Attribute;
import negotiation.db.Status;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class AdvertisementBean {

    @Resource
    private UserTransaction ut;
    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;
    
    public Advertisement update(Advertisement ad) {
        try {
            ut.begin();
            ad = em.merge(ad);
            ut.commit();
            
            
        } catch (RollbackException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(AdvertisementBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ad;
    }
    
    public List<Advertisement> getAllData() {
        TypedQuery<Advertisement> advertisementsQuery = em.createNamedQuery("getAll", Advertisement.class);
        return advertisementsQuery.getResultList();
    }

    public String add(String agent, AgentType type, String product, Set<String> attributeNames) {
        boolean success = false;
        String message;

        try {
            ut.begin();

            Advertisement advertisement = new Advertisement(agent, type, product, Status.New);

            for (String attributeName : attributeNames) {
                Attribute attribute;

                TypedQuery<Attribute> findAttributeQuery = em.createNamedQuery("findAttribute", Attribute.class);
                findAttributeQuery.setParameter("name", attributeName);
                if (findAttributeQuery.getResultList().size() > 0)
                    attribute = findAttributeQuery.getSingleResult();
                else
                    attribute = new Attribute(attributeName);

                advertisement.getAttributes().add(attribute);
                attribute.getAdvertisements().add(advertisement);
            }

            em.persist(advertisement);

            success = true;
            message = "success " + agent;
            ut.commit();
        } catch (RollbackException ex) {
            message = ex.getCause().toString();
        } catch (HeuristicMixedException ex) {
            message = ex.toString();
        } catch (HeuristicRollbackException ex) {
            message = ex.toString();
        } catch (SecurityException ex) {
            message = ex.toString();
        } catch (IllegalStateException ex) {
            message = ex.toString();
        } catch (NotSupportedException ex) {
            message = ex.toString();
        } catch (SystemException ex) {
            message = ex.toString();
        } finally {
            try {
                if (!success) {
                    ut.rollback();
                }
            } catch (Exception ex) {
                return ex.toString();
            }
        }

        return message;
    }
}
