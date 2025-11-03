package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import training.iqgateway.dao.VehicleDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.entities.VehicleEO;
import training.iqgateway.utils.HibernateUtils;

public class VehicleDAOImpl implements VehicleDAO {

	@Override
	public Integer insertVehicle(VehicleEO vehicleEO) {

		String SelectMaxIdFromVehcileHQL = "SELECT MAX(vehicle_id) FROM VehicleEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		sessionRef.beginTransaction();
		Query queryRef = sessionRef.createQuery(SelectMaxIdFromVehcileHQL);

		Number maxIdResult = (Number) queryRef.uniqueResult();
		int maxID = (maxIdResult != null) ? maxIdResult.intValue() : 0;

		vehicleEO.setVehicle_id(maxID + 1);
		Integer returnedPK = (Integer) sessionRef.save(vehicleEO);
		sessionRef.getTransaction().commit();
		sessionRef.close();
		return returnedPK;
	}

	@Override
	public Integer updateVehicle(VehicleEO vehicleEO) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			sessionRef.update(vehicleEO);
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer deleteVehicle(Integer vehicleID) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			VehicleEO vehicleEO = (VehicleEO) sessionRef.get(VehicleEO.class, vehicleID);
			if (vehicleEO != null) {
				sessionRef.delete(vehicleEO);
				sessionRef.getTransaction().commit();
				sessionRef.close();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public VehicleEO findVehicleByID(Integer vehicleID) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		VehicleEO vehicleEO = (VehicleEO) sessionRef.get(VehicleEO.class, vehicleID);
		sessionRef.close();
		return vehicleEO;
	}

	@Override
	public List<VehicleEO> findAllVehicles() {

		String SelectAllVehicleHQL = "FROM VehicleEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAllVehicleHQL);
		List<VehicleEO> vehcileEOList = queryRef.list();
		sessionRef.close();
		return vehcileEOList;
	}

	@Override
	public List<VehicleEO> findVehicleByType(String vehicleType) {

		String SelectVehicleByTypeHQL = "FROM VehicleEO WHERE vehicle_type LIKE :vehicle_type";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectVehicleByTypeHQL);
		queryRef.setParameter("vehicle_type", vehicleType);
		List<VehicleEO> vehcileEOList = queryRef.list();
		sessionRef.close();
		return vehcileEOList;
	}

}
