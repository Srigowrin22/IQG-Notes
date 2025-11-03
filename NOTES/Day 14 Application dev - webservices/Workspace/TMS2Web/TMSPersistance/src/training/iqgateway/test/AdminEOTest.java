//package training.iqgateway.test;
//
//import java.sql.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.AdminDAO;
//import training.iqgateway.dao.impl.AdminDAOImpl;
//import training.iqgateway.entities.AdminEO;
//import training.iqgateway.entities.RoleEO;
//
//public class AdminEOTest {
//
//	private AdminDAO adminDAORef = new AdminDAOImpl();
//
//	@Test
//	public void testInsertAdmin() {
//		RoleEO roleEO = new RoleEO(2, null);
//		Date date = java.sql.Date.valueOf("2019-03-02");
//		AdminEO adminEO = new AdminEO(roleEO, null, "Laura", "F", "8764 7686 3423", 9876545672l, "adm123", date, 0);
//		Integer returnedPK = adminDAORef.insertAdmin(adminEO);
//		Assert.assertEquals(new Integer(38), returnedPK);
//	}
//
//	@Test
//	public void testUpdateAdmin() {
//		RoleEO roleEO = new RoleEO(2, null);
//		Date date = java.sql.Date.valueOf("2019-09-02");
//		AdminEO adminEO = new AdminEO(roleEO, "PLC05", "King", "M", "8764 7686 3423", 8796966684l, "adm123", date, 0);
//		Integer result = adminDAORef.updateAdmin(adminEO);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testauthorizeAdmin() {
//		Integer result = adminDAORef.authorizeAdmin("PLC05", "adm123");
//		System.out.println(result);
//	}
//
//	@Test
//	public void deleteAuthorizeAdmin() {
//		Integer result = adminDAORef.deleteAdmin("PLC05");
//		System.out.println(result);
//	}
//
//	@Test
//	public void testFindAdminByDesigID() {
//		AdminEO adminEO = adminDAORef.findAdminByDesigID("PLC04");
//		System.out.println(adminEO);
//	}
//
//	@Test
//	public void testFindAdminByAadhar() {
//		AdminEO adminEO = adminDAORef.findAdminByAadhar("9887 6577 8784");
//		System.out.println(adminEO);
//	}
//
//	@Test
//	public void testFindAllAdmin() {
//		List<AdminEO> adminEOList = adminDAORef.findAllAdmins();
//		Assert.assertNotNull(adminEOList);
//		for (Iterator iterator = adminEOList.iterator(); iterator.hasNext();) {
//			AdminEO adminEO = (AdminEO) iterator.next();
//			System.out.println(adminEO);
//		}
//	}
//
//	@Test
//	public void testFindAdminByRoleID() {
//		List<AdminEO> adminEOList = adminDAORef.findAdminByRoleID(1);
//		Assert.assertNotNull(adminEOList);
//		for (Iterator iterator = adminEOList.iterator(); iterator.hasNext();) {
//			AdminEO adminEO = (AdminEO) iterator.next();
//			System.out.println(adminEO);
//		}
//	}
//
//	@Test
//	public void testFindAdminByName() {
//		List<AdminEO> adminEOList = adminDAORef.findAdminByName("Sa%");
//		Assert.assertNotNull(adminEOList);
//		for (Iterator iterator = adminEOList.iterator(); iterator.hasNext();) {
//			AdminEO adminEO = (AdminEO) iterator.next();
//			System.out.println(adminEO);
//		}
//	}
//
//}
