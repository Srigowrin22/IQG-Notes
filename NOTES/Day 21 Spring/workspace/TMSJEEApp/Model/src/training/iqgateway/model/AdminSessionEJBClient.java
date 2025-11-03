package training.iqgateway.model;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import training.iqgateway.services.AdminSessionEJB;

public class AdminSessionEJBClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            AdminSessionEJB adminSessionEJB =
                (AdminSessionEJB)context.lookup("TMSJEEApp-Model-AdminSessionEJB#training.iqgateway.services.AdminSessionEJB");
            for (AdminEO admineo :
                 (List<AdminEO>)adminSessionEJB.getAdminEOFindAll()) {
                printAdminEO(admineo);
            }
            //            for (AdminEO admineo : (List<AdminEO>)adminSessionEJB.findAdminByRoleID(1L)) {
            //                printAdminEO(admineo);
            //            }
            //            for (AdminEO admineo : (List<AdminEO>)adminSessionEJB.findAdminByName("Avi")) {
            //                printAdminEO(admineo);
            //            }
            //            for (RoleEO roleeo : (List<RoleEO>)adminSessionEJB.getRoleEOFindAll()) {
            //                printRoleEO(roleeo);
            //            }
            //            for (RoleEO roleeo : (List<RoleEO>)adminSessionEJB.findRoleByRoleName("RTO")) {
            //                printRoleEO(roleeo);
            //            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printAdminEO(AdminEO admineo) {
        System.out.println("aadhar = " + admineo.getAadhar());
        System.out.println("designationId = " + admineo.getDesignationId());
        System.out.println("gender = " + admineo.getGender());
        System.out.println("hireDate = " + admineo.getHireDate());
        System.out.println("id = " + admineo.getId());
        System.out.println("name = " + admineo.getName());
        System.out.println("password = " + admineo.getPassword());
        System.out.println("phone = " + admineo.getPhone());
        System.out.println("signup = " + admineo.getSignup());
        System.out.println("roleEO = " + admineo.getRoleEO().getRoleId());
    }

    private static void printRoleEO(RoleEO roleeo) {
        System.out.println("roleId = " + roleeo.getRoleId());
        System.out.println("roleName = " + roleeo.getRoleName());
        System.out.println("adminEOList = " + roleeo.getAdminEOList());
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7101");
        return new InitialContext(env);
    }
}
