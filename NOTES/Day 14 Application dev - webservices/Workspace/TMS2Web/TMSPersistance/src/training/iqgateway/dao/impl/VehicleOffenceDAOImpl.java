package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import training.iqgateway.dao.VehicleOffenceDAO;
import training.iqgateway.entities.VehicleOffenceEO;
import training.iqgateway.utils.HibernateUtils;
import training.iqgateway.utils.Validation;

public class VehicleOffenceDAOImpl implements VehicleOffenceDAO {

	private static Validation validate = new Validation();

	@Override
	public Integer insertVehicleOffence(VehicleOffenceEO vehOffEO) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		sessionRef.beginTransaction();

		String time = vehOffEO.getTime();
		vehOffEO.setTime(validate.validateTime(time));

		Integer returnedPK = (Integer) sessionRef.save(vehOffEO);
		sessionRef.getTransaction().commit();
		sessionRef.close();
		return returnedPK;
	}

	@Override
	public Integer updateVehicleOffence(VehicleOffenceEO vehOffEO) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			String time = vehOffEO.getTime();
			vehOffEO.setTime(validate.validateTime(time));
			sessionRef.update(vehOffEO);
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer deleteVehicleOffence(Integer vehicleOffenceID) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			VehicleOffenceEO vehicleOffEO = (VehicleOffenceEO) sessionRef.get(VehicleOffenceEO.class, vehicleOffenceID);
			if (vehicleOffEO != null) {
				sessionRef.delete(vehicleOffEO);
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
	public Integer clearVehicleOffence(Integer vehicleOffenceID) {

		try {
			String SelectAllVehicleOffByIDHQL = "FROM VehicleOffenceEO";
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			Query queryRef = sessionRef.createQuery(SelectAllVehicleOffByIDHQL);
			VehicleOffenceEO vehicleOffEO = (VehicleOffenceEO) sessionRef.get(VehicleOffenceEO.class, vehicleOffenceID);
			if (vehicleOffEO != null) {
				vehicleOffEO.setStatus(1);
				vehicleOffEO.setProof1(null);
				vehicleOffEO.setProof2(null);
				sessionRef.update(vehicleOffEO);
				sessionRef.close();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public VehicleOffenceEO findVehicleOffenceByID(Integer vehicleOffenceID) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		VehicleOffenceEO vehicleOffEO = (VehicleOffenceEO) sessionRef.get(VehicleOffenceEO.class, vehicleOffenceID);
		sessionRef.close();
		return vehicleOffEO;
	}

	@Override
	public List<VehicleOffenceEO> findAllVehicleOffence() {

		String SelectAllVehicleOffenceHQL = "FROM VehicleOffenceEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAllVehicleOffenceHQL);
		List<VehicleOffenceEO> vehcileOffEOList = queryRef.list();
		sessionRef.close();
		return vehcileOffEOList;
	}

	@Override
	public List<VehicleOffenceEO> findVehicleOffenceByRegisID(String regisID) {

		String SelectAllVehicleOffenceHQL = "FROM VehicleOffenceEO WHERE registration_id.registration_id = :registration_id";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAllVehicleOffenceHQL);
		queryRef.setParameter("registration_id", regisID);
		List<VehicleOffenceEO> vehcileOffEOList = queryRef.list();
		sessionRef.close();
		return vehcileOffEOList;
	}

	@Override
	public List<VehicleOffenceEO> findVehicleOffenceByStatus(String registrationID, Integer status) {

		String SelectAllVehicleOffenceHQL = "FROM VehicleOffenceEO WHERE registration_id.registration_id = :registration_id AND status = :status";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAllVehicleOffenceHQL);
		queryRef.setParameter("registration_id", registrationID);
		queryRef.setParameter("status", status);
		List<VehicleOffenceEO> vehcileOffEOList = queryRef.list();
		sessionRef.close();
		return vehcileOffEOList;
	}

}
