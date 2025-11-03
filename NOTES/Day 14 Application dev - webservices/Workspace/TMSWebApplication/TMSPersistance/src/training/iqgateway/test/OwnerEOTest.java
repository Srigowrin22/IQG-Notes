//package training.iqgateway.test;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.OwnerDAO;
//import training.iqgateway.dao.impl.OwnerDAOImpl;
//import training.iqgateway.entities.OwnerEO;
//
//public class OwnerEOTest {
//
//	private OwnerDAO ownerDAORef = new OwnerDAOImpl();
//
//	@Test
//	public void testInsertOwner() {
//		OwnerEO ownerEO = new OwnerEO(null, "Buzz", null, "9872 1234 4343", "DF878FH786", 9874128993l, "Nagarbhavi",
//				"buzz226");
//		ownerDAORef.insertOwner(ownerEO);
//	}
//
//	@Test
//	public void testUpdateAdmin() {
//		OwnerEO ownerEO = new OwnerEO(null, "Imran", null, "9872 1234 4343", "DF878FH786", 9877688893l,
//				"Royapuram, Chennai", "buzz226");
//		Integer result = ownerDAORef.updateOwner(ownerEO);
//		System.out.println(result);
//	}
//
//	@Test
//	public void deleteOwner() {
//		Integer result = ownerDAORef.deleteOwner("9872 1234 4343", "buzz226");
//		System.out.println(result);
//	}
//
//	@Test
//	public void testFindOwnerByAadhar() {
//		OwnerEO ownerEO = ownerDAORef.findOwnerByAadhar("8763 1287 7634");
//		System.out.println(ownerEO);
//	}
//
//	@Test
//	public void testFindAllOwner() {
//		List<OwnerEO> ownerEOList = ownerDAORef.findAllOwners();
//		Assert.assertNotNull(ownerEOList);
//		for (Iterator iterator = ownerEOList.iterator(); iterator.hasNext();) {
//			OwnerEO ownerEO = (OwnerEO) iterator.next();
//			System.out.println(ownerEO);
//		}
//	}
//
//	@Test
//	public void testFindOwnerByName() {
//		List<OwnerEO> ownerEOList = ownerDAORef.findOwnerByName("AVI");
//		Assert.assertNotNull(ownerEOList);
//		for (Iterator iterator = ownerEOList.iterator(); iterator.hasNext();) {
//			OwnerEO ownerEO = (OwnerEO) iterator.next();
//			System.out.println(ownerEO);
//		}
//	}
//
//}
