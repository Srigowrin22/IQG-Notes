package training.iqgateway.services;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.OffenceTypesEO;
import training.iqgateway.model.OwnerEO;
import training.iqgateway.model.RegistrationEO;
import training.iqgateway.model.RoleEO;
import training.iqgateway.model.VehicleEO;
import training.iqgateway.model.VehicleOffenceEO;

@Stateless(name = "RTOSessionEJB",
           mappedName = "TMSJEEApp-Model-RTOSessionEJB")
public class RTOSessionEJBBean implements RTOSessionEJB, RTOSessionEJBLocal {
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public RTOSessionEJBBean() {
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
        Query q =
            em.createQuery("SELECT a FROM AdminEO a WHERE a.name LIKE :name");
        q.setParameter("name", name);
        return q.getResultList();
    }

    /** <code>select o from AdminEO o</code> */
    public List<AdminEO> getAdminEOFindAll() {
        return em.createNamedQuery("AdminEO.findAll").getResultList();
    }

    // --- OffenceTypes Methods ---

    public String persistOffenceTypesEO(OffenceTypesEO offenceEO) {
        if (offenceEO.getOffenceType() == null ||
            offenceEO.getPenaltyAmt() == null ||
            offenceEO.getVehicleType() == null) {
            return "Error : Incomplete fields";
        }
        Query q =
            em.createQuery("SELECT o FROM OffenceTypesEO o WHERE o.offenceType = :offenceType AND o.vehicleType = :vehicleType");
        q.setParameter("offenceType", offenceEO.getOffenceType());
        q.setParameter("vehicleType", offenceEO.getVehicleType());
        if (!q.getResultList().isEmpty()) {
            return "OffenceType already exists";
        }
        em.persist(offenceEO);
        return "Succesfully added";
    }

    public String mergeOffenceTypeEO(OffenceTypesEO offenceEO) {
        if (offenceEO.getOffenceType() == null ||
            offenceEO.getPenaltyAmt() == null ||
            offenceEO.getVehicleType() == null) {
            return "Error : Incomplete fields";
        }
        OffenceTypesEO managed =
            em.find(OffenceTypesEO.class, offenceEO.getOffenceId());
        if (managed == null)
            return "Failed to update";
        em.merge(offenceEO);
        return "Succesfully updated";
    }

    public Boolean removeOffenceTypeEO(Integer offenceID) {
        if (offenceID == null || offenceID <= 0)
            return false;
        OffenceTypesEO managed =
            em.find(OffenceTypesEO.class, offenceID.longValue());
        if (managed != null) {
            em.remove(managed);
            return true;
        }
        return false;
    }

    public OffenceTypesEO findOffenceTypeByID(Integer offenceID) {
        return em.find(OffenceTypesEO.class,
                       offenceID == null ? null : offenceID.longValue());
    }

    /** <code>select o from OffenceTypesEO o</code> */
    public List<OffenceTypesEO> getOffenceTypesEOFindAll() {
        return em.createNamedQuery("OffenceTypesEO.findAll").getResultList();
    }

    public List<OffenceTypesEO> findOffenceByVehicleType(String vehicleType) {
        Query q =
            em.createQuery("SELECT o FROM OffenceTypesEO o WHERE o.vehicleType = :vehicleType");
        q.setParameter("vehicleType", vehicleType);
        return q.getResultList();
    }

    public List<OffenceTypesEO> findOffenceByOffenceType(String offenceType) {
        Query q =
            em.createQuery("SELECT o FROM OffenceTypesEO o WHERE o.offenceType = :offenceType");
        q.setParameter("offenceType", offenceType);
        return q.getResultList();
    }

