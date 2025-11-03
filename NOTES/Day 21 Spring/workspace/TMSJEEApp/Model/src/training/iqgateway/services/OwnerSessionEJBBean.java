package training.iqgateway.services;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.model.VehicleOffenceEO;

@Stateless(name = "OwnerSessionEJB",
           mappedName = "TMSJEEApp-Model-OwnerSessionEJB")
public class OwnerSessionEJBBean implements OwnerSessionEJB,
                                            OwnerSessionEJBLocal {
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public OwnerSessionEJBBean() {
    }

    public Object queryByRange(String jpqlStmt, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    // --- Owner CRUD ---

    public OwnerEO persistOwnerEO(OwnerEO ownerEO) {
        em.persist(ownerEO);
        return ownerEO;
    }

    public OwnerEO mergeOwnerEO(OwnerEO ownerEO) {
        return em.merge(ownerEO);
    }

    public Boolean removeOwnerEO(OwnerEO ownerEO) {
        OwnerEO managed = em.find(OwnerEO.class, ownerEO.getOwnerAadhar());
        if (managed != null) {
            em.remove(managed);
            return true;
        }
        return false;
    }

    // --- Find Owner by Aadhar ---

    public OwnerEO findOwnerByAadhar(String aadhar) {
        Query q =
            em.createQuery("SELECT o FROM OwnerEO o WHERE o.ownerAadhar = :aadhar");
        q.setParameter("aadhar", aadhar);
        List results = q.getResultList();
        return results.isEmpty() ? null : (OwnerEO)results.get(0);
    }

    // --- Find Registration by Owner Aadhar ---

    public List<RegistrationEO> findRegistrationByAadhar(String aadhar) {
        Query q =
            em.createQuery("SELECT r FROM RegistrationEO r WHERE r.ownerEO.ownerAadhar = :aadhar");
        q.setParameter("aadhar", aadhar);
        return q.getResultList();
    }

    // --- Find Registration by ID ---

    public RegistrationEO findRegistrationByID(String regisID) {
        return em.find(RegistrationEO.class, regisID);
    }

    // --- Find Vehicle by ID ---

    public VehicleEO findVehicleByID(Long vehicleID) {
        return em.find(VehicleEO.class, vehicleID);
    }

    // --- Find VehicleOffence by RegisID ---

    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID) {
        Query q =
            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID");
        q.setParameter("regisID", regisID);
        return q.getResultList();
    }

    // --- Find VehicleOffence by ID ---

    public VehicleOffenceEO findVehicleOffenceByID(Long vehicleOffenceID) {
        return em.find(VehicleOffenceEO.class, vehicleOffenceID);
    }

    // --- Clear Vehicle Offence ---

    public Boolean removeVehicleOffenceEO(Long vehicleOffenceID) {
        VehicleOffenceEO managed =
            em.find(VehicleOffenceEO.class, vehicleOffenceID);
        if (managed != null) {
            managed.setStatus(1L);
            managed.setProof1(null);
            managed.setProof2(null);
            em.merge(managed);
            return true;
        }
        return false;
    }

    // --- Find OffenceType by ID ---

    public OffenceTypesEO findOffenceTypeByID(Long offenceID) {
        return em.find(OffenceTypesEO.class, offenceID);
    }

    // --- Owner login ---
    //
    //    public boolean login(String ownerAadhar, String ownerName,
    //                         String password) {
    //        Query q =
    //            em.createQuery("SELECT o FROM OwnerEO o WHERE o.ownerAadhar = :aadhar");
    //        q.setParameter("aadhar", ownerAadhar);
    //        List results = q.getResultList();
    //        if (results.isEmpty())
    //            return false;
    //        OwnerEO user = (OwnerEO)results.get(0);
    //        return user.getOwnerName().equals(ownerName) &&
    //            user.getPassword().equals(password);
    //    }
    //}


    public boolean login(String ownerAadhar, String ownerName,
                         String password) {
        Query q =
            em.createQuery("SELECT o FROM OwnerEO o WHERE o.ownerAadhar = :aadhar");
        q.setParameter("aadhar", ownerAadhar);
        List<OwnerEO> results = q.getResultList();
        if (results.isEmpty())
            return false;
        OwnerEO user = results.get(0);
        return user.getOwnerName().equals(ownerName) &&
            user.getPassword().equals(password);
    }

}
