//package training.iqgateway.test;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.RoleDAO;
//import training.iqgateway.dao.impl.RoleDAOImpl;
//import training.iqgateway.entities.RoleEO;
//
//public class RoleEOTest {
//
//	private RoleDAO roleDAORef = new RoleDAOImpl();
//
//	@Test
//	public void testInsertRole() {
//		RoleEO roleEO = new RoleEO(null, "June");
//		roleDAORef.insertRole(roleEO);
//	}
//
//	@Test
//	public void testUpdateRole() {
//		RoleEO roleEO = new RoleEO(5, "July");
//		Integer result = roleDAORef.updateRole(roleEO);
//		System.out.println(result);
//	}
//
//	@Test
//	public void deleteRole() {
//		Integer result = roleDAORef.deleteRole(5);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testFindRoleByID() {
//		Integer roleID = 1;
//		RoleEO roleEO = roleDAORef.findRoleByRoleID(roleID);
//		System.out.println(roleEO);
//	}
//
//	@Test
//	public void testFindAllOwner() {
//		List<RoleEO> roleEOList = roleDAORef.findAllRoles();
//		Assert.assertNotNull(roleEOList);
//		for (Iterator iterator = roleEOList.iterator(); iterator.hasNext();) {
//			RoleEO roleEO = (RoleEO) iterator.next();
//			System.out.println(roleEO);
//		}
//	}
//
//	@Test
//	public void testFindOwnerByName() {
//		String roleName = "R%";
//		List<RoleEO> roleEOList = roleDAORef.findRoleByRoleName(roleName);
//		Assert.assertNotNull(roleEOList);
//		for (Iterator iterator = roleEOList.iterator(); iterator.hasNext();) {
//			RoleEO roleEO = (RoleEO) iterator.next();
//			System.out.println(roleEO);
//		}
//	}
//}
