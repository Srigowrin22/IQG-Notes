//package training.iqgateway.test;
//
//import java.sql.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import training.iqgateway.dao.VehicleDAO;
//import training.iqgateway.dao.impl.VehicleDAOImpl;
//import training.iqgateway.entities.VehicleEO;
//
//public class VehicleEOTest {
//
//	private VehicleDAO vehDAORef = new VehicleDAOImpl();
//
//	@Test
//	public void testInsertVehicle() {
//		Date date = java.sql.Date.valueOf("2018-09-02");
//		VehicleEO vehicleEO = new VehicleEO(null, "Toyota", "Fortune", "Car", "Diesel", 2, "Pink", date);
//		Integer returnedPK = vehDAORef.insertVehicle(vehicleEO);
//		System.out.println(returnedPK);
//		Assert.assertEquals(new Integer(20), returnedPK);
//	}
//
//	@Test
//	public void testUpdateVehicle() {
//		Date date = java.sql.Date.valueOf("2018-09-02");
//		VehicleEO vehicleEO = new VehicleEO(6, "Toyota", "Fortune", "Car", "Diesel", 2, "Pink", date);
//		Integer result = vehDAORef.updateVehicle(vehicleEO);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testDeleteVehicle() {
//		Integer result = vehDAORef.deleteVehicle(20);
//		System.out.println(result);
//	}
//
//	@Test
//	public void testFindVehicleByID() {
//		Integer vehicleID = 1;
//		VehicleEO vehicleEO = vehDAORef.findVehicleByID(vehicleID);
//		System.out.println(vehicleEO);
//	}
//
//	@Test
//	public void testFindAllVehicle() {
//		List<VehicleEO> vehicleEOList = vehDAORef.findAllVehicles();
//		Assert.assertNotNull(vehicleEOList);
//		for (Iterator iterator = vehicleEOList.iterator(); iterator.hasNext();) {
//			VehicleEO vehicleEO = (VehicleEO) iterator.next();
//			System.out.println(vehicleEO);
//		}
//	}
//
//	@Test
//	public void testFindVehicleByType() {
//		String vehicleType = "Ca%";
//		List<VehicleEO> vehicleEOList = vehDAORef.findVehicleByType(vehicleType);
//		Assert.assertNotNull(vehicleEOList);
//		for (Iterator iterator = vehicleEOList.iterator(); iterator.hasNext();) {
//			VehicleEO vehicleEO = (VehicleEO) iterator.next();
//			System.out.println(vehicleEO);
//		}
//	}
//
//}
