//package training.iqgateway.test;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.VehicleOffenceDAO;
//import training.iqgateway.dao.impl.VehicleOffenceDAOImpl;
//import training.iqgateway.entities.OffenceTypesEO;
//import training.iqgateway.entities.RegistrationEO;
//import training.iqgateway.entities.RoleEO;
//import training.iqgateway.entities.VehicleOffenceEO;
//
//public class VehicleOffenceEOTest {
//
//	private VehicleOffenceDAO vehOffDAORef = new VehicleOffenceDAOImpl();
//
//	@Test
//	public void testInsertVehicleOffence() {
//		Date date = java.sql.Date.valueOf("2025-02-01");
//		RegistrationEO registrationEO = new RegistrationEO();
//		registrationEO.setRegistration_id("KA02KA0818");
//		OffenceTypesEO offTypesEO = new OffenceTypesEO();
//		offTypesEO.setOffence_id(2);
//		byte[] bytes = new byte[10];
//
//		VehicleOffenceEO vehicleOffenceEO = new VehicleOffenceEO(0, offTypesEO, registrationEO, "Dehli", date,
//				"11:30:45", 0, "RTO01", bytes, bytes);
//		vehOffDAORef.insertVehicleOffence(vehicleOffenceEO);
//
//	}
//
//	@Test
//	public void testUpdateVehicleOffence() {
//		Date date = java.sql.Date.valueOf("2025-02-01");
//		RegistrationEO registrationEO = new RegistrationEO();
//		registrationEO.setRegistration_id("KA02KA0818");
//		OffenceTypesEO offTypesEO = new OffenceTypesEO();
//		offTypesEO.setOffence_id(2);
//		byte[] bytes = new byte[10];
//
//		VehicleOffenceEO vehicleOffenceEO = new VehicleOffenceEO(27, offTypesEO, registrationEO, "Delhi, India", date,
//				"14:30:45", 0, "RTO01", bytes, bytes);
//		vehOffDAORef.updateVehicleOffence(vehicleOffenceEO);
//	}
//
//	@Test
//	public void testDeleteVehicleOffence() {
//		Integer vehicleOffenceID = 26;
//		Integer result = vehOffDAORef.deleteVehicleOffence(vehicleOffenceID);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testClearVehicleOffence() {
//		Integer vehicleOffenceID = 19;
//		Integer result = vehOffDAORef.clearVehicleOffence(vehicleOffenceID);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testFindVehicleOffenceeByID() {
//		Integer vehicleOffenceID = 19;
//		VehicleOffenceEO vehicleOffenceEO = vehOffDAORef.findVehicleOffenceByID(vehicleOffenceID);
//		System.out.println(vehicleOffenceEO);
//	}
//
//	@Test
//	public void testFindAllVehicleOffence() {
//		List<VehicleOffenceEO> vehicleOffenceEOList = vehOffDAORef.findAllVehicleOffence();
//		Assert.assertNotNull(vehicleOffenceEOList);
//		for (Iterator iterator = vehicleOffenceEOList.iterator(); iterator.hasNext();) {
//			VehicleOffenceEO vehicleOffenceEO = (VehicleOffenceEO) iterator.next();
//			System.out.println(vehicleOffenceEO);
//		}
//	}
//
//	@Test
//	public void testFindVehicleOffenceByRegisID() {
//		String registrationID = "KA02KA0818";
//		List<VehicleOffenceEO> vehicleOffenceEOList = vehOffDAORef.findVehicleOffenceByRegisID(registrationID);
//		Assert.assertNotNull(vehicleOffenceEOList);
//		for (Iterator iterator = vehicleOffenceEOList.iterator(); iterator.hasNext();) {
//			VehicleOffenceEO vehicleOffenceEO = (VehicleOffenceEO) iterator.next();
//			System.out.println(vehicleOffenceEO);
//		}
//	}
//
//	@Test
//	public void testFindVehicleOffenceByStatus() {
//		String registrationID = "KA02KA0818";
//		Integer status = 1;
//		List<VehicleOffenceEO> vehicleOffenceEOList = vehOffDAORef.findVehicleOffenceByStatus(registrationID, status);
//		Assert.assertNotNull(vehicleOffenceEOList);
//		for (Iterator iterator = vehicleOffenceEOList.iterator(); iterator.hasNext();) {
//			VehicleOffenceEO vehicleOffenceEO = (VehicleOffenceEO) iterator.next();
//			System.out.println(vehicleOffenceEO);
//		}
//	}
//
//}
