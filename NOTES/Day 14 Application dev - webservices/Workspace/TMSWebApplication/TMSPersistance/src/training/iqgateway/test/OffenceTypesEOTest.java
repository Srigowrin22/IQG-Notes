//package training.iqgateway.test;
//
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.OffenceTypesDAO;
//import training.iqgateway.dao.impl.OffenceTypesDAOImpl;
//import training.iqgateway.entities.OffenceTypesEO;
//
//public class OffenceTypesEOTest {
//
//	private OffenceTypesDAO offenceDAORef = new OffenceTypesDAOImpl();
//
//	@Test
//	public void testInsertOffenceTypes() {
//		OffenceTypesEO offTypesEO = new OffenceTypesEO(null, "Testing", "CAR", 5270);
//		Integer returnedPK = offenceDAORef.insertOffenceTypes(offTypesEO);
//		Assert.assertEquals(new Integer(19), returnedPK);
//	}
//
//	@Test
//	public void testUpdateOffenceTypes() {
//		OffenceTypesEO offTypesEO = new OffenceTypesEO(19, "Testing update", "CAR", 6500);
//		offenceDAORef.updateOffenceTypes(offTypesEO);
//
//	}
//
//	@Test
//	public void testFindByOffenceID() {
//		OffenceTypesEO offTypeEO = offenceDAORef.findByOffenceID(19);
//		Assert.assertNotNull(offTypeEO);
//		System.out.println(offTypeEO);
//
//	}
//
//	@Test
//	public void testDeleteOffenceType() {
//		System.out.println("Deleted: " + offenceDAORef.deleteOffenceTypes(19));
//	}
//
//	@Test
//	public void testFindAllOffences() {
//		List<OffenceTypesEO> offenceTypesList = offenceDAORef.findAllOffenceTypes();
//		Assert.assertNotNull(offenceTypesList);
//		for (Iterator iterator = offenceTypesList.iterator(); iterator.hasNext();) {
//			OffenceTypesEO offenceTypesEO = (OffenceTypesEO) iterator.next();
//			System.out.println(offenceTypesEO);
//		}
//	}
//
//	@Test
//	public void testFindVehOffenceByVehType() {
//		List<OffenceTypesEO> offenceTypesList = offenceDAORef.findOffenceTypesByVehicleType("CAR");
//		Assert.assertNotNull(offenceTypesList);
//		for (Iterator iterator = offenceTypesList.iterator(); iterator.hasNext();) {
//			OffenceTypesEO offenceTypesEO = (OffenceTypesEO) iterator.next();
//			System.out.println(offenceTypesEO);
//
//		}
//	}
//
//	@Test
//	public void testFindVehOffenceByPenalty() {
//		List<OffenceTypesEO> offenceTypesList = offenceDAORef.findOffenceTypesByPenalty(5000);
//		Assert.assertNotNull(offenceTypesList);
//		for (Iterator iterator = offenceTypesList.iterator(); iterator.hasNext();) {
//			OffenceTypesEO offenceTypesEO = (OffenceTypesEO) iterator.next();
//			System.out.println(offenceTypesEO);
//
//		}
//	}
//}
