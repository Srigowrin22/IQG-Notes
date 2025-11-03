package training.iqgateway.services;

import java.util.List;

import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.TypedQuery;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.RoleEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.model.VehicleOffenceEO;

@Stateless(name = "ClerkSessionEJB",
           mappedName = "TMSJEEApp-Model-ClerkSessionEJB")
public class ClerkSessionEJBBean implements ClerkSessionEJB,
                                            ClerkSessionEJBLocal {
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public ClerkSessionEJBBean() {
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

    // --- Admin Methods ---

    public AdminEO findAdminByDesigID(String designationID) {
        return em.find(AdminEO.class, designationID);
    }

    public List<AdminEO> findAdminByName(String name) {
        TypedQuery<AdminEO> q =
            em.createQuery("SELECT a FROM AdminEO a WHERE a.name LIKE :name",
                           AdminEO.class);
        return q.setParameter("name", name).getResultList();
    }

    /** <code>select o from AdminEO o</code> */
    public List<AdminEO> getAdminEOFindAll() {
        return em.createNamedQuery("AdminEO.findAll").getResultList();
    }

    // --- OffenceTypes Methods ---

    public OffenceTypesEO findOffenceTypeByID(Long offenceID) {
        return em.find(OffenceTypesEO.class, offenceID);
    }

    /** <code>select o from OffenceTypesEO o</code> */
    public List<OffenceTypesEO> getOffenceTypesEOFindAll() {
        return em.createNamedQuery("OffenceTypesEO.findAll").getResultList();
    }

    public List<OffenceTypesEO> findOffenceByVehicleType(String vehicleType) {
        TypedQuery<OffenceTypesEO> q =
            em.createQuery("SELECT o FROM OffenceTypesEO o WHERE o.vehicleType = :vehicleType",
                           OffenceTypesEO.class);
        return q.setParameter("vehicleType", vehicleType).getResultList();
    }

    public List<OffenceTypesEO> findOffenceByOffenceType(String offenceType) {
        Query q =
            em.createQuery("SELECT o FROM OffenceTypesEO o WHERE o.offenceType LIKE :offenceType");
        return q.setParameter("offenceType", offenceType).getResultList();
    }

    // --- Owner Methods ---

    public OwnerEO findOwnerByAadhar(String ownerAadhar) {
        Query q =
            em.createQuery("SELECT o FROM OwnerEO o WHERE o.ownerAadhar = :aadhar");
        List<OwnerEO> result =
            q.setParameter("aadhar", ownerAadhar).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /** <code>select o from OwnerEO o</code> */
    public List<OwnerEO> getOwnerEOFindAll() {
        return em.createNamedQuery("OwnerEO.findAll").getResultList();
    }

    public List<OwnerEO> findOwnerByName(String ownerName) {
        Query q =
            em.createQuery("SELECT o FROM OwnerEO o WHERE o.ownerName LIKE :ownerName");
        return q.setParameter("ownerName", ownerName).getResultList();
    }

    // --- Role Methods ---

    /** <code>select o from RoleEO o</code> */
    public List<RoleEO> getRoleEOFindAll() {
        return em.createNamedQuery("RoleEO.findAll").getResultList();
    }

    // --- Registration Methods ---

    public RegistrationEO findRegistrationByID(String regisID) {
        return em.find(RegistrationEO.class, regisID);
    }

    /** <code>select o from RegistrationEO o</code> */
    public List<RegistrationEO> getRegistrationEOFindAll() {
        return em.createNamedQuery("RegistrationEO.findAll").getResultList();
    }

    // --- Vehicle Methods ---

    public VehicleEO findVehicleByID(Long vehicleID) {
        return em.find(VehicleEO.class, vehicleID);
    }

    /** <code>select o from VehicleEO o</code> */
    public List<VehicleEO> getVehicleEOFindAll() {
        return em.createNamedQuery("VehicleEO.findAll").getResultList();
    }

    // --- VehicleOffence Methods ---

    public VehicleOffenceEO persistVehicleOffenceEO(VehicleOffenceEO vehOffEO) {
        //        Query q =
        //            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID");
        //        List<VehicleOffenceEO> vehOffEOList =
        //            q.setParameter("regisID", vehOffEO.getRegistrationEO().getRegistrationId()).getResultList();
        //        if (!vehOffEOList.isEmpty()) {
        //            for (VehicleOffenceEO vehOffence : vehOffEOList) {
        //                if (vehOffEO.getOffenceTypesEO().getOffenceId().equals(vehOffence.getOffenceTypesEO().getOffenceId()) &&
        //                    vehOffEO.getOffenceDate().equals(vehOffence.getOffenceDate()) &&
        //                    vehOffEO.getLocation().equals(vehOffence.getLocation())) {
        //                    return null;
        //                }
        //            }
        //        }
        em.persist(vehOffEO);
        return vehOffEO;
    }

    public VehicleOffenceEO mergeVehicleOffenceEO(VehicleOffenceEO vehOffEO) {
        return em.merge(vehOffEO);
    }

//    public Boolean removeVehicleOffenceEO(VehicleOffenceEO vehOffEO) {
//        VehicleOffenceEO managed =
//            em.find(VehicleOffenceEO.class, vehOffEO.getVehicleOffenceId());
//        if (managed != null) {
//            managed.setStatus(1L);
//            managed.setProof1(null);
//            managed.setProof2(null);
//            em.merge(managed);
//            return true;
//        }
//        return false;
//    }
    
    public Boolean removeVehicleOffenceEO(VehicleOffenceEO vehOffEO) {
        try {
            VehicleOffenceEO managed = 
                em.find(VehicleOffenceEO.class, vehOffEO.getVehicleOffenceId());
            
            if (managed != null) {
                // Only remove if status is pending (0)
                if (managed.getStatus() != null && managed.getStatus() == 0L) {
                    managed.setStatus(1L); // Mark as cleared
                    em.merge(managed);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public VehicleOffenceEO findVehicleOffenceByID(Long vehicleOffenceID) {
        return em.find(VehicleOffenceEO.class, vehicleOffenceID);
    }

    /** <code>select o from VehicleOffenceEO o</code> */
    public List<VehicleOffenceEO> getVehicleOffenceEOFindAll() {
        return em.createNamedQuery("VehicleOffenceEO.findAll").getResultList();
    }

    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID) {
        Query q =
            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID");
        return q.setParameter("regisID", regisID).getResultList();
    }

    //    public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
    //                                                             Long status) {
    //        Query q =
    //            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationID = :regisID AND v.status = :status");
    //        return q.setParameter("regisID", registrationID).setParameter("status",
    //                                                                      status).getResultList();
    //    }

    public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
                                                             Long status) {
        Query q =
            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID AND v.status = :status");
        return q.setParameter("regisID", registrationID).setParameter("status",
                                                                      status).getResultList();
    }


    // --- Login Logic ---

    public boolean login(AdminEO adminEO) {
        if (adminEO == null || adminEO.getName() == null ||
            adminEO.getDesignationId() == null ||
            adminEO.getPassword() == null) {
            return false;
        }
        AdminEO user = em.find(AdminEO.class, adminEO.getDesignationId());
        if (user != null && user.getName().equals(adminEO.getName()) &&
            user.getPassword().equals(adminEO.getPassword()) &&
            user.getSignup() == 1) {
            return true;
        }
        return false;
    }
}


