package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import training.iqgateway.dao.AdminDAO;
import training.iqgateway.entities.AdminEO;
import training.iqgateway.utils.HibernateUtils;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public Integer insertAdmin(AdminEO adminEO) {
		String returnedPK = null;
		Session sessionRef = null;
		try {
			sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();

			Integer roleID = adminEO.getRole_id().getRole_id();

			switch (roleID) {
			case 1:
				adminEO.setDesignationID(findPrefixCount(sessionRef, "RTO"));
				break;
			case 2:
				adminEO.setDesignationID(findPrefixCount(sessionRef, "PLC"));
				break;
			case 3:
				adminEO.setDesignationID(findPrefixCount(sessionRef, "CLK"));
				break;
			case 4:
				adminEO.setDesignationID(findPrefixCount(sessionRef, "ADM"));
				break;
			default:
				throw new IllegalArgumentException("Unknown roleID: " + roleID);
			}
			
			String SelectMaxIdFromAdminHQL = "SELECT MAX(id) FROM AdminEO";
			Query queryRef = sessionRef.createQuery(SelectMaxIdFromAdminHQL);

			Number maxIdResult = (Number) queryRef.uniqueResult();
			int maxID = (maxIdResult != null) ? maxIdResult.intValue() : 0;

			adminEO.setId(maxID + 1);

			returnedPK = (String) sessionRef.save(adminEO);
			System.out.println(returnedPK);
			sessionRef.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionRef != null) {
				sessionRef.close();
			}
		}
		return 0;
	}

	private String findPrefixCount(Session sessionRef, String prefix) {

		String SelectMaxIDCountHQL = "SELECT MAX(a.designation_id) FROM AdminEO a WHERE a.designation_id LIKE :prefixParam";
		Query query = sessionRef.createQuery(SelectMaxIDCountHQL);
		query.setParameter("prefixParam", prefix + "%");
		String maxDesig = (String) query.uniqueResult();

		String designationID = null;
		int nextNum = 1;
		if (maxDesig != null) {
			String numStr = maxDesig.substring(prefix.length());
			try {
				nextNum = Integer.parseInt(numStr) + 1;
			} catch (NumberFormatException e) {
				nextNum = 1;
			}
		}
		designationID = prefix + String.format("%02d", nextNum);
		return designationID;
	}

	@Override
	public Integer updateAdmin(AdminEO adminEO) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			sessionRef.update(adminEO);
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public Integer authorizeAdmin(String designation_id, String password) {
		Session sessionRef = null;
		try {
			sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			AdminEO adminEO = (AdminEO) sessionRef.get(AdminEO.class, designation_id);

			if (adminEO == null) {
				sessionRef.getTransaction().rollback();
				return 0;
			}

			if (adminEO.getPassword().equals(password) && adminEO.getSignup() != 1) {
				adminEO.setSignup(1);
				sessionRef.update(adminEO);
				sessionRef.getTransaction().commit();
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sessionRef != null) {
				sessionRef.close();
			}
		}
		return 0;
	}

	@Override
	public Integer deleteAdmin(String designationID) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			AdminEO adminEO = sessionRef.get(AdminEO.class, designationID);
			if (adminEO != null) {
				sessionRef.delete(adminEO);
			}
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public AdminEO findAdminByDesigID(String designationID) {

		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		AdminEO adminEO = sessionRef.get(AdminEO.class, designationID);
		sessionRef.close();
		return adminEO;
	}

	@Override
	public AdminEO findAdminByAadhar(String aadhar) {

		String SelectAdminByAadharHQL = "FROM AdminEO WHERE aadhar = :aadhar";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAdminByAadharHQL);
		queryRef.setParameter("aadhar", aadhar);
		AdminEO adminEO = (AdminEO) queryRef.uniqueResult();
		sessionRef.close();
		return adminEO;
	}

	@Override
	public List<AdminEO> findAllAdmins() {

		String SelectAllAdminsHQL = "FROM AdminEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectAllAdminsHQL);
		List<AdminEO> adminEOList = queryRef.list();
		sessionRef.close();
		return adminEOList;
	}

	@Override
	public List<AdminEO> findAdminByRoleID(Integer roleID) {

		String SelectFindAdminByRoleIDHQL = "FROM AdminEO AS a WHERE a.role_id.role_id = :role_id";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectFindAdminByRoleIDHQL);
		queryRef.setParameter("role_id", roleID);
		List<AdminEO> adminEOList = queryRef.list();
		sessionRef.close();
		return adminEOList;
	}

	@Override
	public List<AdminEO> findAdminByName(String name) {

		String SelectFindAdminByNameHQL = "SELECT a FROM AdminEO AS a WHERE a.name LIKE :name";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectFindAdminByNameHQL);
		queryRef.setParameter("name", name);
		List<AdminEO> adminEOList = queryRef.list();
		sessionRef.close();
		return adminEOList;
	}

}
