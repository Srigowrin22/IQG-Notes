package training.iqgateway.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import training.iqgateway.dao.RoleDAO;
import training.iqgateway.entities.RoleEO;
import training.iqgateway.utils.HibernateUtils;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public Integer insertRole(RoleEO roleEO) {

		String SelectMaxIDForRole = "SELECT MAX(role_id) FROM RoleEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		sessionRef.beginTransaction();
		Query queryRef = sessionRef.createQuery(SelectMaxIDForRole);

		Number maxIdResult = (Number) queryRef.list();
		int maxID = (maxIdResult != null) ? maxIdResult.intValue() : 0;

		roleEO.setRole_id(maxID + 1);

		Integer returnedPK = (Integer) sessionRef.save(roleEO);
		sessionRef.getTransaction().commit();
		sessionRef.close();
		return returnedPK;
	}

	@Override
	public Integer countRoles() {

		String SelectCountRolesHQL = "SELECT COUNT(r) FROM RolesEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectCountRolesHQL);
		Integer count = (Integer) queryRef.uniqueResult();
		sessionRef.close();
		return count;
	}

	@Override
	public Integer updateRole(RoleEO roleEO) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			sessionRef.update(roleEO);
			sessionRef.getTransaction().commit();
			sessionRef.close();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Integer deleteRole(Integer roleID) {
		try {
			Session sessionRef = HibernateUtils.getSessionFactory().openSession();
			sessionRef.beginTransaction();
			RoleEO roleEO = (RoleEO) sessionRef.get(RoleEO.class, roleID);
			if (roleEO != null) {
				sessionRef.delete(roleEO);
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
	public RoleEO findRoleByRoleID(Integer roleID) {
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		RoleEO roleEO = (RoleEO) sessionRef.get(RoleEO.class, roleID);
		sessionRef.close();
		return roleEO;
	}

	@Override
	public List<RoleEO> findRoleByRoleName(String roleName) {

		String SelectRoleByRoleNameHQL = "FROM RoleEO r WHERE r.role_name LIKE :role_name";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectRoleByRoleNameHQL);
		queryRef.setParameter("role_name", roleName);
		List<RoleEO> roleEOList = queryRef.list();
		sessionRef.close();
		return roleEOList;
	}

	@Override
	public List<RoleEO> findAllRoles() {

		String SelectFindAllRolesHQL = "FROM RoleEO";
		Session sessionRef = HibernateUtils.getSessionFactory().openSession();
		Query queryRef = sessionRef.createQuery(SelectFindAllRolesHQL);
		List<RoleEO> roleEOList = queryRef.list();
		sessionRef.close();
		return roleEOList;
	}
}
