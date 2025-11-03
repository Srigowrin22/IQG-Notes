//package training.iqgateway.test;
//
//import java.sql.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.RegistrationDAO;
//import training.iqgateway.dao.impl.RegistrationDAOImpl;
//import training.iqgateway.entities.OwnerEO;
//import training.iqgateway.entities.RegistrationEO;
//import training.iqgateway.entities.VehicleEO;
//
//public class RegistrationEOTest {
//
//	private RegistrationDAO regisDAORef = new RegistrationDAOImpl();
//
//	@Test
//	public void testInsertRegistration() {
//		VehicleEO vehicleEO = new VehicleEO();
//		vehicleEO.setVehicle_id(14);
//		OwnerEO ownerEO = new OwnerEO();
//		ownerEO.setOwner_aadhar("9463 2345 1233");
//		Date date = java.sql.Date.valueOf("2018-06-23");
//		RegistrationEO regisEO = new RegistrationEO("KA07AP7893", "Karnataka", date, "RTO02", vehicleEO, ownerEO);
//		regisDAORef.insertRegistration(regisEO);
//	}
//
//	@Test
//	public void testUpdateRegistration() {
//		VehicleEO vehicleEO = new VehicleEO();
//		vehicleEO.setVehicle_id(14);
//		OwnerEO ownerEO = new OwnerEO();
//		ownerEO.setOwner_aadhar("9463 2345 1233");
//		Date date = java.sql.Date.valueOf("2018-06-23");
//		RegistrationEO regisEO = new RegistrationEO("KA07AP7893", "Nagarbhavi, Banagalore", date, "RTO03", vehicleEO,
//				ownerEO);
//		regisDAORef.updateRegistration(regisEO);
//	}
//
//	@Test
//	public void deleteRegistration() {
//		String regisID = "KA07AP7893";
//		Integer result = regisDAORef.deleteRegistration(regisID);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testFindRegistrationById() {
//		String regisID = "KA02KA0818";
//		RegistrationEO regisEO = regisDAORef.findRegistrationByID(regisID);
//		System.out.println(regisEO);
//	}
//
//	@Test
//	public void testFindRegistrationByAadhar() {
//		String aadhar = "8363 2334 4322";
//		List<RegistrationEO> regisEOList = regisDAORef.findRegistrationByAadhar(aadhar);
//		Assert.assertNotNull(regisEOList);
//		for (Iterator iterator = regisEOList.iterator(); iterator.hasNext();) {
//			RegistrationEO regisEO = (RegistrationEO) iterator.next();
//			System.out.println(regisEO);
//		}
//	}
//
//	@Test
//	public void testFindAllRegistration() {
//		List<RegistrationEO> regisEOList = regisDAORef.findAllRegistrations();
//		Assert.assertNotNull(regisEOList);
//		for (Iterator iterator = regisEOList.iterator(); iterator.hasNext();) {
//			RegistrationEO regisEO = (RegistrationEO) iterator.next();
//			System.out.println(regisEO);
//		}
//	}
//}
