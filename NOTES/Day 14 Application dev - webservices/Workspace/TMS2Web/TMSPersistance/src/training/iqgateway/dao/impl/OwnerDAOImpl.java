package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import training.iqgateway.dao.OwnerDAO;
import training.iqgateway.entities.OwnerEO;
import training.iqgateway.utils.HibernateUtils;

public class OwnerDAOImpl implements OwnerDAO {

	@Override
	public Integer insertOwner(OwnerEO ownerEO) {

		try {
			String SelectMaxIdFromOwnerHQL = "SELECT MAX(owner_id) FROM OwnerEO";
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();

			Query queryRef = sessionRef.createQuery(SelectMaxIdFromOwnerHQL);
			Number maxIdResult = (Number) queryRef.uniqueResult();
			int maxID = (maxIdResult != null) ? maxIdResult.intValue() : 0;
			ownerEO.setOwner_id(maxID + 1);

			String returnedPK = (String) sessionRef.save(ownerEO);
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer updateOwner(OwnerEO ownerEO) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			sessionRef.update(ownerEO);
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer deleteOwner(String ownerAadhar, String password) {
		try {
			String SelectAadharForDeleteHQL = "FROM OwnerEO o WHERE o.owner_aadhar = :owner_aadhar AND password = :password";
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();

			Query queryRef = sessionRef.createQuery(SelectAadharForDeleteHQL);
			queryRef.setParameter("owner_aadhar", ownerAadhar);
			queryRef.setParameter("password", password);
			OwnerEO ownerEO = (OwnerEO) queryRef.uniqueResult();
			if (ownerEO != null) {
				sessionRef.delete(ownerEO);
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
	public OwnerEO findOwnerByAadhar(String ownerAadhar) {

		String SelectOwnerByAadharHQL = "FROM OwnerEO WHERE owner_aadhar = :owner_aadhar";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectOwnerByAadharHQL);
		queryRef.setParameter("owner_aadhar", ownerAadhar);
		OwnerEO ownerEO = (OwnerEO) queryRef.uniqueResult();
		sessionRef.close();
		return ownerEO;
	}

	@Override
	public List<OwnerEO> findAllOwners() {

		String SelectAllOwnersHQL = "FROM OwnerEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAllOwnersHQL);
		List<OwnerEO> ownerEOList = queryRef.list();
		sessionRef.close();
		return ownerEOList;
	}

	@Override
	public List<OwnerEO> findOwnerByName(String ownerName) {

		String SelectOwnerByNameHQL = "FROM OwnerEO WHERE owner_name = :owner_name";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectOwnerByNameHQL);
		queryRef.setParameter("owner_name", ownerName);
		List<OwnerEO> ownerEOList = queryRef.list();
		sessionRef.close();
		return ownerEOList;
	}

}
