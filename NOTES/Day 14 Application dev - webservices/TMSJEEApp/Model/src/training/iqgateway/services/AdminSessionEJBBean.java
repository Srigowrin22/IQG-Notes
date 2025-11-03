package training.iqgateway.services;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import training.iqgateway.model.AdminEO;
import training.iqgateway.model.RoleEO;

@Stateless(name = "AdminSessionEJB",
           mappedName = "TMSJEEApp-Model-AdminSessionEJB")
public class AdminSessionEJBBean implements AdminSessionEJB,
                                            AdminSessionEJBLocal {
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public AdminSessionEJBBean() {
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

    public AdminEO persistAdminEO(AdminEO adminEO) {

        if (adminEO.getRoleEO() != null &&
            adminEO.getRoleEO().getRoleId() != null) {
            String prefix;
            Long roleId = adminEO.getRoleEO().getRoleId();
            if (roleId == 1L)
                prefix = "RTO";
            else if (roleId == 2L)
                prefix = "PLC";
            else if (roleId == 3L)
                prefix = "CLK";
            else
                prefix = "ADM";

            String nextDesigId = getNextDesignationID(prefix);
            adminEO.setDesignationId(nextDesigId);
        }

        // Manual ID generation (fetch max existing id)
        Long lastId =
            (Long)em.createQuery("SELECT MAX(a.id) FROM AdminEO a").getSingleResult();
        adminEO.setId(lastId == null ? 1L : lastId + 1);

        em.persist(adminEO);
        return em.find(AdminEO.class, adminEO.getDesignationId());
        //        return adminEO;

    }

    private String getNextDesignationID(String prefix) {
        List ids =
            em.createQuery("SELECT a.designationId FROM AdminEO a WHERE a.designationId LIKE :prefix").setParameter("prefix",
                                                                                                                    prefix +
                                                                                                                    "%").getResultList();
        int maxNum = 0;
        for (Object obj : ids) {
            try {
                String id = (String)obj;
                String numPart = id.substring(prefix.length());
                int num = Integer.parseInt(numPart);
                if (num > maxNum)
                    maxNum = num;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int nextNum = maxNum + 1;
        return prefix + String.format("%02d", nextNum);
    }


    public AdminEO mergeAdminEO(AdminEO adminEO) {
        em.merge(adminEO);
        return em.find(AdminEO.class, adminEO.getDesignationId());
    }

    public Boolean removeAdminEO(AdminEO adminEO) {
        adminEO = em.find(AdminEO.class, adminEO.getDesignationId());
        if (adminEO != null) {
            em.remove(adminEO);
            return true;
        }
        return false;
    }

    public int authorizeAdmin(String designationId, String password,
                              String newPassword) {
        AdminEO adminEO = em.find(AdminEO.class, designationId);
        if (adminEO != null && adminEO.getPassword().equals(password)) {
            int updated =
                em.createQuery("UPDATE AdminEO a SET a.signup = 1, a.password = :newPassword WHERE a.designationId = :designationId").setParameter("designationId",
                                                                                                                                                   designationId).setParameter("newPassword",
                                                                                                                                                                               newPassword).executeUpdate();
            return updated;
        }
        return 0;
    }

    /** <code>select o from AdminEO o</code> */
    public List<AdminEO> getAdminEOFindAll() {
        return em.createNamedQuery("AdminEO.findAll").getResultList();
    }

    public AdminEO findAdminByDesigID(String designationId) {
        return em.find(AdminEO.class, designationId);
    }

    public AdminEO findAdminByAadhar(String aadhar) {
        Query q =
            em.createQuery("SELECT a FROM AdminEO a WHERE a.aadhar = :aadhar");
        List<AdminEO> results =
            q.setParameter("aadhar", aadhar).getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<AdminEO> findAdminByRoleID(Long roleId) {
        System.out.print(roleId);
        Query q =
            em.createQuery("SELECT a FROM AdminEO a WHERE a.roleEO.roleId = :roleId");
        return q.setParameter("roleId", roleId).getResultList();
    }

    public List<AdminEO> findAdminByName(String name) {
        List<AdminEO> result =
            em.createQuery("SELECT a FROM AdminEO a WHERE a.name LIKE :name").setParameter("name",
                                                                                           name).getResultList();
        return result;
    }

    // RoleEO Business methods

    public RoleEO persistRoleEO(RoleEO roleEO) {
        em.persist(roleEO);
        return em.find(RoleEO.class, roleEO.getRoleId());
    }

    public RoleEO mergeRoleEO(RoleEO roleEO) {
        return em.merge(roleEO);
    }

    public Boolean removeRoleEO(RoleEO roleEO) {
        roleEO = em.find(RoleEO.class, roleEO.getRoleId());
        if (roleEO != null) {
            em.remove(roleEO);
            return true;
        }
        return false;
    }

    /** <code>select o from RoleEO o</code> */
    public List<RoleEO> getRoleEOFindAll() {
        return em.createNamedQuery("RoleEO.findAll").getResultList();
    }


    public RoleEO findRoleByRoleID(Long roleId) {
        return em.find(RoleEO.class, roleId);
    }

    public RoleEO findRoleByRoleName(String roleName) {
        try {
            Query q =
                em.createQuery("SELECT r FROM RoleEO r WHERE r.roleName = :roleName");
            q.setParameter("roleName", roleName);
            return (RoleEO)q.getSingleResult();
        } catch (NoResultException e) {
            // No matching role found, return null or handle as needed
            return null;
        }

    }

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
            System.out.println(user.getDesignationId() + " " + user.getName() +
                               " " + user.getPassword());
            System.out.println(adminEO.getDesignationId() + " " +
                               adminEO.getName() + " " +
                               adminEO.getPassword());

            return true;
        }
        return false;
    }

}
