package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import training.iqgateway.dao.OffenceTypesDAO;
import training.iqgateway.entities.OffenceTypesEO;
import training.iqgateway.utils.HibernateUtils;

public class OffenceTypesDAOImpl implements OffenceTypesDAO {

	@Override
	public Integer insertOffenceTypes(OffenceTypesEO offTypesEO) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		sessionRef.beginTransaction();
		Integer returedPK = (Integer) sessionRef.save(offTypesEO);
		sessionRef.getTransaction().commit();
		return returedPK;
	}

	@Override
	public void updateOffenceTypes(OffenceTypesEO offTypesEO) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		sessionRef.beginTransaction();
		sessionRef.update(offTypesEO);
		sessionRef.getTransaction().commit();

	}

	@Override
	public void deleteOffenceTypes(Integer offenceID) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		sessionRef.beginTransaction();
		OffenceTypesEO offTypesEO = (OffenceTypesEO) sessionRef.get(OffenceTypesEO.class, offenceID);
		if (offTypesEO != null) {
			sessionRef.delete(offTypesEO);
		}
		sessionRef.getTransaction().commit();

	}

	@Override
	public OffenceTypesEO findByOffenceID(Integer offenceID) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		OffenceTypesEO offTypesEO = (OffenceTypesEO) sessionRef.get(OffenceTypesEO.class, offenceID);
		return offTypesEO;
	}

	@Override
	public List<OffenceTypesEO> findAllOffenceTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OffenceTypesEO> findOffenceTypesByVehicleType(String vehicleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OffenceTypesEO> findOffenceTypesByPenalty(Integer penaltyAmt) {
		// TODO Auto-generated method stub
		return null;
	}

}