    public OffenceTypesEO findOffenceByTypeAndVehicle(String offenceType,
                                                      String vehicleType) {
        Query q =
            em.createQuery("SELECT o FROM OffenceTypesEO o WHERE UPPER(o.offenceType) = :offenceType AND UPPER(o.vehicleType) = :vehicleType");
        q.setParameter("offenceType", offenceType.toUpperCase());
        q.setParameter("vehicleType", vehicleType.toUpperCase());
        List<OffenceTypesEO> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    // --- Owner Methods ---

    public int persistOwnerEO(OwnerEO ownerEO) {
        if (ownerEO.getOwnerName() == null ||
            ownerEO.getOwnerAadhar() == null ||
            ownerEO.getPassword() == null || ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return 0;
        }
        em.persist(ownerEO);
        return 1;
    }

    public int mergeOwnerEO(OwnerEO ownerEO) {
        if (ownerEO.getOwnerName() == null ||
            ownerEO.getOwnerAadhar() == null || ownerEO.getPhone() == null ||
            ownerEO.getAddress() == null) {
            return 0;
        }
        OwnerEO managed = em.find(OwnerEO.class, ownerEO.getOwnerAadhar());
        if (managed == null)
            return 0;
        em.merge(ownerEO);
        return 1;
    }

    public OwnerEO findOwnerByAadhar(String ownerAadhar) {
        return em.find(OwnerEO.class, ownerAadhar);
    }

    /** <code>select o from OwnerEO o</code> */
    public List<OwnerEO> getOwnerEOFindAll() {
        return em.createNamedQuery("OwnerEO.findAll").getResultList();
    }

    public List<OwnerEO> findOwnerByName(String ownerName) {
        Query q =
            em.createQuery("SELECT o FROM OwnerEO o WHERE o.ownerName LIKE :ownerName");
        q.setParameter("ownerName", ownerName);
        return q.getResultList();
    }

    // --- Role Methods ---

    /** <code>select o from RoleEO o</code> */
    public List<RoleEO> getRoleEOFindAll() {
        return em.createNamedQuery("RoleEO.findAll").getResultList();
    }

    // --- Registration Methods ---

    public String persistRegistrationEO(RegistrationEO regisEO) {
        if (regisEO.getRegistrationId() == null ||
            regisEO.getLocation() == null || regisEO.getVehicleEO() == null ||
            regisEO.getOwnerEO() == null) {
            return "Error : Incomplete fields";
        }
        VehicleEO vehicleEO =
            em.find(VehicleEO.class, regisEO.getVehicleEO().getVehicleId());
        if (vehicleEO == null) {
            return "Error Vehicle Not Present!";
        }
        Query q =
            em.createQuery("SELECT r FROM RegistrationEO r WHERE r.vehicleEO.vehicleId = :vehId");
        q.setParameter("vehId", regisEO.getVehicleEO().getVehicleId());
        if (!q.getResultList().isEmpty()) {
            return "Error Vehicle ID already registered!";
        }
        RegistrationEO regis =
            em.find(RegistrationEO.class, regisEO.getRegistrationId());
        if (regis != null) {
            return "Error Registration ID already exists!";
        }
        em.persist(regisEO);
        return "Succesfully added";
    }

    public String mergeRegistrationEO(RegistrationEO regisEO) {
        if (regisEO.getRegistrationId() == null) {
            return "Error : Incomplete fields";
        }
        RegistrationEO regis = em.find(RegistrationEO.class, regisEO.getRegistrationId());
        if (regis == null) {
            return "Registration ID does not exist";
        }
        String regisID = regis.getRegistrationId();
        String oldAadhar = regis.getOwnerEO().getOwnerAadhar();
        String newAadhar = regisEO.getOwnerEO().getOwnerAadhar();

        // If owner is being changed (transfer)
        if (!oldAadhar.equals(newAadhar)) {
            // Check for pending offences
            Query q = em.createQuery(
                "SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID AND v.status = 0"
            );
            q.setParameter("regisID", regisID);
            List<VehicleOffenceEO> vehOffEO = q.getResultList();
            if (!vehOffEO.isEmpty()) {
                return "Clear the Vehicle Offence Before Transfer/Update";
            }
        }
        // Now safe to merge (transfer or update)
        em.merge(regisEO);
        return "Successfully updated";
    }

    public String transferRegistrationEO(RegistrationEO regisEO) {
        if (regisEO.getRegistrationId() == null) {
            return "Error : Incomplete fields";
        }
        RegistrationEO regis =
            em.find(RegistrationEO.class, regisEO.getRegistrationId());
        if (regis == null) {
            return "Registration ID doest not exist";
        }
        String regisID = regis.getRegistrationId();
        Query q =
            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID AND v.status = 0");
        q.setParameter("regisID", regisID);
        List<VehicleOffenceEO> vehOffEO = q.getResultList();
        if (!vehOffEO.isEmpty()) {
            return "Clear the Vehicle Offence Before Transfer";
        }
        em.merge(regisEO);
        return "Succesfully updated";
    }

    public Boolean removeRegistrationEO(String regisID) {
        RegistrationEO managed = em.find(RegistrationEO.class, regisID);
        if (managed != null) {
            em.remove(managed);
            return true;
        }
        return false;
    }

    public RegistrationEO findRegistrationByID(String regisID) {
        return em.find(RegistrationEO.class, regisID);
    }

    public RegistrationEO findRegistrationByVehID(Long vehID) {
        Query q =
            em.createQuery("SELECT r FROM RegistrationEO r WHERE r.vehicleEO.vehicleId = :vehID");
        q.setParameter("vehID", vehID);
        List<RegistrationEO> results = q.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    /** <code>select o from RegistrationEO o</code> */
    public List<RegistrationEO> getRegistrationEOFindAll() {
        return em.createNamedQuery("RegistrationEO.findAll").getResultList();
    }

    // --- Vehicle Methods ---

    public String persistVehicleEO(VehicleEO vehicleEO) {
        if (vehicleEO.getVehicleId() == null ||
            vehicleEO.getVehicleBrand() == null ||
            vehicleEO.getVehicleType() == null ||
            vehicleEO.getManufactureDate() == null) {
            return "Error: Incomplete fields";
        }
        if (em.find(VehicleEO.class, vehicleEO.getVehicleId()) != null) {
            return "Vehicle ID already exists";
        }
        em.persist(vehicleEO);
        return "Succesfully inserted";
    }

    public String mergeVehicleEO(VehicleEO vehicleEO) {
        if (vehicleEO.getVehicleId() == null) {
            return "Error: Incomplete fields";
        }
        VehicleEO managed = em.find(VehicleEO.class, vehicleEO.getVehicleId());
        if (managed == null)
            return "Failed to update";
        em.merge(vehicleEO);
        return "Succesfully updated";
    }

    public Boolean removeVehicleEO(Long vehicleID) {
        VehicleEO managed =
            em.find(VehicleEO.class, vehicleID == null ? null : vehicleID.longValue());
        if (managed != null) {
            em.remove(managed);
            return true;
        }
        return false;
    }

    public VehicleEO findVehicleByID(Long vehicleID) {
        return em.find(VehicleEO.class,
                       vehicleID == null ? null : vehicleID.longValue());
    }

    /** <code>select o from VehicleEO o</code> */
    public List<VehicleEO> getVehicleEOFindAll() {
        return em.createNamedQuery("VehicleEO.findAll").getResultList();
    }

    public List<VehicleEO> findVehicleByType(String vehicleType) {
        Query q =
            em.createQuery("SELECT v FROM VehicleEO v WHERE v.vehicleType = :vehicleType");
        q.setParameter("vehicleType", vehicleType);
        return q.getResultList();
    }

    // --- VehicleOffence Methods ---

    public String mergeVehicleOffenceEO(VehicleOffenceEO vehOffEO) {
        if (vehOffEO.getVehicleOffenceId() == null ||
            vehOffEO.getOffenceTypesEO().getOffenceId() == null ||
            vehOffEO.getLocation() == null ||
            vehOffEO.getRegistrationEO().getRegistrationId() == null ||
            vehOffEO.getProof2() == null) {
            return "Error: Incomplete fields";
        }
        VehicleOffenceEO managed =
            em.find(VehicleOffenceEO.class, vehOffEO.getVehicleOffenceId());
        if (managed == null)
            return "Failed to update";
        em.merge(vehOffEO);
        return "Succesfully updated";
    }

    public Boolean removeVehicleOffenceEO(VehicleOffenceEO vehOffEO) {
        if (vehOffEO.getVehicleOffenceId() == null ||
            vehOffEO.getRegistrationEO().getRegistrationId() == null) {
            return false;
        }
        VehicleOffenceEO managed =
            em.find(VehicleOffenceEO.class, vehOffEO.getVehicleOffenceId());
        if (managed == null)
            return false;
        managed.setStatus(1L);
        managed.setProof1(null);
        managed.setProof2(null);
        em.merge(managed);
        return true;
    }

    public VehicleOffenceEO findVehicleOffenceByID(Long vehicleOffenceID) {
        return em.find(VehicleOffenceEO.class,
                       vehicleOffenceID == null ? null :
                       vehicleOffenceID.longValue());
    }

    /** <code>select o from VehicleOffenceEO o</code> */
    public List<VehicleOffenceEO> getVehicleOffenceEOFindAll() {
        return em.createNamedQuery("VehicleOffenceEO.findAll").getResultList();
    }

    public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID) {
        Query q =
            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID");
        q.setParameter("regisID", regisID);
        return q.getResultList();
    }

    public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID,
                                                             Long status) {
        Query q =
            em.createQuery("SELECT v FROM VehicleOffenceEO v WHERE v.registrationEO.registrationId = :regisID AND v.status = :status");
        q.setParameter("regisID", registrationID);
        q.setParameter("status",
                       status == null ? 0L : status); // Ensure type matches DB (Long)
        return q.getResultList();
    }


    // --- Admin login/logout ---

    public Boolean login(AdminEO adminEO) {
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


    public OffenceTypesEO mergeOffenceTypesEO(OffenceTypesEO offenceTypesEO) {
        return null;
    }

    public void removeRegistrationEO(RegistrationEO registrationEO) {
    }

    public void removeOffenceTypesEO(OffenceTypesEO offenceTypesEO) {
    }

    public void removeVehicleEO(VehicleEO vehicleEO) {
    }
}

